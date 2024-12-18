import java.io.*;
import java.net.*;

public class DataStream 
{

    public static void main(String[] args) throws Exception
    {
        Socket socket = new Socket("203.162.10.109", 2207);

        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        DataInputStream input = new DataInputStream(socket.getInputStream());

        String request = "B21DCCN556;SHWzW8g9";

        output.writeUTF(request);
        output.flush();

        String response = input.readUTF();
        int s = input.readInt();

        String ans = decodeCaesarCipher(response, s);

        output.writeUTF(ans);
        output.flush();

        socket.close();
    }

    // Phương thức giải mã mật mã Caesar
    public static String decodeCaesarCipher(String message, int shift) 
    {
        StringBuilder decodedMessage = new StringBuilder();
        
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char decodedChar = (char) (c + shift);
                
                // Kiểm tra nếu ký tự là chữ cái in hoa hoặc in thường
                if (Character.isUpperCase(c)) {
                    if (decodedChar < 'A') {
                        decodedChar += 26;  // Quay vòng lại từ 'Z'
                    }
                    if (decodedChar > 'Z')
                        decodedChar -= 26;
                } else if (Character.isLowerCase(c)) {
                    if (decodedChar < 'a') {
                        decodedChar += 26;  // Quay vòng lại từ 'z'
                    }
                    if (decodedChar > 'z')
                        decodedChar -=26;
                }
                
                decodedMessage.append(decodedChar);
            } else {
                // Nếu không phải chữ cái, giữ nguyên ký tự
                decodedMessage.append(c);
            }
        }
        
        return decodedMessage.toString();
    }
}
