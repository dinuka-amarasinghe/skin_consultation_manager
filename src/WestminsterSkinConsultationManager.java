import java.util.*;
import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    Scanner input = new Scanner(System.in);
    static ArrayList<Doctor> doctorsList = new ArrayList<>(); //Represents the list of doctors in the center
    static ArrayList<Consultation> consultationsList = new ArrayList<>(); //Represents the list of consultations
    static ArrayList<Patient> patientsList = new ArrayList<>(); //Represents the list of patients
    static ArrayList<LocalTime> timeSlots = new ArrayList<>(); //Represents the time slots available for consultations
    static Set<Integer> patientIdSet = new HashSet<>(); //Stores all the patient unique IDs
    static Set<Integer> doctorIdSet = new HashSet<>(); //Stores all the doctor medical license numbers
    private String name; //name of a patient/ doctor within the class
    private String surname; //surname of a patient/ doctor within the class
    private String mobileNumber; //mobile number of a patient/ doctor within the class
    private LocalDate dateOfBirth; //date of birth of a patient/ doctor within the class


    /**
     * @return list of doctors
     */
    @Override
    public ArrayList<Doctor> getDoctorsList() {
        return doctorsList;
    }

    /**
     * @return list of consultations
     */
    @Override
    public ArrayList<Consultation> getConsultationsList() {
        return consultationsList;
    }

    /**
     * @return list of patients
     */
    @Override
    public ArrayList<Patient> getPatientsList() {
        return patientsList;
    }

    /**
     * @return set of patient Unique Ids
     */
    @Override
    public Set<Integer> getPatientIdSet() {
        return patientIdSet;
    }

    /**
     * @return list of time available consultation time slots
     */
    @Override
    public ArrayList<LocalTime> getTimeSlots() {
        return timeSlots;
    }

    /**
     * Initialises the time slots available for consultations
     */
    @Override
    public void initializeTimeSlots() {
        timeSlots.add(LocalTime.of(9,0));
        timeSlots.add(LocalTime.of(10,0));
        timeSlots.add(LocalTime.of(11,0));
        timeSlots.add(LocalTime.of(12,0));
        timeSlots.add(LocalTime.of(13,0));
        timeSlots.add(LocalTime.of(14,0));
        timeSlots.add(LocalTime.of(15,0));
    }

    /**
     * Checks to make sure there are no more than 10 doctors in the center
     * @return returns 1 if number of doctors does not exceed 10, returns 2 if number of doctors exceeds 10.
     */
    @Override
    public int checkSize() {
        if(doctorsList.size() < 10){
            return 1;
        }
        else {
            return 2;
        }
    }

    /**
     * Adds Name,Surname,Mobile Number & Date Of Birth of a Doctor or Patient.
     */
    @Override
    public void addPersonalDetails() {
        boolean validEntry = false;
        System.out.print("Enter First Name: ");
        name = input.next();
        input.nextLine();
        do {
            if (name.matches(".*\\d+.*")) {
                System.out.print("First Name Cannot Contain Numbers, Please Try Again: ");
                name = input.next();
                input.nextLine();
            } else {
                name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
                validEntry = true;
            }
        }
        while (!validEntry);
        validEntry = false;
        System.out.print("Enter Surname: ");
        surname = input.next();
        input.nextLine();
        do {
            if (surname.matches(".*\\d+.*")) {
                System.out.print("Surname Cannot Contain Numbers, Please Try Again: ");
                surname = input.next();
                input.nextLine();
            } else {
                surname = surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();
                validEntry = true;
            }
        }
        while (! validEntry);
        validEntry = false;
        System.out.print("Enter Mobile Number In This Format (+94-711234567): ");
        mobileNumber = input.next();
        do {
            if (mobileNumber.matches("^[+94]{3}-\\d{9}$")) {
                validEntry = true;
            } else {
                System.out.print("Invalid Mobile Number, Try Again: ");
                mobileNumber = input.next();
            }
        }
        while (! validEntry);
        System.out.print("Enter Date of Birth In This Format (yyyy-mm-dd): ");
        String dob = input.next();
        input.nextLine();
        dateOfBirth = null;
        do {
            try {
                dateOfBirth = LocalDate.parse(dob);
            } catch (DateTimeParseException e) {
                System.out.print("Invalid Date Of Birth, Try Again: ");
                dob = input.next();
            }
        }
        while (dateOfBirth == null);
    }

    /**
     * Calls @addPersonalDetails method and adds Medical License Number & Specialisation of Doctor.
     */
    @Override
    public void addDoctor() {
        addPersonalDetails();
        boolean validEntry = false;
        int mLicenseNumber = 0;
        System.out.print("Enter Medical License Number: ");
        do {
            try {
                mLicenseNumber = Integer.parseInt(input.next());
                if(! doctorIdSet.contains(mLicenseNumber)){
                    doctorIdSet.add(mLicenseNumber);
                    validEntry = true;
                }
                else {
                    System.out.print("Medical License Number "+mLicenseNumber+" Already Exists! Try Again: ");
                }

            } catch (Exception e) {
                System.out.print("Invalid Medical License Number, Try Again: ");
            }
        }
        while (! validEntry);
        validEntry = false;
        int selection = 0;
        String specialisation;
        System.out.print("""
                                            
                    1 - Cosmetic Dermatology
                    2 - Medical Dermatology
                    3 - Paediatric Dermatology
                                            
                    Enter Specialisation Number:\040""");
        do {
            try {
                selection = Integer.parseInt(input.next());
                if (selection > 0 && selection < 4) {
                    validEntry = true;
                } else {
                    System.out.print("Invalid Selection, Try Again: ");
                }
            } catch (Exception e) {
                System.out.print("Invalid Selection, Try Again: ");
            }
        }
        while (! validEntry);
        if (selection == 1) {
            specialisation = String.valueOf(Specialisation.COSMETIC_DERMATOLOGY);
        } else if (selection == 2) {
            specialisation = String.valueOf(Specialisation.MEDICAL_DERMATOLOGY);
        } else {
            specialisation = String.valueOf(Specialisation.PAEDIATRIC_DERMATOLOGY);
        }
        Doctor newDoctor = new Doctor(name, surname, mobileNumber, dateOfBirth, mLicenseNumber, specialisation);
        doctorsList.add(newDoctor);
        System.out.println("\nDoctor " + name + " " + surname + " Successfully Added To The System!");
    }


    /**
     * Deletes a doctor from the system by selecting the medical license number.
     */
    @Override
    public void deleteDoctor() {
        String name = null;
        int license = 0;
        if (doctorsList.isEmpty()){
            System.out.println("\nNo Doctors Available To Delete!");
        }
        else {
            System.out.println("List Of Doctors");
            for (int i = 0; i < doctorsList.size() ; i++) {
                System.out.println((i+1)+":- Name:" + doctorsList.get(i).getName()+" "+doctorsList.get(i).getSurname()+" " +
                        "| Medical License Number: "+doctorsList.get(i).getMedicalLicenseNumber());
            }
            boolean validEntry = false;
            System.out.print("\nEnter Medical License Number Of The Doctor To Delete: ");
            do{
                try {
                    int licenseNumber = Integer.parseInt(input.next());
                    for (int i = 0; i < doctorsList.size(); i++) {
                        if (licenseNumber == doctorsList.get(i).getMedicalLicenseNumber()) {
                            name = doctorsList.get(i).getName() + " " + doctorsList.get(i).getSurname();
                            license = doctorsList.get(i).getMedicalLicenseNumber();
                            doctorsList.remove(i);
                            validEntry = true;
                            break;
                        } else if (!doctorIdSet.contains(licenseNumber)) {
                            System.out.print("No Doctor Exists For Medical License Number " + licenseNumber + ", Try Again: ");
                            break;
                        }
                    }
                }
                catch (Exception e){
                    System.out.print("Invalid Doctor Medical License Number, Try Again: ");
                }
            }
            while (!validEntry);
            System.out.println("\nDoctor "+name+" Bearing Medical License Number "+license+" Has Been Deleted From The System!");
            System.out.println("\nTotal Remaining Doctors In The Center: "+doctorsList.size());
        }
    }

    /**
     * Prints the list of doctors alphabetically sorted by surnames.
     */
    @Override
    public void printDoctors() {
        ArrayList<Doctor> doctorsListCopy = new ArrayList<>(doctorsList);
        doctorsListCopy.sort(Doctor.doctorNameComparator);
        if (doctorsListCopy.isEmpty()) {
            System.out.println("\nNo Doctors In The System!");
        }
        else {
            System.out.println("\nList Of Doctors Sorted Using Surname:");
            for (int i = 0; i < doctorsListCopy.size() ; i++) {
                System.out.println("Doctor "+ (i+1)+": \n" + doctorsListCopy.get(i).toString());
            }
        }
    }


    /**
     * Adds consultation (Date, Time, Cost & Notes) details, Patient details & selects a doctor to book consultations.
     */
    @Override
    public void addConsultation() {
        if(doctorsList.isEmpty()){
            System.out.println("\nPlease Add A Doctor To Book Consultations!");
        }
        else{
            int consultationId;
            if(consultationsList.isEmpty()){
                consultationId = 1;
            }
            else{
                int id=0;
                for (Consultation consultation : consultationsList) {
                    id = consultation.getConsultationId();
                }
                consultationId=id+1;
            }
            boolean validEntry= false;
            System.out.print("Enter Date For The Consultation In This Format (yyyy-mm-dd): ");
            String date = input.next();
            LocalDate consultationDate = null;
            do {
                try {
                    consultationDate = LocalDate.parse(date);
                    validEntry = true;
                } catch (DateTimeParseException e) {
                    System.out.print("Invalid Date, Try Again: ");
                    date = input.next();
                }
            }
            while (! validEntry);
            validEntry = false;
            int time;
            LocalTime selectedTime = null;
            System.out.println("\nTime Of Consultation: \n");
            for (int i = 0; i < timeSlots.size() ; i++) {
                System.out.println((i+1)+" : "+timeSlots.get(i));
            }
            System.out.print("\nSelect Time Of Consultation: ");
            do {
                try{
                    time = Integer.parseInt(input.next());
                    if(time==1){
                        selectedTime = timeSlots.get(0);
                        validEntry = true;
                    }
                    else if (time==2){
                        selectedTime = timeSlots.get(1);
                        validEntry = true;
                    }
                    else if (time==3){
                        selectedTime = timeSlots.get(2);
                        validEntry = true;
                    }
                    else if (time==4){
                        selectedTime = timeSlots.get(3);
                        validEntry = true;
                    }
                    else if (time==5){
                        selectedTime = timeSlots.get(4);
                        validEntry = true;
                    }
                    else if (time==6){
                        selectedTime = timeSlots.get(5);
                        validEntry = true;
                    }
                    else if (time==7){
                        selectedTime = timeSlots.get(6);
                        validEntry = true;
                    }
                    else {
                        System.out.print("Invalid Time Selection, Try Again: ");
                    }
                }
                catch(Exception e){
                    System.out.print("Invalid Time Selection, Try Again: ");
                }
            }
            while(!validEntry);
            System.out.println("\nList Of Doctors\n");
            for (int i = 0; i < doctorsList.size() ; i++) {
                System.out.println("Doctor "+(i+1)+": Medical License Number: " + doctorsList.get(i).getMedicalLicenseNumber() +
                        "|| Name: "+doctorsList.get(i).getName()+" "+doctorsList.get(i).getSurname() + " || Specialisation: "+doctorsList.get(i).getSpecialisation());
            }
            validEntry = false;
            Doctor doctor = null;
            Doctor initialDoctor = null;
            int doctorId;
            System.out.print("\nEnter Medical License Number Of The Doctor: ");
            do {
                try {
                    doctorId = Integer.parseInt(input.next());
                    for (Doctor value : doctorsList) {
                        if (value.getMedicalLicenseNumber() == doctorId) {
                            doctor = value;
                            initialDoctor = doctor;
                            validEntry = true;
                        } else if (!doctorIdSet.contains(doctorId)) {
                            input.nextLine();
                            System.out.print("No Doctor Available For Medical License Number " + doctorId + " Try Again: ");
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.print("Invalid Medical License Number, Try Again: ");
                }
            }
            while (! validEntry);
            validEntry = false;
            String specialisation = doctor.getSpecialisation();
            ArrayList<Doctor> tempDoctorList = new ArrayList<>();
            for (Doctor value : doctorsList) {
                if (value.getSpecialisation().equals(specialisation)) {
                    if (! value.equals(doctor)) {
                        tempDoctorList.add(value);
                    }
                }
            }
            do {
                if (!consultationsList.isEmpty()) {
                    for (Consultation consultation : consultationsList) {
                        if (consultation.getDoctor().equals(doctor) && consultation.getDate().equals(consultationDate)
                                && consultation.getTime().equals(selectedTime)) {
                            if(tempDoctorList.isEmpty()){
                                System.out.println("\nDoctor " + doctor.getName() + " " + doctor.getSurname() + " Is Not Available At " + selectedTime
                                        + " On " + consultationDate + "!");
                                System.out.println("No Other Doctors Available For "+specialisation+", Unable To Book Consultation!");
                                //noinspection UnusedAssignment
                                consultationId--;
                                return;
                            }
                            int randomIndex = (int) (Math.random() * tempDoctorList.size());
                            doctor = tempDoctorList.get(randomIndex);
                        } else {
                            validEntry = true;
                        }
                    }
                }
                else {
                    validEntry = true;
                }
            }
            while (!validEntry);
            if(!initialDoctor.equals(doctor)){
                System.out.println("Doctor "+doctor.getName()+" "+doctor.getSurname()+" (ID :"+doctor.getMedicalLicenseNumber()+") Selected For Consultation.");
            }
            validEntry = false;
            Patient patient = null;
            int consultationCost = 0;
            int patientUniqueID;
            System.out.print("\nEnter Patient Unique ID: ");
            do {
                try {
                    patientUniqueID = Integer.parseInt(input.next());
                    if (patientsList.isEmpty()) {
                        System.out.println("\n+++++Enter Details Of The Patient+++++");
                        addPersonalDetails();
                        consultationCost = 15;
                        patient = new Patient(name, surname, mobileNumber, dateOfBirth, patientUniqueID);
                        patientsList.add(patient);
                        patientIdSet.add(patientUniqueID);
                        validEntry = true;
                    } else {
                        for (int i = 0; i < patientsList.size(); i++) {
                            if (patientsList.get(i).getUniqueId() == patientUniqueID) {
                                consultationCost = 25;
                                patient = patientsList.get(i);
                                System.out.println("Patient Details Available!");
                                validEntry = true;
                                break;
                            } else if (!patientIdSet.contains(patientUniqueID)) {
                                System.out.println("\n+++++Enter Details Of The Patient+++++");
                                addPersonalDetails();
                                consultationCost = 15;
                                patient = new Patient(name, surname, mobileNumber, dateOfBirth, patientUniqueID);
                                patientsList.add(patient);
                                patientIdSet.add(patientUniqueID);
                                validEntry = true;
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.print("Invalid Patient Unique ID, Try Again: ");
                }
            }
            while(!validEntry);
            System.out.println("Cost Of Consultation: GBP "+consultationCost);
            input.nextLine();
            System.out.print("Add Any Special Notes: ");
            String notes = input.nextLine();
            System.out.println("Note Encrypted :- (Key-1234)");
            notes = encrypt(notes);
            Consultation newConsultation = new Consultation(consultationId,doctor, patient, consultationDate, selectedTime, consultationCost, notes);
            consultationsList.add(newConsultation);
            System.out.println("\nConsultation "+consultationId+" Successfully Added To The System!");
        }
    }


    /**
     * Cancels consultations by selecting medical license number
     */
    @Override
    public void cancelConsultation() {
        boolean validEntry = false;
        Consultation consultation=null;
        ArrayList<Integer> list = new ArrayList<>();
        for (Consultation value : consultationsList) {
            list.add(value.getConsultationId());
        }
        if (consultationsList.isEmpty()){
            System.out.println("\nNo Consultations Available To Cancel!");
        }
        else {
            viewConsultations();
            int consultationNo;
            System.out.print("\nEnter Consultation No For Cancellation: ");
            do {
                try{
                    consultationNo = Integer.parseInt(input.next());
                    for (Consultation s : consultationsList) {
                        if (s.getConsultationId() == consultationNo) {
                            consultation = s;
                            validEntry = true;
                            break;
                        } else if (!list.contains(consultationNo)){
                            System.out.print("Consultation No "+consultationNo+" Doesn't Exist,Try Again: ");
                            break;
                        }
                    }
                }
                catch(Exception e){
                    System.out.print("\nInvalid Consultation No, Try Again: ");
                }
            }
            while (!validEntry);
            consultationsList.remove(consultation);
            list.clear();
            System.out.println("\nConsultation Of Patient "+consultation.getPatient().getName()+" "+consultation.getPatient().getSurname()+
                    (" (ID "+consultation.getPatient().getUniqueId()+") On "+consultation.getDate()+" Cancelled Successfully."));
        }
    }

    /**
     * Decrypts the encrypted notes of a consultation.
     */
    @Override
    public void decryptNotes() {
        if (consultationsList.isEmpty()){
            System.out.println("No Consultations To Decrypt Notes!");
        }
        else {
            boolean validEntry = false;
            boolean validNumber = false;
            String key;
            String decryptedNote = "";
            int number;
            ArrayList<Integer> id = new ArrayList<>();
            for (Consultation value : consultationsList) {
                id.add(value.getConsultationId());
            }
            System.out.print("\nEnter Key For Decryption: ");
            do {
                key = input.next();
                if (key.equals("1234")) {
                    viewConsultations();
                    System.out.print("\nEnter Consultation No To Decrypt Notes: ");
                    do {
                        try {
                            number = Integer.parseInt(input.next());
                            for (int i = 0; i < id.size(); i++) {
                                if (consultationsList.get(i).getConsultationId() == number) {
                                    decryptedNote = decrypt(consultationsList.get(i).getNotes());
                                    validNumber = true;
                                }
                                else {
                                    System.out.print("Invalid Consultation Number,Try Again: ");
                                }
                            }
                        }
                        catch (Exception e) {
                            System.out.print("Invalid Consultation Number,Try Again: ");
                        }
                    }
                    while (!validNumber);
                    validEntry = true;
                }
                else {
                    System.out.print("Invalid Key For Decryption,Try Again: ");
                }
            } while (!validEntry);
            System.out.println("\nDecrypted Note:- \n"+decryptedNote);

        }

    }


    /**
     * encrypts a string input
     * @param input String to be encrypted
     * @return encrypted String
     */
    @Override
    public String encrypt(String input) {
        final String secretKey = "1234";
        AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
        return aesEncryptionDecryption.encrypt(input, secretKey);

    }

    /**
     * decrypts a string input
     * @param input String to be decrypted
     * @return decrypted String
     */
    @Override
    public String decrypt(String input) {
        final String secretKey = "1234";
        AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
        return aesEncryptionDecryption.decrypt(input, secretKey);
    }


    /**
     * Displays list of consultations with all consultation details
     */
    @Override
    public void viewConsultations() {
        if (consultationsList.isEmpty()){
            System.out.println("\nNo Consultations Available To View!");
        }
        else {
            for (Consultation consultation : consultationsList) {
                System.out.println("\n"+consultation.toString());
            }
        }
    }

    /**
     * Saves all user-entered program data into a text file.
     */
    @Override
    public void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream("ProgramData.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(doctorsList);
            oos.writeObject(consultationsList);
            oos.writeObject(patientsList);
            oos.writeObject(doctorIdSet);
            oos.writeObject(patientIdSet);
            System.out.println("\nSuccessfully Stored Program Data Into File!");
            oos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads the previously saved program data from the text file.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        try {
            FileInputStream fis = new FileInputStream("ProgramData.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            doctorsList = (ArrayList<Doctor>) ois.readObject();
            consultationsList = (ArrayList<Consultation>) ois.readObject();
            patientsList = (ArrayList<Patient>) ois.readObject();
            doctorIdSet = (Set<Integer>) ois.readObject();
            patientIdSet = (Set<Integer>) ois.readObject();
            System.out.println("\nSuccessfully Loaded Program From File!");
            ois.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    static class AESEncryptionDecryption {
        private static SecretKeySpec secretKey;
        private static final String ALGORITHM = "AES";


        /**
         * Creating byte array to store string
         * @param myKey key to be used for encryption and decryption
         */
        public void prepareSecretKey(String myKey) {
            MessageDigest sha;
            try {
                byte[] key = myKey.getBytes(StandardCharsets.UTF_8);
                sha = MessageDigest.getInstance("SHA-1");
                key = sha.digest(key);
                key = Arrays.copyOf(key, 16);
                secretKey = new SecretKeySpec(key, ALGORITHM);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        /**
         * Encrypting String
         * @param strToEncrypt String to be encrypted
         * @param secret key for encryption
         * @return encrypted String
         */
        public String encrypt(String strToEncrypt, String secret) {
            try {
                prepareSecretKey(secret);
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
            } catch (Exception e) {
                System.out.println("Error while encrypting: " + e);
            }
            return null;
        }


        /**
         * Decrypting String
         * @param strToDecrypt String to be decrypted
         * @param secret key for decryption
         * @return decrypted String
         */
        public String decrypt(String strToDecrypt, String secret) {
            try {
                prepareSecretKey(secret);
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
            } catch (Exception e) {
                System.out.println("Error while decrypting: " + e);
            }
            return null;
        }
    }


    /**
     * Exits the program
     */
    @Override
    public void exit() {
        System.out.print("Do You Want To Save Before Exiting (Enter Y for Yes / N for No)? ");
        String answer = input.next();
        if (answer.equalsIgnoreCase("Y")) {
            saveToFile();
            System.out.println("\nExiting Program.....Thank You!");
            System.exit(0);
        } else if (answer.equalsIgnoreCase("N")) {
            System.out.println("\nExiting Program.....Thank You!");
            System.exit(0);
        } else {
            System.out.print("\nInvalid Input, Try Again!\n");
            exit();
        }
    }
}


