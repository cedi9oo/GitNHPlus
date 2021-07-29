package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Caregiver;
import model.Patient;
import model.Treatment;
import utils.DateConverter;
import utils.UserCredentials;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TreatmentController {
    @FXML
    private ComboBox<Caregiver> caregiverComboBox;
    @FXML
    private Label lblPatientName;
    @FXML
    private Label lblCarelevel;
    @FXML
    private TextField txtBegin;
    @FXML
    private TextField txtEnd;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextArea taRemarks;
    @FXML
    private DatePicker datepicker;
    @FXML
    private Button btnChange;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnLockUnlock;


    private AllTreatmentController controller;
    private Stage stage;
    private Patient patient;
    private Treatment treatment;

    private ObservableList<Caregiver> ComboBoxData =
            FXCollections.observableArrayList();

    /**
     * initialize the treatment window
     * @param controller
     * @param stage
     * @param treatment
     */
    public void initializeController(AllTreatmentController controller, Stage stage, Treatment treatment) {
        this.stage = stage;
        this.controller = controller;
        createCaregiverComboBoxData();
        caregiverComboBox.setConverter(new StringConverter<Caregiver>() {
            @Override
            public String toString(Caregiver object) {
                return object.getSurname();
            }

            @Override
            public Caregiver fromString(String string) {
                return null;
            }
        });

        caregiverComboBox.setItems(ComboBoxData);
        for (int x = 0; x<ComboBoxData.size();x++){
            if (ComboBoxData.get(x).getCid()==treatment.getCid()){
                caregiverComboBox.getSelectionModel().select(x);
                break;
            }
        }
        PatientDAO pDao = DAOFactory.getDAOFactory().createPatientDAO();
        try {
            this.patient = pDao.read((int) treatment.getPid());
            this.treatment = treatment;
            showData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!UserCredentials.mayLockAndUnlock()) {
            btnLockUnlock.setVisible(false);
        }
    }

    /**
     * fill the caregiver combo box with data
     */
    private void createCaregiverComboBoxData(){
        CaregiverDAO dao = DAOFactory.getDAOFactory().createCaregiverDAO();
        try {
            List<Caregiver> caregiverList = (ArrayList<Caregiver>) dao.readAll();
            for (Caregiver caregiver: caregiverList) {
                this.ComboBoxData.add(caregiver);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * this methode show all data from a treatment
     */
    private void showData() {
        this.lblPatientName.setText(patient.getSurname() + ", " + patient.getFirstName());
        this.lblCarelevel.setText(patient.getCareLevel());
        LocalDate date = DateConverter.convertStringToLocalDate(treatment.getDate());
        this.datepicker.setValue(date);
        this.txtBegin.setText(this.treatment.getBegin());
        this.txtEnd.setText(this.treatment.getEnd());
        this.txtDescription.setText(this.treatment.getDescription());
        this.taRemarks.setText(this.treatment.getRemarks());
    }

    /**
     * This method is executed when the change button is clicked in the new treatment window,
     * which updates the treatment.
     */
    @FXML
    public void handleChange() {
        this.treatment.setDate(this.datepicker.getValue().toString());
        this.treatment.setBegin(txtBegin.getText());
        this.treatment.setEnd(txtEnd.getText());
        this.treatment.setDescription(txtDescription.getText());
        this.treatment.setRemarks(taRemarks.getText());
        this.treatment.setCid(caregiverComboBox.getSelectionModel().getSelectedItem().getCid());
        doUpdate();
        controller.readAllAndShowInTableView();
        stage.close();
    }

    /**
     * this methode is linked with TreatmentDAO and updated the treatment
     */
    private void doUpdate() {
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.update(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is executed when the cancel button is clicked,
     * which cancels the procedure.
     */
    @FXML
    public void handleCancel() {
        stage.close();
    }

    /**
     * This method is executed when the Lock/unlock button is clicked,
     * which lock or unlock a treatment.
     */
    @FXML
    public void handleLockUnlock() {
        if (UserCredentials.mayLockAndUnlock()) {
            treatment.setLocked(!treatment.isLocked());
        }
    }
}