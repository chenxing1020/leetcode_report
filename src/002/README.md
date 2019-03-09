# 002.两数相加

## **问题描述**  

给出两个**非空**的链表用来表示两个非负的整数。其中，它们各自的位数是按照**逆序**的方式存储的，并且它们的每个节点只能存储**一位**数字。  

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。  

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。  

**示例**：  
输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)  
输出：7 -> 0 -> 8  
原因：342 + 465 = 807  

## **解决方案**  

&emsp;&emsp;这个题目没什么太多的东西，需要考虑的东西大概有三个地方：  

1. 两个数长度不一样(处理空结点)  
2. 处理每一次加法的进位  
3. 考虑结果溢出的进位

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l=new ListNode(0),p=l;
        int pre=0,sum=0;
        while(l1!=null || l2!=null){
            int temp1 = (l1==null ? 0 : l1.val);
            int temp2 = (l2==null ? 0 : l2.val);
            sum=temp1+temp2+pre;
            
            p.next=new ListNode(sum % 10);
            p=p.next;
            pre=sum / 10;
            if (l1!=null) l1=l1.next;
            if (l2!=null) l2=l2.next;
        }
        
        if (pre>0) p.next=new ListNode(pre);
        return l.next;
    }

}
```