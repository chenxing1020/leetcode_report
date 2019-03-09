import java.io.*;
import java.util.*;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1=nums1.length;
        int len2=nums2.length;
        int i=(len1+1)/2;
        int j=(len1+len2+1)/2-i;
        System.out.println(i+" "+j);
        double result=0;
        int max=getLeftMax(nums1,nums2,i-1,j-1);
        int min=getRightMin(nums1,nums2,i,j);
        System.out.println(max+" "+min);
        while (max>min){
            i++;
            j--;
            System.out.println(i+" "+j);
            max=getLeftMax(nums1,nums2,i-1,j-1);
            min=getRightMin(nums1,nums2,i,j);
        }
        return (double)(max+min)/2;
    }
    public int getLeftMax(int[] a,int[] b,int m,int n){
        int n1=0,n2=0;
        if ((m>=0)&&(m<a.length)) n1=a[m];
        if ((n>=0)&&(n<b.length)) n2=b[n];
        return Math.max(n1,n2);
    }
    public int getRightMin(int[] a,int[] b,int m,int n){
        int n1=a[a.length],n2=b[b.length];
        if ((m>=0)&&(m<a.length)) n1=a[m];
        if ((n>=0)&&(n<b.length)) n2=b[n];
        return Math.min(n1,n2);
    }
}

public class MainClass4 {
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