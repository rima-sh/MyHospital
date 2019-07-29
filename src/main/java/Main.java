public class Main {

    public static DbManager serverManager;
    public static PatientDAO patientDAO;
    public static DoctorDAO doctorDAO;
    public static InputReceiver inputReceiver ;
    public static Printer printer;
    public static Command command;
    public static String[] fullName;
    public static String passID;
    public static String role;
    public static String specialization;
    public static Boolean success;
    static {
        serverManager = new SqlServerManager();
        serverManager.connect();
        patientDAO = new PatientDAO(serverManager.getConnection());
        doctorDAO = new DoctorDAO(serverManager.getConnection());
        inputReceiver = InputReceiver.getInputReceiver();
        printer = new Printer();
        success = false;
    }
    public static void main(String[] args) {

        //Start

        while (true) {
            getCommand();
            switch (command){
                case add:
                    getRole();
                    getFullname();
                    getPassID();
                    if (role.equalsIgnoreCase("patient"))
                        success = patientDAO.add(new Patient(fullName[0], fullName[1], passID));

                    else if (role.equalsIgnoreCase("doctor")) {
                        printer.specializationRequest();
                        specialization = inputReceiver.getSpecialization();
                        success = doctorDAO.add(new Doctor(fullName[0], fullName[1], passID, specialization));
                    }
                    if (success)
                        printer.successfullyAdded(role, fullName[0] + " " + fullName[1]);
                    break;
                case get:
                    getRole();
                    getPassID();
                    if (role.equalsIgnoreCase("patient")) {
                        System.out.println(patientDAO.get(passID));
                        break;
                    }
                    if (role.equalsIgnoreCase("doctor")) {
                        System.out.println(doctorDAO.get(passID));
                        break;
                    }
                case delete:
                    getRole();
                    getFullname();
                    if (role.equalsIgnoreCase("patient")) {
                        if (patientDAO.remove(new Patient(fullName[0], fullName[1], null))) {
                            printer.successfullyDeleted(role, fullName[0] + " " + fullName[1]);
                            break;
                        }
                        printer.repetedName(fullName[0] + " " + fullName[1], role);
                        getPassID();
                        patientDAO.remove(passID);
                        printer.successfullyDeleted(role, fullName[0] + " " + fullName[1]);
                        break;
                    }
                    if (doctorDAO.remove(new Doctor(fullName[0], fullName[1], null,null))) {
                        printer.successfullyDeleted(role, fullName[0] + " " + fullName[1]);
                        break;
                    }
                    printer.repetedName(fullName[0] + " " + fullName[1], role);
                    getPassID();
                doctorDAO.remove(passID);
                    printer.successfullyDeleted(role, fullName[0] + " " + fullName[1]);
                    break;

            }
            if (command == Command.exit)
                break;
        }
        inputReceiver.close();
        serverManager.disconnect();
    }





        public static void getCommand() {
            printer.commandRequest();
            while (true) {
                try {
                    command = inputReceiver.getCommand();
                    break;
                } catch (WrongInputException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
        public static void getRole ()
        {
            printer.specifyRole(command);
            while (true) {
                try {
                    role = inputReceiver.getRole();
                    break;
                } catch (WrongInputException e) {
                    printer.roleNotValid();
                    continue;
                }
            }
        }
    public static void getFullname ()
    {
        printer.fullNameRequest(command);
        while (true) {
            try {
                fullName = inputReceiver.getNameSurname();
                break;
            } catch (WrongInputException e) {
                printer.nameNotValid();
                continue;
            }
        }
    }
    public static void getPassID ()
    {
        printer.passIdRequest();
        while (true) {
            try {
                passID = inputReceiver.getPassID();
                break;
            } catch (WrongInputException e) {
                printer.passIdNotValid();
                continue;
            }
        }
    }
}
