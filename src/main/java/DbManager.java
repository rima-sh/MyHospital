import java.sql.Connection;

public interface DbManager {
    void connect();
    void disconnect();
    Connection getConnection();

}
