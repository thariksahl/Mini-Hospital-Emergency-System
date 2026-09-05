package hospital;

/**
 * Singly Linked List implementation for a patient's visit history.
 * Supports: add, remove (by visitId), search (by visitId), display.
 */
public class VisitList {
    private Visit head;

    public VisitList() {
        this.head = null;
    }

    // Add a new visit to the end of the list
    public void addVisit(Visit newVisit) {
        if (head == null) {
            head = newVisit;
            return;
        }
        Visit current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newVisit;
    }

    // Remove a visit by visitId. Returns true if removed, false if not found.
    public boolean removeVisit(int visitId) {
        if (head == null) {
            System.out.println("No visit history to remove from.");
            return false;
        }

        if (head.visitId == visitId) {
            head = head.next;
            return true;
        }

        Visit current = head;
        while (current.next != null) {
            if (current.next.visitId == visitId) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }

        System.out.println("Visit ID " + visitId + " not found in history.");
        return false;
    }

    // Search for a visit by visitId. Returns the Visit or null if not found.
    public Visit searchVisit(int visitId) {
        Visit current = head;
        while (current != null) {
            if (current.visitId == visitId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Display all visits in this patient's history
    public void displayVisits() {
        if (head == null) {
            System.out.println("  No visit history for this patient.");
            return;
        }
        Visit current = head;
        while (current != null) {
            System.out.println("  " + current);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}
