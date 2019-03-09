import java.io.*;

class Solution1 {
    public int removeDuplicates(int[] nums) {
        int i = 1, ti = 0, len = nums.length, tmp;
        if (len == 0) return 0;
        else tmp = nums[0];
        while (i < len) {
            while (i < len && tmp == nums[i]) i++;
            int shift = i - ti - 1;
            len -= shift;
            for (int j = ti + 1; j < len; j++)
                nums[j] = nums[j + shift];
            ti++;
            tmp = nums[ti];
            i = ti + 1;
        }
        return len;
    }
}

class Solution2 {
    public int removeDuplicates(int[] nums) {
        int count = 0, i = 0, len = nums.length;
        if (len == 0) return 0;
        while (i < len) {
            while (i < len && nums[count] == nums[i]) i++;
            if (i < len) nums[++count] = nums[i];
        }
        return count + 1;
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

    public static String integerArrayToString(int[] nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for (int index = 0; index < length; index++) {
            int number = nums[index];
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in .readLine()) != null) {
            int[] nums = stringToIntegerArray(line);

            int ret = new Solution2().removeDuplicates(nums);
            String out = integerArrayToString(nums, ret);

            System.out.print(out);
        }
    }
}