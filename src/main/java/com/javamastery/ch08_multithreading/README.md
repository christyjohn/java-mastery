# Chapter 8 — Multithreading

## Overview

This chapter builds a practical understanding of Java's thread model, thread lifecycle, shared mutable state, intrinsic monitor locks, thread communication, and the Java Memory Model (JMM).

The objective is not merely to make programs run concurrently. It is to understand why concurrency bugs occur, how Java's synchronization guarantees work, and how to design code that remains correct when multiple threads access shared state.

This chapter uses **Java 21** and is part of the `java-mastery` project.

> **Chapter boundary:** Chapter 8 focuses on threads, `synchronized`, intrinsic monitor locks, `wait()`/`notify()`, interruption, and memory-model fundamentals. Higher-level utilities such as executors, `ReentrantLock`, `Condition`, atomic classes, concurrent collections, `CompletableFuture`, fork/join, and virtual threads receive their main treatment in Chapter 9 — `ch09_concurrency`.

## Learning Objectives

By completing this chapter, you should be able to:

- Create threads using `Thread` and `Runnable`, and explain `start()` versus `run()`.
- Understand thread states, scheduling, names, priorities, daemon threads, and thread groups.
- Use `sleep()`, `join()`, and interruption correctly.
- Implement cooperative cancellation and preserve interruption semantics.
- Explain race conditions, lost updates, critical sections, shared mutable state, and thread safety.
- Use synchronized instance methods, static synchronized methods, and synchronized blocks.
- Explain intrinsic locks, object monitors, class-level locks, reentrancy, lock scope, and lock granularity.
- Distinguish an instance monitor (`this`) from a class monitor (`SomeClass.class`).
- Coordinate threads using `wait()`, `notify()`, and `notifyAll()`.
- Explain monitor ownership, why waiting must be condition-based, and why `wait()` belongs inside a loop.
- Implement producer-consumer and bounded-buffer examples using monitor-based coordination.
- Explain visibility, atomicity, ordering, data races, safe publication, and happens-before relationships.
- Explain what `volatile` guarantees and why it does not make compound operations such as `count++` atomic.
- Recognize and reason about deadlock, starvation, livelock, lock-order inversion, and thread leaks.
- Design deterministic concurrency demonstrations and avoid relying on scheduler timing for correctness.

## Chapter Scope

### Included in this chapter

- Thread creation, lifecycle, interruption, and cooperative cancellation.
- Race conditions and thread safety.
- `synchronized`, intrinsic locks, and monitor ownership.
- Static synchronization and class-level locking.
- Reentrant synchronization and lock ordering.
- `wait()`, `notify()`, `notifyAll()`, guarded blocks, and monitor-based thread communication.
- Java Memory Model, `volatile`, happens-before, safe publication, and final-field semantics.
- Concurrency hazards and controlled experiments.

### Primarily covered in Chapter 9 — Concurrency Utilities

Study these in Chapter 9 rather than duplicating their main implementations here:

- `Executor`, `ExecutorService`, `ScheduledExecutorService`, `Callable`, and `Future`.
- `ReentrantLock`, `ReadWriteLock`, `Condition`, and explicit lock APIs.
- `AtomicInteger`, `AtomicLong`, `AtomicReference`, `LongAdder`, CAS, and `VarHandle`.
- `ConcurrentHashMap`, `BlockingQueue`, and other concurrent collections.
- `Semaphore`, `CountDownLatch`, `CyclicBarrier`, and `Phaser`.
- `CompletableFuture`, asynchronous composition, and exception handling.
- `ForkJoinPool`, work-stealing, and virtual threads.

A brief comparison with these APIs is useful when it clarifies why a higher-level abstraction is preferable, but the detailed implementations belong in Chapter 9.

## Suggested Package Structure

Place these packages under `src/main/java/com/javamastery/ch08_multithreading/`. The class names below are a comprehensive study checklist, not a requirement to create every file at once. Add each class as you reach its topic.

```text
ch08_multithreading/
├── README.md
│
├── fundamentals/
│   ├── ThreadCreationDemo.java
│   ├── RunnableDemo.java
│   ├── ThreadVsRunnableDemo.java
│   ├── ThreadStartVsRunDemo.java
│   ├── MultipleThreadsDemo.java
│   ├── ThreadNamingDemo.java
│   ├── ThreadPriorityDemo.java
│   ├── DaemonThreadDemo.java
│   ├── ThreadStateDemo.java
│   ├── ThreadLifecycleDemo.java
│   ├── CurrentThreadDemo.java
│   ├── ThreadGroupDemo.java
│   ├── ThreadUncaughtExceptionDemo.java
│   └── DeprecatedThreadMethodsExplanation.java
│
├── lifecycle/
│   ├── SleepDemo.java
│   ├── JoinDemo.java
│   ├── TimedJoinDemo.java
│   ├── YieldDemo.java
│   ├── InterruptFlagDemo.java
│   ├── InterruptSleepDemo.java
│   ├── InterruptWaitingThreadDemo.java
│   ├── InterruptRunningThreadDemo.java
│   ├── InterruptRestorationDemo.java
│   ├── CooperativeCancellationDemo.java
│   ├── ShutdownFlagDemo.java
│   ├── UncaughtExceptionHandlerDemo.java
│   └── ThreadTerminationDemo.java
│
├── raceconditions/
│   ├── RaceConditionDemo.java
│   ├── SharedCounterRaceDemo.java
│   ├── LostUpdateDemo.java
│   ├── SharedObjectRaceDemo.java
│   ├── BankAccountRaceDemo.java
│   ├── CheckThenActRaceDemo.java
│   ├── ReadModifyWriteDemo.java
│   ├── SynchronizedCounter.java
│   ├── ThreadSafeCounter.java
│   ├── ThreadSafeBankAccount.java
│   ├── ThreadConfinedCounterDemo.java
│   └── RaceConditionFixComparisonDemo.java
│
├── synchronization/
│   ├── MonitorLockDemo.java
│   ├── IntrinsicLockDemo.java
│   ├── SynchronizedMethodDemo.java
│   ├── SynchronizedBlockDemo.java
│   ├── InstanceLockDemo.java
│   ├── StaticSynchronizationDemo.java
│   ├── ClassLevelLockDemo.java
│   ├── InstanceVsClassLockDemo.java
│   ├── SameLockMultipleMethodsDemo.java
│   ├── DifferentLocksDemo.java
│   ├── LockScopeDemo.java
│   ├── LockGranularityDemo.java
│   ├── SynchronizedOnThisDemo.java
│   ├── SynchronizedOnClassDemo.java
│   ├── SynchronizedOnDifferentObjectsDemo.java
│   ├── ReentrantSynchronizationDemo.java
│   ├── NestedSynchronizationDemo.java
│   ├── SynchronizedInheritanceDemo.java
│   ├── SynchronizedMethodOverrideDemo.java
│   ├── ConstructorSynchronizationDemo.java
│   ├── ThisEscapeDemo.java
│   ├── LockOrderingDemo.java
│   ├── MonitorOwnershipDemo.java
│   ├── MonitorReleaseAndAcquireDemo.java
│   ├── SynchronizedVisibilityDemo.java
│   └── CustomLockDemo.java
│
├── communication/
│   ├── WaitNotifyDemo.java
│   ├── WaitNotifyAllDemo.java
│   ├── WaitRequiresMonitorDemo.java
│   ├── NotifyRequiresMonitorDemo.java
│   ├── WaitReleasesMonitorDemo.java
│   ├── SleepDoesNotReleaseMonitorDemo.java
│   ├── WaitInWhileLoopDemo.java
│   ├── SpuriousWakeupExplanation.java
│   ├── MissedNotificationDemo.java
│   ├── IncorrectWaitNotifyDemo.java
│   ├── ProducerConsumerDemo.java
│   ├── SingleSlotBufferDemo.java
│   ├── BoundedBufferDemo.java
│   ├── MultipleProducersConsumersDemo.java
│   ├── ThreadCoordinationDemo.java
│   ├── OrderedExecutionDemo.java
│   ├── PrintNumbersInOrderDemo.java
│   ├── AlternatingThreadsDemo.java
│   ├── OddEvenPrinterDemo.java
│   ├── ThreadBarrierUsingWaitNotifyDemo.java
│   └── GuardedBlockDemo.java
│
├── memorymodel/
│   ├── VisibilityProblemDemo.java
│   ├── VolatileVisibilityDemo.java
│   ├── VolatileCounterDemo.java
│   ├── VolatileVsSynchronizedDemo.java
│   ├── AtomicityVsVisibilityDemo.java
│   ├── HappensBeforeDemo.java
│   ├── ProgramOrderRuleDemo.java
│   ├── MonitorUnlockLockRuleDemo.java
│   ├── VolatileHappensBeforeDemo.java
│   ├── ThreadStartHappensBeforeDemo.java
│   ├── ThreadJoinHappensBeforeDemo.java
│   ├── FinalFieldSemanticsDemo.java
│   ├── SafePublicationDemo.java
│   ├── UnsafePublicationDemo.java
│   ├── InstructionReorderingExplanation.java
│   ├── DoubleCheckedLockingDemo.java
│   ├── ImmutableObjectSharingDemo.java
│   └── DataRaceDemo.java
│
├── pitfalls/
│   ├── DeadlockDemo.java
│   ├── DeadlockPreventionDemo.java
│   ├── DeadlockLockOrderingDemo.java
│   ├── StarvationDemo.java
│   ├── LivelockDemo.java
│   ├── ThreadContentionDemo.java
│   ├── ExcessiveSynchronizationDemo.java
│   ├── CoarseVsFineGrainedLockingDemo.java
│   ├── SynchronizationOverheadDemo.java
│   ├── UnresponsiveThreadDemo.java
│   ├── IgnoredInterruptDemo.java
│   ├── SwallowedInterruptedExceptionDemo.java
│   ├── LockOrderInversionDemo.java
│   └── ThreadLeakDemo.java
│
├── experiments/
│   ├── ThreadSchedulingExperiment.java
│   ├── ThreadPriorityExperiment.java
│   ├── YieldBehaviorExperiment.java
│   ├── VisibilityExperiment.java
│   ├── VolatileExperiment.java
│   ├── SynchronizationExperiment.java
│   ├── LockContentionExperiment.java
│   ├── ThreadCreationCostExperiment.java
│   ├── ContextSwitchingExperiment.java
│   └── ThreadStateObservationExperiment.java
│
└── exercises/
    ├── Exercise01ConcurrentCounter.java
    ├── Exercise02BankAccount.java
    ├── Exercise03TicketBooking.java
    ├── Exercise04InventoryManagement.java
    ├── Exercise05ProducerConsumer.java
    ├── Exercise06PrintInOrder.java
    ├── Exercise07OddEvenPrinter.java
    ├── Exercise08CustomMonitorLock.java
    ├── Exercise09DeadlockDetection.java
    ├── Exercise10DeadlockPrevention.java
    ├── Exercise11ThreadCancellation.java
    ├── Exercise12ThreadSafeSingleton.java
    └── Exercise13BoundedBuffer.java
```

### Package Responsibilities

| Package | Purpose |
|---|---|
| `fundamentals` | Thread creation, execution, states, naming, priorities, daemon threads, and exception handling. |
| `lifecycle` | Sleeping, joining, interruption, cancellation, and termination. |
| `raceconditions` | Shared state, lost updates, check-then-act races, and safe counter/account implementations. |
| `synchronization` | Intrinsic monitors, instance and class locks, static synchronization, reentrancy, lock scope, and custom monitor locks. |
| `communication` | Monitor-based waiting and notification, guarded blocks, producer-consumer, and ordered execution. |
| `memorymodel` | Visibility, atomicity, `volatile`, happens-before, data races, and safe publication. |
| `pitfalls` | Deadlock, starvation, livelock, contention, incorrect interruption handling, and thread leaks. |
| `experiments` | Controlled observations of scheduling, visibility, synchronization, and thread behavior. |
| `exercises` | Independent challenges that combine concepts from the chapter. |

## Important Concepts and Demonstrations

### 1. Thread Fundamentals and Lifecycle

Prioritize these demonstrations:

- `ThreadCreationDemo`, `RunnableDemo`, and `ThreadVsRunnableDemo`.
- `ThreadStartVsRunDemo`: show that `start()` schedules a new thread while calling `run()` is an ordinary method invocation.
- `ThreadStateDemo` and `ThreadLifecycleDemo`: observe `NEW`, `RUNNABLE`, `BLOCKED`, `WAITING`, `TIMED_WAITING`, and `TERMINATED`.
- `DaemonThreadDemo` and `ThreadPriorityDemo`: learn the API behavior without assuming priorities guarantee execution order.
- `JoinDemo`, `InterruptSleepDemo`, and `CooperativeCancellationDemo`: practise coordination and cancellation.

### 2. Race Conditions and Synchronization

Use a shared counter and bank account to compare unsafe and safe approaches.

For monitor locking, demonstrate all of these cases:

- Two threads invoke the same synchronized instance method on the same object.
- Two threads invoke synchronized methods on different instances.
- Two synchronized instance methods on one object compete for the same monitor.
- Static synchronized methods use the corresponding `Class` object's monitor.
- Static synchronized and instance synchronized methods use different monitors.
- A synchronized block can use `this`, a class object, or a dedicated private lock object.
- Synchronized calls are reentrant when a thread already owns the same monitor.
- Nested locks can deadlock if threads acquire locks in inconsistent orders.

**Static synchronization rule:** a `static synchronized` method locks the `Class` object; an instance synchronized method locks that particular instance. They do not automatically exclude each other.

**Custom lock rule:** `CustomLockDemo.java` should be an educational monitor-based lock built with `synchronized`, `wait()`, and `notifyAll()`. Treat it as an exercise in ownership and coordination, not as a production replacement for `ReentrantLock`.

### 3. Thread Communication

Study the relationship between a monitor and the condition being awaited:

- `wait()` must be called by a thread that owns the relevant monitor.
- `wait()` releases that monitor while waiting and reacquires it before returning.
- `sleep()` does not release monitors held by the sleeping thread.
- `notify()` wakes one waiting thread, if any; `notifyAll()` wakes all threads waiting on that object's monitor. A notified thread must still reacquire the monitor.
- Always test the condition in a `while` loop because a thread may wake when the condition is still false, including due to a spurious wakeup.
- Coordinate state changes and notifications under the same monitor to avoid missed-notification bugs.

Implement a single-slot buffer first, then a bounded buffer, and finally multiple producers and consumers. Keep deliberately incorrect demonstrations clearly labelled and ensure they can be stopped safely.

### 4. Java Memory Model

Understand and demonstrate:

- **Visibility:** one thread's changes are not automatically guaranteed to become visible to another thread without an appropriate synchronization relationship.
- **Atomicity:** an operation such as `count++` is a read-modify-write sequence, not an atomic increment.
- **Ordering:** the JMM permits certain reorderings while preserving its guarantees.
- **Happens-before:** program order, monitor unlock/lock, volatile write/read, and thread start/join relationships.
- **Safe publication:** make objects available to other threads through a mechanism that establishes the required guarantees.
- **Final fields:** understand the special initialization guarantees for properly constructed objects and why leaking `this` during construction is dangerous.

Do not assume that a visibility bug will reproduce every time. A test that happens to work on one machine does not prove that unsynchronized code is correct.

### 5. Concurrency Hazards

- **Deadlock:** threads wait indefinitely for locks held by each other.
- **Starvation:** a thread repeatedly fails to obtain the resources or execution opportunity it needs.
- **Livelock:** threads remain active but repeatedly react in ways that prevent progress.
- **Contention:** multiple threads compete for the same resource.
- **Thread leak:** threads remain alive longer than intended because their lifecycle is not managed correctly.
- **Swallowed interruption:** code catches `InterruptedException` and discards the cancellation signal.

Use controlled coordination for demonstrations. Avoid leaving deliberately deadlocked threads running indefinitely, especially in a shared IDE process.

## Practice Challenges

Complete these after studying the related demonstrations. Try implementing each from a blank file before consulting your earlier examples.

1. **Concurrent Counter:** create a counter that several threads increment. Reproduce the race, then fix it with synchronization.
2. **Bank Account:** support concurrent deposits and withdrawals while preserving balance invariants.
3. **Ticket Booking:** simulate concurrent booking attempts and prevent overselling.
4. **Inventory Management:** protect stock updates from lost updates and check-then-act races.
5. **Producer-Consumer:** build a bounded buffer with `wait()` and `notifyAll()`.
6. **Print in Order:** coordinate three threads so their output follows a required order.
7. **Odd-Even Printer:** use monitor-based coordination to print alternating odd and even numbers.
8. **Custom Monitor Lock:** implement lock ownership, waiting, release, and notification; define the behavior for invalid unlock attempts.
9. **Deadlock Detection:** create a controlled deadlock scenario and identify its lock dependency.
10. **Deadlock Prevention:** use a consistent lock ordering to eliminate the deadlock.
11. **Thread Cancellation:** build a worker that terminates cleanly after interruption.
12. **Thread-Safe Singleton:** implement and explain a correct lazy-initialization approach using Java's synchronization guarantees.
13. **Bounded Buffer:** support multiple producers and consumers while preserving capacity and empty/full conditions.

## Completion Checklist

### Fundamentals and lifecycle

- [ ] Explain `Thread` versus `Runnable` and `start()` versus `run()`.
- [ ] Explain all six `Thread.State` values.
- [ ] Use `join()`, interruption, and cooperative cancellation correctly.
- [ ] Explain daemon threads and why thread priority does not guarantee scheduling order.

### Race conditions and synchronization

- [ ] Reproduce a lost update and explain why it occurs.
- [ ] Explain intrinsic monitor locks and monitor ownership.
- [ ] Demonstrate synchronized methods and blocks.
- [ ] Explain instance locks versus class-level locks.
- [ ] Explain static synchronization and reentrant synchronization.
- [ ] Implement and explain a basic custom monitor lock.

### Thread communication

- [ ] Explain `wait()`, `notify()`, and `notifyAll()`.
- [ ] Explain why `wait()` must be used while owning the relevant monitor.
- [ ] Explain why waiting conditions belong in a loop.
- [ ] Implement producer-consumer and bounded-buffer examples.

### Java Memory Model and hazards

- [ ] Explain visibility, atomicity, ordering, and happens-before.
- [ ] Explain the guarantees and limitations of `volatile`.
- [ ] Explain safe publication and final-field semantics.
- [ ] Demonstrate and prevent deadlock.
- [ ] Explain starvation, livelock, contention, and thread leaks.
- [ ] Distinguish a useful concurrency experiment from a correctness proof.

### Engineering practice

- [ ] Keep each demonstration focused on one concept.
- [ ] Separate correct examples from intentionally broken examples.
- [ ] Use bounded waits and controlled cleanup in tests.
- [ ] Write tests for correctness properties rather than assumed scheduling order.
- [ ] Complete the exercises without copying the solution first.

## Recommended Implementation Order

1. **Fundamentals:** create threads, compare `start()` and `run()`, and inspect lifecycle states.
2. **Lifecycle:** practise `sleep()`, `join()`, interruption, and cancellation.
3. **Race conditions:** reproduce lost updates and compare unsafe and synchronized counters.
4. **Synchronization:** explore intrinsic locks, static synchronization, class monitors, reentrancy, and custom monitor locks.
5. **Communication:** implement `wait()`/`notifyAll()`, ordered execution, and producer-consumer.
6. **Memory model:** study visibility, `volatile`, happens-before, and safe publication.
7. **Pitfalls:** investigate deadlock, starvation, livelock, and incorrect interruption handling.
8. **Experiments and exercises:** run controlled experiments and solve the integrated challenges.

Do not create all the files at the beginning. Add each class when you study its topic, and write a short explanation of the observation and the underlying guarantee in its comments or accompanying notes.

## Project and Testing Guidelines

- Use one focused public class per source file where practical.
- Keep examples under `src/main/java/com/javamastery/ch08_multithreading/`.
- Put automated tests under `src/test/java/com/javamastery/ch08_multithreading/`.
- Use clear names for deliberately broken examples, such as `IncorrectWaitNotifyDemo` or `UnsafePublicationDemo`.
- Do not depend on `Thread.sleep()` to guarantee that another thread has reached a particular state. Use coordination primitives when deterministic ordering is required.
- Put time bounds on waits and joins used by tests, and ensure worker threads can be cleaned up.
- A deadlock demonstration should be isolated and controlled; do not allow it to hang the normal test suite.
- Do not treat printed output from one run as proof of memory-model correctness.
- Keep performance observations in `experiments/`; reliable microbenchmarking requires more care than a simple elapsed-time measurement.

## Recommended Resources

### Books

- *Core Java, Volume I — Fundamentals* — Cay S. Horstmann.
- *Java Concurrency in Practice* — Brian Goetz et al.

### Videos

- [Jakob Jenkov — Java Concurrency](https://www.youtube.com/@JakobJenkov)
- [Coder Army — Java Programming](https://www.youtube.com/@CoderArmy)

### Official Java 21 Documentation

- [Java Language Specification — Chapter 17: Threads and Locks](https://docs.oracle.com/javase/specs/jls/se21/html/jls-17.html)
- [Java `Thread` API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Thread.html)
- [Java `Object` API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Object.html)

## Prerequisites

- Java fundamentals: classes, objects, methods, and interfaces.
- Exception handling.
- Basic understanding of object references and shared mutable state.
- Familiarity with running Java applications from IntelliJ IDEA or Maven.

## Chapter Outcome

By the end of this chapter, you should be able to explain how Java threads execute, identify concurrency hazards, coordinate threads with intrinsic monitors, and reason about visibility and ordering using the Java Memory Model.

You should also understand when these low-level mechanisms are appropriate and be ready to study higher-level concurrency utilities in Chapter 9.

**Guiding principle:** Understand the concurrency problem and the guarantees you need before choosing a synchronization mechanism.
