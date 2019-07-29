
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDAO implements DAO<Doctor> {

    private Connection connection ;
    DoctorDAO(Connection connection){
        this.connection = connection;
    }
    @Override
    public Doctor get(String passId) {
        try {
            String query = "Select name, surname, specialization from Doctors where PassportID = ?";
            PreparedStatement statement= connection.prepareStatement(query);
            statement.setString(1,passId);
            ResultSet resultSet= statement.executeQuery();
            resultSet.next();
            return new Doctor(resultSet.getString("name"), resultSet.getString("surname"),
                    passId, resultSet.getString("specialization"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(Doctor doctor)  {
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("Select count(id) from Doctors where PassportID = ?");
            statement.setString(1,doctor.getPassID());
            ResultSet rs = statement.executeQuery();
            if(rs.next()&& rs.getInt(1) != 0)
                throw new RepeatedPasswordIDException();
            String query = "Insert into Doctors(name ,surname, PassportID, Specialization) " +
                    "VALUES (?,?,?,?)";
            statement= connection.prepareStatement(query);
            statement.setString(1,doctor.getName());
            statement.setString(2,doctor.getSurname());
            statement.setString(3,doctor.getPassID());
            statement.setString(4,doctor.getSpecialization());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RepeatedPasswordIDException repetedPasswordIDExeption) {
            repetedPasswordIDExeption.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean remove(Doctor doctor) {
        try {
            PreparedStatement statement;
            if(doctor.getPassID()!= null) {
                return this.remove(doctor.getPassID());
            }

            statement = connection.prepareStatement
                    ("Select count(id) from Doctors where name = ? and surname = ?");
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getSurname());
            ResultSet rs = statement.executeQuery();
            if (rs.next() && rs.getInt(1) != 1) {
                return false;
            }
            String query = "DELETE From Doctors Where name = ? and surname = ?  ";
            statement =connection.prepareStatement(query);
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getSurname());
            statement.execute();
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        }
            return false;

    }
    public boolean remove(String passID)
    {
        try {
            PreparedStatement statement;
            String query = "DELETE From Doctors Where passportID =?  ";
            statement= connection.prepareStatement(query);
            statement.setString(1,passID);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

            return false;

    }

}

