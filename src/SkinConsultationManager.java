import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Set;

public interface SkinConsultationManager {
    ArrayList<Doctor> getDoctorsList();

    ArrayList<Consultation> getConsultationsList();

    ArrayList<Patient> getPatientsList();

    Set<Integer> getPatientIdSet();

    ArrayList<LocalTime> getTimeSlots();

    void initializeTimeSlots();

    int checkSize();

    void addPersonalDetails();

    void addDoctor();

    void deleteDoctor();

    void printDoctors();
    
    void addConsultation();

    void cancelConsultation();

    void decryptNotes();

    String encrypt(String input);

    String decrypt(String input);

    void viewConsultations();

    void saveToFile();

    void loadFromFile();

    void exit();

}
