/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

import GUI.Menu;
import data.InjectionList;
import data.StudentList;
import data.VaccineList;
import java.time.LocalDate;
import utils.FileUtils;
import utils.InputUtils;

/**
 *
 * @author Admin
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
        injectionList.updateFromStudentList(studentList);
        Menu menu = new Menu();
        menu.addMenuItem();
        int userChoice;
        boolean changed = false;
        do {
            menu.printMenu();
            userChoice = menu.getChoice("Choose option: ");
            switch (userChoice) {
                case 1:
                    injectionList.showInjection();
                    break;
                case 2:
                    changed = injectionList.addInjection(studentList, vaccineList);
                    break;
                case 3:
                    changed = injectionList.updateInjection();
                    changed = true;
                    break;
                case 4:
                    changed = injectionList.deleteInjection();
                    changed = true;
                    break;
                case 5:
                    injectionList.searchInjection();
                    break;
                default:
                    if (changed) 
                        if (InputUtils.getBool("Save changes? (Y/N): ")) {;
                            FileUtils.saveToFile(INJECTION_FILE_NAME, injectionList);
                            System.out.println("Saved.");
                        }
            }
        } while (userChoice > 0 && userChoice < 6);
    }
    
}
