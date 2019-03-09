# 136.只出现一次的数字

## **问题描述**  

给定一个**非空**整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

**说明**：  
你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

**示例1**:  
输入： [2, 2, 1]  
输出： 1  

**示例2**:  
输入： [4, 1, 2, 1, 2]  
输出： 4  

## **解决方案**  

### **Solution 1**

&emsp;&emsp;因为需要算法具有线性时间复杂度，所以就排除了暴力搜索。第一个想法就是用一个HashSet集合来存储nums数组中每个整数的状态，在遍历数组的时候，如果当前元素没有在集合中出现，就将元素推入集合，否则就说明该元素不是要找的那个single number，且相同数字只出现两次，直接将该元素推出集合。所以，当一次遍历结束后，HashSet中只剩下了要找到那个只出现一次的数字，代码如下：  

```java
public int singleNumber(int[] nums){
    HashSet<Integer> hs=new HashSet<Integer>();
    for (int num:nums){
        if (hs.contains(num)) hs.remove(num);
        else hs.add(num);
    }
    return hs.iterator().next();
}
```

*时间复杂度分析*：  
&emsp;&emsp;因为HashSet是按照hashcode值来寻址的，所以时间复杂度相当于遍历一次nums数组的时间，即为O(n)，满足线性时间复杂度的要求。

### **Solution 2**

&emsp;&emsp;题目的进阶要求提出能否不使用额外空间，因为这个题目具有特殊性，每个重复的数字仅出现两次，所以就出现了一个更简单高效的计算方式：位运算。如果两个数字相同，那么经过异或运算之后的结果就是0，所以将nums数组中所有的数字都依次异或运算之后的结果就是要找出的只出现过一次的数字。

```java
public int singleNumber(int[] nums) {
    int result=0;
        for (int num:nums){
        result=result^num;
    }
    return result;
}
```

*时间复杂度分析*：  
&emsp;&emsp;复杂度为O(n)，满足线性时间复杂度的要求。