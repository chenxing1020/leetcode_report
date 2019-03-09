import java.io.*;
import java.util.*;

class Solution1{
    public int singleNumber(int[] nums){
        HashSet<Integer> hs=new HashSet<Integer>();
        for (int num:nums){
            if (hs.contains(num)) hs.remove(num);
            else hs.add(num);
        }
        return hs.iterator().next();
    }
}

class Solution2 {
    public int singleNumber(int[] nums) {
        int result=0;
        for (int num:nums){
            result=result^num;
        }
        return result;
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
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            
            int ret = new Solution2().singleNumber(nums);
            
            String out = String.valueOf(ret);
            
            System.out.print(out);
        }
    }
}