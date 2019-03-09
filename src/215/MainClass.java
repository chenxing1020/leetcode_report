import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Arrays;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        return findK(nums, 0, nums.length - 1, k);
    }

    public int findK(int[] arr, int start, int end, int k) {

        int lo = start;
        int hi = end;
        int pivot = arr[start];
        while (lo < hi) {
            while (lo < hi && arr[hi] <= pivot) hi--;
            while (lo < hi && arr[lo] >= pivot) lo++;
            if (lo < hi) {
                int temp = arr[lo];
                arr[lo] = arr[hi];
                arr[hi] = temp;
            }
        }
        arr[start] = arr[lo];
        arr[lo] = pivot;

        int n = lo - start + 1;
        if (n < k) return findK(arr, lo + 1, end, k - n);
        if (n > k) return findK(arr, start, lo - 1, k);
        return arr[lo];
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
            line = in .readLine();
            int k = Integer.parseInt(line);

            int ret = new Solution().findKthLargest(nums, k);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}