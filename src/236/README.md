# 236.二叉树的最近公共祖先

## **问题描述**  

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。  
百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”  
例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]  

![image](./bst.png)  

**示例 1**：  
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1  
输出: 3  
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。  
**示例 2**：  
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4  
输出: 5  
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。  
**说明**:  
所有节点的值都是唯一的。  
p、q 为不同节点且均存在于给定的二叉搜索树中。  

## **解决方案**  

&emsp;&emsp;有了[leetcode235](../235/README.md)的解题经验，可以确定解题思路，仍然是在考虑五种情况，当p,q分别在遍历结点的不同侧时，就可以确定该结点是p,q最近的公共祖先结点。不过这一题不能用二叉搜索树的性质，只好对每个结点都进行递归判断。  
&emsp;&emsp;我想到的思路就是：

* 先判断p,q有没有其中一个是另一个祖父节点的情况，如果存在直接就找到结果。
* 然后就开始遍历每个结点，对于每个结点，都进行左子树判断
  * 如果p和q都在当前结点的左子树上，就沿着左子树继续搜索
  * 如果p和q中只有一个结点在当前结点的左子树上，则该结点就是要找的最近公共祖先结点
  * 否则就沿着右子树进行搜索

&emsp;&emsp;上述的判断用到了boolean的位运算，代码如下：

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (isChild(p,q)) return p;
        if (isChild(q,p)) return q;
        return findMid(root,p,q);
    }

    public TreeNode findMid(TreeNode node,TreeNode p,TreeNode q){
        boolean pp=isLeftChild(node,p);
        boolean qq=isLeftChild(node,q);
        if (pp ^ qq) return node;
        else if (pp & qq) return findMid(node.left,p,q);
        else return findMid(node.right,p,q);
    }
    //判断p是否在node的左子树上
    public boolean isLeftChild(TreeNode node,TreeNode p){
        if (node==null) return false;
        return isChild(node.left,p);
    }
    //判断p是否是node的子结点
    public boolean isChild(TreeNode node,TreeNode p){
        if (node==null) return false;
        if (node==p) return true;
        if (isChild(node.left,p) || isChild(node.right,p)) return true;
        return false;
    }
    
}
```

## **优化**

&emsp;&emsp;我上面的这个思路有个很明显的问题在于，有很多结点在递归过程中被多次访问了，这就导致了时间的很大消耗，所以这里应该找到一种不用重复访问结点的方法。  
&emsp;&emsp;这里参考了leetcode上耗时最短的程序，分别用left和right来记录递归的返回值，递归结束的标志是结点为空或者遍历到p或者q，这样当整个递归过程都结束的时候，不断返回的时候，如果left和right都不为空，就说明该结点的左右子树分别包含了p和q，可以在递归过程中就返回结果，节省了大量的时间。

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == root || q == root) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null) {
            return root;
        } else if(left == null) {
            return right;
        } else {
            return left;
        }
    }
}
```