# 155.最小栈

## **问题描述**  

设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。  

* push(x) -- 将元素 x 推入栈中。
* pop() -- 删除栈顶的元素。
* top() -- 获取栈顶元素。
* getMin() -- 检索栈中的最小元素。

**示例**:  
MinStack minStack = new MinStack();  
minStack.push(-2);  
minStack.push(0);  
minStack.push(-3);  
minStack.getMin();   --> 返回 -3.  
minStack.pop();  
minStack.top();      --> 返回 0.  
minStack.getMin();   --> 返回 -2.  

## **解决方案**  

&emsp;&emsp;直接用双栈，另外一个栈用于存放历史最小值，这样避免pop()操作将最小值推出去。

```java
class MinStack {

    /** initialize your data structure here. */
    private Stack<Integer> s=new Stack<>();
    private Stack<Integer> min=new Stack<>();
    
    public MinStack() {
    }
    
    public void push(int x) {
        s.push(x);
        if (min.isEmpty() || min.peek()>=x) min.push(x);
    }
    
    public void pop() {
        int x=s.pop();
        if (min.peek()==x) min.pop();
    }
    
    public int top() {
        return s.peek();
    }
    
    public int getMin() {
        return min.peek();
    }
}
```