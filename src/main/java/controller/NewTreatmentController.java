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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The <code>NewTreatmentController</code> contains the entire logic of the NewTreatment window view.
 */
public class NewTreatmentController {
    @FXML
    private Label lblSurname;
    @FXML
    private Label lblFirstname;
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
    private ComboBox<Caregiver> caregiverComboBox;

    private AllTreatmentController controller;
    private Patient patient;
    private Stage stage;

    private ObservableList<Caregiver> ComboBoxData = FXCollections.observableArrayList();

    /**
     * initialize the new treatment window
     * @param controller
     * @param stage
     * @param patient
     */
    public void initialize(AllTreatmentController controller, Stage stage, Patient patient) {
        this.controller= controller;
        this.patient = patient;
        this.stage = stage;

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
        createCaregiverComboBoxData();
        caregiverComboBox.setItems(ComboBoxData);
        caregiverComboBox.getSelectionModel().select(0);

        showPatientData();
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
     * this methode show patient data
     */
    private void showPatientData(){
        this.lblFirstname.setText(patient.getFirstName());
        this.lblSurname.setText(patient.getSurname());
    }

    /**
     * handles a add-click-event. Creates a treatment and calls the create method in the {@link TreatmentDAO}
     */
    @FXML
    public void handleAdd(){
        LocalDate date = this.datepicker.getValue();
        String s_begin = txtBegin.getText();
        LocalTime begin = DateConverter.convertStringToLocalTime(txtBegin.getText());
        LocalTime end = DateConverter.convertStringToLocalTime(txtEnd.getText());
        String description = txtDescription.getText();
        String remarks = taRemarks.getText();
        Caregiver caregiver = caregiverComboBox.getSelectionModel().getSelectedItem();
        Treatment treatment = new Treatment(patient.getPid(),caregiver.getCid(), date,
                begin, end, description, remarks, false);
        createTreatment(treatment);
        controller.readAllAndShowInTableView();
        stage.close();
    }

    /**
     * this method is for creating a treatment
     * @param treatment
     */
    private void createTreatment(Treatment treatment) {
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.create(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is executed when the cancel button is clicked,
     * which cancels the procedure.
     */
    @FXML
    public void handleCancel(){
        stage.close();
    }
}