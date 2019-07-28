public abstract class Human {

    private String name;
    private String surname;
    private String passID;

    protected Human(String name, String surname, String passID) {
        this.name = name;
        this.surname = surname;
        this.passID = passID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassID() {
        return passID;
    }

    public void setPassID(String passID) {
        this.passID = passID;
    }
}
