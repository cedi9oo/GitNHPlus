package model;

import utils.DateConverter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is for the data of the caregiver and extends with Person.
 */
public class Caregiver extends Person{

    private long cid;
    private String phonenumber;
    private List<Treatment> allTreatments = new ArrayList<Treatment>();
    private LocalDate creationDate;
    private boolean locked;

    /**
     * constructs a caregiver from the given params
     * @param firstName
     * @param surname
     * @param phonenumber
     * @param creationDate
     * @param locked
     */
    public Caregiver(String firstName, String surname, String phonenumber,LocalDate creationDate,boolean locked) {
        super(firstName, surname);
        this.phonenumber = phonenumber;
        this.locked = locked;
        this.creationDate = creationDate;
    }

    /**
     * constructs a caregiver from the given params
     * @param cid
     * @param firstName
     * @param surname
     * @param phonenumber
     * @param creationDate
     * @param locked
     */
    public Caregiver(long cid, String firstName, String surname, String phonenumber,LocalDate creationDate, boolean locked) {
        super(firstName, surname);
        this.cid = cid;
        this.phonenumber = phonenumber;
        this.locked = locked;
        this.creationDate =creationDate;
    }

    /**
     * This is a getter method to get the caregiver ID
     * @return Caregiver ID (CID)
     */
    public long getCid() {
        return cid;
    }

    /**
     * This is a getter method to get the phonenumber from the caregiver
     * @return Caregiver phonenumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * This is a setter method to set the phonenumber from the patient
     * @param phonenumber
     */
    public void setPhonenumber(String phonenumber) { this.phonenumber = phonenumber; }

    /**
     * adds a treatment to the treatment-list, if it does not already contain it.
     * @param m Treatment
     * @return true if the treatment was not already part of the list. otherwise false
     */
    public boolean add(Treatment m) {
        if (!this.allTreatments.contains(m)) {
            this.allTreatments.add(m);
            return true;
        }
        return false;
    }

    /**
     * This is a getter method to get the creation Date from the caregiver
     * @return creation Date from the caregiver
     */
    public String getCreationDate(){
        return creationDate.toString();
    }

    /**
     * This is a setter method to set the creation Date from the caregiver
     * @param creationDate
     */
    public void setCreationDate(String creationDate){
        this.creationDate = DateConverter.convertStringToLocalDate(creationDate);
    }

    /**
     * this is a getter method to get the treatment as locked
     * @return locked
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * this is a setter method to set the treatment as locked
     * @param locked
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * string-representation of the caregiver
     * @return tring-representation of the caregiver
     */
    public String toString() {
        return "Caregiver" + "\nCID: " + this.cid +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nPhonenumber: " + this.phonenumber +
                "\nCreationDate: " + this.creationDate +
                "\nLocked: " + this.locked +
                "\n";
    }
}