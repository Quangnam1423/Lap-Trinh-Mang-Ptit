/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package UDP;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Student implements Serializable {

    /**
     * @param args the command line arguments
     */
    private static final long serialVersionUID = 20171107;
    
    private String id;
    private String code;
    private String name;
    private String email;

    public Student(String id, String code, String name, String email) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.email = email;
    }

    public Student(String code) {
        this.id = "";
        this.code = code;
        this.name = "";
        this.email = "";
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void formatName() {
        String parts[] = this.name.split("\\s+");
        StringBuilder formattedName = new StringBuilder("");
        
        for (String part : parts) {
            formattedName.append(Character.toUpperCase(part.charAt(0)))
                        .append(part.substring(1).toLowerCase())
                        .append(" ");
        }
        
        this.name = formattedName.toString().trim();
    }
    
    public void createEmail() {
        String parts[] = this.name.split("\\s+");
        StringBuilder emailBuilder = new StringBuilder(parts[parts.length - 1].toLowerCase());
        for (int i = 0; i < parts.length - 1; i++) {
            emailBuilder.append(Character.toLowerCase(parts[i].charAt(0)));
        }
        emailBuilder.append("@ptit.edu.vn");
        this.email = emailBuilder.toString();
    }
    
    @Override
    public String toString() {
        return "Student{id='" + id + "', code='" + code + "', name='" + name + "', email='" + email + "'}";
    }
}
