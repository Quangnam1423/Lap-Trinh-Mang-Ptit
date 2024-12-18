/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package RMI;

/**
 *
 * @author Administrator
 */

import java.rmi.*;
import java.rmi.registry.*;
import java.io.*;
import java.util.*;

public class client {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        Registry registry = LocateRegistry.getRegistry("203.162.10.109");
        
        ObjectService objService = (ObjectService) registry.lookup("RMIObjectService");
        
        String studentCode = "B21DCCN304";
        String qCode = "5rJMDbAZ";
        
        Object responseData = objService.requestObject(studentCode, qCode);
        
        Student input = (Student) responseData;
        StringBuilder newCode = new StringBuilder("B");
        newCode.append(input.getEnrollmentYear() /10 % 10);
        newCode.append(input.getEnrollmentYear() % 10);
        newCode.append(input.getName().toUpperCase());
        input.setName(Capitalize(input.getFullName()));
        input.setCode(newCode.toString());
        
        objService.submitObject(studentCode, qCode, (Serializable) input);
    }
    
    private static String Capitalize(String name) {
        String parts[] = name.split("\\s+");
        StringBuilder res = new StringBuilder("");
        
        for (int i = 0; i < parts.length - 1; i++) {
            res.append(Character.toUpperCase(parts[i].charAt(0)))
                .append(parts[i].substring(1).toLowerCase())
                .append(' ');
        }
        res.append(parts[parts.length - 1].toUpperCase());
        return res.toString().trim();
    }
}
