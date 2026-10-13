# Data Structures and Algorithms (DSA)

This package contains selected data structure implementations and problem-solving exercises developed as part of my Java mastery and technical interview preparation.

The immediate objective is to understand the fundamentals of data structures, explore how they relate to the Java Collections Framework, and apply them to common data structure and algorithm (DSA) interview problems.

This is a deliberately limited scope. Advanced data structures and algorithm theory will be explored separately later.

## Objectives

- Understand the internal workings of fundamental data structures.
- Strengthen Java fundamentals through practical implementation.
- Understand the design and behavior of the Java Collections Framework.
- Analyze time and space complexity.
- Develop problem-solving skills through LeetCode and similar platforms.
- Recognize common problem-solving patterns and choose appropriate data structures.
- Prepare for senior Java developer and technical interview discussions.

## Package Organization

Implementations are organized by topic and learning progression.

```text
com.javamastery/
├── ch00_data_structures/
│   ├── README.md
│   └── implementation/
│       ├── ch01_arrays/
│       │   ├── DynamicArray.java
│       │   └── StaticArrayOperations.java
│       │
│       ├── ch02_linkedlist/
│       │   ├── SinglyLinkedList.java
│       │   └── DoublyLinkedList.java
│       │
│       ├── ch03_stack/
│       │   └── ArrayStack.java
│       │
│       ├── ch04_queue/
│       │   ├── CircularArrayQueue.java
│       │   └── CircularArrayDeque.java
│       │
│       ├── ch05_hashing/
│       │   ├── HashTable.java
│       │   └── HashSetLite.java
│       │
│       ├── ch06_trees/
│       │   ├── BinaryTree.java
│       │   ├── BinarySearchTree.java
│       │   └── TreeTraversals.java
│       │
│       ├── ch07_heap/
│       │   └── BinaryHeap.java
│       │
│       ├── ch08_disjoint_set/
│       │   └── DisjointSetUnion.java
│       │
│       ├── ch09_sorting_searching/
│       │   ├── searching/
│       │   │   ├── LinearSearch.java
│       │   │   └── BinarySearch.java
│       │   └── sorting/
│       │       ├── BubbleSort.java
│       │       ├── SelectionSort.java
│       │       ├── InsertionSort.java
│       │       ├── MergeSort.java
│       │       ├── QuickSort.java
│       │       ├── CountingSort.java
│       │       └── RadixSort.java
│       │
│       ├── ch10_recursion/
│       │   ├── RecursionBasics.java
│       │   ├── Factorial.java
│       │   ├── Fibonacci.java
│       │   ├── BinarySearchRecursive.java
│       │   ├── MergeSortRecursive.java
│       │   └── TowerOfHanoi.java
│       │
│       ├── ch11_backtracking/
│       │   ├── SubsetGenerator.java
│       │   ├── PermutationGenerator.java
│       │   └── NQueens.java
│       │
│       ├── ch12_greedy/
│       │   ├── ActivitySelection.java
│       │   └── HuffmanCoding.java
│       │
│       ├── ch13_graphs/
│       │   ├── representation/
│       │   │   ├── Graph.java
│       │   │   ├── AdjacencyListGraph.java
│       │   │   └── AdjacencyMatrixGraph.java
│       │   ├── traversal/
│       │   │   ├── BreadthFirstSearch.java
│       │   │   └── DepthFirstSearch.java
│       │   ├── mst/
│       │   │   ├── KruskalMST.java
│       │   │   └── PrimMST.java
│       │   └── shortestpath/
│       │       ├── Dijkstra.java
│       │       └── BellmanFord.java
│       │
│       ├── ch14_dynamic_programming/
│       │   ├── FibonacciMemoization.java
│       │   ├── FibonacciTabulation.java
│       │   ├── FibonacciSpaceOptimized.java
│       │   └── Knapsack01.java
│       │
│       ├── ch15_strings/
│       │   ├── StringOperations.java
│       │   ├── NaivePatternSearch.java
│       │   ├── RabinKarp.java
│       │   └── KMP.java
│       │
│       ├── ch16_math_bit_manipulation/
│       │   ├── GCD.java
│       │   ├── PrimeChecker.java
│       │   ├── SieveOfEratosthenes.java
│       │   └── BitManipulation.java
│       │
│       └── ch17_advanced_algorithms/
│           └── README.md
│
├── ch01_fundamentals/
└── ch02_oop/
```

The directory structure is a blueprint. Packages and classes will be added as the corresponding topics are studied.

## Learning Sequence

| Package           | Focus                                 |
|-------------------|---------------------------------------|
| `ch01_arrays`     | `Array operations and dynamic arrays` |
| `ch02_linkedlist` | `Singly and doubly linked lists`      |
| `ch03_stack` | `Stack operations and LIFO behavior` |
| `ch04_queue` | `Queues, circular buffers, and deques` |
| `ch05_hashing` | `Hash tables, hash functions, collision handling, and sets` |
| `ch06_trees` | `Binary trees, binary search trees, and tree traversals` |
| `ch07_heap` | `Binary heaps and priority-queue fundamentals` |
| `ch08_disjoint_set` | `Union-find, path compression, and union by rank or size` |
| `ch09_sorting_searching` | `Searching and comparison/non-comparison sorting algorithms` |
| `ch10_recursion` | `Base cases, recursive calls, and divide-and-conquer` |
| `ch11_backtracking` | `State-space exploration, subsets, permutations, and N-Queens` |
| `ch12_greedy` | `Greedy choices, activity selection, and Huffman coding` |
| `ch13_graphs` | `Representations, BFS, DFS, minimum spanning trees, and shortest paths` |
| `ch14_dynamic_programming` | `Memoization, tabulation, and optimization of overlapping subproblems` |
| `ch15_strings` | `String operations and pattern matching` |
| `ch16_math_bit_manipulation` | `Number theory and bitwise techniques` |
| `ch17_advanced_algorithms` | `Reserved for advanced topics introduced later` |

The third phase is problem-driven. These structures should be implemented when the relevant concepts are encountered rather than treated as prerequisites for beginning problem-solving.

## Implementation Guidelines

For each data structure:

1. **Understand the concept:** Study its properties, invariants, operations, use cases, and limitations.
2. **Define the API:** Identify the essential operations and their expected behavior.
3. **Implement from scratch:** Use Java arrays, references, generics, and object-oriented principles as appropriate.
4. **Handle edge cases:** Test empty structures, single elements, duplicates, invalid indices, and boundary conditions.
5. **Analyze complexity:** Record the expected time and auxiliary space complexity of the important operations.
6. **Write automated tests:** Verify correctness with JUnit.
7. **Reimplement from memory:** Recreate the essential implementation without consulting the original code.
8. **Compare with the JDK:** Study the corresponding Java collection's API, behavior, complexity guarantees, and design trade-offs.
9. **Apply the concept:** Solve relevant problems independently.

The purpose is to understand the underlying principles, not to recreate the entire Java Collections Framework.

## Java Collections Framework Study

Implementations should be accompanied by a study of the corresponding Java APIs.

| Concept | Topics to understand |
|---|---|
| `List` | Ordering, indexed access, insertion and removal costs |
| `ArrayList` | Backing array, capacity growth, amortized complexity |
| `LinkedList` | Node references, iterator-based operations, trade-offs |
| `Deque` | Stack and queue operations |
| `ArrayDeque` | Circular-array behavior and end operations |
| `Map` | Key-value relationships, uniqueness and lookup |
| `HashMap` | Hashing, `equals()`, `hashCode()`, collisions and resizing |
| `Set` | Uniqueness and membership |
| `HashSet` | Hash-based membership and duplicate handling |
| `PriorityQueue` | Heap ordering, comparators and priority-based retrieval |
| `TreeMap` / `TreeSet` | Sorted ordering and navigational operations |
| `Iterator` | Traversal and iterator contracts |
| `Comparable` / `Comparator` | Natural ordering and custom ordering |
| Generics | Type safety, generic APIs, bounded type parameters and type erasure |

Where useful, inspect the JDK source code to understand implementation choices. The objective is to learn both the public contracts and the underlying implementation techniques.

## Problem-Solving Roadmap

Problem-solving begins before every planned data structure has been implemented.

Progress through the following patterns as their prerequisites are covered:

1. Arrays and strings
2. Hash maps and sets
3. Two pointers
4. Sliding windows
5. Stack and deque problems
6. Binary search
7. Linked-list problems
8. Binary trees and tree traversal
9. Heaps and top-K problems
10. BFS and DFS
11. Intervals, prefix sums, and other common patterns
12. Union-Find and Trie problems when relevant

For each pattern, solve a small set of problems independently before increasing difficulty. Review incorrect approaches and complexity, and revisit problems later without consulting previous solutions.

## Testing

Automated tests belong under the corresponding package in `src/test/java`.

Example:

```text
src/main/java/
└── com/javamastery/ch00_data_structures/
    └── arrays/
        └── DynamicArray.java

src/test/java/
└── com/javamastery/ch00_data_structures/
    └── arrays/
        └── DynamicArrayTest.java
```

Tests should cover:

- Normal operations
- Empty and single-element cases
- Boundary conditions
- Duplicate elements, where applicable
- Invalid input and exception behavior
- Resizing, collision handling, or ordering, where applicable
- Correctness of operations after repeated insertions and removals

JUnit is used for automated testing.

## Complexity Analysis

Document the expected complexity of the primary operations for each implementation.

For example, a dynamic array typically has the following complexity:

| Operation | Expected time complexity |
|---|---|
| Indexed access | O(1) |
| Update by index | O(1) |
| Append | O(1) amortized |
| Insert at an arbitrary index | O(n) |
| Remove at an arbitrary index | O(n) |
| Search by value | O(n) |

Complexities depend on the operation and implementation. State assumptions explicitly when documenting them.

## Scope and Deferred Topics

This package prioritizes practical interview preparation and Java Collections Framework understanding.

The following topics are intentionally deferred:

- AVL trees and red-black tree implementations
- B-trees and B+ trees
- Segment trees, Fenwick trees, and sparse tables
- Suffix trees and advanced string structures
- Open-addressing hash tables
- Advanced graph algorithms and network flow
- Persistent and specialized spatial data structures
- Formal algorithm analysis and advanced complexity theory

These topics may be studied later in a separate, dedicated DSA repository using William Fiset, CLRS, MIT OpenCourseWare, Stanford CS161, and other relevant resources.

## Completion Criteria

An implementation is considered complete when:

- Its essential operations work correctly.
- Important edge cases are tested.
- Its time and space complexity are understood.
- Its relationship to the corresponding Java API is documented.
- It can be reconstructed independently.
- The structure has been applied to relevant problem-solving exercises.

Not every structure must be completed before interview problem-solving begins. Implement additional structures when they support a current learning objective or solve a specific problem.

## Guiding Principle

**Learn the concept, implement the essentials, understand the JDK equivalent, and solve problems.**

The goal of this package is practical Java mastery and interview readiness—not the exhaustive implementation of every known data structure.
