import java.io.*;
import java.util.*;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m=nums1.length,n=nums2.length;
        int i=0,j=0,flag=0;
        int[] merge=new int[m+n];
        while (i<m || j<n){
            while ( i<m && j<n && nums1[i]<=nums2[j]){
                merge[flag]=nums1[i];
                i++;
                flag++;
            }
            while ( i<m && j<n && nums1[i]>nums2[j]){
                merge[flag]=nums2[j];
                j++;
                flag++;
            }
            //nums1中的数已完全遍历
            while (i==m && j<n){
                merge[flag]=nums2[j];
                j++;
                flag++;
            }
            //nums2中的数已完全遍历
            while (i<m && j==n){
                merge[flag]=nums1[i];
                i++;
                flag++;
            }
        }
        if (flag % 2==0) return (double)(merge[flag/2-1]+merge[flag/2])/2;
        else return (double)merge[flag / 2];
    }
}

public class MainClass1 {
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
            int[] nums1 = stringToIntegerArray(line);
            line = in.readLine();
            int[] nums2 = stringToIntegerArray(line);
            
            double ret = new Solution().findMedianSortedArrays(nums1, nums2);
            
            System.out.print(ret);
        }
    }
}