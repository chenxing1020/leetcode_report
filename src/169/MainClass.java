import java.io.*;
import java.util.*;

class Solution1 {
    public int majorityElement(int[] nums) {
        Map < Integer, Integer > map = new HashMap < Integer, Integer > ();
        int n = nums.length;
        if (n == 1) return nums[0];
        for (int num: nums) {
            if (map.containsKey(num)) {
                int tmp = map.get(num) + 1;
                if (tmp > n / 2) return num;
                map.put(num, tmp);
            } else map.put(num, 1);
        }
        return 0;
    }
}

class Solution2 {
    public int majorityElement(int[] nums) {
        int count = 0, result = 0;
        for (int num: nums) {
            if (count == 0) result = num;
            if (result == num) count++;
            else count--;
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
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in .readLine()) != null) {
            int[] nums = stringToIntegerArray(line);

            int ret = new Solution2().majorityElement(nums);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}