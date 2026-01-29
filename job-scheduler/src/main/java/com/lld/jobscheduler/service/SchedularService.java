package com.lld.jobscheduler.service;

import java.util.concurrent.TimeUnit;

public interface SchedularService {
    public void schedule(Runnable task, long initialDelay, TimeUnit unit);
    public void scheduleAtFixedRate(Runnable task, long initialDelay, long recurringDelay, TimeUnit unit);
}
