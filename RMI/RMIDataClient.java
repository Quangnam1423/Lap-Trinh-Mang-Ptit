package RMI;

import java.util.*;
import java.io.*;
import java.rmi.*;
import java.rmi.registry.*;

public class RMIDataClient
{
    public static void main(String[] args) throws Exception 
    {
        Registry registry = LocateRegistry.getRegistry("203.162.10.109");
        DataService dataService = (DataService) registry.lookup("RMIDataService");

        String studentCode = "B21DCCN556";
        String qCode = "9JpWmZN1";

        String receiveString = (String) dataService.requestData(studentCode, qCode);

        String parts[] = receiveString.trim().split(";");
        String array[] = parts[0].trim().split(",");
        int n = Integer.parseInt(parts[1].trim());

        int numbers[] = new int[array.length];
        for (int i = 0 ; i < array.length ; i++ )
        {
            numbers[i] = Integer.parseInt(array[i].trim());
        }
        Arrays.sort(numbers);
        for (int number : numbers)
            System.out.print(number + " ");
        System.out.println();
        int result = numbers[numbers.length - n];
        dataService.submitData(studentCode, qCode, result);
    }
}