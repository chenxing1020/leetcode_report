# 394.字符串解码

## 问题描述

给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

```c
示例:
s = "3[a]2[bc]", 返回 "aaabcbc".
s = "3[a2[c]]", 返回 "accaccacc".
s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
```

## 解决方案

&emsp;&emsp;符号匹配的题基本上都是用栈来实现，本地可以采用两个栈来存储信息，一个只存放数字，另一个存放字符串。结束的条件就是碰到`']'`，将字符串栈中最近的`'['`后面的所有字符串连接起来并整体复制即可。

```java
class Solution {
    public static String decodeString(String s) {
        if (s.equals("")) return "";
        char[] c = s.toCharArray();
        int k = 0, len = c.length;
        Stack<String> strStack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();
        while (k < len) {
            //get number
            if (c[k] > 47 && c[k] < 58) {
                int num = 0;
                while (k < len && c[k] > 47 && c[k] < 58) {
                    num = num * 10 + c[k++] - 48;
                }
                intStack.push(num);
            } else {
                //multiply String
                if (c[k] == ']') {
                    String tmpS = "", catStr = "";
                    //get concat String
                    while (!strStack.peek().equals("[")) {
                        catStr = strStack.pop().concat(catStr);
                    }
                    //pop "["
                    strStack.pop();
                    int n = intStack.pop();
                    for (int i = 0; i < n; i++) {
                        tmpS = tmpS.concat(catStr);
                    }
                    strStack.push(tmpS);
                } else {
                    strStack.push(String.valueOf(c[k]));
                }
                k++;
            }
        }

        //concat every element in stack
        String result = strStack.pop();
        while (strStack.size() > 0) {
            result = strStack.pop().concat(result);
        }
        return result;
    }
}

```