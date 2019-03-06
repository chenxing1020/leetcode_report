import java.io.*;
import java.util.*;
import com.eclipsesource.json.*;

class Solution {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list=new ArrayList<>();
    
        int row=matrix.length;
        if (row==0) return list;
        int col=matrix[0].length;
        
        int i=0;
        int[][] flag=new int[row][col];

        //每个内循环都是从(i,i)开始的，所以要保证(i,i)在矩阵范围内
        //每个内循环都是遵照向右、向下、向左、向上的顺序进行的。
        while ((i<row)&&(i<col)){       
            int fx=i,fy=i;
            
            //向右
            while ((fy<col)&&(flag[fx][fy]==0)){  
                list.add(matrix[fx][fy]);
                flag[fx][fy]=1;     //标记已经取过的地址
                fy++;
            }
            
            //向下
            fx=i+1;fy--;
            while ((fx<row)&&(flag[fx][fy]==0)){
                list.add(matrix[fx][fy]);
                flag[fx][fy]=1;
                fx++;
            }

            //向左
            fx--;fy--;
            while ((fy>=0)&&(flag[fx][fy]==0)){
                list.add(matrix[fx][fy]);
                flag[fx][fy]=1;
                fy--;
            }

            //向上
            fx--;fy++;
            while ((fx>=0)&&(flag[fx][fy])==0){
                list.add(matrix[fx][fy]);
                flag[fx][fy]=1;
                fx--;
            }
            
            //开始新的大循环
            i++;

        }

        return list;
        
    }
}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
          return new int[0];
        }
    
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }
    
    public static int[][] stringToInt2dArray(String input) {
        JsonArray jsonArray = JsonArray.readFrom(input);
        if (jsonArray.size() == 0) {
          return new int[0][0];
        }
    
        int[][] arr = new int[jsonArray.size()][];
        for (int i = 0; i < arr.length; i++) {
          JsonArray cols = jsonArray.get(i).asArray();
          arr[i] = stringToIntegerArray(cols.toString());
        }
        return arr;
    }
    
    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }
    
        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }
    
    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[][] matrix = stringToInt2dArray(line);
            
            List<Integer> ret = new Solution().spiralOrder(matrix);
            
            String out = integerArrayListToString(ret);
            
            System.out.print(out);
        }
    }
}