/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Objects;

/**
 *
 * @author MeltMelt
 */
public class Vaccine {

    private String vaccineID;
    private String vaccineName;

    public Vaccine() {
    }

    public Vaccine(String vaccineID) {
        this.vaccineID = vaccineID;
    }

    public Vaccine(String vaccineID, String vaccineName) {
        this.vaccineID = vaccineID;
        this.vaccineName = vaccineName;
    }

    public String getVaccineID() {
        return vaccineID;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineID(String vaccineID) {
        this.vaccineID = vaccineID;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
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
        final Vaccine other = (Vaccine) obj;
        if (!Objects.equals(this.vaccineID, other.vaccineID)) {
            return false;
        }
        return true;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.vaccineID);
        return hash;
    }

    public String toString() {
        return String.format("%10s | %20s", this.vaccineID, this.vaccineName);
    }
}
