# 014.最长公共前缀

## **问题描述**  

编写一个函数来查找字符串数组中的最长公共前缀。  

如果不存在公共前缀，返回空字符串 `""`。  

**示例 1**：  
输入: ["flower","flow","flight"]  
输出: "fl"  

**示例 2**：  
输入: ["dog","racecar","car"]  
输出: ""  
解释: 输入不存在公共前缀。  

**说明**：  
所有输入只包含小写字母`a-z`。  

## **解决方案**

&emsp;&emsp;我的思路是用第一个字符串为基准（实际上也可以先找出长度最短的字符串作为基准），然后按位对所有字符串进行依次比较，一旦比较不匹配就直接退出循环，否则所有字符串都匹配完了再将当前位的字符压入StringBuilder。

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length==0) return "";
        if (strs.length==1) return strs[0];
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<strs[0].length();i++){
            char c=strs[0].charAt(i);
            int j=1;
            while (j<strs.length){
                if (i>=strs[j].length()) return sb.toString();
                if (c!=strs[j].charAt(i)) return sb.toString();
                j++;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
```