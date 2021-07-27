package model;

import utils.DateConverter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Caregiver extends Person{

    private long cid;
    private String phonenumber;
    private List<Treatment> allTreatments = new ArrayList<Treatment>();
    private boolean locked;

    public Caregiver(String firstName, String surname, String phonenumber,boolean locked) {
        super(firstName, surname);
        this.phonenumber = phonenumber;
        this.locked = locked;
    }

    public Caregiver(long cid, String firstName, String surname, String phonenumber, boolean locked) {
        super(firstName, surname);
        this.cid = cid;
        this.phonenumber = phonenumber;
        this.locked = locked;
    }

    public long getCid() {
        return cid;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) { this.phonenumber = phonenumber; }

    public boolean add(Treatment m) {
        if (!this.allTreatments.contains(m)) {
            this.allTreatments.add(m);
            return true;
        }
        return false;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }


    public String toString() {
        return "Caregiver" + "\nCID: " + this.cid +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nPhonenumber: " + this.phonenumber +
                "\nLocked: " + this.locked +
                "\n";
    }
}