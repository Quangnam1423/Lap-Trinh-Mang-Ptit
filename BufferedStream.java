import java.util.*;
import java.io.*;
import java.net.*;

public class BufferedStream
{
    public static void main(String[] args) throws Exception
    {
        Socket socket = new Socket("203.162.10.109", 2208);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        output.write("B21DCCN556;2Jb5aFHi");
        output.newLine();
        output.flush();

        String serverResponse = input.readLine();
        System.out.println(serverResponse);

        String[] words = serverResponse.trim().split("\\s+");

        String longestWord = "";
        for (String word : words)
        {
            if (word.length() > longestWord.length())
            {
                longestWord = word;
            }
        }
        int position = serverResponse.indexOf(longestWord);

        output.write(longestWord);
        output.newLine();
        output.flush();

        output.write(String.valueOf(position));
        output.newLine();
        output.flush();

        socket.close();
    }
}