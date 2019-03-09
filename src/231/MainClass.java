import java.io.*;

class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n<1) return false;
        while (n>1) {
            if (n%2!=0) return false;
            n/=2;
        }
        return true;
    }
}

public class MainClass {
    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);
            
            boolean ret = new Solution().isPowerOfTwo(n);
            
            String out = booleanToString(ret);
            
            System.out.print(out);
        }
    }
}