//package UDP;

import java.io.*;
import java.net.*;
import java.util.*;

public class UDPString
{
    public static void main(String[] args) throws Exception
    {
        DatagramSocket client = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
        client.setSoTimeout(5000);
        int port = 2208;

        // send request message
        String message = ";B21DCCN556;QYC69Wdg";
        byte sendData[] = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, port);
        client.send(sendPacket);

        // receive message from server
        byte receiveData[] = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length, serverAddress, port);
        client.receive(receivePacket);

        String serverMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println(serverMessage);

        String parts[] = serverMessage.split(";");

        HashSet<Character> set = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (Character c : parts[1].toCharArray())
        {
            if (Character.isLetter(c))
            {
                if (!set.contains(c))
                {
                    set.add(c);
                    stringBuilder.append(c);
                }
            }
        }

        // send result
        String sendMessageString = parts[0] + ";" + stringBuilder.toString();
        System.out.println(sendMessageString);
        byte result[] = sendMessageString.getBytes();
        sendPacket = new DatagramPacket(result, result.length, serverAddress, port);
        client.send(sendPacket);
        client.close();
    }
}