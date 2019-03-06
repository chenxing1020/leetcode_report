# 226.翻转二叉树  

## 问题描述  

翻转一棵二叉树。  

**样例**：
输入：

```c
     4
   /   \
  2     7
 / \   / \
1   3 6   9
```

输出：

```c
     4
   /   \
  7     2
 / \   / \
9   6 3   1
```

**备注**:  
这个问题是受到 Max Howell 的 原问题 启发的 ：  

谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。

## 解题思路  

&emsp;&emsp;对于二叉树的题目，都可以用递归和非递归的方式来解答。

### 递归的方法

&emsp;&emsp;递归的方法很简洁，就是交换当前的左右结点，直接调用递归。

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root==null) return root;
        TreeNode temp=root.left;
        root.left=invertTree(root.right);
        root.right=invertTree(temp);
        return root;
    }
}
```

### 非递归的方法

&emsp;&emsp;非递归的方法即二叉树的层次遍历，依次将每一层的结点放入队列，然后交换队列中的每一个结点的左右结点，再将它的左右结点加入队列，直到队列中没有元素为止。

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        Queue<TreeNode> q=new LinkedList<>();
        if (root!=null) q.add(root);
        
        while(q.peek()!=null){
            TreeNode temp=q.poll();
            TreeNode swap=temp.left;
            temp.left=temp.right;
            temp.right=swap;
            if (temp.left!=null) q.add(temp.left);
            if (temp.right!=null) q.add(temp.right);
        }
        
        return root;
    }
}
```
