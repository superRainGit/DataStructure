package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch04.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.Collections;

@RunWith(Parameterized.class)
@Slf4j
public class Ch04StackTest {

    @Parameterized.Parameters
    public static Collection<String> collect() {
        return Collections.singletonList("345+*612+/-");
    }

    public String input;

    /**
     * 测试双端队列
     */
    @Test
    public void testDeque() {
        DequeX<Integer> dequeX = new DequeX<>(6);
        dequeX.insertLeft(1);
        dequeX.insertLeft(2);
        dequeX.insertRight(3);
        dequeX.insertRight(4);
        dequeX.insertRight(5);
        dequeX.display();
        log.info("queue size = {}", dequeX.size());
        log.info("remove right = {}", dequeX.removeRight());
        dequeX.display();
        dequeX.insertRight(6);
        dequeX.display();
        log.info("remove left = {}", dequeX.removeLeft());
        log.info("remove left = {}", dequeX.removeLeft());
        log.info("remove right = {}", dequeX.removeRight());
        dequeX.display();
    }

    /**
     * 后缀表达式的计算
     */
    @Test
    public void testPost() {
        ParsePost parsePost = new ParsePost(this.input);
        parsePost.doParse();
    }

    /**
     * 中缀表达式转为后缀表达式
     */
    @Test
    public void testInToPost() {
        InToPost inToPost = new InToPost(this.input);
        inToPost.transfer();
    }

    /**
     * 优先级队列
     */
    @Test
    public void testPriorityQueue() {
        PriorityQueueX queueX = new PriorityQueueX(5);
        queueX.inert(2);
        queueX.inert(1);
        queueX.inert(5);
        queueX.inert(4);
        queueX.display();
        System.out.println(queueX.remove());
        queueX.inert(3);
        queueX.display();
    }

    /**
     * 队列
     */
    @Test
    public void testQueue() {
        QueueX queue = new QueueX(5);
        queue.insert('2');
        queue.insert('c');
        queue.insert('d');
        queue.insert('g');
        System.out.println(queue.remove());
        queue.insert('s');
        System.out.println(queue.remove());
        queue.insert('r');
        System.out.println(queue.remove());
        queue.insert('p');
        System.out.println(queue.remove());
        queue.insert('i');
        System.out.println(queue.remove());
        queue.display();
    }

    @Test
    public void testBracketChecker() {
        System.out.println(input);
        BracketChecker checker = new BracketChecker(input);
        System.out.println(checker.isCorrect());
    }

    @Test
    public void testReverse() {
        System.out.println(input);
        Reverser reverser = new Reverser(input);
        System.out.println(reverser.doReverse());
    }

    public Ch04StackTest(String input) {
        this.input = input;
    }

    @Test
    public void testStack() {
        StackX stackX = new StackX(10);
        stackX.push('1');
        stackX.push('3');
        stackX.push('9');
        stackX.push('5');
        stackX.push('8');
        stackX.push('5');
        System.out.println(stackX.peek());
        stackX.display();
        System.out.println(stackX.pop());
        System.out.println(stackX.peek());
        stackX.display();
    }
}
