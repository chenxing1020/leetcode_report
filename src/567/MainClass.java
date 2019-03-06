 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.io.IOException;
 import java.util.List;
 import java.util.ArrayList;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
    	int len1=s1.length();
        int len2=s2.length();
        int[] count=new int[26];
        int[] temp=new int[26];
        for (int i=0;i<len1;i++){
            int n=(int)s1.charAt(i)-97;
            count[n]++;
        }
        System.arraycopy(count, 0, temp, 0, 26);        //数组拷贝

        int i=0,index,k=0 ;
        while(i<=len2-len1){            
            index=(int)s2.charAt(i)-97;
            k=i;

            while ((temp[index]>0)&&(k<len2-1)){
                k++;
                temp[index]--;
                index=(int)s2.charAt(k)-97;
            }
            if (temp[index]>0) temp[index]--;           //考虑k=len2-1的情况
            if (checkZero(temp)) return true;
            else if (k==len2-1) return false;
            
            if (count[index]>=0) {              //下次匹配开始的位置
                index+=97;
                char ch=(char) index;
                i=s2.indexOf(String.valueOf(ch),i);
                i++;
            }
            else
                i=k+1;
            
            System.arraycopy(count, 0, temp, 0, 26);
        }
        return false;
    }

    public boolean checkZero(int[] a){          //查全零状态，即匹配完成。
        for (int i=0;i<a.length;i++){
            if (a[i]!=0) return false;
        }
        return true;
    }

}

public class MainClass {
    public static String stringToString(String input) {
        if (input == null) {
            return "null";
        }
        return input.toString();
    }
    
    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s1 = stringToString(line);
            line = in.readLine();
            String s2 = stringToString(line);
            
            boolean ret = new Solution().checkInclusion(s1, s2);
            
            String out = booleanToString(ret);
            
            System.out.println(out);
        }
    }
}

