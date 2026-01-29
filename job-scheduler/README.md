# Job Scheduler

A custom in-memory job scheduler implementation in Java. It demonstrates how a scheduler can be built without using `ScheduledExecutorService`—using a priority queue, a polling thread, and a fixed thread pool for execution.

## Features

- **One-time jobs** – Schedule a task to run once after a delay.
- **Recurring jobs (fixed rate)** – Schedule a task to run at a fixed interval.
- **Multi-threaded execution** – Jobs run in a fixed-size thread pool (default 5 threads).
- **Thread-safe** – Safe for multiple threads calling the scheduler while jobs run in the pool.
- **Graceful shutdown** – `shutdown()` wakes the polling thread and stops the executor cleanly.

## Architecture

```
SchedulerService (API)
        ↓ depends on
JobExecutor (owns queue + thread pool)
        ↓
  PriorityQueue<Job>  +  polling thread  +  ExecutorService
```

- **SchedulerService** – Public API. Call `schedule()` or `scheduleAtFixedRate()` to submit jobs.
- **JobExecutor** – Holds the job queue, a daemon polling thread, and a fixed thread pool. Polling thread finds ready jobs and hands them to the executor; pool threads run the tasks.
- **Job** – Represents a scheduled unit of work (task, `nextExecutionTime`, schedule type, recurring delay). Ordered by `nextExecutionTime` so the soonest job is at the head of the queue.

Jobs are ordered by `nextExecutionTime`; the polling thread waits (with `Object.wait(timeout)`) until the next job is due or a new job is submitted, then runs ready jobs via the thread pool. Recurring jobs are re-queued with an updated `nextExecutionTime` after submission.

## Prerequisites

- Java 8 or later

## Build and Run

### Using IDE

Open the project in IntelliJ IDEA or Eclipse and run `Driver.main()`.

### Using command line

From the project root (`job-scheduler/`):

```bash
# Compile
javac -d out src/main/java/com/lld/jobscheduler/**/*.java

# Run (adjust path if your package structure differs)
java -cp out com.lld.jobscheduler.driver.Driver
```

Or compile/run from `src/main/java`:

```bash
cd src/main/java
javac com/lld/jobscheduler/driver/Driver.java com/lld/jobscheduler/entity/Job.java com/lld/jobscheduler/enums/ScheduleType.java com/lld/jobscheduler/executor/JobExecutor.java com/lld/jobscheduler/service/SchedularService.java com/lld/jobscheduler/service/SchedularServiceImpl.java
java com.lld.jobscheduler.driver.Driver
```

## Usage Example

```java
JobExecutor jobExecutor = new JobExecutor(5);  // 5 worker threads
SchedularService scheduler = new SchedularServiceImpl(jobExecutor);

// One-time job: run once after 2 seconds
scheduler.schedule(() -> System.out.println("One-time task"), 2, TimeUnit.SECONDS);

// Recurring job: first run after 1 second, then every 2 seconds
scheduler.scheduleAtFixedRate(
    () -> System.out.println("Recurring task"),
    1, 2, TimeUnit.SECONDS
);

// Keep main alive so jobs can run (polling thread is daemon)
Thread.sleep(20_000);
jobExecutor.shutdown();
```
