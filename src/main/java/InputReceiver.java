import java.util.Scanner;

public class InputReceiver {
    private static InputReceiver inputReceiver = new InputReceiver();
    private Scanner scanner = new Scanner(System.in);

    public static InputReceiver getInputReceiver() {
        return inputReceiver;
    }

    private InputReceiver() {
    }
    public  Command getCommand() throws WrongInputException {
        switch (scanner.nextLine().toLowerCase()) {
            case "add":
                return Command.add;
            case "delete":
                return Command.delete;
            case "get":
                return Command.get;
            case "select all":
                return Command.selectAll;
            case "exit": return Command.exit;
        }
        throw new WrongInputException();


    }
    public String[] getNameSurname() throws WrongInputException {

        String[] fullName = scanner.nextLine().split(" ");
        if(fullName.length == 2){
            fullName[0] = fullName[0].substring(0,1).toUpperCase()+
                    fullName[0].substring(1).toLowerCase();
            fullName[1] = fullName[1].substring(0,1).toUpperCase()+
                    fullName[1].substring(1).toLowerCase();
            return fullName;
        }


        throw new WrongInputException();
    }
    public String getPassID() throws WrongInputException {
        String passID= scanner.nextLine().toUpperCase();
        if(passID.length()==9)
            return passID;
        throw new WrongInputException();
    }
    public  String getRole() throws WrongInputException {
        String role= scanner.nextLine().toLowerCase();
        if(role.equals("doctor") || role.equals("patient"))
            return role;
        throw new WrongInputException();
    }
    public  String getSpecialization(){
        String specialization= scanner.nextLine().toLowerCase();

            return specialization.substring(0,1).toUpperCase()+specialization.substring(1);

    }
    public  String getDisease(){
        String disease= scanner.nextLine().toLowerCase();

        return disease.substring(0,1).toUpperCase()+disease.substring(1);

    }
    public void close()
    {
        scanner.close();
    }
}
