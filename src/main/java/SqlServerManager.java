import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlServerManager implements DbManager {
    private Connection connection;
    @Override
    public void connect() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;database=MyDB",
                    "sa",
                    "1111");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Connection getConnection()
    {
        return connection;
    }
}
