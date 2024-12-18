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
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String args[]) throws Exception {
        DatagramSocket client = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
        int port = 2207;
        
        String message = ";B21DCCN304;Fx2Igfkm";
        byte sendData[] = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, port);
        client.send(sendPacket);
        
        byte receivedata[] = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receivedata, receivedata.length);
        client.receive(receivePacket);
        
        String serverMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
        
        String parts[] = serverMessage.split(";");
        int sum = 0;
        for (int i = 0; i < parts[1].length(); i++) {
            sum = sum + parts[1].charAt(i) - '0';
        }
        parts[0] = parts[0]+ ";";
        parts[0] = parts[0]+ String.valueOf(sum);
        
        byte result[] = parts[0].getBytes();
        sendPacket = new DatagramPacket(result, result.length, serverAddress, port);
        client.send(sendPacket);
        
        client.close();
    }
}
