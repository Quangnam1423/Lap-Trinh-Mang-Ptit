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
        
        DataService dataService = (DataService) registry.lookup("RMIDataService");
        
        String studentCode = "B21DCCN304";
        String qCode = "OTYAjB3S";
        
        Object responseData = dataService.requestData(studentCode, qCode);
        
        int N = (Integer) responseData;
        
        List<Integer> primeNumbers = findPrimes(N);
        
        dataService.submitData(studentCode, qCode, primeNumbers);
    }
    
    private static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i*i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    
    private static List<Integer> findPrimes(int N) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }
}
