/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package RMI;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Student implements Serializable{

    /**
     * @param args the command line arguments
     */
    private static final long serialVersionUID = 20241130L;
    
    private String id;
    private String name;
    private int enrollmentYear;
    private String code;

    public Student() {
    }

    public Student(String id, String name, int enrollmentYear) {
        this.id = id;
        this.name = name;
        this.enrollmentYear = enrollmentYear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        String[] parts = name.split("\\s+");
        String n = parts[parts.length - 1] + "_";
        for (int i = 0; i < parts.length - 1; i++) 
            n = n + parts[i].charAt(0);
        return n;
    }
    
    public String getFullName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", enrollmentYear=" + enrollmentYear + ", code=" + code + '}';
    }
    
    
}
