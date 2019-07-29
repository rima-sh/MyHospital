public class Printer implements View {
    private  String decore = "\n----------------------------------------------------------\n";

    public void commandRequest(){
        System.out.println(decore+ "Please enter a command!\n ADD DELETE GET EXIT ");
    }

    public void specifyRole(Command command){
        System.out.print(decore+ "Do you want to "+command+ " a doctor or a patient? " );
    }
    public  void specializationRequest(){
        System.out.print(decore+ "What Specialization does the doctor have? " );
    }

    public  void fullNameRequest(Command command){
        System.out.print(decore+ "Please Specify name and surname! " );
    }

    public void passIdRequest()
    {
        System.out.print(decore+ "Enter the Passport Id ");
    }
    public void repetedName(String fullName, String role){
        System.out.println(decore+ "There are several or no"+role+" with name "+ fullName+" " );
    }
    public void roleNotValid(){
        System.out.println(decore+"Enterd role is not valid. ");
    }
    public void nameNotValid(){
        System.out.println(decore+"Entered name is note valid.");
    }
    public void passIdNotValid(){
        System.out.println(decore+"Enterd passport ID is not valid.");
    }
    public void successfullyAdded(String role, String fullName)
    {
        System.out.println(decore+role.substring(0,1).toUpperCase()+ role.substring(1).toLowerCase() +
                " "+fullName+" was succesfully added! ");
    }
    public void successfullyDeleted(String role, String fullName)
    {
        System.out.println(decore+role.substring(0,1).toUpperCase()+ role.substring(1).toLowerCase() +
                " "+fullName+" was succesfully deleted! ");
    }
}
