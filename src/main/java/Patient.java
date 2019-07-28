public class Patient extends Human {

    private String disease;

    protected Patient(String name, String surname, String passID,String disease) {
        this(name, surname,passID);
        this.setDisease(disease);

    }

    public Patient(String name, String surname, String passID) {
        super(name, surname, passID);
    }


    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    @Override
    public String toString() {
        return "================================================\n"+
                "Patient "+ getName()+" "+ getSurname()+" "+ getPassID()+"\n"+
                "================================================\n";
    }

}
