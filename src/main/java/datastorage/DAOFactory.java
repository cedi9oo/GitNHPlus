package datastorage;

/**
 * The class DAOFactory (as singleton) has as task the generation (and return) of specific DAO objects
 */
public class DAOFactory {

    private static DAOFactory instance;

    private DAOFactory() {

    }

    /**
     * this method generates a singleton getter for DAOFactory
     * @return instance
     */
    public static DAOFactory getDAOFactory() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    /**
     * This method generates a Treatment DAO
     * @return TreatmentDAO with his connection
     */
    public TreatmentDAO createTreatmentDAO() {
        return new TreatmentDAO(ConnectionBuilder.getConnection());
    }

    /**
     * This method generates a Patient DAO
     * @return TreatmentDAO with his connection
     */
    public PatientDAO createPatientDAO() {
        return new PatientDAO(ConnectionBuilder.getConnection());
    }

    /**
     * This method generates a Caregiver DAO
     * @return TreatmentDAO with his connection
     */
    public CaregiverDAO createCaregiverDAO() {
        return new CaregiverDAO(ConnectionBuilder.getConnection());
    }

}