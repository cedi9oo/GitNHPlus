package datastorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDatabaseAccess {


    private static LoginDatabaseAccess instance;
    private Connection connection;

    private LoginDatabaseAccess() {
        connection = ConnectionBuilder.getConnection();
    }

    public static LoginDatabaseAccess getLogin() {
        if (instance == null) {
            instance = new LoginDatabaseAccess();
        }
        return instance;
    }

    public boolean loginValid(final String user, final String hashedPassword) {
        try {
            Statement st = connection.createStatement();
            String query = String.format("SELECT * FROM credentials WHERE username = '%s'", user);
            ResultSet result = null;
            result = st.executeQuery(query);
            if (result.next()) {
                return result.getString("password (hash)").equals(hashedPassword);
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
