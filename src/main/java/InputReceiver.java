import java.util.Scanner;

public class InputReceiver {
    private static InputReceiver inputReceiver = new InputReceiver();
    private Scanner scanner = new Scanner(System.in);
    Printer printer = new Printer();

    public static InputReceiver getInputReceiver() {
        return inputReceiver;
    }

    private InputReceiver() {
    }
    public  Command getCommand() {
        printer.commandRequest();
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
        return getCommand();


    }
    public String[] getNameSurname(Command command) {
        printer.fullNameRequest(command);
        String[] fullName = scanner.nextLine().split(" ");
        if(fullName.length == 2)
            return fullName;
        printer.nameNotValid();
        return getNameSurname(command);
    }
    public String getPassID(){
        printer.passIdRequest();
        String passID= scanner.nextLine();
        if(passID.length()==9)
            return passID;
        printer.passIdNotValid();
        return getPassID();
    }
    public  String getRole(Command command){
        printer.specifyRole(command);
        String role= scanner.nextLine().toLowerCase();
        if(role.equals("doctor") || role.equals("patient"))
            return role;
        printer.roleNotValid();
        return getRole(command);
    }
    public void close()
    {
        scanner.close();
    }
}
