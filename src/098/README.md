# 098.验证二叉搜索树

## 问题描述  

给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。  

```c
示例 1:
输入:
    2
   / \
  1   3
输出: true

示例 2:
输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
```

## 解题思路

这一题的思路就是利用中序遍历，中序遍历过程中如果当前的访问节点值大于等于前一个访问的节点值，则说明当前二叉树不是搜索二叉树。

本题的测试用例卡了整型的边界，所以需要用一个bool变量来控制第一次节点的访问。

```java
class Solution {
    boolean result=true,start=false;
    int preVal;
    public boolean isValidBST(TreeNode root) {
        if (root==null) return true;
        frontSearch(root);
        return result;
    }
    public void frontSearch(TreeNode root){
        if (root==null) return;
        frontSearch(root.left);
        if (start){
            if (preVal>=root.val) result=false;
        } else start=true;
        preVal=root.val;
        frontSearch(root.right);        
    }
}
```

时间复杂度：O(n)

空间复杂度：O(1)