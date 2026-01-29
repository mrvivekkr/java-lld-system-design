package com.lld.jobscheduler.executor;

import com.lld.jobscheduler.entity.Job;
import com.lld.jobscheduler.enums.ScheduleType;

import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JobExecutor is responsible for:
 * - Managing the job queue
 * - Polling jobs from the queue
 * - Executing jobs at the right time using a fixed thread pool
 * - Rescheduling recurring jobs
 */
public class JobExecutor {
    private final PriorityQueue<Job> jobQueue;
    private final ExecutorService executorService;
    private final Thread pollingThread;
    private volatile boolean shutdown = false;
    private static final int DEFAULT_THREAD_POOL_SIZE = 5;
    private final Object lock = new Object();

    public JobExecutor() {
        this(DEFAULT_THREAD_POOL_SIZE);
    }

    public JobExecutor(int threadPoolSize) {
        this.jobQueue = new PriorityQueue<>();
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
        // Start polling thread that checks queue and submits ready jobs to thread pool
        this.pollingThread = new Thread(this::pollAndExecuteJobs, "job-executor-polling");
        this.pollingThread.setDaemon(true);
        this.pollingThread.start();
    }

    /**
     * Submits a job to the executor's queue.
     * The executor will execute it when the time arrives.
     */
    public void submit(Job job) {
        synchronized (lock) {
            jobQueue.add(job);
            lock.notifyAll();
        }
    }

    /**
     * Polling thread that continuously checks the queue for ready jobs
     * and submits them to the thread pool for execution.
     */
    private void pollAndExecuteJobs() {
        while(!shutdown){
            synchronized (lock){
                while(!jobQueue.isEmpty() && jobQueue.peek().getNextExecutionTime()<=System.currentTimeMillis()){
                    Job nextJob = jobQueue.poll();
                    executeJob(nextJob);
                }

                // wait if there are no jobs to run or queue is empty
                if(jobQueue.isEmpty() || jobQueue.peek().getNextExecutionTime()>System.currentTimeMillis()){
                    try{
                        long waitTime = jobQueue.isEmpty() ? 0 : jobQueue.peek().getNextExecutionTime() - System.currentTimeMillis() ;
                        if(waitTime>0)
                            lock.wait(waitTime);
                    }catch (InterruptedException e){
                        return;
                    }
                }

            }
        }
    }

    /**
     * Submits the job to the executor service so it runs in a pool thread.
     * Rescheduling for recurring jobs happens after the job completes (inside the submitted task).
     */
    private void executeJob(Job job) {
        try {
            executorService.submit(job);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        // Reschedule recurring jobs; must hold lock when touching queue (runs in pool thread)
        if (job.getScheduleType() == ScheduleType.FIXED_RATE && job.getRecurringDelay() > 0) {
            synchronized (lock) {
                if (!shutdown) {
                    job.setNextExecutionTime(System.currentTimeMillis() + job.getRecurringDelay());
                    jobQueue.add(job);
                    lock.notifyAll();
                }
            }
        }
    }

    /**
     * Shuts down the executor and stops accepting new jobs.
     * Wakes the polling thread if it is blocked in wait() so it can exit promptly.
     */
    public void shutdown() {
        synchronized (lock) {
            shutdown = true;
            lock.notifyAll();
        }
        executorService.shutdown();
        try {
            pollingThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
