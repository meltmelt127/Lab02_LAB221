/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.time.LocalDate;
import java.util.Objects;
import utils.InputUtils;

/**
 *
 * @author Admin
 */
public class Injection {

    private Student student;
    private Vaccine vaccine;
    private String place1;
    private LocalDate date1;
    private String place2;
    private LocalDate date2;

    public Injection() {
    }

    public Injection(Student student) {
        this.student = student;
    }

    public Injection(Student student, Vaccine vaccine, String place1, LocalDate date1, String place2, LocalDate date2) {
        this.student = student;
        this.vaccine = vaccine;
        this.place1 = place1;
        this.date1 = date1;
        this.place2 = place2;
        this.date2 = date2;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Injection other = (Injection) obj;
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.student);
        return hash;
    }

    @Override
    public String toString() {
        if (vaccine == null) {
            return String.format("%10s | %20s | %13s | %15s | %15s | %12s | %15s | %12s", student.getStudentID(), student.getStudentName(), "null", "null", "null", "null", "null", "null");
        } else {
            return String.format("%10s | %20s | %13s | %15s | %15s | %12s | %15s | %12s", student.getStudentID(), student.getStudentName(), InputUtils.returnNullStringIfNull(vaccine.getVaccineID()), InputUtils.returnNullStringIfNull(vaccine.getVaccineName()), InputUtils.returnNullStringIfNull(place1), InputUtils.returnNullStringIfNull(date1 == null ? null : date1.toString()), InputUtils.returnNullStringIfNull(place2), InputUtils.returnNullStringIfNull(date2 == null ? null : date2.toString()));
        }
    }

}
