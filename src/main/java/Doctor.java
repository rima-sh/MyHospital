import java.util.ArrayList;

public class Doctor extends Human {
    protected String specialization;
    protected ArrayList<Patient> patients = new ArrayList<>();


    public Doctor(String name, String surname, String passID, String specialization) {
        this(name, surname, passID);
        this.setSpecialization(specialization);

    }

    private Doctor(String name, String surname, String passID) {
        super(name, surname, passID);
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
