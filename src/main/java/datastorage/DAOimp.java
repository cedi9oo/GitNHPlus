package datastorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * The DAOimp class implements the DAO interface for a specific data class
 * @param <T>
 */
public abstract class DAOimp<T> implements DAO<T>{
    protected Connection conn;

    /**
     * constructor for a new DAOimp instance
     * @param conn
     */
    public DAOimp(Connection conn) {
        this.conn = conn;
    }

    /**
     * Creates a new object in the database
     * @param t generic Object to be created
     * @throws SQLException if something fail
     */
    @Override
    public void create(T t) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getCreateStatementString(t));
    }

    /**
     * reads and return the object with the given key in the database
     * @param key
     * @return object
     * @throws SQLException throws an error when something is not present
     */
    @Override
    public T read(long key) throws SQLException {
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadByIDStatementString(key));
        if (result.next()) {
            object = getInstanceFromResultSet(result);
        }
        return object;
    }

    /**
     * reads and return all objects
     * @return
     * @throws SQLException throws an error when something is not present
     */
    @Override
    public List<T> readAll() throws SQLException {
        ArrayList<T> list = new ArrayList<T>();
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllStatementString());
        list = getListFromResultSet(result);
        return list;
    }

    /**
     * update the object with given id
     * @param t
     * @throws SQLException throws an error when something is not present
     */
    @Override
    public void update(T t) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getUpdateStatementString(t));
    }

    /**
     * deleted the object with given id
     * @param key
     * @throws SQLException throws an error when something is not present
     */
    @Override
    public void deleteById(long key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getDeleteStatementString(key));
    }

    /**
     * creates a sql-query-string to create a new object
     * @param t
     * @return String of the new create object
     */
    protected abstract String getCreateStatementString(T t);

    /**
     * creates a sql-query-string to read an object
     * @param key
     * @return String of the new create object
     */
    protected abstract String getReadByIDStatementString(long key);

    /**
     * Returns one data-instance from the given ResultSet
     * @param set
     * @return Returns one data-instance from the given ResultSet
     * @throws SQLException
     */
    protected abstract T getInstanceFromResultSet(ResultSet set) throws SQLException;

    /**
     * creates a sql-query-string to read all object
     * @return a List of Strings of all object
     */
    protected abstract String getReadAllStatementString();

    /**
     * Returns all data-instance from the given ResultSet
     * @param set
     * @return Returns all data-instance from the given ResultSet
     * @throws SQLException throws an error when something is not present
     */
    protected abstract ArrayList<T> getListFromResultSet(ResultSet set) throws SQLException;

    /**
     * creates a sql-query-string to update an object
     * @param t
     * @return String of updated object
     */
    protected abstract String getUpdateStatementString(T t);

    /**
     * creates a sql-query-string to delete an object
     * @param key
     * @return String of updated object
     */
    protected abstract String getDeleteStatementString(long key);
}
