package com.zhangcy.java.data.structure.ch04;

/**
 * 队列[x]
 * 循环队列[实现的方式是当队列中头于(尾+1)%(队列的长度) 差距为1的方式]
 * @author zhangcy
 */
public class QueueX {

    /**
     * 队列中的数组
     */
    private char[] arr;

    /**
     * 数据的长度[拥有有效数据的长度]
     */
    private int currentIndex;

    /**
     * 队列的最大长度[拥有的最大数据长度]
     */
    private int maxSize;

    /**
     * 队头指针
     */
    private int font;

    /**
     * 队尾指针
     */
    private int rare;

    /**
     * 构造函数
     */
    public QueueX(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new char[maxSize];
        this.currentIndex = 0;
        // 默认数据指向队列的头部
        this.font = this.rare = 0;
    }

    /**
     * 插入数据
     */
    public void insert(char ch) {
        // 判断队列数据是否是满的数据
        if(this.font == (this.rare + 1) % maxSize) {
            throw new IllegalArgumentException("queue is full");
        }
        // 直接将元素入队
        // 当超过队尾的数据时 如果目前队列中有位置可以存储数据 那么需要将尾指针往前跳
        // 此时需要将rare指针的数据进行修改
        int insert = this.rare % maxSize;
        arr[insert] = ch;
        this.rare = (++this.rare) % maxSize;
        currentIndex++;
    }

    /**
     * 从队列中移除一个元素 一般是从队列的头部移动
     */
    public char remove() {
        if(this.font == this.rare) {
            throw new IllegalArgumentException("queue is empty");
        }
        // 修改队列真实数据的长度
        currentIndex--;
        // 需要判断当中队列中获取数据时 如果头指针的开始已经超过了末尾的位置 需要手动的回环
        int remove = this.font;
        char temp = arr[remove];
        this.font = (++this.font) % maxSize;
        return temp;
    }

    /**
     * 展示队列中元素
     */
    public void display() {
        // 队头小于队尾
        if(font < rare) {
            for(int i = font;i < rare;i++) {
                System.out.print(arr[i] + " ");

            }
            System.out.println();
        } else {
            for(int i = font;i < maxSize;i++) {
                System.out.print(arr[i] + " ");

            }
            for(int i = 0;i < rare;i++) {
                System.out.print(arr[i] + " ");

            }
            System.out.println();
        }
    }
}
