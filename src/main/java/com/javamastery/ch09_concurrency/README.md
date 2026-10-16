# Chapter 9 — Concurrency Utilities

## Overview

This chapter builds on `ch08_multithreading` and focuses on Java's higher-level concurrency APIs: task execution, executor services, explicit locks, atomic variables, concurrent collections, synchronization utilities, asynchronous composition, fork/join parallelism, and virtual threads.

The goal is to learn not only how to use these APIs, but how to select the right abstraction, reason about its guarantees and limitations, handle failures and cancellation, manage resources, test concurrent behavior, and recognize performance trade-offs.

This chapter uses **Java 21** as the baseline for the `java-mastery` project.

> **Chapter boundary:** Chapter 8 is the main home for thread fundamentals, intrinsic monitor locks, `synchronized`, `wait()`/`notify()`, thread lifecycle, and Java Memory Model foundations. Chapter 9 applies those foundations through higher-level utilities. Some concepts overlap intentionally, but the emphasis here is on the utility APIs and their correct use.

## Learning Objectives

By completing this chapter, you should be able to:

- Distinguish tasks from threads and choose suitable task-execution mechanisms.
- Use `Executor`, `ExecutorService`, and `ScheduledExecutorService`.
- Submit `Runnable` and `Callable` tasks and work with `Future`.
- Configure, monitor, and shut down executor services safely.
- Understand thread-pool sizing, queueing, backpressure, rejection, and task cancellation.
- Use `ReentrantLock`, `ReadWriteLock`, `StampedLock`, and `Condition` where appropriate.
- Use atomic variables, compare-and-set (CAS), `LongAdder`, and `VarHandle` appropriately.
- Choose among concurrent collections and understand their concurrency semantics.
- Coordinate tasks with `Semaphore`, `CountDownLatch`, `CyclicBarrier`, and `Phaser`.
- Compose asynchronous workflows with `CompletableFuture`, including failure handling, timeouts, and custom executors.
- Understand `ForkJoinPool`, recursive tasks, work-stealing, and parallel decomposition.
- Use Java 21 virtual threads for suitable workloads and understand their limitations.
- Understand `ThreadLocal`, thread confinement, immutable data, and context propagation trade-offs.
- Write concurrency tests that verify invariants rather than assuming thread scheduling order.
- Measure performance responsibly and distinguish correctness testing from benchmarking.
- Explain when simpler sequential code is preferable to concurrency.

## Chapter Scope

### Main topics in this chapter

- Executor framework and task lifecycle.
- Thread pools, scheduled work, cancellation, rejection, and shutdown.
- Explicit locking APIs and synchronizers.
- Atomic variables and concurrent data structures.
- Coordination utilities.
- `CompletableFuture` and asynchronous composition.
- Fork/join and parallel computation.
- Virtual threads and blocking I/O workloads.
- Concurrency design, testing, diagnostics, and performance experiments.

### Topics primarily covered in Chapter 8

- Creating threads with `Thread` and `Runnable`.
- Thread lifecycle, `sleep()`, `join()`, and interruption fundamentals.
- Intrinsic monitor locks and `synchronized`.
- `wait()`, `notify()`, and `notifyAll()` mechanics.
- Fundamental Java Memory Model concepts and happens-before relationships.
- Basic demonstrations of race conditions, deadlock, starvation, and livelock.

Revisit these Chapter 8 topics when a Chapter 9 API relies on the same underlying concepts.

## Suggested Package Structure

Place these packages under `src/main/java/com/javamastery/ch09_concurrency/`. The list is intentionally extensive. Treat it as a roadmap, not a requirement to create every class immediately. Implement each program as its topic is studied, and keep each demonstration focused on a small number of concepts.

```text
ch09_concurrency/
├── README.md
│
├── executors/
│   ├── ExecutorDemo.java
│   ├── DirectExecutorDemo.java
│   ├── ExecutorServiceDemo.java
│   ├── RunnableSubmissionDemo.java
│   ├── CallableSubmissionDemo.java
│   ├── ExecuteVsSubmitDemo.java
│   ├── FutureResultDemo.java
│   ├── FutureGetBlocksDemo.java
│   ├── FutureIsDoneDemo.java
│   ├── FutureCancellationDemo.java
│   ├── FutureExceptionHandlingDemo.java
│   ├── InvokeAllDemo.java
│   ├── InvokeAnyDemo.java
│   ├── FixedThreadPoolDemo.java
│   ├── SingleThreadExecutorDemo.java
│   ├── CachedThreadPoolDemo.java
│   ├── WorkQueueObservationDemo.java
│   ├── ThreadFactoryDemo.java
│   ├── CustomThreadFactoryDemo.java
│   ├── ExecutorThreadNamingDemo.java
│   ├── ExecutorShutdownDemo.java
│   ├── GracefulShutdownDemo.java
│   ├── ForcedShutdownDemo.java
│   ├── AwaitTerminationDemo.java
│   ├── ShutdownInterruptionHandlingDemo.java
│   ├── RejectedExecutionDemo.java
│   ├── RejectionPolicyDemo.java
│   ├── BoundedQueueExecutorDemo.java
│   ├── TaskExceptionObservationDemo.java
│   ├── ExecutorMetricsDemo.java
│   └── ExecutorResourceLeakDemo.java
│
├── scheduling/
│   ├── ScheduledExecutorServiceDemo.java
│   ├── ScheduleOnceDemo.java
│   ├── ScheduleAtFixedRateDemo.java
│   ├── ScheduleWithFixedDelayDemo.java
│   ├── FixedRateVsFixedDelayDemo.java
│   ├── ScheduledTaskCancellationDemo.java
│   ├── ScheduledTaskExceptionDemo.java
│   ├── PeriodicTaskShutdownDemo.java
│   ├── DelayedTaskOrderingDemo.java
│   └── ScheduledExecutorCleanupDemo.java
│
├── locks/
│   ├── LockInterfaceDemo.java
│   ├── ReentrantLockDemo.java
│   ├── LockFinallyUnlockDemo.java
│   ├── TryLockDemo.java
│   ├── TimedTryLockDemo.java
│   ├── InterruptibleLockDemo.java
│   ├── FairVsNonFairLockDemo.java
│   ├── ReentrantLockReentrancyDemo.java
│   ├── MultipleConditionsDemo.java
│   ├── ConditionAwaitSignalDemo.java
│   ├── ConditionGuardedWaitDemo.java
│   ├── ReadWriteLockDemo.java
│   ├── ReentrantReadWriteLockDemo.java
│   ├── ReadWriteLockUpgradePitfallDemo.java
│   ├── StampedLockReadWriteDemo.java
│   ├── StampedLockOptimisticReadDemo.java
│   ├── StampedLockValidationDemo.java
│   ├── StampedLockConversionDemo.java
│   ├── ExplicitLockVsSynchronizedDemo.java
│   ├── CustomLockUsingReentrantLockDemo.java
│   ├── CustomLockUsingConditionDemo.java
│   ├── MultiLockOrderingDemo.java
│   └── LockDiagnosticsDemo.java
│
├── atomics/
│   ├── AtomicIntegerDemo.java
│   ├── AtomicLongDemo.java
│   ├── AtomicBooleanDemo.java
│   ├── AtomicReferenceDemo.java
│   ├── AtomicArrayDemo.java
│   ├── AtomicFieldUpdaterDemo.java
│   ├── CompareAndSetDemo.java
│   ├── GetAndIncrementDemo.java
│   ├── UpdateAndAccumulateDemo.java
│   ├── CASRetryLoopDemo.java
│   ├── AtomicReferenceStateTransitionDemo.java
│   ├── AtomicityAcrossMultipleVariablesDemo.java
│   ├── AbaProblemExplanation.java
│   ├── AtomicStampedReferenceDemo.java
│   ├── LongAdderDemo.java
│   ├── LongAccumulatorDemo.java
│   ├── AtomicLongVsLongAdderDemo.java
│   ├── VarHandleBasicsDemo.java
│   ├── VarHandleCompareAndSetDemo.java
│   ├── VarHandleMemoryModesDemo.java
│   └── AtomicCounterComparisonDemo.java
│
├── collections/
│   ├── ConcurrentHashMapBasicsDemo.java
│   ├── ConcurrentHashMapAtomicOperationsDemo.java
│   ├── ConcurrentHashMapComputeDemo.java
│   ├── ConcurrentHashMapFrequencyMapDemo.java
│   ├── ConcurrentHashMapIterationDemo.java
│   ├── ConcurrentMapVsSynchronizedMapDemo.java
│   ├── ConcurrentSkipListMapDemo.java
│   ├── ConcurrentSkipListSetDemo.java
│   ├── ConcurrentLinkedQueueDemo.java
│   ├── ConcurrentLinkedDequeDemo.java
│   ├── CopyOnWriteArrayListDemo.java
│   ├── CopyOnWriteArraySetDemo.java
│   ├── ArrayBlockingQueueDemo.java
│   ├── LinkedBlockingQueueDemo.java
│   ├── LinkedBlockingDequeDemo.java
│   ├── PriorityBlockingQueueDemo.java
│   ├── DelayQueueDemo.java
│   ├── SynchronousQueueDemo.java
│   ├── LinkedTransferQueueDemo.java
│   ├── BlockingQueueProducerConsumerDemo.java
│   ├── BlockingQueuePoisonPillDemo.java
│   ├── BlockingQueueInterruptionDemo.java
│   ├── WeaklyConsistentIterationDemo.java
│   ├── ConcurrentCollectionSelectionDemo.java
│   └── ConcurrentCollectionPitfallsDemo.java
│
├── synchronizers/
│   ├── SemaphoreBasicsDemo.java
│   ├── SemaphoreResourcePoolDemo.java
│   ├── FairSemaphoreDemo.java
│   ├── SemaphorePermitLeakDemo.java
│   ├── CountDownLatchDemo.java
│   ├── StartTogetherLatchDemo.java
│   ├── WaitForWorkersLatchDemo.java
│   ├── CyclicBarrierDemo.java
│   ├── CyclicBarrierActionDemo.java
│   ├── CyclicBarrierFailureDemo.java
│   ├── PhaserBasicsDemo.java
│   ├── PhaserDynamicRegistrationDemo.java
│   ├── PhaserMultiPhaseDemo.java
│   ├── SynchronizerComparisonDemo.java
│   └── ResourceAdmissionControlDemo.java
│
├── completablefuture/
│   ├── CompletableFutureBasicsDemo.java
│   ├── RunAsyncDemo.java
│   ├── SupplyAsyncDemo.java
│   ├── ThenApplyDemo.java
│   ├── ThenAcceptDemo.java
│   ├── ThenRunDemo.java
│   ├── ThenComposeDemo.java
│   ├── ThenCombineDemo.java
│   ├── ThenAcceptBothDemo.java
│   ├── RunAfterBothDemo.java
│   ├── ApplyToEitherDemo.java
│   ├── AcceptEitherDemo.java
│   ├── AllOfDemo.java
│   ├── AnyOfDemo.java
│   ├── AsyncVsNonAsyncContinuationDemo.java
│   ├── CustomExecutorCompletableFutureDemo.java
│   ├── CompletableFutureExceptionallyDemo.java
│   ├── CompletableFutureHandleDemo.java
│   ├── CompletableFutureWhenCompleteDemo.java
│   ├── CompletableFutureExceptionPropagationDemo.java
│   ├── CompletableFutureTimeoutDemo.java
│   ├── CompletableFutureCompleteOnTimeoutDemo.java
│   ├── CompletableFutureManualCompletionDemo.java
│   ├── CompletableFutureCancellationDemo.java
│   ├── CompletableFuturePipelineDemo.java
│   ├── CompletableFutureFanOutFanInDemo.java
│   ├── CompletableFutureRetryDemo.java
│   ├── CompletableFutureFallbackDemo.java
│   ├── CompletableFutureBlockingPitfallDemo.java
│   ├── CompletableFutureExecutorIsolationDemo.java
│   └── CompletableFutureServiceAggregationDemo.java
│
├── forkjoin/
│   ├── ForkJoinPoolBasicsDemo.java
│   ├── RecursiveTaskDemo.java
│   ├── RecursiveActionDemo.java
│   ├── DivideAndConquerSumDemo.java
│   ├── ParallelArrayProcessingDemo.java
│   ├── WorkStealingObservationDemo.java
│   ├── ForkJoinThresholdExperiment.java
│   ├── ForkJoinBlockingPitfallDemo.java
│   ├── CommonPoolObservationDemo.java
│   └── ForkJoinVsExecutorDemo.java
│
├── virtualthreads/
│   ├── PlatformThreadDemo.java
│   ├── VirtualThreadBasicsDemo.java
│   ├── ThreadStartVirtualDemo.java
│   ├── VirtualThreadPerTaskExecutorDemo.java
│   ├── VirtualThreadNamingDemo.java
│   ├── VirtualThreadInterruptionDemo.java
│   ├── VirtualThreadCancellationDemo.java
│   ├── VirtualThreadBlockingIODemo.java
│   ├── PlatformVsVirtualThreadDemo.java
│   ├── VirtualThreadThreadLocalDemo.java
│   ├── VirtualThreadSynchronizationDemo.java
│   ├── VirtualThreadPinningExplanation.java
│   ├── VirtualThreadResourceLimitingDemo.java
│   ├── VirtualThreadExecutorShutdownDemo.java
│   └── VirtualThreadWorkloadExperiment.java
│
├── threadlocal/
│   ├── ThreadLocalBasicsDemo.java
│   ├── ThreadLocalIsolationDemo.java
│   ├── ThreadLocalInitialValueDemo.java
│   ├── ThreadLocalRemoveDemo.java
│   ├── ThreadLocalPoolLeakDemo.java
│   ├── InheritableThreadLocalDemo.java
│   ├── ThreadLocalVsSharedStateDemo.java
│   └── ThreadLocalContextPropagationDemo.java
│
├── design/
│   ├── ImmutableSharedStateDemo.java
│   ├── ThreadConfinementDemo.java
│   ├── ProducerConsumerWithBlockingQueueDemo.java
│   ├── BoundedTaskSubmissionDemo.java
│   ├── BackpressureSimulationDemo.java
│   ├── CpuBoundVsIoBoundDemo.java
│   ├── ExecutorSizingExperiment.java
│   ├── ResourcePoolWithSemaphoreDemo.java
│   ├── ConcurrentCacheDemo.java
│   ├── SingleFlightRequestDeduplicationDemo.java
│   ├── ParallelServiceAggregationDemo.java
│   ├── TimeoutBudgetPropagationDemo.java
│   ├── BulkheadIsolationDemo.java
│   ├── GracefulServiceShutdownDemo.java
│   └── ConcurrencyStrategyComparisonDemo.java
│
├── diagnostics/
│   ├── ThreadDumpHowTo.md
│   ├── ThreadMXBeanDemo.java
│   ├── DeadlockedThreadDetectionDemo.java
│   ├── ExecutorQueueMonitoringDemo.java
│   ├── ActiveThreadMonitoringDemo.java
│   ├── CompletableFutureDiagnosticsDemo.java
│   ├── ContentionObservationDemo.java
│   └── JcmdAndJstackNotes.md
│
├── experiments/
│   ├── PlatformVsVirtualThreadExperiment.java
│   ├── FixedPoolVsCachedPoolExperiment.java
│   ├── ExecutorQueueCapacityExperiment.java
│   ├── AtomicVsSynchronizedCounterExperiment.java
│   ├── AtomicLongVsLongAdderExperiment.java
│   ├── ConcurrentMapContentionExperiment.java
│   ├── ReadWriteLockWorkloadExperiment.java
│   ├── CompletableFutureCompositionExperiment.java
│   ├── ForkJoinThresholdExperiment.java
│   ├── BlockingVsNonBlockingExperiment.java
│   └── TaskGranularityExperiment.java
│
└── exercises/
    ├── Exercise01ParallelFileProcessing.java
    ├── Exercise02BoundedExecutor.java
    ├── Exercise03ScheduledJobRunner.java
    ├── Exercise04ThreadSafeCache.java
    ├── Exercise05ResourcePool.java
    ├── Exercise06ConcurrentWordCounter.java
    ├── Exercise07RateLimitedService.java
    ├── Exercise08AsyncServiceAggregator.java
    ├── Exercise09AsyncTimeoutAndFallback.java
    ├── Exercise10ParallelRecursiveSum.java
    ├── Exercise11VirtualThreadHttpSimulator.java
    ├── Exercise12GracefulShutdownCoordinator.java
    ├── Exercise13ConcurrentWorkQueue.java
    ├── Exercise14BulkheadIsolation.java
    ├── Exercise15SingleFlightDeduplicator.java
    └── Exercise16ConcurrencyStressTest.java
```

## Package Responsibilities

| Package | Purpose |
|---|---|
| `executors` | Task submission, results, pools, exception handling, rejection, cancellation, and shutdown. |
| `scheduling` | One-shot and periodic jobs, scheduling semantics, cancellation, and cleanup. |
| `locks` | Explicit locks, conditions, read/write locking, optimistic reads, and lock trade-offs. |
| `atomics` | CAS, atomic variables, accumulators, ABA, and `VarHandle`. |
| `collections` | Concurrent maps, queues, copy-on-write collections, blocking queues, and iteration semantics. |
| `synchronizers` | Semaphores, latches, barriers, phasers, and admission control. |
| `completablefuture` | Asynchronous transformations, composition, fan-out/fan-in, errors, cancellation, and timeouts. |
| `forkjoin` | Divide-and-conquer algorithms, work-stealing, and task-granularity experiments. |
| `virtualthreads` | Java 21 virtual threads, blocking workloads, cancellation, resource limits, and pinning concepts. |
| `threadlocal` | Per-thread state, cleanup, context propagation, and thread-local pitfalls. |
| `design` | Applying concurrency primitives to service-like designs and common architecture patterns. |
| `diagnostics` | Thread dumps, deadlock detection, executor monitoring, and diagnostic tooling. |
| `experiments` | Controlled comparisons of performance and behavior; not substitutes for benchmarks or correctness proofs. |
| `exercises` | Larger independent programs that combine multiple APIs and design decisions. |

## Topics and Key Concepts

### 1. Executor Framework

Study `Executor` first, then `ExecutorService`. Understand the difference between submitting work and managing the threads that execute it.

Implement examples that demonstrate:

- `execute()` versus `submit()`.
- `Runnable` versus `Callable`.
- Obtaining results through `Future`.
- Blocking behavior of `Future.get()`, completion checks, cancellation, and exceptions.
- `invokeAll()` and `invokeAny()`.
- Fixed, single-thread, and cached thread pools, including their trade-offs.
- Custom `ThreadFactory` implementations for naming and configuring worker threads.
- Bounded queues and rejected tasks.
- Rejection policies and what each policy means for application behavior.
- Graceful shutdown, forced shutdown, and `awaitTermination()`.
- Preserving interruption when shutdown logic catches `InterruptedException`.
- Why a pool should be shut down by the component that owns it.

**Important:** An unbounded queue or an unbounded task-submission rate can lead to excessive memory use and growing latency. Thread pools do not automatically provide backpressure.

### 2. Scheduled Tasks

Understand `ScheduledExecutorService` and the distinction between:

- `schedule()` for a delayed one-shot task.
- `scheduleAtFixedRate()` for periodic scheduling based on target start times.
- `scheduleWithFixedDelay()` for a delay measured after one execution completes.

Explore what happens when a periodic task throws an exception, is cancelled, or is interrupted during shutdown. Do not assume scheduled execution happens at an exact wall-clock time.

### 3. Explicit Locks

Implement and compare `ReentrantLock`, `ReadWriteLock`, `ReentrantReadWriteLock`, and `StampedLock`.

Study:

- Correct unlock in a `finally` block.
- `tryLock()`, timed acquisition, and interruptible acquisition.
- Fair versus non-fair locks.
- Reentrancy and multiple `Condition` objects.
- Guarded condition loops with `await()` and the appropriate signalling method.
- Read/write workloads and the limitations of lock upgrading.
- `StampedLock` optimistic reads and validation.
- Why `StampedLock` is not reentrant and must be used according to its API contract.
- When `synchronized` is simpler and preferable.

Do not assume an explicit lock is always faster than `synchronized`; measure realistic workloads before drawing performance conclusions.

### 4. Atomic Variables and CAS

Study `AtomicInteger`, `AtomicLong`, `AtomicBoolean`, `AtomicReference`, atomic arrays, and `LongAdder`.

Implement demonstrations for:

- Atomic increments and compare-and-set loops.
- Functional update operations.
- CAS retry loops and contention.
- Atomic references for state transitions.
- Why independent atomic variables do not automatically preserve a multi-variable invariant.
- The ABA problem and `AtomicStampedReference`.
- `AtomicLong` versus `LongAdder` under contention.
- `VarHandle` operations and memory-ordering modes.

Use atomics when their semantics fit the problem. A complex invariant may be clearer and safer behind one lock than spread across several independent atomic variables.

### 5. Concurrent Collections

Learn the concurrency contract of each collection rather than treating them as interchangeable.

- `ConcurrentHashMap` for concurrent map operations.
- Atomic map methods such as `putIfAbsent()`, `computeIfAbsent()`, `compute()`, and `merge()`.
- `ConcurrentSkipListMap` and `ConcurrentSkipListSet` for sorted concurrent data.
- `ConcurrentLinkedQueue` and `ConcurrentLinkedDeque` for non-blocking queue/deque operations.
- `CopyOnWriteArrayList` and `CopyOnWriteArraySet` for read-heavy workloads with infrequent writes.
- `BlockingQueue` implementations for producer-consumer coordination.
- `ArrayBlockingQueue`, `LinkedBlockingQueue`, `PriorityBlockingQueue`, `DelayQueue`, `SynchronousQueue`, and `LinkedTransferQueue`.
- Weakly consistent iteration and snapshot-style iteration where applicable.
- Why compound operations across multiple collection calls may still require coordination.

Do not choose a collection solely because it is labelled concurrent. Consider ordering, blocking behavior, memory costs, update frequency, capacity, and the required consistency guarantees.

### 6. Synchronization Utilities

Implement:

- `Semaphore` to limit simultaneous access to a finite resource.
- `CountDownLatch` for one-shot coordination.
- `CyclicBarrier` for a group of threads to meet at a phase boundary.
- `Phaser` for multi-phase coordination and dynamic registration.

For each, demonstrate normal completion, interruption, timeout where supported, and failure paths. Always release acquired semaphore permits in a `finally` block. Understand that a latch is one-shot, whereas barriers and phasers support repeated phases in different ways.

### 7. CompletableFuture and Asynchronous Composition

Start with `runAsync()` and `supplyAsync()`, then learn the continuation methods:

- `thenApply()`, `thenAccept()`, and `thenRun()`.
- `thenCompose()` for dependent asynchronous stages.
- `thenCombine()` and related methods for independent stages.
- `allOf()` and `anyOf()` for coordinating multiple futures.
- Async versus non-async continuations and executor selection.
- `exceptionally()`, `handle()`, and `whenComplete()`.
- Timeout methods, manual completion, cancellation, and exception propagation.
- Fan-out/fan-in workflows and aggregation of independent service calls.
- Why calling `get()` or `join()` too early can turn an asynchronous pipeline into blocking code.
- Why the default common pool should not automatically be used for every workload.
- Isolating blocking operations on an appropriate executor.

**Important:** `CompletableFuture` cancellation does not necessarily interrupt or stop the underlying computation. Design cancellation and timeout behavior explicitly.

### 8. Fork/Join and Parallel Decomposition

Use `RecursiveTask` and `RecursiveAction` to implement divide-and-conquer computations.

Study:

- Recursive decomposition and the base-case threshold.
- Work-stealing and task granularity.
- The common `ForkJoinPool` and explicit pool ownership.
- Why too many tiny tasks can increase overhead.
- Why blocking I/O inside fork/join tasks can harm pool progress.
- Comparison with ordinary executor tasks and sequential implementations.

### 9. Virtual Threads — Java 21

Virtual threads make thread-per-task designs practical for many blocking workloads, but they do not make CPU-bound work execute faster by themselves.

Implement and compare:

- Platform threads and virtual threads.
- `Thread.ofVirtual()` and `Executors.newVirtualThreadPerTaskExecutor()`.
- Large numbers of blocking tasks with bounded external resources.
- Naming, interruption, cancellation, and executor shutdown.
- The use and cleanup of `ThreadLocal`.
- Resource limiting with a semaphore when a downstream service has limited capacity.
- Synchronization inside virtual-thread workloads.
- Pinning concepts and relevant Java 21 limitations.

**Java 21 note:** Virtual threads are a standard feature in Java 21. Some related capabilities introduced in later Java releases must not be assumed available in this project's Java 21 baseline.

### 10. ThreadLocal and Context

Study `ThreadLocal` as per-thread state, not as a general-purpose replacement for passing data explicitly.

Implement examples showing:

- Initial values and per-thread isolation.
- `remove()` and cleanup.
- How reused platform threads can retain stale values between tasks.
- `InheritableThreadLocal` behavior and its limitations.
- Why thread-local context does not automatically propagate through every asynchronous stage.
- Why virtual-thread-per-task designs change some thread-local usage trade-offs.

Prefer explicit context passing when it improves clarity and lifecycle control.

### 11. Concurrency Design

Combine the APIs to model practical systems:

- Bounded task submission and backpressure.
- Resource pools and admission control.
- CPU-bound versus I/O-bound workloads.
- Concurrent caches and request deduplication.
- Single-flight computation to avoid duplicate concurrent work.
- Parallel service aggregation.
- Timeouts and fallback strategies.
- Bulkhead isolation between workload classes.
- Graceful shutdown and draining in-flight work.
- Thread confinement and immutable shared state.

For each design, document its invariants, ownership model, failure behavior, cancellation policy, resource limits, and shutdown behavior.

### 12. Diagnostics and Testing

Learn to inspect concurrency problems rather than relying only on console output.

- Use thread dumps to investigate blocked and waiting threads.
- Use `ThreadMXBean` to inspect thread information and detect monitor deadlocks.
- Learn when `jcmd` and `jstack` are useful.
- Observe executor queue depth and active task counts.
- Add bounded waits and deterministic coordination to tests.
- Test correctness under repeated concurrent execution.
- Separate functional tests from performance benchmarks.
- Use dedicated tools such as JMH for benchmarking and JCStress for exploring concurrency outcomes where appropriate; these may require additional Maven dependencies and their own setup.

## Practice Projects

Complete these larger exercises after implementing the focused demonstrations.

1. **Parallel File Processing:** split independent work across tasks, combine results, handle failures, and shut down cleanly.
2. **Bounded Executor:** create an executor with a bounded queue and explicit rejection behavior.
3. **Scheduled Job Runner:** schedule periodic jobs, handle task failures, support cancellation, and shut down gracefully.
4. **Thread-Safe Cache:** implement concurrent lookup and update behavior with a clear consistency contract.
5. **Resource Pool:** limit concurrent access with a semaphore and ensure permits are never leaked.
6. **Concurrent Word Counter:** process multiple input chunks and aggregate word frequencies safely.
7. **Rate-Limited Service:** restrict concurrent or time-based access and document the exact rate-limiting semantics.
8. **Async Service Aggregator:** call independent simulated services concurrently and combine their results.
9. **Async Timeout and Fallback:** apply deadlines, handle failures, and return a fallback without leaving uncontrolled work behind.
10. **Parallel Recursive Sum:** implement a fork/join solution and compare it with a sequential baseline.
11. **Virtual-Thread HTTP Simulator:** simulate many blocking I/O tasks while limiting access to a constrained downstream service.
12. **Graceful Shutdown Coordinator:** stop accepting work, allow in-flight tasks to finish within a deadline, then handle remaining tasks.
13. **Concurrent Work Queue:** implement a producer-consumer pipeline with bounded capacity and cancellation.
14. **Bulkhead Isolation:** isolate two workloads so saturation in one does not exhaust the other's execution resources.
15. **Single-Flight Deduplicator:** ensure concurrent requests for the same key share one in-progress computation.
16. **Concurrency Stress Test:** repeatedly exercise a shared component and verify invariants under concurrent access.

## Completion Checklist

### Executors and scheduling

- [ ] Explain `Executor`, `ExecutorService`, `Runnable`, `Callable`, and `Future`.
- [ ] Compare common executor types and their queueing behavior.
- [ ] Handle task results, exceptions, cancellation, rejection, and shutdown.
- [ ] Explain fixed-rate versus fixed-delay scheduling.
- [ ] Design bounded task submission and meaningful overload behavior.

### Locks and atomics

- [ ] Use `ReentrantLock` with correct unlock semantics.
- [ ] Explain timed and interruptible locking.
- [ ] Use `Condition` with guarded loops.
- [ ] Explain read/write locks, optimistic reads, and their trade-offs.
- [ ] Implement CAS-based updates and explain ABA.
- [ ] Compare `AtomicLong` and `LongAdder` for suitable workloads.
- [ ] Explain why multiple atomic variables do not guarantee a multi-variable invariant.

### Collections and synchronizers

- [ ] Choose a concurrent collection based on its contract and workload.
- [ ] Implement producer-consumer using a `BlockingQueue`.
- [ ] Use `Semaphore`, `CountDownLatch`, `CyclicBarrier`, and `Phaser`.
- [ ] Handle interruption and failure without leaking resources.

### Asynchronous and parallel execution

- [ ] Compose dependent tasks with `thenCompose()`.
- [ ] Combine independent tasks with `thenCombine()` and `allOf()`.
- [ ] Handle exceptions, cancellation, timeouts, and custom executors.
- [ ] Implement a fork/join divide-and-conquer task.
- [ ] Explain when virtual threads are useful and what they do not solve.
- [ ] Explain `ThreadLocal` cleanup and context-propagation concerns.

### Engineering and diagnostics

- [ ] State the invariants and resource limits for a concurrent design.
- [ ] Implement graceful shutdown and bounded waits.
- [ ] Diagnose a deadlock using thread information or a thread dump.
- [ ] Write tests that do not assume a particular scheduling order.
- [ ] Distinguish correctness tests from performance measurements.
- [ ] Complete the practice projects and explain why each API was selected.

## Recommended Implementation Order

1. **Executors:** start with task submission, results, pools, cancellation, and shutdown.
2. **Scheduling:** implement delayed and periodic tasks.
3. **Explicit locks:** practise `ReentrantLock`, conditions, read/write locks, and `StampedLock`.
4. **Atomics:** study CAS, atomic references, `LongAdder`, and `VarHandle`.
5. **Concurrent collections:** compare maps, queues, copy-on-write collections, and blocking queues.
6. **Synchronizers:** implement semaphores, latches, barriers, and phasers.
7. **CompletableFuture:** practise chaining, composition, aggregation, errors, and timeouts.
8. **Fork/join:** implement recursive tasks and measure different thresholds.
9. **Virtual threads and ThreadLocal:** compare blocking workloads and examine context lifecycle.
10. **Design and diagnostics:** build the practice projects, test invariants, and investigate failure modes.
11. **Experiments:** compare alternatives only after you understand their semantics.

Do not create every class at once. Add each demonstration as you study it, write a short note about the guarantee it demonstrates, and implement the exercises independently before reviewing your examples.

## Project and Testing Guidelines

- Use one focused public class per file where practical.
- Keep chapter code under `src/main/java/com/javamastery/ch09_concurrency/`.
- Put automated tests under `src/test/java/com/javamastery/ch09_concurrency/`.
- Use Java 21 APIs only unless a later-version comparison is explicitly documented as research rather than executable project code.
- Prefer deterministic coordination over `Thread.sleep()` when a test requires a particular state or ordering.
- Put timeouts on blocking operations used in tests and clean up executors in `finally` blocks or try-with-resources where the API supports it.
- Do not create enormous numbers of platform threads as a casual experiment; constrain resource usage and keep experiments repeatable.
- Do not treat one benchmark run as conclusive. Warm-up, workload shape, contention, hardware, and JVM behavior all affect measurements.
- Keep deliberately failing or resource-intensive demonstrations isolated from the normal test suite.
- Document cancellation semantics: cancelling a `Future` or timing out a `CompletableFuture` is not proof that the underlying work has stopped.
- Prefer the simplest correct abstraction. Concurrency increases complexity and is not automatically beneficial.

## Recommended Resources

### Books

- *Java Concurrency in Practice* — Brian Goetz et al.
- *Core Java, Volume I — Fundamentals* — Cay S. Horstmann.
- *The Well-Grounded Java Developer, Second Edition* — Benjamin J. Evans, Jason Clark, and Martijn Verburg.

### Videos

- [Jakob Jenkov — Java Concurrency](https://www.youtube.com/@JakobJenkov)
- [Coder Army — Java Programming](https://www.youtube.com/@CoderArmy)

### Official Java 21 Documentation

- [Concurrency Utilities Package](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/package-summary.html)
- [ExecutorService API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/ExecutorService.html)
- [CompletableFuture API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/CompletableFuture.html)
- [Concurrent Collections](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/package-summary.html)
- [Virtual Threads — Java 21](https://docs.oracle.com/en/java/javase/21/core/virtual-threads.html)
- [Java Language Specification — Threads and Locks](https://docs.oracle.com/javase/specs/jls/se21/html/jls-17.html)

## Prerequisites

- Completion of Chapter 8 — `ch08_multithreading`.
- A solid understanding of race conditions, intrinsic locks, interruption, and happens-before.
- Familiarity with interfaces, generics, lambdas, functional interfaces, and exception handling.
- Basic experience running Java programs and tests through IntelliJ IDEA or Maven.

## Chapter Outcome

By the end of this chapter, you should be able to select and use Java concurrency utilities for task execution, coordination, shared-state management, asynchronous composition, and parallel computation. You should also be able to explain the trade-offs, test key correctness properties, diagnose common failures, and manage resources deliberately.

Expertise comes from more than implementing APIs: it comes from being able to explain each design's invariants, failure modes, cancellation behavior, resource limits, and performance trade-offs.

**Guiding principle:** Choose the simplest concurrency abstraction that satisfies the correctness, throughput, latency, and resource constraints of the problem.
