# 148.排序链表

## 问题描述

在 `O(nlogn)` 时间复杂度和常数级空间复杂度下，对链表进行排序。  

```c
示例 1:
输入: 4->2->1->3
输出: 1->2->3->4

示例 2:
输入: -1->5->3->4->0
输出: -1->0->3->4->5
```

## 解决方案

&emsp;&emsp;链表的排序主要问题在于无法有效的获取当前结点位置之前的结点信息，很多像快排这种原位排序很难展开。但是由于`快慢指针`的存在，很容易找到链表的中间结点，并且之前有过合并两个有序链表的经验，所以很容易想到常规排序中的`归并排序`。  
&emsp;&emsp;但是之前自己在写的时候始终无法想到好的办法在链表中实现分治，看了一下网友的评论瞬间恍然大悟，在每一次找到中间结点的时候，同时记录中间结点的前一个结点，然后将前一个结点的next设为null，其实就相当于断链，这样就让这个分治的逻辑变得清楚了。

```java
class Solution {
    public ListNode sortList(ListNode head) {
        if (head==null) return head;
        return mergeSort(head);
    }
    
    public ListNode mergeSort(ListNode head){
        if (head.next==null) return  head;
        ListNode fast=head,low=head,pre=null;
        
        while (fast!=null && fast.next!=null){
            fast=fast.next.next;
            pre=low;
            low=low.next;
        }
        //断链
        pre.next=null;
        ListNode left=mergeSort(head);
        ListNode right=mergeSort(low);
        
        return merge(left,right);
    }
    public ListNode merge(ListNode p,ListNode q){
        ListNode res=new ListNode(0),cur=res;
        while(p!=null && q!=null){
            if (p.val<q.val){
                cur.next=p;
                p=p.next;
            }else{
                cur.next=q;
                q=q.next;
            }
            cur=cur.next;
        }
        if (p!=null) cur.next=p;
        else cur.next=q;
        return res.next;
    }
}
```