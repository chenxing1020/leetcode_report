# 424.替换后的最长重复字符

## 问题描述  

给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。  

```c
示例 1:
输入:
s = "ABAB", k = 2
输出:
4
解释:
用两个'A'替换为两个'B',反之亦然。

示例 2:
输入:
s = "AABABBA", k = 1
输出:
4
解释:
将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
子串 "BBBB" 有最长重复字母, 答案为 4。
```

## 解题思路

&emsp;&emsp;这题也很明显是一个滑动窗口的题目，用max来记录窗口中出现次数最多的字母出现的次数，这样窗口在滑动的时候就会出现两种情况：

* max+k>=r-l+1(窗口长度)：此时说明窗口的长度仍然可以扩展，即右指针可以右移。
* max+k<r-l+1：此时说明当前窗口不能满足包含所有可重复字符，所以左指针右移。

```java
class Solution {
    public int characterReplacement(String s, int k) {
        if (s==null || s.equals("")) return 0;
        int[] count=new int[256];
        char[] c=s.toCharArray();
        int res=0;
        int l=0,r=0,max=0;
        count[c[0]]=1;
        while(r<c.length){
            max=findMax(count);
            if (max+k>=r-l+1) {
                res=Math.max(res,r-l+1);
                r++;
                if (r<c.length) count[c[r]]++;
            }
            else{
                count[c[l]]--;
                l++;
            }
        }
        return res;
    }
    public static int findMax(int[] arr){
        int max=0;
        for (int i=65;i<=90;i++) max=Math.max(max,arr[i]);
        return max;
    }
}
```

## 进阶

&emsp;&emsp;在看了大佬的解法之后发现，其实不需要每次都找到窗口中的最大值，因为在窗口收缩