import java.io.*;
import java.util.*;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int i = 0, j = 0, left = 0, right = 0;
        if (m == 0) right = nums2[0];
        else if (n == 0) right = nums1[0];
        while (i < m || j < n) {
            left = right;
            if ((j == n) || (i < m && j < n && nums1[i] <= nums2[j])) right = nums1[i++];
            else right = nums2[j++];

            if (i + j == (m + n + 1) / 2 + 1) break;
        }
        if ((m + n) % 2 == 0) return (double)(left + right) / 2;
        else return (double) left;
    }
}

public class MainClass2 {
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
            int[] nums1 = stringToIntegerArray(line);
            line = in .readLine();
            int[] nums2 = stringToIntegerArray(line);

            double ret = new Solution().findMedianSortedArrays(nums1, nums2);

            System.out.print(ret);
        }
    }
}