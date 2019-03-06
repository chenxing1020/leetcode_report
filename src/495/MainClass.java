import java.io.*;

class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int sum=0;
        if (timeSeries.length==0) return 0;
        for (int i=0;i<timeSeries.length-1;i++){
            if (timeSeries[i]+duration-1>=timeSeries[i+1]) sum+=timeSeries[i+1]-timeSeries[i];
            else sum+=duration;
        }
        sum+=duration;
        return sum;
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
            int[] timeSeries = stringToIntegerArray(line);
            line = in.readLine();
            int duration = Integer.parseInt(line);
            
            int ret = new Solution().findPoisonedDuration(timeSeries, duration);
            
            String out = String.valueOf(ret);
            
            System.out.print(out);
        }
    }
}