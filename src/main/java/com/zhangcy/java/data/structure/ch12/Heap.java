package com.zhangcy.java.data.structure.ch12;

/**
 * 堆对象 使用数组模拟树的实现
 * @author zhangcy
 */
public class Heap {

    /**
     * 数组去模拟堆
     * 因为堆本身是一个完全的二叉树  所以可以使用数组的方式去表示堆
     * 子节点和父节点的关系
     * 如果当前节点的下标是X
     * 那么父节点就是(X-1)/2
     * 左子节点(2X + 1)
     * 右子节点(2X + 2)
     */
    private int[] data;

    /**
     * 堆的最大的大小
     */
    private int heapSize;

    /**
     * 堆的模式 是大碓顶还是小堆顶
     */
    private HeapMode heapMode;

    /**
     * 当前堆的
     */
    private int currentIndex;

    public Heap() {
        this(16);
    }

    public Heap(int heapSize) {
        this(heapSize, HeapMode.MAX);
    }

    public Heap(int heapSize, HeapMode heapMode) {
        this.heapSize = heapSize;
        this.data = new int[heapSize];
        this.heapMode = heapMode;
        this.currentIndex = 0;
    }

    /**
     * 插入节点
     */
    public void insert(int iData) {
        // 先在能插入堆的最后一个节点插入数据
        this.data[this.currentIndex] = iData;
        // 循环的对比父节点
        // 判断是大堆顶还是小堆顶
        if(HeapMode.MAX == this.heapMode) {
            // 大堆顶
            this.rotateMax();
        } else {
            // 小堆顶
            this.rotateMin();
        }
        this.currentIndex++;
    }

    /**
     * 大碓顶的更替
     */
    private void rotateMax() {
        // 找到当前节点的父节点
        int parent = (this.currentIndex - 1) / 2;
        // 将当前节点做一个备份
        int temp = this.data[this.currentIndex];
        int rotateIndex = this.currentIndex;
        // 判断当前节点于父节点的关系
        while (this.data[parent] < temp && rotateIndex > 0) {
            this.data[rotateIndex] = this.data[parent];
            rotateIndex = parent;
            parent = (parent - 1) / 2;
        }
        this.data[parent] = temp;
    }

    /**
     * 小堆顶的更替
     */
    private void rotateMin() {
        // 找到当前节点的父节点
        int parent = (this.currentIndex - 1) / 2;
        // 将当前节点做一个备份
        int temp = this.data[this.currentIndex];
        int rotateIndex = this.currentIndex;
        // 判断当前节点于父节点的关系
        while (this.data[parent] > temp && rotateIndex > 0) {
            this.data[currentIndex] = this.data[parent];
            rotateIndex = parent;
            parent = (parent - 1) / 2;
        }
        this.data[parent] = temp;
    }

    /**
     * 移除堆的堆顶元素
     */
    public int remove() {
        // 直接获取堆顶的元素
        int result = this.data[0];
        // 将目前最末尾的元素直接移动到根节点
        this.data[0] = this.data[this.currentIndex];
        // 需要将对应的数据进行移动
        // 如果是大堆顶
        if(HeapMode.MAX == this.heapMode) {
            removeMax();
        } else {
            removeMin();
        }
        this.currentIndex--;
        return result;
    }

    /**
     * 移动最大堆顶元素之后的移动
     */
    private void removeMax() {
        // 从0号节点进行获取
        int parent = 0;
        int left = 2 * parent + 1;
        int right = 2 * parent + 2;
        int temp = this.data[parent];
        // 取出左右节点
        // 判断当前节点是否已经大于左右节点
        while(temp < this.data[left] || temp < this.data[right]) {
            // 计算左右节点的差值
            int rightDiff = this.data[parent * 2 + 2] - temp;
            int leftDiff = this.data[parent * 2 + 1] - temp;
            // 右面的差值大
            if(rightDiff > leftDiff) {
                this.data[parent] = this.data[right];
                parent = right;
            } else {
                this.data[parent] = this.data[left];
                parent = left;
            }
            left = 2 * parent + 1;
            right = 2 * parent + 2;
        }
    }

    /**
     * 移动最大堆顶元素之后的移动
     */
    private void removeMin() {
        // 从0号节点进行获取
        int parent = 0;
        int left = 2 * parent + 1;
        int right = 2 * parent + 2;
        int temp = this.data[parent];
        // 取出左右节点
        // 判断当前节点是否已经大于左右节点
        while(temp > this.data[left] || temp > this.data[right]) {
            // 计算左右节点的差值
            int rightDiff = temp - this.data[parent * 2 + 2];
            int leftDiff = temp - this.data[parent * 2 + 1];
            // 右面的差值大
            if(rightDiff > leftDiff) {
                this.data[parent] = this.data[right];
                parent = right;
            } else {
                this.data[parent] = this.data[left];
                parent = left;
            }
            left = 2 * parent + 1;
            right = 2 * parent + 2;
        }
    }

    @Override
    public String toString() {
        String subString = "";
        if(currentIndex > 0) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < currentIndex; i++) {
                sb.append(this.data[i]).append(",");
            }
            subString = sb.substring(0, sb.length() - 1);
        }
        return "[" + subString + "]";
    }
}
