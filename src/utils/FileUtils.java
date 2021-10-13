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
        File f = null;
        FileReader fr = null;
        BufferedReader bf = null;
        StudentList list = new StudentList();
        try {
            f = new File(fName);
            fr = new FileReader(f);
            bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String id = stk.nextToken().toUpperCase().trim();
                String name = stk.nextToken().trim();
                Student student = new Student(id, name);
                list.add(student);
            }
        } catch (IOException | NumberFormatException | DateTimeParseException e) {
            System.out.println(e);
        } finally {
            try {
                if (bf != null) {
                    bf.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return list;
    }

    
    public static VaccineList readVaccineListFromFile(String fName) {
        File f = null;
        FileReader fr = null;
        BufferedReader bf = null;
        VaccineList list = new VaccineList();
        try {
            f = new File(fName);
            fr = new FileReader(f);
            bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String id = stk.nextToken().toUpperCase().trim();
                String name = stk.nextToken().trim();
                Vaccine vaccine = new Vaccine(id, name);
                list.add(vaccine);
            }
        } catch (IOException | NumberFormatException | DateTimeParseException e) {
            System.out.println(e);
        } finally {
            try {
                if (bf != null) {
                    bf.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return list;
    }

    public static InjectionList readInjectionListFromFile(String fName) {
        File f = null;
        FileReader fr = null;
        BufferedReader bf = null;
        InjectionList list = new InjectionList();
        try {
            f = new File(fName);
            fr = new FileReader(f);
            bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String studentID = stk.nextToken().toUpperCase().trim();
                String studentName = stk.nextToken().trim();
                String vaccineID = InputUtils.checkNullString(stk.nextToken().toUpperCase().trim());
                String vaccineName = InputUtils.checkNullString(stk.nextToken().trim());
                String place1 = InputUtils.checkNullString(stk.nextToken().trim());
                String temp = InputUtils.checkNullString(stk.nextToken().trim());
                LocalDate date1;
                if (temp == null)
                    date1 = null;
                else 
                    date1 = LocalDate.parse(temp, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String place2 = InputUtils.checkNullString(stk.nextToken().trim());
                temp = InputUtils.checkNullString(stk.nextToken().trim());
                LocalDate date2;
                if (temp == null)
                    date2 = null;
                else 
                    date2 = LocalDate.parse(temp, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Injection injection = new Injection(studentID, studentName, vaccineID, vaccineName, place1, date1, place2, date2);
                list.add(injection);
            }
        } catch (IOException e) {
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println(e);
        } finally {
            try {
                if (bf != null) {
                    bf.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return list;
    }

    public static void saveToFile(String fName, InjectionList list) {
        File f = null;
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            f = new File(fName);
            fw = new FileWriter(f);
            pw = new PrintWriter(fw);
            for (Injection injection : list) {
                if (injection.getVaccineID() != null)
                    pw.println(injection);
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
