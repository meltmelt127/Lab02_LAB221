/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.time.LocalDate;
import java.util.ArrayList;
import utils.InputUtils;

/**
 *
 * @author Admin
 */
public class InjectionList extends ArrayList<Injection> {
    StudentList studentList;

    public InjectionList() {
    }

    public InjectionList(StudentList studentList) {
        this.studentList = studentList;
    }
    
    public void updateFromStudentList(StudentList studentList) {
        for (Student student : studentList) {
            int position = this.indexOf(new Injection(student.getStudentID()));
            if (position < 0) 
                this.add(new Injection(student));
        } 
    }
    
    public void showInjection() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("                      INJECTION LIST                         ");
        System.out.println("-------------------------------------------------------------");
        for (Injection injection : this) 
            System.out.println(injection);
        System.out.println("-------------------------------------------------------------");
    }
    
    private Injection getInjectionByStudentID(String studentID, StudentList studentList) {
        int postion = this.indexOf(new Injection(studentID));
        if (postion < 0) {
            int studentPosition = studentList.indexOf(new Student(studentID));
            Student student = studentList.get(studentPosition);
            return new Injection(student);
        }
        return this.get(postion);
    }
    
    public boolean addInjection(StudentList studentList, VaccineList vaccineList) {
        String studentID;
        Injection injection;
        boolean isContinue;
        boolean input2ndVaccination;
        boolean changed = false;
        do {
            studentID = InputUtils.getStudentID(studentList);
            injection = getInjectionByStudentID(studentID, studentList);
            //haven't had injection
            if (injection.getVaccineID() == null) {
                injection.setVaccineID(InputUtils.getVaccineID(vaccineList));
                injection.setVaccineName(vaccineList.getVaccineNameByID(injection.getVaccineID()));
                injection.setPlace1(InputUtils.getString("Input place 1: "));
                injection.setDate1(InputUtils.getDate("Input date 1 (format: yyyy-mm-dd): "));
                if (injection.getDate1().compareTo(LocalDate.now().minusDays(28)) <= 0) {
                    input2ndVaccination = InputUtils.getBool("Do you want to input information of second injection? (Y/N): ");
                    if (input2ndVaccination) {
                        injection.setPlace2(InputUtils.getString("Input place 2: "));
                        do {
                            LocalDate temp = InputUtils.getDate("Input date 2 (format: yyyy-mm-dd): ");
                            if (injection.getDate1().plusDays(28).compareTo(temp) <= 0 && injection.getDate1().plusDays(84).compareTo(temp) >= 0)
                                injection.setDate2(temp);
                            else System.out.println("The second dose of vaccine must be given 4 to 12 weeks after the first injection!");
                        } while (injection.getDate2() == null);
                    }
                }
                changed = true;
                //had first injection
            } else if (injection.getPlace1() != null && injection.getPlace2() == null) { 
                input2ndVaccination = InputUtils.getBool("This student has completed the first injection. Do you want to input information of second injection? (Y/N): ");
                if (input2ndVaccination) {
                    injection.setPlace2(InputUtils.getString("Input place 2: "));
                    do {
                        LocalDate temp = InputUtils.getDate("Input date 2 (format: yyyy-mm-dd): ");
                        if (injection.getDate1().plusDays(28).compareTo(temp) <= 0 && injection.getDate1().plusDays(84).compareTo(temp) >= 0)
                            injection.setDate2(temp);
                        else System.out.println("The second dose of vaccine must be given 4 to 12 weeks after the first injection!");
                    } while (injection.getDate2() == null);
                }
                changed = true;
            } else //had 2 injections
                System.out.println("Student has completed 2 injections!");
            isContinue = InputUtils.getBool("Do you want to add another injection? (Y/N): ");
        } while (isContinue);
        return changed;
    }
    
    public boolean updateInjection() {
        boolean changed = false;
        boolean update2ndInjection;
        String studentID = InputUtils.getString("Input student ID you want to update: ").toUpperCase();
        int position = this.indexOf(new Injection(studentID));
        if (position < 0) {
            System.out.println("Injection does not exist!");
            System.out.println("Update failed!");
        } else {
            Injection injection = this.get(position);
            if (injection.getVaccineID() == null) {
                System.out.println("Please add information of the injection for this student before updating!");
                System.out.println("Update failed!");
            } else {
                injection.setPlace1(InputUtils.getString("Update place 1: "));
                injection.setDate1(InputUtils.getDate("Update date 1 (format: yyyy-mm-dd): "));
                //just had first injection
                if (injection.getDate2() == null) {
                    update2ndInjection = InputUtils.getBool("Do you want to add second injection? (Y/N): ");
                    if (update2ndInjection) {
                        injection.setPlace2(InputUtils.getString("Update place 2: "));
                        do {
                            LocalDate temp = InputUtils.getDate("Update date 2 (format: yyyy-mm-dd): ");
                            if (injection.getDate1().plusDays(28).compareTo(temp) <= 0 && injection.getDate1().plusDays(84).compareTo(temp) >= 0)
                                injection.setDate2(temp);
                            else System.out.println("The second dose of vaccine must be given 4 to 12 weeks after the first injection!");
                        } while (injection.getDate2() == null);
                    }
                } else { //had 2 injections and want to update all
                    injection.setPlace2(InputUtils.getString("Update place 2: "));
                    injection.setDate2(null);
                    do {
                        LocalDate temp = InputUtils.getDate("Update date 2 (format: yyyy-mm-dd): ");
                        if (injection.getDate1().plusDays(28).compareTo(temp) <= 0 && injection.getDate1().plusDays(84).compareTo(temp) >= 0)
                            injection.setDate2(temp);
                        else System.out.println("The second dose of vaccine must be given 4 to 12 weeks after the first injection!");
                    } while (injection.getDate2() == null);
                }
                changed = true;
                System.out.println("Update successful!");
            }
        }
        return changed;
    }
    
    public boolean deleteInjection() {
        boolean changed = false;
        String studentID = InputUtils.getString("Input student ID you want to delete: ").toUpperCase();
        int position = this.indexOf(new Injection(studentID));
        if (position < 0) {
            System.out.println("Injection does not exist!");
        } else {
            if (InputUtils.getBool("Are you sure you want to delete student " + studentID + "? (Y/N): ")) {
                this.remove(position);
                System.out.println("Delete successful!");
                changed = true;
            } else {
                System.out.println("Delete failed!");
            }
        }
        return changed;
    }
    
    public void searchInjection() {
        String studentID = InputUtils.getString("Input student ID you want to search: ").toUpperCase();
        int position = this.indexOf(new Injection(studentID));
        if (position < 0) 
            System.out.println("Injection does not exist!");
        else 
            System.out.println(this.get(position));
    }
}
