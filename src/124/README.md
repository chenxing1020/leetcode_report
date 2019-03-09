# 124.二叉树中的最大路径和

## **问题描述**  

给定一个`非空`二叉树，返回其最大路径和。  

本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。  

```c
示例 1:
输入: [1,2,3]
       1
      / \
     2   3
输出: 6

示例 2:
输入: [-10,9,20,null,null,15,7]
   -10
   / \
  9  20
    /  \
   15   7
输出: 42
```

## **解决方案**  

&emsp;&emsp;这题首先是关于二叉树的问题，所以首先想到递归，在递归过程中计算出每一个结点处所能达到的最大路径。  
&emsp;&emsp;显然，每个结点所能达到的路径和有4种，所以在递归过程中要不断比较这个四个值与记录全局最大路径的变量max的大小：

* 当前结点值
* 当前结点值+左子结点的已知路径最大值
* 当前结点值+右子结点的已知路径最大值
* 当前结点值+左子结点的已知路径最大值+右子结点的已知路径最大值（这种情况不能继承，因为跨越左右子结点的路径不能向上）

&emsp;&emsp;而对于每一个结点，想要继续形成通路的话，每个结点的最大路径和就是上述情况1、2和3的较大值。  

```java
class Solution {
    int max=Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root){
        Search(root);
        return max;
    }
    public int Search(TreeNode root) {
        int left=(root.left==null?0:Search(root.left));
        int right=(root.right==null?0:Search(root.right));
        int temp=root.val+(left<0?0:left)+(right<0?0:right);
        int tempLeft=root.val+(left<0?0:left);
        int tempRight=root.val+(right<0?0:right);
        if (temp>max) max=temp;
        if (tempLeft>max) max=tempLeft;
        if (tempRight>max) max=tempRight;
        return (tempLeft>tempRight?tempLeft:tempRight);
    }
}
```