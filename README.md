# Java Mastery

A structured, long-term journey to mastering Java — from language fundamentals to advanced JVM concepts, concurrency, functional programming, design patterns, and data structures and algorithms.

This repository serves as my personal Java learning laboratory, practical notebook, and reference library. It brings together knowledge from books, video tutorials, online courses, and hands-on experimentation in one organized codebase.

## Objectives

The goal of this repository is to develop a deep understanding of Java, its ecosystem, and the principles behind writing reliable, maintainable, and efficient software.

Key learning objectives:

- **Core Java:** Build a strong foundation in language fundamentals, object-oriented programming, types, exceptions, strings, arrays, and the Collections Framework.
- **Generics and Functional Programming:** Understand generic types, lambdas, functional interfaces, streams, and functional programming principles.
- **JVM Internals:** Explore class loading, runtime memory areas, object references, garbage collection, and JVM behavior.
- **Concurrency:** Study threads, synchronization, the Java Memory Model, concurrent collections, executors, locks, and asynchronous programming.
- **Modern Java:** Learn language and API improvements across modern Java releases.
- **Data Structures and Algorithms:** Implement fundamental data structures, algorithms, and problem-solving techniques in Java.
- **Design and Software Engineering:** Explore design patterns, clean code, unit testing, and maintainable application architecture.
- **Practical Mastery:** Reinforce theoretical knowledge through experiments, exercises, debugging, and implementation from first principles.

## Repository Contents

The code in this repository comes from several complementary sources:

- **Learning examples:** Code examples studied or adapted from books, video tutorials, and online courses.
- **Personal experiments:** Small, focused programs created to investigate Java language behavior, API design, JVM internals, performance, and concurrency.
- **Exercises and implementations:** Solutions written independently to reinforce concepts and assess my understanding.
- **Unit tests:** Automated tests used to verify behavior and explore edge cases.
- **Reference implementations:** Selected implementations retained for future revision and technical interview preparation.

Examples based on external resources will include attribution where practical. Adapted examples may be modified for clarity, additional test coverage, or further experimentation. Original implementations and observations will be identified where appropriate.

The purpose is not simply to collect code, but to understand *why it works, how it behaves, and where its limitations lie*.

## Learning Resources

The following books and video resources guide the learning process. The list will evolve as new resources are introduced.

### Books

| Book | Primary focus |
|---|---|
| *Core Java, Volume I: Fundamentals*, 12th Edition — Cay S. Horstmann | Java language fundamentals, object-oriented programming, APIs, and core libraries |
| *Core Java*, 13th Edition — Cay S. Horstmann | Planned future reference for newer Java features and updated material |
| *Java Generics and Collections*, 2nd Edition | Generics, collection interfaces, implementations, and API design |
| *Effective Java*, 3rd Edition — Joshua Bloch | Best practices, API design, object creation, generics, and robust Java programming |
| *Java Concurrency in Practice* — Brian Goetz et al. | Thread safety, synchronization, concurrency design, and the Java Memory Model |
| *Functional Programming in Java* | Functional programming concepts and their application in Java |
| *Modern Java* | Modern language features, APIs, and contemporary Java development |
| *The Well-Grounded Java Developer*, 2nd Edition | Modern Java development, JVM concepts, and the wider Java ecosystem |
| *Head First Design Patterns* | Object-oriented design principles and reusable design patterns |
| *Practical Unit Testing with JUnit and Mockito*, 2nd Edition | Unit testing, test design, JUnit, and Mockito |

Book-specific examples and exercises will be organized by concept rather than by book, allowing related ideas from different sources to be studied together.

### Video Resources

| Resource | Primary focus |
|---|---|
| [Coder Army — Java Playlist](https://www.youtube.com/watch?v=LBqE4YOvhyc&list=PLQEaRBV9gAFsR15tNo2QLF9d2qc-c018p) | Core Java concepts and practical demonstrations |
| Jakob Jenkov's Java video playlists | Java fundamentals, concurrency, JVM internals, and advanced topics |
| [Kunal Kushwaha — Java + DSA + Interview Preparation Course](https://www.youtube.com/playlist?list=PL9gnSGHSqcnr_DxHsP7AW9ftq0AtAyYqJ) | Java programming, data structures and algorithms, and interview preparation |
| William Fiset's video playlists | Data structures, algorithms, and problem-solving techniques |
| Udemy courses | Supplementary learning resources for specific topics as needed |

Additional courses, articles, official documentation, and other reference materials may be added as the learning journey progresses.

## Repository Structure

The repository uses a Maven project structure and organizes source code by chapter and concept.

```text
java-mastery/
├── README.md
├── .gitignore
├── pom.xml
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── javamastery/
    │               ├── ch01_fundamentals/
    │               ├── ch02_oop/
    │               ├── ch03_strings_arrays/
    │               ├── ch04_exceptions/
    │               ├── ch05_generics/
    │               ├── ch06_collections/
    │               ├── ch07_functional/
    │               ├── ch08_io_nio/
    │               ├── ch09_jvm/
    │               ├── ch10_multithreading/
    │               ├── ch11_concurrency/
    │               ├── ch12_modern_java/
    │               ├── ch13_dsa/
    │               └── ch14_design_patterns/
    └── test/
        └── java/
            └── com/
                └── javamastery/
                    └── ...
```

The chapter list is illustrative and will expand as additional topics are introduced.

### Organization Principles

- **Chapters** represent broad learning areas and preserve a consistent learning sequence.
- **Concept packages** group related Java topics, such as `datatypes`, `arrays`, `collections`, and `concurrency`.
- **Examples** contain code studied from books, tutorials, and courses.
- **Exercises** contain practice solutions and implementations developed to reinforce understanding.
- **Experiments** contain exploratory programs used to investigate language behavior, APIs, runtime characteristics, and edge cases.
- **Tests** contain automated checks for implementations where testing is useful.

Packages use a chapter-oriented naming convention, for example:

```java
package com.javamastery.ch01_fundamentals.datatypes.examples;
```

This organization keeps the repository focused on concepts rather than tied to any single instructor or learning resource.

## Development Environment

The initial development environment is:

- **Language:** Java
- **JDK:** Eclipse Temurin 21
- **IDE:** IntelliJ IDEA
- **Build tool:** Apache Maven
- **Testing:** JUnit 5, with Mockito introduced as needed
- **Version control:** Git and GitHub

The project will initially target Java 21. The target version may evolve as newer Java releases and language features are explored.

## Learning Approach

The repository follows a practice-oriented learning approach:

1. Study a concept using one or more books, courses, or video resources.
2. Implement the examples and understand the underlying behavior.
3. Write additional experiments to investigate edge cases and internal mechanics.
4. Solve related exercises independently before referring to solutions.
5. Add unit tests where they help verify correctness or clarify expected behavior.
6. Refactor code and document important observations.
7. Commit meaningful changes to GitHub to maintain a history of progress.

Where possible, examples will be written and tested independently instead of relying exclusively on copying or executing code from external resources.

## Goals for the Long Term

This repository is intended to become a dependable personal reference for:

- Core Java and advanced language features.
- JVM architecture, memory management, and garbage collection.
- Multithreading and concurrency.
- Collections, generics, and functional programming.
- Data structures, algorithms, and technical interview preparation.
- Design patterns, testing, and software engineering practices.
- Further exploration of Java technologies as learning progresses.

## Attribution and Disclaimer

This is an independent educational repository. References to books, courses, instructors, and other learning materials acknowledge the sources that inform the learning process. Their inclusion does not imply endorsement or affiliation.

Copyright in original source material remains with its respective authors and publishers. Where examples are adapted from external resources, attribution will be provided when practical, and substantial reproduced material will be avoided.

---

**Learning by understanding, experimenting, implementing, and testing — one concept at a time.**