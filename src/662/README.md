# 662.二叉树最大宽度

## 问题描述

给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。

每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。

```c
示例 1:
输入: 
           1
         /   \
        3     2
       / \     \  
      5   3     9 
输出: 4
解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。

示例 2:
输入: 
          1
         /  
        3    
       / \       
      5   3     
输出: 2
解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。

示例 3:
输入: 
          1
         / \
        3   2 
       /        
      5      
输出: 2
解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。

示例 4:
输入: 
          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
输出: 8
解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
```

## 解决方案

最简单的方法，使用双向队列，层序遍历，去掉头尾的null，计算每一层的最大宽度。

```java
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root==null) return 0;
        int max=0;
        Deque<TreeNode> deque=new LinkedList<>();
        deque.offerLast(root);
        while (deque.size()>0){
            while (deque.size()>0 && deque.peekLast()==null) deque.pollLast();
            while (deque.size()>0 && deque.peekFirst()==null) deque.pollFirst();

            int size=deque.size();
            if (size==0) return max;
            if (size>max) max=size;
            for (int i=0;i<size;i++) {
                TreeNode tempNode=deque.pollFirst();
                if (tempNode==null){
                    deque.offerLast(null);
                    deque.offerLast(null);
                }else {
                    deque.offerLast(tempNode.left);
                    deque.offerLast(tempNode.right);
                }
            }
        }
        return max;
    }
}
```