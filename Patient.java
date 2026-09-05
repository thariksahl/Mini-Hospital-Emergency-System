package hospital;

/**
 * Represents a patient record. This is the data stored at each BST node
 * (keyed by patientId), and it also owns a VisitList (singly linked list)
 * holding that patient's previous visits.
 */
public class Patient {
    int patientId;
    String name;
    int age;
    String contactNumber;
    String medicalCondition;
    VisitList visitHistory;

    public Patient(int patientId, String name, int age, String contactNumber, String medicalCondition) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.medicalCondition = medicalCondition;
        this.visitHistory = new VisitList();
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId +
               " | Name: " + name +
               " | Age: " + age +
               " | Contact: " + contactNumber +
               " | Condition: " + medicalCondition;
    }
}
