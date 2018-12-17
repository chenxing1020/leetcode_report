# 567.字符串的排列

## 问题描述
给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。换句话说，第一个字符串的排列之一是第二个字符串的子串。 

---

## 样例数据  
示例1:
```
输入: s1 = "ab" s2 = "eidbaooo"
输出: True
解释: s2 包含 s1 的排列之一 ("ba").
```
示例2:
```
输入: s1= "ab" s2 = "eidboaoo"
输出: False
 ```
注意：输入的字符串只包含小写字母,两个字符串的长度都在 [1, 10,000] 之间.

---
## 初步思路
&emsp;&emsp;在看到题目之后的第一个想法就是，用全排列+子字符串匹配来做，但是很快就否认了这个想法，全排列实在太占时间和空间，再加上字符串匹配，肯定是不现实的。

&emsp;&emsp;随后，想到了List集合类，想法是将字符串s1按字符推进集合tempList，然后按字符遍历s2字符串，每出现一个在s1中的字符即指针后移，并在s1中删除相应字符，这样就就解决了排列匹配的问题，但是实际操作发现集合操作不够简洁，完全可以用String类自身来进行操作。

---
## 初步实现（TLE未通过）

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
    	int i=0,index,len1=s1.length(),len2=s2.length();
        String ss1=s1,ss=null;
        while(i<=len2-len1){					//遍历
            ss=s2.substring(i,i+1);
            index=ss1.indexOf(ss);
            k=i;
            while ((index>=0)&&(k<len2-1)){				//匹配
                k++;
                ss1=ss1.replaceFirst(ss,"");
                ss=s2.substring(k,k+1);
                index=ss1.indexOf(ss);
            }
            if (index>=0) ss1=ss1.replaceFirst(ss,"");
            if (ss1.length()==0) return true;
            else if (k==len2-1) return false;
            ss1=s1;
            if (ss1.indexOf(ss)>=0) {			//剪枝
                i=s2.indexOf(ss,i);		//当前未匹配元素如果是s1中的元素，则下次搜索从i位置后第一个该元素位置开始
                i++;
            }
            else i=k+1;
        }
        return false;
    }
}
```
---
## bug改进
&emsp;&emsp;但是提交之后发现有两组数据出现TLE的情况，于是便开始想着对搜索剪枝，在匹配失败的k指针处如果出现s1串中含有的元素，即说明是重复元素数量超过s1中该元素上限，下次搜索应该从i位置后第一个该元素开始（有点类似与KMP中的最大前缀，只不过这个题目是乱序，只考虑一位即可）。但是不管怎么剪枝，还是TLE，分析原因是因为每次删除和查找元素都占了O(n)的时间复杂度，这样，当字符串数量增加后时间复杂度会几何倍数增长。

&emsp;&emsp;于是开始考虑节省查找和删除的次数，因为s1和s2中只有英文小写字母，所以可以用count[26]数组来计数每个字母出现的次数，删除元素则可以对count数组中的数量进行-1操作，果然直接通过，所以以后解决问题要结合实际，找出最优方法。（修改后代码见附件）

