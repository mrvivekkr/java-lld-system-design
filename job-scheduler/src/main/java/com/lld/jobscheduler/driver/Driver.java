package com.lld.jobscheduler.driver;

import com.lld.jobscheduler.executor.JobExecutor;
import com.lld.jobscheduler.service.SchedularService;
import com.lld.jobscheduler.service.SchedularServiceImpl;

import java.util.concurrent.TimeUnit;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        JobExecutor jobExecutor = new JobExecutor();
        SchedularService schedularService = new SchedularServiceImpl(jobExecutor);
        Runnable task1 = () -> System.out.println("Executing Task1");
        Runnable task2 = () -> System.out.println("Executing Task2");
        Runnable task3 = () -> System.out.println("Executing Task3");

        // Use short delays for testing (change to 60s, 30s etc. for real delays)
        schedularService.schedule(task1, 2, TimeUnit.SECONDS);
        schedularService.scheduleAtFixedRate(task2, 1, 2, TimeUnit.SECONDS);
        schedularService.scheduleAtFixedRate(task3, 5, 1, TimeUnit.SECONDS);

        // Keep main alive so jobs can run; otherwise JVM exits and daemon polling thread is killed
        Thread.sleep(20_000); // 20 seconds
        jobExecutor.shutdown();
    }

}

