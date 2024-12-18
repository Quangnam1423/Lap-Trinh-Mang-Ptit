package TCP;


import java.io.*;
import java.net.*;
import java.util.*;

public class ObjectStream
{
    public static void main(String[] args) throws Exception
    {
        Socket socket = new Socket("203.162.10.109", 2209);
        socket.setSoTimeout(5000);
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

        String request = "B21DCCN556;KelGBmOY";
        output.writeObject(request);
        output.flush();

        Product product = (Product) input.readObject();
        product.setDiscount(calculateDiscount(product.getPrice()));

        output.writeObject(product);
        output.flush();
        
        socket.close();
    }

    public static int calculateDiscount(double price)
    {
        int discount = 0;

        int IntegerPart = (int) price;

        while (IntegerPart != 0)
        {
            discount += IntegerPart % 10;
            IntegerPart /= 10;
        }

        return discount;
    }
}