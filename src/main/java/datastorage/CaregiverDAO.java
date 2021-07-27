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
        return String.format("INSERT INTO caregiver (firstname, surname, phonenumber, creationDate, locked) VALUES ('%s', '%s', '%s', '%s', '%b')",
                caregiver.getFirstName(), caregiver.getSurname(), caregiver.getPhonenumber(),caregiver.getCreationDate(),caregiver.isLocked());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM caregiver WHERE cid = %d", key);
    }

    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet result) throws SQLException {
        Caregiver c = null;
        LocalDate creationDate = DateConverter.convertStringToLocalDate(result.getString(5));
        c = new Caregiver(result.getInt(1), result.getString(2),
                result.getString(3), result.getString(4),creationDate,result.getBoolean(6));
        return c;
    }

    @Override
    protected String getReadAllStatementString() { return "SELECT * FROM caregiver"; }

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

    @Override
    protected String getUpdateStatementString(Caregiver caregiver) {
        return String.format("UPDATE caregiver SET firstname = '%s', surname = '%s', phonenumber = '%s'"+
                        ", creationDate = '%s',locked = '%b' WHERE cid = %d", caregiver.getFirstName(), caregiver.getSurname(), caregiver.getPhonenumber(),
                caregiver.getCreationDate(),caregiver.isLocked(), caregiver.getCid());
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM caregiver WHERE cid=%d", key);
    }

}
