package datastorage;

import java.sql.Connection;
import model.Caregiver;
import model.Patient;
import utils.DateConverter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific patient-SQL-queries.
 */
public class CaregiverDAO extends DAOimp<Caregiver> {

    public CaregiverDAO(Connection conn) {
        super(conn);
    }

    /**
     * generates a <code>INSERT INTO</code>-Statement for a given caregiver
     * @param caregiver for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(Caregiver caregiver) {
        return String.format("INSERT INTO caregiver (firstname, surname, phonenumber, creationDate, locked) VALUES ('%s', '%s', '%s', '%s', '%b')",
                caregiver.getFirstName(), caregiver.getSurname(), caregiver.getPhonenumber(),caregiver.getCreationDate(),caregiver.isLocked());
    }

    /**
     * generates a <code>select</code>-Statement for a given key
     * @param key for which a specific SELECTis to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM caregiver WHERE cid = %d", key);
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Caregiver</code>
     * @param result ResultSet with a single row. Columns will be mapped to a caregiver-object.
     * @return patient with the data from the resultSet.
     */
    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet result) throws SQLException {
        Caregiver c = null;
        LocalDate creationDate = DateConverter.convertStringToLocalDate(result.getString(5));
        c = new Caregiver(result.getInt(1), result.getString(2),
                result.getString(3), result.getString(4),creationDate,result.getBoolean(6));
        return c;
    }

    /**
     * generates a <code>SELECT</code>-Statement for all caregiver.
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() { return "SELECT * FROM caregiver"; }

    /**
     * maps a <code>ResultSet</code> to a <code>Caregiver-List</code>
     * @param result ResultSet with a multiple rows. Data will be mapped to caregiver-object.
     * @return ArrayList with caregiver from the resultSet.
     */
    @Override
    protected ArrayList<Caregiver> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Caregiver> list = new ArrayList<Caregiver>();
        Caregiver c = null;
        while (result.next()) {
            LocalDate creationDate = DateConverter.convertStringToLocalDate(result.getString(5));
            c = new Caregiver(result.getInt(1), result.getString(2),
                    result.getString(3), result.getString(4),creationDate,result.getBoolean(6));
            list.add(c);
        }
        return list;
    }

    /**
     * generates a <code>UPDATE</code>-Statement for a given caregiver
     * @param caregiver for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getUpdateStatementString(Caregiver caregiver) {
        return String.format("UPDATE caregiver SET firstname = '%s', surname = '%s', phonenumber = '%s'"+
                        ", creationDate = '%s',locked = '%b' WHERE cid = %d", caregiver.getFirstName(), caregiver.getSurname(), caregiver.getPhonenumber(),
                caregiver.getCreationDate(),caregiver.isLocked(), caregiver.getCid());
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM caregiver WHERE cid=%d", key);
    }

}
