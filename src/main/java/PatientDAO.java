
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDAO implements DAO<Patient> {

    private Connection connection ;
    PatientDAO(Connection connection){
        this.connection = connection;
    }
    @Override
    public Patient Get(String passId) {
        try {
        String query = "Select name, surname from Patients where PassportID = ?";
        PreparedStatement statement= connection.prepareStatement(query);
        statement.setString(1,passId);
        ResultSet resultSet= statement.executeQuery();
        resultSet.next();
            return new Patient(resultSet.getString("name"), resultSet.getString("surname"),passId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean Add(Patient patient)  {
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("Select count(id) from Patients where PassportID = ?");
            statement.setString(1,patient.getPassID());
            ResultSet rs = statement.executeQuery();
            if(rs.next()&& rs.getInt(1) != 0)
                throw new RepetedPasswordIDExeption();
            String query = "Insert into Patients(name ,surname, PassportID, Disease) " +
                    "VALUES (?,?,?,?)";
            statement= connection.prepareStatement(query);
            statement.setString(1,patient.getName());
            statement.setString(2,patient.getSurname());
            statement.setString(3,patient.getPassID());
            statement.setString(4,patient.getDisease());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RepetedPasswordIDExeption repetedPasswordIDExeption) {
            repetedPasswordIDExeption.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Remove(Patient patient) {
        try {
            PreparedStatement statement;
            String query = "DELETE From Patients Where name = ? and surname = ?  ";
            if(patient.getPassID()!= null) {
                query += " and PassportID = ?";
                statement = connection.prepareStatement(query);
                statement.setString(3, patient.getPassID());

            }
            else {
                statement = connection.prepareStatement
                        ("Select count(id) from Patients where name = ? and surname = ?");
                statement.setString(1, patient.getName());
                statement.setString(2, patient.getSurname());
                ResultSet rs = statement.executeQuery();
                statement =connection.prepareStatement(query);
                if (rs.next() && rs.getInt(1) != 1) {
                    Printer pr = new Printer();
                    pr.repetedName(patient.getName()+" "+patient.getSurname(),"patient");
                    InputReceiver ir= InputReceiver.getInputReceiver();
                    patient.setPassID(ir.getPassID());
                    return Remove(patient);
                }
                statement.execute();
                return true;
            }


            statement.setString(1,patient.getName());
            statement.setString(2, patient.getSurname());
            statement.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

