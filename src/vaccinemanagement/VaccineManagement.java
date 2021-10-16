/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

import GUI.Menu;
import data.Injection;
import data.InjectionList;
import data.Student;
import data.StudentList;
import data.VaccineList;
import utils.FileUtils;
import utils.InputUtils;

/**
 *
 * @author MeltMelt
 */
public class VaccineManagement {

    /**
     * @param args the command line arguments
     */
    private static final String STUDENT_FILE_NAME = "student.txt";
    private static final String VACCINE_FILE_NAME = "vaccine.txt";
    private static final String INJECTION_FILE_NAME = "injection.txt";

    public static void main(String[] args) {
        StudentList studentList = FileUtils.readStudentListFromFile(STUDENT_FILE_NAME);
        VaccineList vaccineList = FileUtils.readVaccineListFromFile(VACCINE_FILE_NAME);
        InjectionList injectionList = FileUtils.readInjectionListFromFile(INJECTION_FILE_NAME);
        injectionList.setStudentList(studentList);
        injectionList.setVaccineList(vaccineList);
        Menu menu = new Menu();
        menu.add("1. Show information all students have been injected.");
        menu.add("2. Add student's vaccine injection information.");
        menu.add("3. Update information of student's vaccine injection.");
        menu.add("4. Delete student vaccine injection information.");
        menu.add("5. Search for injection information by studentID");
        menu.add("Others - Quit.");
        int userChoice;
        boolean changed = false;
        Student student;
        String studentID;
        Injection injection;
        do {
            menu.printMenu();
            userChoice = menu.getChoice("Choose option: ");
            switch (userChoice) {
                case 1:
                    injectionList.showInjection();
                    break;
                case 2:
                    changed = injectionList.addInjection();
                    break;
                case 3:
                    changed = injectionList.updateInjection();
                    changed = true;
                    break;
                case 4:
                    studentID = InputUtils.getString("Input student ID you want to delete: ").toUpperCase();
                    student = new Student(studentID);
                    injection = injectionList.searchInjectionByStudent(student);
                    if (injection != null) {
                        changed = injectionList.deleteInjection(injection);
                    }
                    break;
                case 5:
                    studentID = InputUtils.getString("Input student ID you want to search: ").toUpperCase();
                    student = new Student(studentID);
                    injection = injectionList.searchInjectionByStudent(student);
                    if (injection == null) {
                        System.out.println("Injection does not exist!");
                    } else {
                        System.out.println(injection);
                    }
                    break;
                default:
                    if (changed) {
                        if (InputUtils.getBoolean("Save changes? (Y/N): ")) {
                            FileUtils.saveToFile(INJECTION_FILE_NAME, injectionList);
                            System.out.println("Saved.");
                        }
                    }
            }
        } while (userChoice > 0 && userChoice < 6);

    }

}
