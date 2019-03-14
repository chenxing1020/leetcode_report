# 003.无重复字符的最长子串

## 问题描述

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。  

```c
示例 1:
输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

示例 2:
输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:
输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
```

## 解决方案

&emsp;&emsp;这道题可以用哈希表来做，也可以用滑动窗口来做，hash表的方法比较暴力，这里只分享滑动窗口的做法。  
&emsp;&emsp;用一个256位的数组`count`来记录窗口内每个字符出现的次数，当`count[s[r]]`的值大于1的时候，说明窗口内出现重复字符，这时，让左指针移动到`count[l]>1`的位置，让下一次窗口从重复字符的后一位开始移动，直到遍历整个字符串。

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.equals("") || s==null) return 0;
        int l=0,r=0;
        int[] count=new int[256];
        char[] a=s.toCharArray();

        int max=1;
        while (r<a.length){
            count[a[r]]++;
            if (count[a[r]]>1) {
                if (r-l>max) max=r-l;
                //找到重复元素的位置，作为下一次窗口起点
                while (count[a[l]]==1){
                    count[a[l]]--;
                    l++;
                }
                count[a[l]]--;
                l++;
            }else max=max>(r-l+1)?max:(r-l+1);
            r++;
        }
        return max;
    }
}
```