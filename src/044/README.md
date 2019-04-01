# 044.通配符匹配

## 问题描述

给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。  

    '?' 可以匹配任何单个字符。
    '*' 可以匹配任意字符串（包括空字符串）。

两个字符串完全匹配才算匹配成功。  

说明:  

* s 可能为空，且只包含从 a-z 的小写字母。
* p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。

```c
示例 1:
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。

示例 2:
输入:
s = "aa"
p = "*"
输出: true
解释: '*' 可以匹配任意字符串。

示例 3:
输入:
s = "cb"
p = "?a"
输出: false
解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。

示例 4:
输入:
s = "adceb"
p = "*a*b"
输出: true
解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".

示例 5:
输入:
s = "acdcb"
p = "a*c?b"
输入: false
```

## 解决方案

&emsp;&emsp;这题是leetcode-010正则表达式的匹配的弱化版本，010题中的`'*'`是需要匹配前一个字符的0次、1次和多次的，而本题的`'*'`可以匹配任何字符串的。所以，设状态`dp[i][j]`表示`s的区间[0,i-1]`和`p的区间[0,j-1]`的匹配程度，状态方程的转移为：

* p[j-1]!='*'：比较s[i-1]和p[j-1]；
* p[j-1]=='*'：匹配0次、1次和n次的情况

```java
class Solution {
    public boolean isMatch(String s, String p) {
        char[] sArr=s.toCharArray(),pArr=p.toCharArray();
        boolean[][] dp=new boolean[sArr.length+1][pArr.length+1];
        dp[0][0]=true;
        //初始化
        for (int j=1;j<=pArr.length;j++) if (pArr[j-1]=='*') dp[0][j]=dp[0][j-1];
        for (int i=1;i<=sArr.length;i++){
            for (int j=1;j<=pArr.length;j++){
                if (pArr[j-1]!='*'){
                    dp[i][j]=dp[i-1][j-1] && (sArr[i-1]==pArr[j-1] || pArr[j-1]=='?');
                }else{
                    //匹配0次、1次、n次
                    dp[i][j]=dp[i][j-1] || dp[i-1][j-1] || dp[i-1][j];
                }
            }
        }
        return dp[sArr.length][pArr.length];
    }
}
```