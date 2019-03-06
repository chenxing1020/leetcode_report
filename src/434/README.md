# 434.字符串中的单词数

## 问题描述  

统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。请注意，你可以假定字符串里不包括任何不可打印的字符。

**样例**:  
输入: "Hello, my name is John"  
输出: 5  

## 初步分析

&emsp;&emsp;刚拿到题目的时候，没有细看题目的内容，所以下意识地以为是提取单词的题目，结果提交发现这个题目的要求是不含空格连续字符都是单词，emmmmm...好的吧，所以直接选择对字符串进行遍历，当遇到第一个不是空格的字符时，计数+1，同时开始内部嵌套的新的while循环，将坐标定位到当前单词的末位，然后继续进行大循环，最终遍历完整个字符串。(代码见MainClass1)

## 拓展延伸

&emsp;&emsp;其实这个题目没有什么难度，但是想到这个题目是完全用空格来分割字符串的，所以自然而然就会联想到String类自带的split方法（源码如下，分析直接注释在源码上）

```java
public String[] split(String regex, int limit) {
    char ch = 0;
    if (((regex.value.length == 1 && ".$|()[{^?*+\\".indexOf(ch = regex.charAt(0)) == -1) || //正则表达式只有一位且不是特殊字符
             (regex.length() == 2 && regex.charAt(0) == '\\' && 
             (((ch = regex.charAt(1))-'0')|('9'-ch)) < 0 &&
              ((ch-'a')|('z'-ch)) < 0 &&
              ((ch-'A')|('Z'-ch)) < 0)) && //正则表达式有两位，第一位是转义字符，且第二位不是字母和数字
            (ch < Character.MIN_HIGH_SURROGATE ||
             ch > Character.MAX_LOW_SURROGATE)) //字符串不是utf-16编码
        {
            int off = 0;
            int next = 0;
            boolean limited = limit > 0;  //分割的字符串数组个数
            ArrayList<String> list = new ArrayList<>();
            while ((next = indexOf(ch, off)) != -1) {  //从索引off开始，返回ch字符在字符串中首次出现的索引
                if (!limited || list.size() < limit - 1) {
                    list.add(substring(off, next));  //截取两个索引之间得到的子串
                    off = next + 1;
                } else {    //处理最后最后剩下的子串
                    //assert (list.size() == limit - 1);
                    list.add(substring(off, value.length));
                    off = value.length;
                    break;
                }
            }
            //如果字符串中没有查找到分割串，返回原串
            if (off == 0) return new String[]{this};
            // Add remaining segment
            if (!limited || list.size() < limit) list.add(substring(off, value.length));

            // Construct result
            int resultSize = list.size();
            if (limit == 0) {
                while (resultSize > 0 && list.get(resultSize - 1).length() == 0) {
                    resultSize--;
                }
            }
            String[] result = new String[resultSize];
            return list.subList(0, resultSize).toArray(result);
        }
        return Pattern.compile(regex).split(this, limit);
    }

```
