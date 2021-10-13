/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Admin
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
    
    @Override
    public boolean equals(Object obj) {
        return this.vaccineID.equals(((Vaccine) obj).getVaccineID());
    }
    
    public String toString() {
        return vaccineID + ", " + vaccineName;
    }
}
