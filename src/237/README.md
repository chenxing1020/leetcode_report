# 237.删除链表中的节点

## **问题描述**  

请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。

现有一个链表 -- head = [4,5,1,9]，它可以表示为: `4 -> 5 -> 1 -> 9`

**示例 1**:  
输入: head = [4,5,1,9], node = 5  
输出: [4,1,9]  
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.  

**示例 2**:  
输入: head = [4,5,1,9], node = 1  
输出: [4,5,9]  
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.  

**说明**:  
1.链表至少包含两个节点。  
2.链表中所有节点的值都是唯一的。  
3.给定的节点为非末尾节点并且一定是链表中的一个有效节点。  
4.不要从你的函数中返回任何结果。  


## **解决方案**  

&emsp;&emsp;该题就是简单的链表操作，从头结点开始遍历节点直到找到匹配的节点，没有什么可以写的，直接贴代码：
```java
public void deleteNode(ListNode node,int n) {
    while (node.next!=null){
        if (node.val==n) break;
        else node=node.next;
    }
    ListNode nextNode=node.next;
    node.val=nextNode.val;
    node.next=nextNode.next;
}
```

