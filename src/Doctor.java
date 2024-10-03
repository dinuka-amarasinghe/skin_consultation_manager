import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

public class Doctor extends Person implements Serializable {
    static final long serialVersionUID = 1L;
    private int medicalLicenseNumber;
    private String specialisation;

    public Doctor(String name, String surname, String mobileNumber, LocalDate dateOfBirth, int medicalLicenseNumber,
                  String specialisation) {
        super(name, surname, mobileNumber, dateOfBirth);
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.specialisation = specialisation;
    }

    public int getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }


    @Override
    public String toString() {
        return ("First Name: " + super.getName() + " | Surname: " + super.getSurname() + " | Mobile Number: " + super.getMobileNumber()
                + " | Date Of Birth: " + super.getDateOfBirth() + " | Medical License Number: "+ medicalLicenseNumber +
                " | Specialisation: "+specialisation);
    }


    /**
     * Compares and sorts the list of doctors by surnames.
     */
    public static Comparator<Doctor> doctorNameComparator = (d1, d2) -> {
        String doctorSurName1 = d1.getSurname().toUpperCase();
        String doctorSurName2 = d2.getSurname().toUpperCase();
        return doctorSurName1.compareTo(doctorSurName2);
    };
}
