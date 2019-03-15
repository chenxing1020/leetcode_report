# 111.二叉树的最小深度

## 问题描述

给定一个二叉树，找出其最小深度。  

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。  

说明: 叶子节点是指没有子节点的节点。  

```java
示例：
给定二叉树[3,9,20,null,null,15,7]
    3
   / \
  9  20
    /  \
   15   7
```

返回它的最小深度2.

## 解决方案  

&emsp;&emsp;递归，但是要注意只有一个根节点和一个子节点的情况，此时层数为2.

```java
class Solution {
    public int minDepth(TreeNode root) {
        if (root==null) return 0;
        int left=minDepth(root.left);
        int right=minDepth(root.right);
        return (left!=0 && right!=0)?Math.min(left,right)+1:1+left+right;
    }
}
```