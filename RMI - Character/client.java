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
        
        CharacterService charService = (CharacterService) registry.lookup("RMICharacterService");
        
        String studentCode = "B21DCCN304";
        String qCode = "SGiaX9jl";
        
        Object responseData = charService.requestCharacter(studentCode, qCode);
        
        String input = (String) responseData;
        String[] parts = input.split(";");
        
        String output = encode(parts[0], parts[1]);
        charService.submitCharacter(studentCode, qCode, output);
    }
    
    private static String encode(String key, String str) {
        StringBuilder res = new StringBuilder("");
        
        for (int i = 0; i < str.length(); i++) {
            Character keyChar = key.charAt(i % key.length());
            int x = (str.charAt(i) - 'a' + keyChar - 'a') % 26 + 'a';
            res.append((char) x);
        }
        return res.toString();
    }
}
