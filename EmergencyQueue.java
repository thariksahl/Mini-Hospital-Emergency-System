package hospital;

import java.util.LinkedList;

/**
 * Queue (FIFO) managing patients waiting for emergency treatment.
 * Built on java.util.LinkedList, used strictly as a queue via
 * offer()/poll() to preserve First-In-First-Out order.
 */
public class EmergencyQueue {
    private LinkedList<Patient> queue;

    public EmergencyQueue() {
        this.queue = new LinkedList<>();
    }

    // Enqueue - add a patient to the back of the waiting queue
    public void enqueue(Patient patient) {
        queue.offer(patient);
        System.out.println("Patient " + patient.patientId + " (" + patient.name + ") added to emergency queue.");
    }

    // Dequeue - remove and return the next patient for treatment (front of queue)
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patient to treat.");
            return null;
        }
        Patient next = queue.poll();
        System.out.println("Patient " + next.patientId + " (" + next.name + ") is now being treated.");
        return next;
    }

    // Display all patients currently waiting, in queue order
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No patients currently waiting.");
            return;
        }
        System.out.println("Patients waiting (front to back):");
        int position = 1;
        for (Patient p : queue) {
            System.out.println("  " + position + ". " + p);
            position++;
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
}
