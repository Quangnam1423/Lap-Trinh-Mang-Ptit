package UDP;

import java.util.*;
import java.io.*;
import java.net.*;

public class ObjectUDP
{
    public static void main(String[] args) throws Exception
    {
        DatagramSocket client = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
        int port = 2209;

        // create requestMessage and send it to server
        String sendMessageString = ";B21DCCN556;RtTO51ZW";
        byte sendData[] = sendMessageString.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, port);
        client.send(sendPacket);

        // receive response from server and handle it
        byte receiveData[] = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length, serverAddress, port);
        client.receive(receivePacket);

        // get requestId Part from serverMessage
        byte requestId[] = new byte[8];
        System.arraycopy(receiveData, 0, requestId, 0, 8);

        // get customer object part from serverMessage
        byte CustomerBytes[] = new byte[receivePacket.getLength() - 8];
        System.arraycopy(receiveData, 8, CustomerBytes, 0, CustomerBytes.length);

        // read customer bytes[] as Object form
        ByteArrayInputStream byteStream = new ByteArrayInputStream(CustomerBytes);
        ObjectInputStream objectStream = new ObjectInputStream(byteStream);
        Customer customer = (Customer) objectStream.readObject();
        //normalizeName(customer.getName());
        // update information for customer-
        customer.setUserName(generateUserName(customer.getName()));
        customer.setName(formatName(customer.getName()));
        customer.setDayOfBirth(formatDate(customer.getDayOfBirth()));

        // create a byte stream for object to serialize
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);

        // write object into the stream to program can read object as bytes
        objectOutputStream.writeObject(customer);
        objectOutputStream.flush();

        // create message after update customer information
        byte updatedCustomer[] = byteOutputStream.toByteArray();
        byte updateSendMessage[] = new byte[8 + updatedCustomer.length];
        System.arraycopy(requestId, 0, updateSendMessage, 0, 8);
        System.arraycopy(updatedCustomer, 0 , updateSendMessage, 8, updatedCustomer.length);

        // send message to server after update customer information
        sendPacket = new DatagramPacket(updateSendMessage, updateSendMessage.length, serverAddress, port);
        client.send(sendPacket);

        // close the client connect
        client.close();
    }

    public static String normalizeName(String name) {
        String[] parts = name.split(" ");
        StringBuilder sb = new StringBuilder();
        if (parts.length > 1) {
            sb.append(parts[parts.length - 1].toUpperCase()).append(", ");
            for (int i = 0; i < parts.length - 1; i++) {
                sb.append(parts[i].substring(0, 1).toUpperCase()).append(parts[i].substring(1).toLowerCase()).append(" ");
            }
        } else {
            sb.append(name.toUpperCase());
        }
        System.out.println(sb.toString().trim());
        return sb.toString().trim();
    }

    private static String formatName(String name)
    {
        String returnName = "";
        String parts[] = name.trim().split("\\s+");
        if (parts.length > 1)
        {
            for (int i = 0 ; i < parts.length  - 1 ; i++ )
            {
                returnName += parts[i].substring(0 , 1).toUpperCase() + parts[i].substring(1).toLowerCase() + " ";
            }
            returnName = parts[parts.length - 1].toUpperCase() + ", " + returnName;
        }
        else
        {
            returnName = name.toUpperCase();
        }
        System.out.println(returnName.trim());
        return returnName.trim();
    }

    private static String formatDate(String date)
    {   
        String parts[] = date.trim().split("-");
        String returnDate = "";
        returnDate = parts[1] + "/" + parts[0] + "/" + parts[2];
        return returnDate.trim();
    }

    private static String generateUserName(String name)
    {
        String userName = "";
        String parts[] = name.trim().split("\\s+");
        for (int i = 0 ; i < parts.length - 1 ; i++ )
        {
            userName += parts[i].substring(0 , 1).toLowerCase();
        }
        userName += parts[parts.length - 1].toLowerCase();
        return userName.trim();
    }
}