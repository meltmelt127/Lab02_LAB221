/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;

/**
 *
 * @author MeltMelt
 */
public class VaccineList extends ArrayList<Vaccine> {

    public void showVaccineList() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                            VACCINE LIST                                                            ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        for (Vaccine vaccine : this) {
            System.out.println(vaccine);
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }
}
