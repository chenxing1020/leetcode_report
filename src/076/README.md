# 076.最小覆盖子串

## 问题描述

给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。

```c
示例：
输入: S = "ADOBECODEBANC", T = "ABC"
输出: "BANC"
```

说明：  

* 如果S中不存在这样的子串，则返回空字符串""。
* 如果S中存在这样的子串，我们保证它是唯一的答案。

## 解决方案

&emsp;&emsp;上周末的时候做了一个腾讯提前批的笔试题，大概是这道题的一个简化版，这种一个“串的子串包含另一个串”的题带有很强烈的暗示，提示这种类型的题目可以用滑动窗口。  
&emsp;&emsp;首先，因为Ascii码有256位，所以用一个256位的count数组记录T中出现的字符以及它出现的次数，相当于窗口内需要出现字符的次数。  
&emsp;&emsp;滑动窗口的思路大概有3个步骤。第1步，让窗口的右指针右移，并让右指针所指的字符c的`count[c]`减1，如果`count[c]`的值是大于等于0的，即说明当前的字符c是T中的元素，这个时候用一个`nums`记录值来记录当前窗口中包含T串元素的个数。  
&emsp;&emsp;第2步，当`nums==T.length()`时，说明当前的窗口中已经包含了T的所有元素，这个时候，如果`count[s[l]]<0`，说明当前窗口的头部并不是T中的元素（因为nums的值等于T.length()时，T中元素的count值都是刚好减到0的），所以我们让左指针右移，同时当元素移出窗口的时候就将它的count值加1，直到找到头部元素是T中元素的情况，这样的窗口就是当前取到T中所有元素的最小窗口，这个时候将窗口长度和min值比较，如果找到新的最小值就记录。  
&emsp;&emsp;第3步，左指针右移，继续上面的2个步骤，，开始新一轮的窗口搜索，直到窗口长度小于T的长度，或者右指针无法移动，即`l>S.length()-T.length() || r>=S.length()`。

```java
class Solution {
    public String minWindow(String s, String t) {
        if (s.length()<t.length()) return "";
        char[] a=s.toCharArray();
        char[] b=t.toCharArray();
        int[] count=new int[256];
        for (int i=0;i<b.length;i++) count[b[i]]++;

        int l=0,r=0,start=-1,min=a.length+1,nums=0;
        while (l<=a.length-b.length && r<a.length){
            count[a[r]]--;
            //记录当前窗口中T中元素的个数
            if (count[a[r]]>=0) nums++;
            if (nums==b.length){
                //使窗口第一个值为T中元素
                while (count[a[l]]<0) {
                    count[a[l]]++;
                    l++;
                }
                if (r-l+1<min){
                    min=r-l+1;
                    start=l;
                }
                //l右移开始下一次窗口探索
                count[a[l]]++;
                l++;
                nums--;
            }
            r++;
        }
        if (start==-1) return "";
        else return s.substring(start,start+min);
    }
}
```