import java.io.*;

class Solution {
    public String reverseString(String s) {
        int len=s.length();
        char[] c=new char[len];
        c=s.toCharArray();
        for (int i=0;i<len/2;i++){
            char temp=c[len-i-1];
            c[len-i-1]=c[i];
            c[i]=temp;
        }
        return String.copyValueOf(c);
    }
}

public class MainClass {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            
            String ret = new Solution().reverseString(line);
            
            String out = (ret);
            
            System.out.print(out);
        }
    }
}