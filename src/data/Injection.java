/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class Injection{
    private String studentID;
    private String studentName;
    private String vaccineID;
    private String vaccineName;
    private String place1;
    private LocalDate date1;
    private String place2;
    private LocalDate date2;

    public Injection() {
    }

    public Injection(String studentID) {
        this.studentID = studentID;
    }
    
    public Injection(Student student) {
        this.studentID = student.getStudentID();
        this.studentName= student.getStudentName();
        this.vaccineID = null;
        this.vaccineName = null;
        this.place1 = null;
        this.date1 = null;
        this.place2 = null;
        this.date2 = null;
    }

    public Injection(String studentID, String studentName, String vaccineID, String vaccineName, String place1, LocalDate date1, String place2, LocalDate date2) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.vaccineID = vaccineID;
        this.vaccineName = vaccineName;
        this.place1 = place1;
        this.date1 = date1;
        this.place2 = place2;
        this.date2 = date2;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(String vaccineID) {
        this.vaccineID = vaccineID;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getPlace1() {
        return place1;
    }

    public void setPlace1(String place1) {
        this.place1 = place1;
    }

    public LocalDate getDate1() {
        return date1;
    }

    public void setDate1(LocalDate date1) {
        this.date1 = date1;
    }

    public String getPlace2() {
        return place2;
    }

    public void setPlace2(String place2) {
        this.place2 = place2;
    }

    public LocalDate getDate2() {
        return date2;
    }

    public void setDate2(LocalDate date2) {
        this.date2 = date2;
    }

    
    
    @Override
    public boolean equals(Object obj) {
        return this.studentID.equals(((Injection) obj).getStudentID());
    }

    public String toString() {
        return studentID + ", " + studentName + ", " + vaccineID + ", " + vaccineName + ", " + place1 + ", " + date1 + ", " + place2 + ", " + date2;
    }
    
}
