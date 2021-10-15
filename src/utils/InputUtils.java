/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import data.Injection;
import data.Student;
import data.StudentList;
import data.Vaccine;
import data.VaccineList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author MeltMelt
 */
public class InputUtils {

    public static boolean getBoolean(String message) {
        Scanner sc = new Scanner(System.in);
        String inputStr;
        System.out.print(message);
        inputStr = sc.nextLine().trim().toUpperCase();
        //input from user accepts "Y"
        return inputStr.startsWith("Y");
    }

    public static String getString(String message) {
        String result;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(message);
            result = sc.nextLine();
            if (!result.trim().isEmpty()) {
                return result;
            } else
                System.out.println("Please fill the input!");
        } while (true);
    }

    public static LocalDate getDate(String message) {
        Scanner sc = new Scanner(System.in);
        LocalDate date;
        do {
            try {
                System.out.print(message);
                date = LocalDate.parse(sc.nextLine().replace(" ", ""));
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Please input the correct format!");
            }
        } while (true);
    }
    
    public static String returnNullIfNullString(String string) {
        if (string.equals("null"))
            return null;
        return string;
    }
    
    public static String returnNullStringIfNull(String string) {
        if (string == null)
            return "null";
        return string;
    }
    
    public static Student getStudent(StudentList studentList) {
        studentList.showStudentList();
        do {
            String studentID = InputUtils.getString("Input student ID you want to add: ").toUpperCase();
            int position = studentList.indexOf(new Student(studentID));
            if (position < 0)
                System.out.println("Please input a student ID in this list!");
            else 
                return studentList.get(position);
        } while (true);    
    }
    
    public static Vaccine getVaccine(VaccineList vaccineList) {
        vaccineList.showVaccineList();
        do {
            String vaccineID = InputUtils.getString("Input vaccine ID you want to add: ").toUpperCase();
            int position = vaccineList.indexOf(new Vaccine(vaccineID));
            if (position < 0)
                System.out.println("Please input a vaccine ID in this list!");
            else 
                return vaccineList.get(position);
        } while (true); 
    }
}
