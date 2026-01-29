package com.lld.jobscheduler.service;

import com.lld.jobscheduler.entity.Job;
import com.lld.jobscheduler.enums.ScheduleType;
import com.lld.jobscheduler.executor.JobExecutor;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SchedularServiceImpl implements SchedularService{

    private final JobExecutor jobExecutor;

    public SchedularServiceImpl(JobExecutor jobExecutor) {
        this.jobExecutor = jobExecutor;
    }

    @Override
    public void schedule(Runnable task, long initialDelay, TimeUnit unit) {
        long delayMillis = unit.toMillis(initialDelay);
        long nextExecutionTime = System.currentTimeMillis() + delayMillis;
        Job job = new Job(UUID.randomUUID().toString(), task, ScheduleType.ONE_TIME, nextExecutionTime, 0);
        jobExecutor.submit(job);
    }

    @Override
    public void scheduleAtFixedRate(Runnable task, long initialDelay, long recurringDelay, TimeUnit unit) {
        long delayMillis = unit.toMillis(initialDelay);
        long nextExecutionTime = System.currentTimeMillis() + delayMillis;
        long recurringDelayMillis = unit.toMillis(recurringDelay);
        Job job = new Job(UUID.randomUUID().toString(), task, ScheduleType.FIXED_RATE, nextExecutionTime, recurringDelayMillis);
        jobExecutor.submit(job);
    }
}
