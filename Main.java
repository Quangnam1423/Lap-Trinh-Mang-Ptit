import java.io.*;
import java.net.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Socket socket = new Socket("203.162.10.109", 2206);
        socket.setSoTimeout(5000);

        OutputStream output = socket.getOutputStream();
        InputStream input = socket.getInputStream();

        String request = "B21DCCN556;md7A37qT";

        output.write(request.getBytes());
        output.flush();

        byte response[] = new byte[1024];
        input.read(response);

        String serverResponse = new String(response, 0, response.length);
        String parts[] = serverResponse.trim().split(",");

        int array[] = new int[parts.length];
        for (int i = 0 ; i < parts.length ; i++ )
        {
            array[i] = Integer.parseInt(parts[i]);
        }

        ArrayList<Integer> longest = findLIS(array);
        String ans = "";
        for (Integer number : longest)
        {
            System.out.print(number + " ");
            ans += number + ",";
        }
        ans = ans.substring(0 , ans.length() - 1) + ";" + longest.size();
        System.out.println(ans);
        output.write(ans.getBytes());
        output.flush();

        socket.close();
    }

    public static ArrayList<Integer> findLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return new ArrayList<>();
        
        // dp[i] lưu trữ độ dài LIS kết thúc tại chỉ số i
        int[] dp = new int[n];
        // prev[i] lưu trữ chỉ số phần tử trước trong chuỗi LIS kết thúc tại i
        int[] prev = new int[n];
        
        // Khởi tạo mảng dp và prev
        Arrays.fill(dp, 1);  // Mỗi phần tử có thể tự tạo chuỗi dài 1
        Arrays.fill(prev, -1);
        
        int maxLength = 1;   // Độ dài LIS lớn nhất
        int lastIndex = 0;   // Chỉ số phần tử cuối cùng trong LIS dài nhất
        
        // Tính LIS bằng Dynamic Programming
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                lastIndex = i;
            }
        }
        
        // Khôi phục chuỗi LIS
        ArrayList<Integer> lis = new ArrayList<>();
        for (int i = lastIndex; i != -1; i = prev[i]) {
            lis.add(nums[i]);
        }
        Collections.reverse(lis);  // Đảo lại để có chuỗi LIS từ đầu đến cuối
        
        return lis;
    }
}