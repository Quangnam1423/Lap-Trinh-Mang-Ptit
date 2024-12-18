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
        Socket socket = new Socket("203.162.10.109", 2207);
        
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        
        output.writeUTF("B21DCCN304;j1ntbgF8");
        output.flush();
        
        int n = input.readInt();
        int arr[] = new int[n];
        int sum = 0;
        float a=0, p=0;
        for (int i = 0; i < n; i++) {
            arr[i] = input.readInt();
            sum += arr[i];
        }
        a = sum *1.0f /n;
        for (int i = 0; i < n; i++) {
            p += Math.pow(arr[i]-a, 2);
        }
        p = p / n;
        
        output.writeInt(sum);
        output.writeFloat(a);
        output.writeFloat(p);

        socket.close();
    }
}
