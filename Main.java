package hospital;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Mini Hospital Emergency Management System
 * Console-driven demo tying together:
 *  - PatientBST      (patient records)
 *  - EmergencyQueue   (waiting list)
 *  - TreatmentStack   (completed treatment history)
 *  - VisitList        (per-patient visit history, singly linked list)
 */
public class Main {
    private static PatientBST patientBST = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();
    private static Scanner scanner = new Scanner(System.in);
    private static int visitIdCounter = 1;

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> searchPatient();
                case 3 -> deletePatient();
                case 4 -> patientBST.displayInOrder();
                case 5 -> addToEmergencyQueue();
                case 6 -> treatNextPatient();
                case 7 -> emergencyQueue.displayQueue();
                case 8 -> treatmentStack.displayRecords();
                case 9 -> addVisitToPatient();
                case 10 -> viewPatientVisitHistory();
                case 0 -> System.out.println("Exiting system. Goodbye!");
                default -> System.out.println("Invalid choice, try again.");
            }
            System.out.println();
        } while (choice != 0);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("===== Mini Hospital Emergency Management System =====");
        System.out.println("1.  Register new patient (BST insert)");
        System.out.println("2.  Search patient by ID (BST search)");
        System.out.println("3.  Delete patient by ID (BST delete)");
        System.out.println("4.  Display all patients in order (BST in-order traversal)");
        System.out.println("5.  Add patient to emergency queue (enqueue)");
        System.out.println("6.  Treat next patient (dequeue + push to treatment stack)");
        System.out.println("7.  Display emergency queue");
        System.out.println("8.  Display treatment history (stack)");
        System.out.println("9.  Add visit to patient's history (linked list)");
        System.out.println("10. View a patient's visit history");
        System.out.println("0.  Exit");
    }

    // ---------- BST operations ----------

    private static void registerPatient() {
        int id = readInt("Patient ID: ");
        if (patientBST.search(id) != null) {
            System.out.println("A patient with this ID already exists.");
            return;
        }
        String name = readString("Name: ");
        int age = readInt("Age: ");
        String contact = readString("Contact number: ");
        String condition = readString("Medical condition: ");

        Patient patient = new Patient(id, name, age, contact, condition);
        patientBST.insert(patient);
        System.out.println("Patient registered successfully.");
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient found = patientBST.search(id);
        if (found == null) {
            System.out.println("No patient found with ID " + id);
        } else {
            System.out.println("Found: " + found);
        }
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        patientBST.delete(id);
    }

    // ---------- Queue operations ----------

    private static void addToEmergencyQueue() {
        int id = readInt("Enter Patient ID to add to queue: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("Patient not found. Register the patient first.");
            return;
        }
        emergencyQueue.enqueue(patient);
    }

    private static void treatNextPatient() {
        Patient patient = emergencyQueue.dequeue();
        if (patient == null) {
            return; // dequeue already printed the empty-queue message
        }
        String treatmentGiven = readString("Enter treatment given to " + patient.name + ": ");
        TreatmentRecord record = new TreatmentRecord(
                patient.patientId,
                patient.name,
                treatmentGiven,
                LocalDateTime.now().toString()
        );
        treatmentStack.push(record);
    }

    // ---------- Visit history (linked list) operations ----------

    private static void addVisitToPatient() {
        int id = readInt("Enter Patient ID to add a visit for: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        String doctor = readString("Doctor name: ");
        String diagnosis = readString("Diagnosis: ");
        String treatment = readString("Treatment: ");

        Visit visit = new Visit(visitIdCounter++, LocalDate.now().toString(), doctor, diagnosis, treatment);
        patient.visitHistory.addVisit(visit);
        System.out.println("Visit added to " + patient.name + "'s history.");
    }

    private static void viewPatientVisitHistory() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        System.out.println("Visit history for " + patient.name + ":");
        patient.visitHistory.displayVisits();
    }

    // ---------- Input helpers ----------

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        return value;
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
