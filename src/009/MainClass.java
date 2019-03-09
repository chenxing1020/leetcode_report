import java.io.*;

class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int tmp = x, result = 0;
        while (tmp > 0) {
            result = result * 10 + tmp % 10;
            tmp /= 10;
        }
        if (x == result) return true;
        else return false;
    }
}

public class MainClass {
    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in .readLine()) != null) {
            int x = Integer.parseInt(line);

            boolean ret = new Solution().isPalindrome(x);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }
}