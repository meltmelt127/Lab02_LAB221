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
    VaccineList vaccineList;

    public InjectionList() {
    }

    public StudentList getStudentList() {
        return studentList;
    }

    public void setStudentList(StudentList studentList) {
        this.studentList = studentList;
        addEmptyInjectionForUnvaccinatedStudents();
    }

    public VaccineList getVaccineList() {
        return vaccineList;
    }

    public void setVaccineList(VaccineList vaccineList) {
        this.vaccineList = vaccineList;
    }

    private void addEmptyInjectionForUnvaccinatedStudents() {
        for (Student student : studentList) {
            int position = this.indexOf(new Injection(student));
            if (position < 0) {
                this.add(new Injection(student));
            }
        }
    }

    public void showInjection() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                           INJECTION LIST                                                           ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        for (Injection injection : this) {
            System.out.println(injection);
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

    private void addSecondVaccineToInjection(Injection injection) {
        String place2 = InputUtils.getString("Input place 2: ");
        LocalDate date2;
        do {
            date2 = InputUtils.getDate("Input date 2 (format: yyyy-mm-dd): ");
            if (injection.getDate1().plusDays(28).compareTo(date2) <= 0 && injection.getDate1().plusDays(84).compareTo(date2) >= 0) {
                injection.setDate2(date2);
                break;
            }
            System.out.println("The second dose of vaccine must be given 4 to 12 weeks after the first injection!");
        } while (true);

        injection.setPlace2(place2);
        injection.setDate2(date2);
    }

    private void enterNewInjection(Injection injection) {
        Vaccine vaccine = InputUtils.getVaccine(vaccineList);
        String place1 = InputUtils.getString("Input place 1: ");
        LocalDate date1 = InputUtils.getDate("Input date 1 (format: yyyy-mm-dd): ");

        injection.setVaccine(vaccine);
        injection.setPlace1(place1);
        injection.setDate1(date1);

        if (date1.compareTo(LocalDate.now().minusDays(28)) <= 0) {
            boolean input2ndVaccination = InputUtils.getBoolean("Do you want to input information of second injection? (Y/N): ");
            if (input2ndVaccination) {
                addSecondVaccineToInjection(injection);
            }
        }
    }

    public boolean addInjection() {
        Student student;
        Injection injection;
        boolean isContinue;
        boolean input2ndVaccination;
        boolean changed = false;
        do {
            student = InputUtils.getStudent(studentList);
            int index = this.indexOf(new Injection(student));
            injection = this.get(index);
            //haven't had injection
            if (injection.getVaccine() == null || injection.getVaccine().getVaccineID() == null) {
                enterNewInjection(injection);
                changed = true;
                //had first injection
            } else if (injection.getPlace1() != null && injection.getPlace2() == null) {
                input2ndVaccination = InputUtils.getBoolean("This student has completed the first injection. Do you want to input information of second injection? (Y/N): ");
                if (input2ndVaccination) {
                    addSecondVaccineToInjection(injection);
                    changed = true;
                }
            } else //had 2 injections
            {
                System.out.println("Student has completed 2 injections!");
            }
            isContinue = InputUtils.getBoolean("Do you want to add another injection? (Y/N): ");
        } while (isContinue);
        return changed;
    }

    private void updateFirstInjection(Injection injection) {
        String place1 = InputUtils.getString("Update place 1: ");
        LocalDate date1 = InputUtils.getDate("Update date 1 (format: yyyy-mm-dd): ");

        injection.setPlace1(place1);
        injection.setDate1(date1);
    }

    private void updateSecondInjection(Injection injection) {
        String place2 = InputUtils.getString("Update place 2: ");
        LocalDate date2;
        do {
            date2 = InputUtils.getDate("Update date 2 (format: yyyy-mm-dd): ");
            if (injection.getDate1().plusDays(28).compareTo(date2) <= 0 && injection.getDate1().plusDays(84).compareTo(date2) >= 0) {
                injection.setDate2(date2);
                break;
            }
            System.out.println("The second dose of vaccine must be given 4 to 12 weeks after the first injection!");
        } while (true);

        injection.setPlace2(place2);
        injection.setDate2(date2);
    }

    public boolean updateInjection() {
        boolean changed = false;
        boolean update2ndInjection;
        Student student = new Student(InputUtils.getString("Input student ID you want to update: ").toUpperCase());
        int position = this.indexOf(new Injection(student));
        if (position < 0) {
            System.out.println("Injection does not exist!");
            System.out.println("Update failed!");
        } else {
            Injection injection = this.get(position);
            if (injection.getVaccine().getVaccineID() == null) {
                System.out.println("Please add information of the injection for this student before updating!");
                System.out.println("Update failed!");
            } else {
                updateFirstInjection(injection);
                //just had first injection
                if (injection.getPlace2() == null) {
                    update2ndInjection = InputUtils.getBoolean("Do you want to add second injection? (Y/N): ");
                    if (update2ndInjection) {
                        updateSecondInjection(injection);
                    }
                } else { //had 2 injections and want to update all
                    updateSecondInjection(injection);
                }
                changed = true;
                System.out.println("Update successful!");
            }
        }
        return changed;
    }

    public boolean deleteInjection(Injection injection) {
        if (InputUtils.getBoolean("Are you sure you want to delete student " + injection.getStudent().getStudentID() + "? (Y/N): ")) {
            this.remove(injection);
            System.out.println("Delete successful!");
            return true;
        } else {
            System.out.println("Delete failed!");
            return false;
        }
    }

    public Injection searchInjectionByStudent(Student student) {
        int position = this.indexOf(new Injection(student));
        if (position < 0) {
            return null;
        }
        return this.get(position);
    }
}
