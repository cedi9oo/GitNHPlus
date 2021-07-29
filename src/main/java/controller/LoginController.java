package controller;

import datastorage.ConnectionBuilder;
import datastorage.LoginDatabaseAccess;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * The <code>LoginController</code> contains the entire logic of the login view.
 */
public class LoginController {
    @FXML
    private TextField user;
    @FXML
    private TextField password;

    /**
     * This method is executed when the login button is clicked,
     * if successful you are redirected to the main window, otherwise an error occurs.
     */
   @FXML
    public void handleLogin(){
       String benutzer = user.getText();
       boolean success = LoginDatabaseAccess.getLogin().loginValid(benutzer,
               getHash(password.getText()));

       if (success) {
           Main.getMain().mainWindow(benutzer);
       }
       else {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Info");
           alert.setHeaderText("Falscher Benutzername oder Passwort");
           alert.setContentText("Bitte versuchen Sie es erneut.");
           alert.showAndWait();
       }
   }

    /**
     * This method is executed when the cancel button is clicked,
     * which cancels the procedure.
     */
   @FXML
   public void handleCancel(){
       ConnectionBuilder.closeConnection();
       Platform.exit();
       System.exit(0);
   }

    /**
     * The method is used to generate the hashed password SHA-512.
     * @param passwordToHash
     * @return generatedPassword for databank
     */
    private String getHash(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
