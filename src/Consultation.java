import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation implements Serializable {
    static final long serialVersionUID = 1L;
    private int consultationId;
    private Doctor doctor;
    private Patient patient;
    private LocalDate date;
    private LocalTime time;
    private int cost;
    private String notes;

    public Consultation(int consultationId, Doctor doctor, Patient patient, LocalDate date, LocalTime time, int cost, String notes) {
        this.consultationId = consultationId;
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.time = time;
        this.cost = cost;
        this.notes = notes;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getCost() {
        return cost;
    }

    public String getNotes() {
        return notes;
    }

    public String toString() {
        return ("Consultation ID: "+consultationId
                + "\nDoctor Name: " + doctor.getName() +" "+doctor.getSurname()+ " (ID: " + doctor.getMedicalLicenseNumber()+")"
                + "\nDoctor Specialisation: " + doctor.getSpecialisation()
                + "\nPatient Name: " + patient.getName()+" "+ patient.getSurname()+ " (Unique ID: "+ patient.getUniqueId()+")"
                + "\nDate Of Appointment: "+date
                + "\nTime Of Appointment: "+time
                + "\nCost Of Appointment: "+cost
                + "\nSpecial Notes: "+notes);

    }
}
