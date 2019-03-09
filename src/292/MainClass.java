import java.io.*;

class Solution {
    public boolean canWinNim(int n) {
        if (n%4!=0) return true;
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
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);
            
            boolean ret = new Solution().canWinNim(n);
            
            String out = booleanToString(ret);
            
            System.out.print(out);
        }
    }
}