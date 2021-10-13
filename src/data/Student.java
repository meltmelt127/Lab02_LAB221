/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Admin
 */
public class Student {
    private String studentID;
    private String studentName;

    public Student() {
    }

    public Student(String studentID) {
        this.studentID = studentID;
    }

    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }
    
    @Override
    public boolean equals(Object obj) {
        return this.studentID.equals(((Student) obj).getStudentID());
    }
    
    public String toString() {
        return studentID + ", " + studentName;
    }
}
