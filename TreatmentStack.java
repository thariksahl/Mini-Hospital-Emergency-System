package hospital;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Stack (LIFO) storing completed treatment records.
 * Built on java.util.Stack.
 */
public class TreatmentStack {
    private Stack<TreatmentRecord> stack;

    public TreatmentStack() {
        this.stack = new Stack<>();
    }

    // Push - add a completed treatment record
    public void push(TreatmentRecord record) {
        stack.push(record);
        System.out.println("Treatment record pushed: " + record);
    }

    // Pop - remove and return the most recently completed treatment record
    public TreatmentRecord pop() {
        try {
            return stack.pop();
        } catch (EmptyStackException e) {
            System.out.println("Treatment history is empty. Nothing to pop.");
            return null;
        }
    }

    // Display all treatment records, most recent first
    public void displayRecords() {
        if (isEmpty()) {
            System.out.println("No treatment records yet.");
            return;
        }
        System.out.println("Treatment history (most recent first):");
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println("  " + stack.get(i));
        }
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }
}
