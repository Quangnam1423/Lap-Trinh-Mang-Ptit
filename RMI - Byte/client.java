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

public class client {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        Registry registry = LocateRegistry.getRegistry("203.162.10.109");
        ByteService byteService = (ByteService) registry.lookup("RMIByteService");
        
        String studentCode = "B21DCCN304";
        String qCode = "mVMxO532";
        byte receivedData[] = byteService.requestData( studentCode, qCode);
        
        byte[] compressedData = compressRLE(receivedData);
        
        byteService.submitData(studentCode, qCode, compressedData);
    }
    
    private static byte[] compressRLE(byte[] data) {
        if (data == null || data.length == 0) return new byte[0];
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int cnt = 1;
        for (int i = 1; i < data.length; i++) {
            if (data[i] == data[i - 1]) {
                cnt++;
            } else {
                outputStream.write(data[i-1]);
                outputStream.write(cnt);
                cnt = 1;
            }
        }
        
        outputStream.write(data[data.length - 1]);
        outputStream.write(cnt);
        
        return outputStream.toByteArray();
    }
}
