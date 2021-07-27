package datastorage;

import java.sql.Connection;
import model.Caregiver;
import model.Patient;
import utils.DateConverter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CaregiverDAO extends DAOimp<Caregiver> {

    public CaregiverDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(Caregiver caregiver) {
        return String.format("INSERT INTO caregiver (firstname, surname, phonenumber,locked) VALUES ('%s', '%s', '%s', '%b')",
                caregiver.getFirstName(), caregiver.getSurname(), caregiver.getPhonenumber(),caregiver.isLocked());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM caregiver WHERE cid = %d", key);
    }

    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet result) throws SQLException {
        Caregiver c = null;
        c = new Caregiver(result.getInt(1), result.getString(2),
                result.getString(3), result.getString(4),result.getBoolean(5));
        return c;
    }

    @Override
    protected String getReadAllStatementString() { return "SELECT * FROM caregiver"; }

    @Override
    protected ArrayList<Caregiver> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Caregiver> list = new ArrayList<Caregiver>();
        Caregiver c = null;
        while (result.next()) {
            c = new Caregiver(result.getInt(1), result.getString(2),
                    result.getString(3), result.getString(4),result.getBoolean(5));
            list.add(c);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(Caregiver caregiver) {
        return String.format("UPDATE caregiver SET firstname = '%s', surname = '%s',"+
                "phonenumber = '%s',locked = '%d' WHERE cid = %d", caregiver.getFirstName(), caregiver.getSurname(), caregiver.getPhonenumber(),
                caregiver.isLocked(), caregiver.getCid());
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM caregiver WHERE cid=%d", key);
    }

}
