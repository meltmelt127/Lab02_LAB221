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
public class StudentList extends ArrayList<Student> {

    public void showStudentList() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                            STUDENT LIST                                                            ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        for (Student student : this) {
            System.out.println(student);
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }
}
