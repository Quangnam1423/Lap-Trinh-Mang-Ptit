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
        String hostname = "203.162.10.109";
        int port = 2206;

        Socket socket = new Socket(hostname, port);
        socket.setSoTimeout(5000);
        
        // Khởi tạo luồn gửi và nhận dữ liệu
        OutputStream output = socket.getOutputStream();
        InputStream input = socket.getInputStream();
        
//         Gửi mã sinh viên và mã câu hỏi
        String request = "B21DCCN304;dPXOO6Ry";
        output.write(request.getBytes());
        output.flush();
//        
//        // Nhận phản hồi
        byte response[] = new byte[1024];
        input.read(response);
        String serverResponse = new String(response, 0, response.length);
        ArrayList <Integer> arr = new ArrayList<>();
        String parts[] = serverResponse.split(",");
        System.out.println(serverResponse);
        
        for (int i = 0; i < parts.length; i++) {
            arr.add(Integer.parseInt(parts[i].trim()));
        }
        Collections.sort(arr);
        
        int value1 = arr.get(arr.size() - 2), value2 = arr.get(arr.size() - 1);
        int mindis = value2 - value1;
        for (int i = arr.size()-2; i > 0; i--) {
            if (arr.get(i) - arr.get(i-1) < mindis) {
                value1 = arr.get(i-1);
                value2 = arr.get(i);
                mindis = value2 - value1;
            }
        }
                
        String result = String.valueOf(mindis) + "," + String.valueOf(value1) + "," + String.valueOf(value2);
        output.write(result.getBytes());
        output.flush();
        
        socket.close();
    }
}
