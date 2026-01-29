package com.lld.jobscheduler.entity;

import com.lld.jobscheduler.enums.ScheduleType;

public class Job implements Runnable, Comparable<Job>{

    private final String jobId;
    private final Runnable task;
    private final ScheduleType scheduleType;
    private long nextExecutionTime;
    private long recurringDelay;

    public Job(String jobId, Runnable task, ScheduleType scheduleType, long nextExecutionTime, long recurringDelay) {
        this.jobId = jobId;
        this.task = task;
        this.scheduleType = scheduleType;
        this.nextExecutionTime = nextExecutionTime;
        this.recurringDelay = recurringDelay;
    }


    // Getters
    public String getJobId() {
        return jobId;
    }

    public Runnable getTask() {
        return task;
    }

    public ScheduleType getScheduleType() {
        return scheduleType;
    }

    public long getNextExecutionTime() {
        return nextExecutionTime;
    }

    public long getRecurringDelay() {
        return recurringDelay;
    }

    // Setter for nextExecutionTime (needed for rescheduling)
    public void setNextExecutionTime(long nextExecutionTime) {
        this.nextExecutionTime = nextExecutionTime;
    }

    @Override
    public int compareTo(Job o) {
        return Long.compare(this.nextExecutionTime, o.nextExecutionTime);
    }

    @Override
    public void run() {
        task.run();
    }
}
