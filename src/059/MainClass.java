import java.io.*;
import java.util.*;

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] result=new int [n][n];
        result[0][0]=1;
        int num=1;
        for (int i=0;i<n-1;i++){
            int nLen=n-2*i;
            
            //toRight
            for (int j=0;j<nLen;j++){
                result[i][i+j]=num;
                num++;
            }
            //toDown
            for (int j=1;j<nLen;j++){
                result[i+j][i+nLen-1]=num;
                num++;
            }
            //toLeft
            for (int j=nLen-2;j>=0;j--){
                result[i+nLen-1][i+j]=num;
                num++;
            }
            //toUp
            for (int j=nLen-2;j>0;j--){
                result[i+j][i]=num;
                num++;
            }
        }
        return result;
    }
}

public class MainClass {
    public static String int2dArrayToString(int[][] array) {
        if (array == null) {
            return "null";
        }
        if (array.length == 0) {
            return "[]";
        }
    
        StringBuilder sb = new StringBuilder("[");
        for (int[] item : array) {
            for (int item1 : item){
                sb.append(Integer.toString(item1));
                sb.append(",");
            }
        }
    
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);
            
            int[][] ret = new Solution().generateMatrix(n);
            
            String out = int2dArrayToString(ret);
            
            System.out.print(out);
        }
    }
}