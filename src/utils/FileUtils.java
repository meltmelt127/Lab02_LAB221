/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import data.Injection;
import data.InjectionList;
import data.Student;
import data.StudentList;
import data.Vaccine;
import data.VaccineList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.StringTokenizer;

/**
 *
 * @author MeltMelt
 */
public class FileUtils {

    public static StudentList readStudentListFromFile(String fName) {
        File f = new File(fName);
        StudentList list = new StudentList();
        try (FileReader fr = new FileReader(f)) {
            try (BufferedReader bf = new BufferedReader(fr)) {
                String details;
                while ((details = bf.readLine()) != null) {
                    StringTokenizer stk = new StringTokenizer(details, ",");
                    String id = stk.nextToken().toUpperCase().trim();
                    String name = stk.nextToken().trim();
                    Student student = new Student(id, name);
                    list.add(student);
                }
            }
        } catch (IOException | NumberFormatException | DateTimeParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static VaccineList readVaccineListFromFile(String fName) {
        File f = new File(fName);
        VaccineList list = new VaccineList();
        try (FileReader fr = new FileReader(f)) {
            try (BufferedReader bf = new BufferedReader(fr)) {
                String details;
                while ((details = bf.readLine()) != null) {
                    StringTokenizer stk = new StringTokenizer(details, ",");
                    String id = stk.nextToken().toUpperCase().trim();
                    String name = stk.nextToken().trim();
                    Vaccine vaccine = new Vaccine(id, name);
                    list.add(vaccine);
                }
            }
        } catch (IOException | NumberFormatException | DateTimeParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static LocalDate parseDateFromString(String dateString) {
        if (dateString == null) {
            return null;
        }
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static InjectionList readInjectionListFromFile(String fName) {
        File f = new File(fName);
        InjectionList list = new InjectionList();
        try (FileReader fr = new FileReader(f)) {
            try (BufferedReader bf = new BufferedReader(fr)) {
                String details;
                StringTokenizer stk;
                String studentID, studentName, vaccineID, vaccineName, place1, place2;
                LocalDate date1, date2;
                Injection injection;
                Student student;
                Vaccine vaccine;
                String temp;
                while ((details = bf.readLine()) != null) {
                    stk = new StringTokenizer(details, "|");
                    studentID = stk.nextToken().toUpperCase().trim();
                    studentName = stk.nextToken().trim();
                    student = new Student(studentID, studentName);
                    vaccineID = InputUtils.returnNullIfNullString(stk.nextToken().toUpperCase().trim());
                    vaccineName = InputUtils.returnNullIfNullString(stk.nextToken().trim());
                    vaccine = new Vaccine(vaccineID, vaccineName);
                    place1 = InputUtils.returnNullIfNullString(stk.nextToken().trim());
                    temp = InputUtils.returnNullIfNullString(stk.nextToken().trim());
                    date1 = parseDateFromString(temp);
                    place2 = InputUtils.returnNullIfNullString(stk.nextToken().trim());
                    temp = InputUtils.returnNullIfNullString(stk.nextToken().trim());
                    date2 = parseDateFromString(temp);
                    injection = new Injection(student, vaccine, place1, date1, place2, date2);
                    list.add(injection);
                }
            }
        } catch (FileNotFoundException e) {
            return list;
        } catch (IOException | NumberFormatException | DateTimeParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void saveToFile(String fName, InjectionList list) {
        File f = new File(fName);
        try (FileWriter fw = new FileWriter(f)) {
            try (PrintWriter pw = new PrintWriter(fw)) {
                for (Injection injection : list) {
                    if (injection.getVaccine() != null) {
                        pw.println(injection);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
