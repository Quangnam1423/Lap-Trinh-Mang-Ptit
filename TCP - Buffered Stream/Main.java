/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package forex;

/**
 *
 * @author Administrator
 */

import java.io.*;
import java.net.*;
import java.util.*;
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.Exception
     */
    public static void main(String args[]) throws Exception {
        // create socket
        Socket socket = new Socket("203.162.10.109", 2208);
        
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        
        output.write("B21DCCN304;s4YLTKUw");
        output.newLine();
        output.flush();
        
        String str = input.readLine();
        
        int mark[] = new int[300];
        for (int i = 0; i < str.length(); i++) {
            if ((str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
                    || (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')) {
                mark[str.charAt(i)]++;
            }
        }
        
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            if (mark[str.charAt(i)]> 0) {
                result = result + str.charAt(i);
                mark[str.charAt(i)] = 0;
            }
        }
        
        output.write(result);
        output.newLine();
        output.flush();
    }
}
