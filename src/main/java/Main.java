public class Main {
    public static void main(String[] args) {
        DbManager serverManager = new SqlServerManager();
        serverManager.connect();
        PatientDAO patientDAO = new PatientDAO(serverManager.getConnection());
        DoctorDAO doctorDAO = new DoctorDAO();
        InputReceiver inputReceiver = InputReceiver.getInputReceiver();
        Printer printer = new Printer();


        Command command;
        String[] fullName;
        String passID;
        String role;
        boolean succes = false;

        //Start
        command = inputReceiver.getCommand();
        while (command != Command.exit){
            switch (command){
                case add:
                    role = inputReceiver.getRole(command);
                    fullName = inputReceiver.getNameSurname(command);
                    passID = inputReceiver.getPassID();
                    if (role.equals("patient"))
                        succes = patientDAO.Add(new Patient(fullName[0], fullName[1], passID));

                    else if (role.equals("doctor"))
                        succes = doctorDAO.Add(new Doctor(null,null,null,null));
                    if (succes)
                        printer.successfullyAdded(role, fullName[0] + " " + fullName[1]);
                    break;
                case get:
                    role = inputReceiver.getRole(command);
                    passID = inputReceiver.getPassID();
                    if (role.equals("patient"))
                        System.out.println(patientDAO.Get(passID));
                    else if (role.equals("doctor"))
                        System.out.println(doctorDAO.Get(passID));break;
                    case delete:
                    role = inputReceiver.getRole(command);
                    fullName = inputReceiver.getNameSurname(command);
                    if(!patientDAO.Remove(new Patient(fullName[0],fullName[1],null)))
                        printer.successfullyDeleted(role,fullName[0]+ " "+ fullName[1]);break;

            }
        }


        inputReceiver.close();
        serverManager.disconnect();
    }
}
