package datastorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *  This class is responsible for all login things for the databases
 */
public class LoginDatabaseAccess {


    private static LoginDatabaseAccess instance;
    private Connection connection;

    /**
     * constructor gets the connection to the database
     */
    private LoginDatabaseAccess() {
        connection = ConnectionBuilder.getConnection();
    }

    /**
     * singleton getter to get the instance
     * @return instance
     */
    public static LoginDatabaseAccess getLogin() {
        if (instance == null) {
            instance = new LoginDatabaseAccess();
        }
        return instance;
    }

    /**
     * this method checks if the login is correct, is compared with the database
     * @param user
     * @param hashedPassword
     * @return the hashedPassword
     */
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
