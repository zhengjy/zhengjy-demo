package com.zhengjy.offer.queue;
import java.util.Stack;

/** 用两个栈实现队列
 * Created by zhengjy on 2018/4/18 15:19
 */
public class P68_QueueWithTwoStacks {
    public static void main(String[] args){
        MyQueue<Integer> myQueue = new MyQueue<>();
        System.out.println(myQueue.poll());
        myQueue.offer(1);
        myQueue.offer(2);
        myQueue.offer(3);
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        myQueue.offer(4);
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
    }

}
class MyQueue<T>{
    private Stack<T> stack1 = new Stack<>();
    private Stack<T> stack2 = new Stack<>();

    public void offer(T data){
        stack1.push(data);
    }
    public T poll(){
        if(stack2.isEmpty()){
            if(!stack1.isEmpty()){
                while (!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }
        }else {
            return stack2.pop();
        }
        return null;
    }
}