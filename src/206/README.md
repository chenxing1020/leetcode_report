# 206.反转链表

## **问题描述**  

反转一个单链表。

**示例**:  
输入: 1->2->3->4->5->NULL  
输出: 5->4->3->2->1->NULL  

**进阶**:  
你可以迭代或递归地反转链表。

## **解决方案**  

&emsp;&emsp;做这个题的时候，脑子里也没有仔细想，迭代和递归的区别到底是什么。第一印象就是`递归`就是函数自己调用自己，把问题的规模不断的缩小；`迭代`就是不断地更新当前状态，然后用新的状态继续推进。解法分别如下：  

### **递归过程**

&emsp;&emsp;因为链表的单向性，所以反转链表最关键的就是找到链表的尾结点，即反转后的头结点。不管链表有多长，都可以表示成一个节点和一段链表（**node->nodeList**）的组合形式，所以只要保证当前节点node后面的nodeList是反转完成的，只需要将node接到反转完成的nodeList末尾即可，并且nodeList的尾结点就是的node->next结点。  
&emsp;&emsp;所以问题不断退化，直到变成只有两个结点的链表反转，结束递归过程。代码如下：

```java
public ListNode reverseList(ListNode head) {
        if (head==null) return null;
        ListNode result,node=head.next;
        if (node!=null) {
            result = reverseList(node);
            node.next=head;
            head.next=null;
        }
        else result = head;
        return result;
    }
```

### **迭代过程**

&emsp;&emsp;对于链表中的每一个节点node，它的状态都是head->node->(node->next)->···，而反转后的状态都是node->head，所以就需要将链表“剪开”，即node的next指向head，然后对下一个结点进行同样的操作，这样就保证了已经遍历的过的节点组成的链表都是已经反向的。代码如下：

```java
public ListNode reverseList(ListNode head){
        if ((head==null)||(head.next==null)) return head;
        ListNode node=head.next;
        head.next=null;
        while (node.next!=null){
            ListNode temp=node.next;
            node.next=head;
            head=node;
            node=temp;
        }
        node.next=head;
        return node;
    }
```

## **补充**

>参考文档：SICP 节1.2.1  

&emsp;&emsp;在SICP中是这样对递归和迭代进行区分的：**递归**的计算过程是一种先逐步展开而后收缩的过程，这一计算过程构造一个推迟进行的操作所形成的的链条，收缩阶段表现为这些运算的实际执行；与之相对应，**迭代**计算过程中没有任何增长和收缩，它的状态可以用固定数目的状态变量描述，并且存在一套固定的规则，描述从一个状态到下一个状态转换时变量的更新方式。  
&emsp;&emsp;用SICP中对计算过程的描述对样例数据分别进行递归和迭代的描述如下：

```java
//递归过程,用val值表示节点，f即为计算过程函数
(f 1)
(f 1->(f 2))
(f 1->(f 2->(f 3)))
(f 1->(f 2->(f 3->(f 4))))
(f 1->(f 2->(f 3->(f 4->(f 5)))))
(f 1->(f 2->(f 3->(f 4<-5))))
(f 1->(f 2->(f 3<-4<-5)))
(f 1->(f 2<-3<-4<-5))
(f 1<-2<-3<-4<-5)
5
//迭代过程
(f 1)
(f 2->1)
(f 3->2->1)
(f 4->3->2->1)
(f 5->4->3->2->1)
5
```

&emsp;&emsp;可以很明显地看出递归的先展开后收缩（通过括号的数目来表示），这样就能很明显地看出递归和迭代的区别了。