/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package TCP;

/**
 *
 * @author Administrator
 */

import java.util.*;
import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2209);
        
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        
        output.writeObject("B21DCCN304;FQMc6L7D");
        
        Customer customer = (Customer) input.readObject();
        System.out.println("Received customer: " + customer);
        
        customer.setUserName(formatUserName(customer.getName()));
        customer.setName(formatName(customer.getName()));
        customer.setDayOfBirth(formatDate(customer.getDayOfBirth()));
                
        output.writeObject(customer);
        
        socket.close();
    }
    
    private static String formatName(String name) {
        String parts[] = name.split("\\s+");
        String lastName = parts[parts.length - 1].toUpperCase();
        
        StringBuilder formatted = new StringBuilder(lastName);
        formatted.append(",");
        for (int i = 0; i < parts.length - 1; i++) {
            formatted.append(" ").append(Character.toUpperCase(parts[i].charAt(0)))
                    .append(parts[i].substring(1).toLowerCase());
        }
        return formatted.toString();
    }
    
    private static String formatDate(String date) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date parseDate = inputFormat.parse(date);
        return outputFormat.format(parseDate);
    }
    
    private static String formatUserName(String username) {
        String parts[] = username.split("\\s+");
        StringBuilder res = new StringBuilder();
        
        for (int i = 0; i < parts.length - 1; i++) {
            res.append(parts[i].charAt(0));
        }
        res.append(parts[parts.length - 1]);
        return res.toString().toLowerCase();
    }
}
