import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
    static final long serialVersionUID = 1L;
    private String name;
    private String surname;
    private String mobileNumber;
    private LocalDate dateOfBirth;

    public Person(String name, String surname, String mobileNumber, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.mobileNumber = mobileNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}

