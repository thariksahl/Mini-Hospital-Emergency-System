package hospital;

/**
 * Represents a completed treatment record, pushed onto the TreatmentStack
 * once a patient's emergency treatment is finished.
 */
public class TreatmentRecord {
    int patientId;
    String patientName;
    String treatmentGiven;
    String completionTime;

    public TreatmentRecord(int patientId, String patientName, String treatmentGiven, String completionTime) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.treatmentGiven = treatmentGiven;
        this.completionTime = completionTime;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId +
               " | Name: " + patientName +
               " | Treatment: " + treatmentGiven +
               " | Completed: " + completionTime;
    }
}
