import java.io.Serializable;
import java.time.LocalDate;
public class Patient extends Person implements Serializable {
    static final long serialVersionUID = 1L;
    private int uniqueId;

    public Patient(String firstName,String surname, String mobileNumber, LocalDate dateOfBirth, int uniqueId) {
        super(firstName,surname, mobileNumber, dateOfBirth);
        this.uniqueId = uniqueId;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public String toString() {
        return ("First Name: " + super.getName() + " | Surname: " + super.getSurname() + " | Mobile Number: " + super.getMobileNumber()
                + " | Date Of Birth: " + super.getDateOfBirth() + " | Patient Unique ID: "+ uniqueId);
    }
}

