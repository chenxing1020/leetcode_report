import java.io.*;

class Solution {
    public int[] productExceptSelf1(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums;
        int[] left = new int[n], right = new int[n];
        int[] result = new int[n];
        left[0] = nums[0];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            left[i] = nums[i] * left[i - 1];
            right[n - i - 1] = nums[n - i - 1] * right[n - i];
        }
        result[0] = right[1];
        result[n - 1] = left[n - 2];
        for (int i = 1; i < n - 1; i++) {
            result[i] = left[i - 1] * right[i + 1];
        }
        return result;
    }

    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length, left = 1, right = 1;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = left;
            left *= nums[i];
        }
        for (int j = n - 1; j >= 0; j--) {
            result[j] *= right;
            right *= nums[j];
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

            int[] ret = new Solution().productExceptSelf2(nums);

            String out = integerArrayToString(ret);

            System.out.print(out);
        }
    }
}