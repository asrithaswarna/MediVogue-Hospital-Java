import java.util.Scanner;

// ==================== CUSTOM DATA STRUCTURES ====================

// ==================== ENTITY CLASSES ====================
class Patient {
    int id;
    String name;
    int age;
    String disease;
    
    Patient(int id, String name, int age, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }
}

class Doctor {
    int id;
    String name;
    String specialization;
    
    Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }
}

class Appointment {
    int id;
    String patientName;
    String doctorName;
    String time;
    String date;
    
    Appointment(int id, String patientName, String doctorName, String date, String time) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
    }
}

class Bill {
    int patientId;
    String patientName;
    double consultationFee;
    double medicineCharge;
    double total;
    
    Bill(int patientId, String patientName, double consultationFee, double medicineCharge) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.consultationFee = consultationFee;
        this.medicineCharge = medicineCharge;
        this.total = consultationFee + medicineCharge;
    }
}

// ==================== HASH TABLE IMPLEMENTATION (CO4) ====================
class HashNode {
    int key;
    String name;
    HashNode next;
    
    HashNode(int key, String name) {
        this.key = key;
        this.name = name;
        this.next = null;
    }
}

class HashTable {
    private static final int TABLE_SIZE = 50;
    private HashNode[] buckets;
    private int size;
    
    HashTable() {
        buckets = new HashNode[TABLE_SIZE];
        size = 0;
    }
    
    // Hash Function: h(key) = key % TABLE_SIZE
    private int hashFunction(int key) {
        return key % TABLE_SIZE;
    }
    
    // Insert key-value pair (Separate Chaining)
    void insert(int key, String name) {
        int index = hashFunction(key);
        HashNode newNode = new HashNode(key, name);
        
        if (buckets[index] == null) {
            buckets[index] = newNode;
            size++;
        } else {
            // Check for duplicate
            HashNode current = buckets[index];
            HashNode prev = null;
            
            while (current != null) {
                if (current.key == key) {
                    System.out.println("Duplicate ID detected: " + key);
                    return;
                }
                prev = current;
                current = current.next;
            }
            // Add at end of chain
            prev.next = newNode;
            size++;
        }
    }
    
    // Search by key
    HashNode search(int key) {
        int index = hashFunction(key);
        HashNode current = buckets[index];
        
        while (current != null) {
            if (current.key == key) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
    
    // Delete by key
    boolean delete(int key) {
        int index = hashFunction(key);
        HashNode current = buckets[index];
        HashNode prev = null;
        
        while (current != null) {
            if (current.key == key) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }
    
    // Display hash table contents
    void display() {
        System.out.println("\n--- Hash Table Contents ---");
        boolean empty = true;
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (buckets[i] != null) {
                empty = false;
                System.out.print("Bucket " + i + ": ");
                HashNode current = buckets[i];
                while (current != null) {
                    System.out.print("[" + current.key + ":" + current.name + "] ");
                    current = current.next;
                }
                System.out.println();
            }
        }
        if (empty) {
            System.out.println("Hash table is empty");
        }
        System.out.println("Total entries: " + size);
    }
    
    // Check if key exists
    boolean containsKey(int key) {
        return search(key) != null;
    }
    
    int getSize() {
        return size;
    }
}

// ==================== CUSTOM LINKED LIST FOR PATIENTS ====================
class PatientNode {
    Patient data;
    PatientNode next;
    
    PatientNode(Patient data) {
        this.data = data;
        this.next = null;
    }
}

class PatientLinkedList {
    PatientNode head;
    int size;
    
    PatientLinkedList() {
        head = null;
        size = 0;
    }
    
    // Add patient to linked list
    void add(Patient patient) {
        PatientNode newNode = new PatientNode(patient);
        if (head == null) {
            head = newNode;
        } else {
            PatientNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }
    
    // Display all patients
    void display() {
        if (head == null) {
            System.out.println("No patients found!");
            return;
        }
        PatientNode temp = head;
        while (temp != null) {
            System.out.println("ID: " + temp.data.id + 
                             ", Name: " + temp.data.name + 
                             ", Age: " + temp.data.age + 
                             ", Disease: " + temp.data.disease);
            temp = temp.next;
        }
    }
    
    // Get patient at index
    Patient get(int index) {
        if (index < 0 || index >= size) return null;
        PatientNode temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }
    
    // Get size
    int size() {
        return size;
    }
    
    // Check if empty
    boolean isEmpty() {
        return head == null;
    }
}

// ==================== CUSTOM LINKED LIST FOR DOCTORS ====================
class DoctorNode {
    Doctor data;
    DoctorNode next;
    
    DoctorNode(Doctor data) {
        this.data = data;
        this.next = null;
    }
}

class DoctorLinkedList {
    DoctorNode head;
    int size;
    
    DoctorLinkedList() {
        head = null;
        size = 0;
    }
    
    void add(Doctor doctor) {
        DoctorNode newNode = new DoctorNode(doctor);
        if (head == null) {
            head = newNode;
        } else {
            DoctorNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }
    
    void display() {
        if (head == null) {
            System.out.println("No doctors found!");
            return;
        }
        DoctorNode temp = head;
        while (temp != null) {
            System.out.println("ID: " + temp.data.id + 
                             ", Name: " + temp.data.name + 
                             ", Specialization: " + temp.data.specialization);
            temp = temp.next;
        }
    }
    
    Doctor get(int index) {
        if (index < 0 || index >= size) return null;
        DoctorNode temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }
    
    int size() {
        return size;
    }
}

// ==================== CUSTOM STACK (For Undo Operations) ====================
class Stack {
    private static final int MAX = 100;
    private String[] arr;
    private int top;
    
    Stack() {
        arr = new String[MAX];
        top = -1;
    }
    
    void push(String operation) {
        if (top >= MAX - 1) {
            System.out.println("Stack Overflow!");
            return;
        }
        arr[++top] = operation;
    }
    
    String pop() {
        if (top < 0) {
            return null;
        }
        return arr[top--];
    }
    
    boolean isEmpty() {
        return top < 0;
    }
    
    void display() {
        if (isEmpty()) {
            System.out.println("No operations in history");
            return;
        }
        System.out.println("Recent Operations:");
        for (int i = top; i >= 0; i--) {
            System.out.println((top - i + 1) + ". " + arr[i]);
        }
    }
}

// ==================== CUSTOM QUEUE (For Appointment Scheduling) ====================
class Queue {
    private static final int MAX = 100;
    private Appointment[] arr;
    private int front;
    private int rear;
    private int size;
    
    Queue() {
        arr = new Appointment[MAX];
        front = 0;
        rear = -1;
        size = 0;
    }
    
    void enqueue(Appointment appointment) {
        if (size >= MAX) {
            System.out.println("Queue is full!");
            return;
        }
        rear = (rear + 1) % MAX;
        arr[rear] = appointment;
        size++;
    }
    
    Appointment dequeue() {
        if (isEmpty()) {
            return null;
        }
        Appointment data = arr[front];
        front = (front + 1) % MAX;
        size--;
        return data;
    }
    
    boolean isEmpty() {
        return size == 0;
    }
    
    void display() {
        if (isEmpty()) {
            System.out.println("No pending appointments");
            return;
        }
        System.out.println("Pending Appointments:");
        int count = 0;
        for (int i = 0; i < size; i++) {
            int index = (front + i) % MAX;
            Appointment a = arr[index];
            System.out.println((++count) + ". Patient: " + a.patientName + 
                             " with Dr. " + a.doctorName + 
                             " at " + a.time);
        }
    }
    
    int size() {
        return size;
    }
}

// ==================== MAIN HOSPITAL MANAGEMENT SYSTEM ====================
public class HospitalManagementSystem {
    
    // Data structures
    static PatientLinkedList patients = new PatientLinkedList();
    static DoctorLinkedList doctors = new DoctorLinkedList();
    static Queue appointmentQueue = new Queue();
    static Stack operationStack = new Stack();
    static Bill[] bills = new Bill[100];
    static int billCount = 0;
    
    // Hash Tables for fast lookup and duplicate detection
    static HashTable patientHashTable = new HashTable();
    static HashTable doctorHashTable = new HashTable();
    
    static Scanner sc = new Scanner(System.in);
    static int patientIdCounter = 1;
    static int doctorIdCounter = 1;
    static int appointmentIdCounter = 1;

    // ==================== SORTING ALGORITHMS (Custom Implementation) ====================
    
    // Bubble Sort for Patients by Name
    static void sortPatientsByName() {
        if (patients.size() <= 1) return;
        
        Patient[] tempArray = new Patient[patients.size()];
        for (int i = 0; i < patients.size(); i++) {
            tempArray[i] = patients.get(i);
        }
        
        // Bubble sort
        for (int i = 0; i < tempArray.length - 1; i++) {
            for (int j = 0; j < tempArray.length - i - 1; j++) {
                if (tempArray[j].name.compareTo(tempArray[j + 1].name) > 0) {
                    // Swap
                    Patient temp = tempArray[j];
                    tempArray[j] = tempArray[j + 1];
                    tempArray[j + 1] = temp;
                }
            }
        }
        
        // Rebuild linked list
        patients = new PatientLinkedList();
        for (Patient p : tempArray) {
            patients.add(p);
        }
        
        System.out.println("Patients sorted by name successfully!");
    }
    
    // Selection Sort for Patients by Age
    static void sortPatientsByAge() {
        if (patients.size() <= 1) return;
        
        Patient[] tempArray = new Patient[patients.size()];
        for (int i = 0; i < patients.size(); i++) {
            tempArray[i] = patients.get(i);
        }
        
        // Selection sort
        for (int i = 0; i < tempArray.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < tempArray.length; j++) {
                if (tempArray[j].age < tempArray[minIndex].age) {
                    minIndex = j;
                }
            }
            // Swap
            Patient temp = tempArray[i];
            tempArray[i] = tempArray[minIndex];
            tempArray[minIndex] = temp;
        }
        
        // Rebuild linked list
        patients = new PatientLinkedList();
        for (Patient p : tempArray) {
            patients.add(p);
        }
        
        System.out.println("Patients sorted by age successfully!");
    }
    
    // Insertion Sort for Doctors by Name
    static void sortDoctorsByName() {
        if (doctors.size() <= 1) return;
        
        Doctor[] tempArray = new Doctor[doctors.size()];
        for (int i = 0; i < doctors.size(); i++) {
            tempArray[i] = doctors.get(i);
        }
        
        // Insertion sort
        for (int i = 1; i < tempArray.length; i++) {
            Doctor key = tempArray[i];
            int j = i - 1;
            while (j >= 0 && tempArray[j].name.compareTo(key.name) > 0) {
                tempArray[j + 1] = tempArray[j];
                j--;
            }
            tempArray[j + 1] = key;
        }
        
        // Rebuild linked list
        doctors = new DoctorLinkedList();
        for (Doctor d : tempArray) {
            doctors.add(d);
        }
        
        System.out.println("Doctors sorted by name successfully!");
    }

    // ==================== SEARCHING ALGORITHMS (Custom Implementation) ====================
    
    // Linear Search for Patient by ID
    static Patient linearSearchPatientById(int id) {
        PatientNode temp = patients.head;
        while (temp != null) {
            if (temp.data.id == id) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }
    
    // Linear Search for Patient by Name
    static void linearSearchPatientByName(String name) {
        PatientNode temp = patients.head;
        boolean found = false;
        System.out.println("\n--- Search Results for '" + name + "' ---");
        while (temp != null) {
            if (temp.data.name.toLowerCase().contains(name.toLowerCase())) {
                System.out.println("ID: " + temp.data.id + 
                                 ", Name: " + temp.data.name + 
                                 ", Age: " + temp.data.age + 
                                 ", Disease: " + temp.data.disease);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No patients found with name: " + name);
        }
    }
    
    // Binary Search for Patient by ID (requires sorted array)
    static Patient binarySearchPatientById(int id) {
        // Convert to array and sort by ID first
        Patient[] tempArray = new Patient[patients.size()];
        for (int i = 0; i < patients.size(); i++) {
            tempArray[i] = patients.get(i);
        }
        
        // Sort by ID using bubble sort
        for (int i = 0; i < tempArray.length - 1; i++) {
            for (int j = 0; j < tempArray.length - i - 1; j++) {
                if (tempArray[j].id > tempArray[j + 1].id) {
                    Patient temp = tempArray[j];
                    tempArray[j] = tempArray[j + 1];
                    tempArray[j + 1] = temp;
                }
            }
        }
        
        // Binary search
        int left = 0;
        int right = tempArray.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (tempArray[mid].id == id) {
                return tempArray[mid];
            }
            if (tempArray[mid].id < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    // ==================== HASH TABLE OPERATIONS ====================
    
    // Search patient using hash table (O(1) average)
    static void searchPatientHash(int id) {
        HashNode node = patientHashTable.search(id);
        if (node != null) {
            System.out.println("Found in Hash Table: ID " + node.key + " - " + node.name);
            // Get full details from linked list
            Patient p = linearSearchPatientById(id);
            if (p != null) {
                System.out.println("Full Details: Age " + p.age + ", Disease: " + p.disease);
            }
        } else {
            System.out.println("Patient ID " + id + " not found in hash table!");
        }
    }
    
    // Check for duplicate patient ID before adding
    static boolean isPatientIdDuplicate(int id) {
        return patientHashTable.containsKey(id);
    }
    
    // Check for duplicate doctor ID before adding
    static boolean isDoctorIdDuplicate(int id) {
        return doctorHashTable.containsKey(id);
    }
    
    // Display hash tables
    static void displayHashTables() {
        System.out.println("\n===== PATIENT HASH TABLE =====");
        patientHashTable.display();
        
        System.out.println("\n===== DOCTOR HASH TABLE =====");
        doctorHashTable.display();
    }

    // ==================== CORE FUNCTIONS ====================
    
    static void addPatient() {
        System.out.print("Enter Patient Name: ");
        sc.nextLine();
        String name = sc.nextLine();
        
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        
        System.out.print("Enter Disease: ");
        sc.nextLine();
        String disease = sc.nextLine();
        
        int newId = patientIdCounter;
        
        Patient patient = new Patient(newId, name, age, disease);
        patients.add(patient);
        
        // Also add to hash table for fast lookup
        patientHashTable.insert(newId, name);
        
        // Push to stack for undo
        operationStack.push("Added Patient: " + name + " (ID: " + newId + ")");
        
        System.out.println("Patient added successfully! ID: " + newId);
        patientIdCounter++;
    }

    static void viewPatients() {
        System.out.println("\n--- Patient List (" + patients.size() + " patients) ---");
        patients.display();
    }

    static void addDoctor() {
        System.out.print("Enter Doctor Name: ");
        sc.nextLine();
        String name = sc.nextLine();
        
        System.out.print("Enter Specialization: ");
        String specialization = sc.nextLine();
        
        int newId = doctorIdCounter;
        
        Doctor doctor = new Doctor(newId, name, specialization);
        doctors.add(doctor);
        
        // Add to hash table
        doctorHashTable.insert(newId, name);
        
        operationStack.push("Added Doctor: " + name + " (ID: " + newId + ")");
        
        System.out.println("Doctor added successfully! ID: " + newId);
        doctorIdCounter++;
    }

    static void viewDoctors() {
        System.out.println("\n--- Doctor List (" + doctors.size() + " doctors) ---");
        doctors.display();
    }

    static void bookAppointment() {
        if (patients.size() == 0 || doctors.size() == 0) {
            System.out.println("Need both patients and doctors to book appointment!");
            return;
        }
        
        System.out.print("Enter Patient Name: ");
        sc.nextLine();
        String patientName = sc.nextLine();
        
        System.out.print("Enter Doctor Name: ");
        String doctorName = sc.nextLine();
        
        System.out.print("Enter Date (DD/MM/YYYY): ");
        String date = sc.nextLine();
        
        System.out.print("Enter Time (HH:MM): ");
        String time = sc.nextLine();
        
        Appointment appointment = new Appointment(appointmentIdCounter++, patientName, doctorName, date, time);
        appointmentQueue.enqueue(appointment);
        
        operationStack.push("Booked Appointment: " + patientName + " with Dr. " + doctorName);
        
        System.out.println("Appointment booked successfully! ID: " + (appointmentIdCounter - 1));
    }

    static void processAppointment() {
        Appointment appointment = appointmentQueue.dequeue();
        if (appointment == null) {
            System.out.println("No pending appointments!");
            return;
        }
        
        System.out.println("\n--- Processing Appointment ---");
        System.out.println("Patient: " + appointment.patientName);
        System.out.println("Doctor: " + appointment.doctorName);
        System.out.println("Date: " + appointment.date);
        System.out.println("Time: " + appointment.time);
        System.out.println("Appointment processed successfully!");
    }

    static void viewPendingAppointments() {
        System.out.println("\n--- Pending Appointments (" + appointmentQueue.size() + " appointments) ---");
        appointmentQueue.display();
    }

    static void generateBill() {
        if (patients.size() == 0) {
            System.out.println("No patients to generate bill!");
            return;
        }
        
        System.out.print("Enter Patient ID: ");
        int pid = sc.nextInt();
        
        // Use hash table for quick existence check
        if (!patientHashTable.containsKey(pid)) {
            System.out.println("Patient not found in hash table!");
            return;
        }
        
        Patient patient = linearSearchPatientById(pid);
        if (patient == null) {
            System.out.println("Patient not found in linked list!");
            return;
        }
        
        System.out.print("Consultation Fee: ₹");
        double fee = sc.nextDouble();
        
        System.out.print("Medicine Charges: ₹");
        double medicine = sc.nextDouble();
        
        Bill bill = new Bill(pid, patient.name, fee, medicine);
        bills[billCount++] = bill;
        
        System.out.println("\n===== BILL =====");
        System.out.println("Patient ID: " + pid);
        System.out.println("Patient Name: " + patient.name);
        System.out.println("Consultation Fee: ₹" + fee);
        System.out.println("Medicine Charges: ₹" + medicine);
        System.out.println("Total Amount: ₹" + bill.total);
        
        operationStack.push("Generated Bill for: " + patient.name);
    }

    static void viewBills() {
        if (billCount == 0) {
            System.out.println("No bills generated!");
            return;
        }
        
        System.out.println("\n--- Bill History ---");
        for (int i = 0; i < billCount; i++) {
            Bill b = bills[i];
            System.out.println("Patient: " + b.patientName + 
                             " | Total: ₹" + b.total);
        }
    }

    static void undoLastOperation() {
        String lastOp = operationStack.pop();
        if (lastOp == null) {
            System.out.println("Nothing to undo!");
        } else {
            System.out.println("Undone: " + lastOp);
        }
    }

    static void viewOperationHistory() {
        operationStack.display();
    }

    static void searchPatient() {
        System.out.println("\n--- Search Patient ---");
        System.out.println("1. Linear Search by ID");
        System.out.println("2. Linear Search by Name");
        System.out.println("3. Binary Search by ID");
        System.out.println("4. Hash Table Search by ID (Fast)");
        System.out.print("Enter choice: ");
        
        int choice = sc.nextInt();
        
        switch(choice) {
            case 1:
                System.out.print("Enter Patient ID: ");
                int id1 = sc.nextInt();
                Patient p1 = linearSearchPatientById(id1);
                if (p1 != null) {
                    System.out.println("Found: " + p1.name + ", Age: " + p1.age + ", Disease: " + p1.disease);
                } else {
                    System.out.println("Patient not found!");
                }
                break;
                
            case 2:
                System.out.print("Enter Patient Name: ");
                sc.nextLine();
                String name = sc.nextLine();
                linearSearchPatientByName(name);
                break;
                
            case 3:
                System.out.print("Enter Patient ID: ");
                int id2 = sc.nextInt();
                Patient p2 = binarySearchPatientById(id2);
                if (p2 != null) {
                    System.out.println("Found using Binary Search: " + p2.name);
                } else {
                    System.out.println("Patient not found!");
                }
                break;
                
            case 4:
                System.out.print("Enter Patient ID: ");
                int id3 = sc.nextInt();
                searchPatientHash(id3);
                break;
                
            default:
                System.out.println("Invalid choice!");
        }
    }

    // ==================== MAIN MENU ====================
    public static void main(String[] args) {
        // Add sample data
        Patient p1 = new Patient(patientIdCounter++, "John Doe", 30, "Fever");
        patients.add(p1);
        patientHashTable.insert(p1.id, p1.name);
        
        Patient p2 = new Patient(patientIdCounter++, "Jane Smith", 25, "Cold");
        patients.add(p2);
        patientHashTable.insert(p2.id, p2.name);
        
        Patient p3 = new Patient(patientIdCounter++, "Bob Johnson", 45, "Diabetes");
        patients.add(p3);
        patientHashTable.insert(p3.id, p3.name);
        
        Doctor d1 = new Doctor(doctorIdCounter++, "Dr. Smith", "Cardiology");
        doctors.add(d1);
        doctorHashTable.insert(d1.id, d1.name);
        
        Doctor d2 = new Doctor(doctorIdCounter++, "Dr. Johnson", "Neurology");
        doctors.add(d2);
        doctorHashTable.insert(d2.id, d2.name);
        
        Doctor d3 = new Doctor(doctorIdCounter++, "Dr. Williams", "Pediatrics");
        doctors.add(d3);
        doctorHashTable.insert(d3.id, d3.name);

        while (true) {
            System.out.println("\n╔═══════════════════════════════════════════════╗");
            System.out.println("║    HOSPITAL MANAGEMENT SYSTEM WITH HASHING    ║");
            System.out.println("╠═══════════════════════════════════════════════╣");
            System.out.println("║ 1. Add Patient                                ║");
            System.out.println("║ 2. View Patients                              ║");
            System.out.println("║ 3. Add Doctor                                 ║");
            System.out.println("║ 4. View Doctors                               ║");
            System.out.println("║ 5. Book Appointment                           ║");
            System.out.println("║ 6. View Pending Appointments                  ║");
            System.out.println("║ 7. Process Appointment                        ║");
            System.out.println("║ 8. Generate Bill                              ║");
            System.out.println("║ 9. View Bills                                 ║");
            System.out.println("║10. Search Patient                             ║");
            System.out.println("║11. Sort Patients by Name                      ║");
            System.out.println("║12. Sort Patients by Age                       ║");
            System.out.println("║13. Sort Doctors by Name                       ║");
            System.out.println("║14. View Operation History                     ║");
            System.out.println("║15. Undo Last Operation                        ║");
            System.out.println("║16. Display Hash Tables                        ║");
            System.out.println("║17. Exit                                       ║");
            System.out.println("╚═══════════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: addPatient(); break;
                case 2: viewPatients(); break;
                case 3: addDoctor(); break;
                case 4: viewDoctors(); break;
                case 5: bookAppointment(); break;
                case 6: viewPendingAppointments(); break;
                case 7: processAppointment(); break;
                case 8: generateBill(); break;
                case 9: viewBills(); break;
                case 10: searchPatient(); break;
                case 11: sortPatientsByName(); break;
                case 12: sortPatientsByAge(); break;
                case 13: sortDoctorsByName(); break;
                case 14: viewOperationHistory(); break;
                case 15: undoLastOperation(); break;
                case 16: displayHashTables(); break;
                case 17: 
                    System.out.println("Thank you for using Hospital Management System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}