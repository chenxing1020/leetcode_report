# 297.二叉树的序列化与反序列化

## 问题描述  

序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。  

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。  

```c
示例: 
你可以将以下二叉树：
    1
   / \
  2   3
     / \
    4   5
序列化为 "[1,2,3,null,null,4,5]"
```

## 解决方案  

&emsp;&emsp;先说点题外话，看这个题是因为有个同学的朋友参加了头条的面试，让他撸这个代码，我当时在听鹅厂的宣讲会，整个宣讲会一点干货没有，我就用手机撸了这题。还有今天被同学吐槽简历不好看，这大概就是我目前一个面试通知都没有的原因吧= =  
&emsp;&emsp;说回这个题目，我的思路是用层序遍历，然后按层将结果写入StringBuilder变量中。这是序列化的过程。  
&emsp;&emsp;反序列化其实也挺简单的，仍然按照层序遍历的方法，按层将结点放入队列，然后每poll一个结点，就从字符串数组中取两个数当作该结点的左右孩子，然后将左右孩子推入队列，直到所有的结点出队，或者字符数组清空。

```java
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root==null) return null;
        Queue<TreeNode> q=new LinkedList<>();
        StringBuilder sb=new StringBuilder();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode temp=q.poll();
            if (temp==null) {
                sb.append("null,");
                continue;
            }
            sb.append(temp.val+",");
            q.add(temp.left);
            q.add(temp.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data==null) return null;
        String[] arr=data.split(",");
        Queue<TreeNode> q=new LinkedList<>();
        int i=0;
        TreeNode root=new TreeNode(Integer.parseInt(arr[i]));
        q.add(root);
        while(!q.isEmpty() && i<arr.length){
            TreeNode temp=q.poll();
            i++;
            if (i<arr.length && !arr[i].equals("null")){
                temp.left=new TreeNode(Integer.parseInt(arr[i]));
                q.add(temp.left);
            }
            i++;
            if (i<arr.length && !arr[i].equals("null")){
                temp.right=new TreeNode(Integer.parseInt(arr[i]));
                q.add(temp.right);
            }
        }
        return root;
    }
}
```