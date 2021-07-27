package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Caregiver;
import model.Treatment;
import org.hsqldb.rights.User;
import utils.UserCredentials;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AllCaregiverController {
    @FXML
    private TableView<Caregiver> tableView;
    @FXML
    private TableColumn<Caregiver, Integer> colID;
    @FXML
    private TableColumn<Caregiver, String> colFirstName;
    @FXML
    private TableColumn<Caregiver, String> colSurname;
    @FXML
    private TableColumn<Caregiver, String> colTelephone;

    @FXML
    private Button btnLockUnlock;
    @FXML
    Button btnDelete;
    @FXML
    Button btnAdd;
    @FXML
    TextField txtSurname;
    @FXML
    TextField txtFirstname;
    @FXML
    TextField txtTelephone;

    private ObservableList<Caregiver> tableviewContent = FXCollections.observableArrayList();
    private CaregiverDAO dao;

    /**
     * Initializes the corresponding fields. Is called as soon as the corresponding FXML file is to be displayed.
     */
    public void initialize() {
        readAllAndShowInTableView();

        tableView.setRowFactory(tv -> new TableRow<Caregiver>() {
            @Override
            public void updateItem(Caregiver item, boolean empty) {
                super.updateItem(item, empty) ;
                if (item==null||!item.isLocked()) {
                    setStyle("");
                } else {
                    setStyle("-fx-background-color: #ec5757;");
                }
            }
        });

        this.colID.setCellValueFactory(new PropertyValueFactory<Caregiver, Integer>("cid"));

        //CellValuefactory zum Anzeigen der Daten in der TableView
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("firstName"));
        //CellFactory zum Schreiben innerhalb der Tabelle
        this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colSurname.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("surname"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colTelephone.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("phonenumber"));
        this.colTelephone.setCellFactory(TextFieldTableCell.forTableColumn());



        //Anzeigen der Daten
        this.tableView.setItems(this.tableviewContent);

        if (!UserCredentials.mayLockAndUnlock()) {
            btnLockUnlock.setVisible(false);
        }

        if (!UserCredentials.mayDeleteElements()) {
            btnDelete.setVisible(false);
        }

        if (!UserCredentials.mayCreateCaregivers()) {
            btnAdd.setVisible(false);
            txtSurname.setVisible(false);
            txtFirstname.setVisible(false);
            txtTelephone.setVisible(false);
        }
    }

    /**
     * handles new firstname value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditFirstname(TableColumn.CellEditEvent<Caregiver, String> event){
        if (!UserCredentials.mayModifyCaregivers()) {
            return;
        }
        event.getRowValue().setFirstName(event.getNewValue());
        doUpdate(event);
    }

    /**
     * handles new surname value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditSurname(TableColumn.CellEditEvent<Caregiver, String> event){
        if (!UserCredentials.mayModifyCaregivers()) {
            return;
        }
        event.getRowValue().setSurname(event.getNewValue());
        doUpdate(event);
    }

    /**
     * handles new birthdate value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditPhonenumber(TableColumn.CellEditEvent<Caregiver, String> event){
        if (!UserCredentials.mayModifyCaregivers()) {
            return;
        }
        event.getRowValue().setPhonenumber(event.getNewValue());
        doUpdate(event);
    }

    /**
     * updates a caregiver by calling the update-Method in the {@link CaregiverDAO}
     * @param t row to be updated by the user (includes the patient)
     */
    private void doUpdate(TableColumn.CellEditEvent<Caregiver, String> t) {
        try {
            dao.update(t.getRowValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * calls readAll in {@link CaregiverDAO} and shows patients in the table
     */
    private void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createCaregiverDAO();
        List<Caregiver> allCaregivers;
        try {
            allCaregivers = dao.readAll();
            for (Caregiver c : allCaregivers) {
                if (c.isLocked()) {
                    if (UserCredentials.maySeeLockedElements()) {
                        this.tableviewContent.add(c);
                    }
                } else {
                    this.tableviewContent.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a delete-click-event. Calls the delete methods in the {@link CaregiverDAO}
     */
    @FXML
    public void handleDeleteRow() {
        Caregiver selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        try {
            List<Treatment> allTreatments = DAOFactory.getDAOFactory().createTreatmentDAO().readAll();
            for(Treatment treatment: allTreatments) {
                if (treatment.getCid() == selectedItem.getCid()) {
                    return;
                }
            }
            dao.deleteById(selectedItem.getCid());
            this.tableView.getItems().remove(selectedItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a add-click-event. Creates a caregiver and calls the create method in the {@link CaregiverDAO}
     */
    @FXML
    public void handleAdd() {
        String surname = this.txtSurname.getText();
        String firstname = this.txtFirstname.getText();
        String phonenumber = this.txtTelephone.getText();
        LocalDate creationDate = LocalDate.now();
        try {
            Caregiver c = new Caregiver(firstname, surname, phonenumber,creationDate, false);
            dao.create(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        readAllAndShowInTableView();
        clearTextfields();
    }

    /**
     * removes content from all textfields
     */
    private void clearTextfields() {
        this.txtFirstname.clear();
        this.txtSurname.clear();
        this.txtTelephone.clear();
    }

    public void handleLockUnlock() {
        Caregiver c = tableView.getSelectionModel().getSelectedItem();
        if (c == null) {
            return;
        }
        if (UserCredentials.mayLockAndUnlock()) {
            c.setLocked(!c.isLocked());
        }
        try {
            dao.update(c);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
