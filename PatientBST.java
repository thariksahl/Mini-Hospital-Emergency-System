package hospital;

/**
 * Binary Search Tree storing Patient records, keyed by patientId.
 * Supports: insert, search, delete, in-order traversal.
 */
public class PatientBST {

    // Internal node class - kept private since only PatientBST needs it
    private class Node {
        Patient patient;
        Node left, right;

        Node(Patient patient) {
            this.patient = patient;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public PatientBST() {
        this.root = null;
    }

    // ---------- INSERT ----------
    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private Node insertRec(Node node, Patient patient) {
        if (node == null) {
            return new Node(patient);
        }
        if (patient.patientId < node.patient.patientId) {
            node.left = insertRec(node.left, patient);
        } else if (patient.patientId > node.patient.patientId) {
            node.right = insertRec(node.right, patient);
        } else {
            System.out.println("Patient ID " + patient.patientId + " already exists. Insert ignored.");
        }
        return node;
    }

    // ---------- SEARCH ----------
    public Patient search(int patientId) {
        Node result = searchRec(root, patientId);
        return (result != null) ? result.patient : null;
    }

    private Node searchRec(Node node, int patientId) {
        if (node == null || node.patient.patientId == patientId) {
            return node;
        }
        if (patientId < node.patient.patientId) {
            return searchRec(node.left, patientId);
        }
        return searchRec(node.right, patientId);
    }

    // ---------- DELETE ----------
    public void delete(int patientId) {
        root = deleteRec(root, patientId);
    }

    private Node deleteRec(Node node, int patientId) {
        if (node == null) {
            System.out.println("Patient ID " + patientId + " not found. Nothing deleted.");
            return null;
        }

        if (patientId < node.patient.patientId) {
            node.left = deleteRec(node.left, patientId);
        } else if (patientId > node.patient.patientId) {
            node.right = deleteRec(node.right, patientId);
        } else {
            // Found the node to delete

            // Case 1: no children
            if (node.left == null && node.right == null) {
                return null;
            }
            // Case 2: one child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // Case 3: two children - replace with in-order successor
            // (smallest value in the right subtree)
            Node successor = findMin(node.right);
            node.patient = successor.patient;
            node.right = deleteRec(node.right, successor.patient.patientId);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // ---------- IN-ORDER TRAVERSAL ----------
    // Displays patients in ascending order of Patient ID
    public void displayInOrder() {
        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }
        inOrderRec(root);
    }

    private void inOrderRec(Node node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.println(node.patient);
            inOrderRec(node.right);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }
}
