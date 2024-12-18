package UDP;

import java.io.*;
import java.util.*;
import java.net.*;

public class DataType
{
    public static void main(String[] agrs) throws Exception
    {
        DatagramSocket client = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
        int port = 2207;

        String requestMessage = ";B21DCCN556;GavzdKOw";
        byte sendData[] = requestMessage.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, port);
        client.send(sendPacket);

        byte receiveData[] = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length, serverAddress, port);
        client.receive(receivePacket);

        String serverMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println(serverMessage);

        String parts[] = serverMessage.trim().split(";");
        String requestId = parts[0];
        int n = Integer.parseInt(parts[1]);

        ArrayList<Integer> primeNumber = getPrimeNumber();
        String resultMessage = requestId + ";";

        for (int i = 0 ; i < n ; i++ )
        {
            resultMessage += Integer.toString(primeNumber.get(i)) + ",";
        }
        resultMessage = resultMessage.substring(0, resultMessage.length() - 1);
        byte result[] = resultMessage.getBytes();

        sendPacket = new DatagramPacket(result, result.length, serverAddress, port);
        client.send(sendPacket);

        client.close();
    }

    public static ArrayList<Integer> getPrimeNumber()
    {
        ArrayList<Integer> primeNumber = new ArrayList<>();
        boolean numbers[] = new boolean[1000001];

        Arrays.fill(numbers, true);

        numbers[0] = false;
        numbers[1] = false;
        for (int i = 2 ; i * i < 1000001 ; i++ )
        {
            if (numbers[i])
            {
                for (int j = i * i ; j < 1000001 ; j += i)
                {
                    numbers[j] = false;
                }
            }
        }
        for (int i = 2 ; i < 1000001 ; i++ )
        {
            if (numbers[i])
            {
                primeNumber.add(i);
            }
        }
        return primeNumber;
    }
}