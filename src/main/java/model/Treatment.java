package model;

import utils.DateConverter;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class is for the data of the treatments.
 */
public class Treatment {
    private long tid;
    private long pid;
    private long cid;
    private LocalDate date;
    private LocalTime begin;
    private LocalTime end;
    private String description;
    private String remarks;
    private Boolean locked;

    /**
     * constructs a treatment from the given params.
     * @param pid
     * @param cid
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     * @param locked
     */
    public Treatment(long pid,long cid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks,Boolean locked) {
        this.pid = pid;
        this.cid = cid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
        this.locked = locked;
    }

    /**
     * constructs a treatment from the given params.
     * @param tid
     * @param pid
     * @param cid
     * @param date
     * @param begin
     * @param end
     * @param description
     * @param remarks
     * @param locked
     */
    public Treatment(long tid, long pid,long cid, LocalDate date, LocalTime begin,
                     LocalTime end, String description, String remarks,Boolean locked) {
        this.tid = tid;
        this.pid = pid;
        this.cid = cid;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.description = description;
        this.remarks = remarks;
        this.locked = locked;
    }

    /**
     * This is a getter method to get the treatment ID
     * @return treatment ID
     */
    public long getTid() {
        return tid;
    }

    /**
     * This is a getter method to get the patient ID
     * @return patient ID
     */
    public long getPid() {
        return this.pid;
    }

    /**
     * This is a getter method to get the caregiver ID
     * @return caregiver ID
     */
    public long getCid() {
        return this.cid;
    }

    /**
     * This is a setter method to set the caregiver id
     * @param cid
     */
    public void setCid(long cid){
        this.cid = cid;
    }

    /**
     * This is a getter method to get the date of treatment
     * @return Date from treatment
     */
    public String getDate() {
        return date.toString();
    }

    /**
     * This is a getter method to get the time start of the treatment
     * @return Begin of the treatment
     */
    public String getBegin() {
        return begin.toString();
    }

    /**
     * This is a getter method to get the time end of the treatment
     * @return time end of the treatment
     */
    public String getEnd() {
        return end.toString();
    }

    /**
     * This is a setter method to set the date of the treatment
     * @param s_date
     */
    public void setDate(String s_date) {
        LocalDate date = DateConverter.convertStringToLocalDate(s_date);
        this.date = date;
    }

    /**
     * This is a setter method to set the time start of the treatment
     * @param begin
     */
    public void setBegin(String begin) {
        LocalTime time = DateConverter.convertStringToLocalTime(begin);
        this.begin = time;
    }

    /**
     * This is a setter method to set the time end of the treatment
     * @param end
     */
    public void setEnd(String end) {
        LocalTime time = DateConverter.convertStringToLocalTime(end);
        this.end = time;
    }

    /**
     * This is a getter method to get the description of the treatment
     * @return description from the treatment
     */
    public String getDescription() {
        return description;
    }

    /**
     * This is a setter method to set the description of the treatment
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This is a getter method to get the remark of the treatment
     * @return remarks from the treatment
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This is a setter method to set the remark of the treatment
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * this is a getter method to get the treatment as locked
     * @return locked treatment
     */
    public boolean isLocked(){
        return locked;
    }

    /**
     * this is a setter method to set the treatment as locked
     * @param locked
     */
    public void setLocked(boolean locked){
        this.locked =locked;
    }

    /**
     * string-representation of the treatment
     * @return string-representation of the treatment
     */
    public String toString() {
        return "\nBehandlung" + "\nTID: " + this.tid +
                "\nPID: " + this.pid +
                "\nCID: " + this.cid +
                "\nDate: " + this.date +
                "\nBegin: " + this.begin +
                "\nEnd: " + this.end +
                "\nDescription: " + this.description +
                "\nRemarks: " + this.remarks +
                "\nLocked: " + this.locked + "\n";    }
}