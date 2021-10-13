/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author MeltMelt
 */
public class Menu extends ArrayList {

    public void addMenuItem() {
        this.add("1. Show information all students have been injected.");
        this.add("2. Add student's vaccine injection information.");
        this.add("3. Update information of student's vaccine injection.");
        this.add("4. Delete student vaccine injection information.");
        this.add("5. Search for injection information by studentID");
        this.add("Others - Quit.");
    }

    public void printMenu() {
        System.out.println("=============================================================");
        System.out.println("                            MENU                             ");
        System.out.println("=============================================================");
        for (Object obj : this) 
            System.out.println(obj);
    }

    public static int getChoice(String message) {
        int result = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(message);
                result = Integer.parseInt(sc.nextLine());
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Please input an integer!");
            }
        } while (true);
    }
}
