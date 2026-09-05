# Mini Hospital Emergency Management System

A console-based Java application simulating patient registration, emergency
treatment flow, and visit history for a hospital, built for CIT300 (Data
Structures and Algorithms).

## Data Structures Used

| Requirement | Data Structure | Class |
|---|---|---|
| Patient Records | Binary Search Tree, keyed by Patient ID | `PatientBST` |
| Emergency Patient Queue | Queue (FIFO) | `EmergencyQueue` |
| Treatment History | Stack (LIFO) | `TreatmentStack` |
| Patient Visit History | Singly Linked List (one per patient) | `VisitList` |

## Project Structure

```
src/hospital/
├── Patient.java          # Patient model (BST node data)
├── PatientBST.java        # BST: insert, search, delete, in-order traversal
├── EmergencyQueue.java     # Queue: enqueue, dequeue, display
├── TreatmentRecord.java    # Treatment record model (stack node data)
├── TreatmentStack.java     # Stack: push, pop, display
├── Visit.java              # Visit model (linked list node)
├── VisitList.java          # Singly linked list: add, remove, search, display
└── Main.java                # Console menu tying everything together
```

## How It Works

1. **Register a patient** → inserted into the `PatientBST` keyed by Patient ID.
2. **Add to emergency queue** → an existing patient is enqueued for treatment.
3. **Treat next patient** → dequeues the patient at the front of the queue and
   pushes a `TreatmentRecord` onto the `TreatmentStack`.
4. **Add a visit** → appends a `Visit` node to that patient's own `VisitList`.
5. Patients, the queue, treatment history, and any patient's visit history
   can all be displayed independently from the menu.

## How to Compile and Run

```bash
javac -d out src/hospital/*.java
java -cp out hospital.Main
```

## Design Decisions

- Each `Patient` object owns its own `VisitList`, since the visit history
  belongs to a specific patient rather than being a single global list.
- BST deletion for a node with two children replaces the node's data with
  its in-order successor (smallest value in the right subtree), which is
  the standard approach for keeping the BST property intact.
- `EmergencyQueue` and `TreatmentStack` are thin wrappers around
  `java.util.LinkedList` / `java.util.Stack`, used strictly through
  queue/stack-only operations (`offer`/`poll`, `push`/`pop`) to preserve
  FIFO/LIFO semantics, while the BST and linked list were built manually
  since those are the structures being assessed.

## Author

Sahl — CIT300 Individual Mid Assignment
