# 21.合并两个有序链表

## **问题描述**  

将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。  

**样例**：  
输入：1->2->4, 1->3->4  
输出：1->1->2->3->4->4  

## **解决方案**  

&emsp;&emsp;创建一个新的结点作为合并后链表的头结点，然后依次遍历l1和l2链表，将结点接到新的链表上。

```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null) return l2;
        ListNode p=new ListNode(0),current=p;
        current.next=l1;
        while(l1!=null && l2!=null){
            if (l1.val<=l2.val) {
                current.next=l1;
                current=current.next;
                l1=l1.next;
            }else{
                current.next=l2;
                current=current.next;
                l2=l2.next;
            }
        }
        if (l1!=null) current.next=l1;
        else current.next=l2;
        return p.next;
    }
}
```