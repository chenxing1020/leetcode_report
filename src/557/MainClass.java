import java.io.*;
import java.util.*;

//TLE
class Solution1 {
    public String reverseWords(String s) {
        String result="";
        if (s!=null) {
            String[] arr=s.split(" ");
            int aLen=arr.length;
            for (int i=0;i<aLen;i++){
                char[] c=arr[i].toCharArray();
                for (int j=c.length-1;j>=0;j--){
                    result+=c[j];
                }
                if (i!=aLen-1) result+=" ";
            }
        }
        return result;
    }
}

//StringBuilder
class Solution2 {
    public String reverseWords(String s) {
        StringBuilder result=new StringBuilder();
        if (s!=null) {
            String[] arr=s.split(" ");
            int aLen=arr.length;
            for (int i=0;i<aLen;i++){
                String ss=arr[i];
                for (int j=ss.length()-1;j>=0;j--){
                    result.append(ss.charAt(j));
                }
                if (i!=aLen-1) result.append(" ");
            }
        }
        return result.toString();
    }
}

//char[]
class Solution3{
    public String reverseWords(String s) {
        if (s==null) return null;
        char[] c=s.toCharArray();
        int start=0;
        for (int i=0;i<c.length;i++){
            if (c[i]==' '){
                reverseWord(c,start,i);
                start=i+1;
            }
        }
        if (start<c.length) reverseWord(c,start,c.length);
        return String.copyValueOf(c);
    }

    public static void reverseWord(char[] ch,int start,int end){
        for (int i=start;i<(end+start)/2;i++){
            char temp=ch[end+start-1-i];
            ch[end+start-1-i]=ch[i];
            ch[i]=temp;
        }
    }
}

public class MainClass {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {

            String ret = new Solution3().reverseWords(line);
            
            String out = (ret);
            
            System.out.print(out);
        }
    }
}