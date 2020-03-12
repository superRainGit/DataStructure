package com.zhangcy.java.data.structure.ch10;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 表示234树的类
 * @author zhangcy
 */
@Slf4j
public class Tree<T extends Comparable<T>> {

    /**
     * 根节点
     */
    private Node<T> root;

    /**
     * 在数据搜索指定的元素
     * 假设在查找的过程中一定是有跟元素的
     * 即最少一个节点
     */
    public void find(T data) {
        if(ObjectUtil.isNull(root)) {
            log.info("Tree is empty");
            return;
        }
        // 从根节点开始遍历
        Node<T> currentNode = root;
        // 循环遍历
        while(true) {
            boolean canFind = currentNode.hasFindVal(data);
            // 如果能找到 则提示可以找到
            if(canFind) {
                log.info("find {}", data);
            } else {
                // 判断当前节点是不是叶子节点 如果是直接提示没有找到 否则找应该查询的位置
                if(currentNode.isLeaf()) {
                    log.info("can not find {}", data);
                    break;
                }
                // 判断从那个位置进行查找
                currentNode = currentNode.findNextChild(data);
            }
        }
    }

    /**
     * 向234树中插入元素
     * 234树从插入元素的情况是在叶子节点进行数据的插入
     * 有三种情况
     * 1、从上至下没有任何数据节点数据项是满的 直接在叶子节点进行插入
     * 2、从上至下的过程中一旦出现节点有满数据的 直接需要节点的分裂
     * 节点的分裂有两种情况
     *  1、普通中间节点的分裂
     *  2、根节点的分裂
     */
    public void insert(T data) {
        Node<T> currentNode = root;
        if(ObjectUtil.isNull(currentNode)) {
            // 插入节点 先判断根节点是不是空 如果是 那么直接构建根节点就可以
            currentNode = new Node<>();
            currentNode.insertNewData2Node(data);
            this.root = currentNode;
        } else {
            // 表明是不是已经做过节点的分裂了 如果没有 那么进行节点的分裂 否则不再进行
            int alreadySplit = 0;
            // 如果跟节点不为空 那么直接开始从上至下进行数据应该在的位置的查找
            while(true) {
                // 判断一下当前节点是不是已经满了 如果已经满了 需要当前节点进行分裂
                if(currentNode.isFull() && 0 == alreadySplit) {
                    alreadySplit = 1;
                    // 节点分裂需要判断节点是根节点还是中间节点
                    if(currentNode == this.root) {
                        // 如果是根节点
                        this.splitRootNode();
                        currentNode = this.root;
                    } else {
                        this.splitMiddleNode(currentNode);
                        currentNode = currentNode.parentNode;
                    }
                } else {
                    // 如果不是满节点 那么直接判断当前节点是不是叶子节点
                    if(currentNode.isLeaf()) {
                        // 如果是叶子节点 那么就直接按顺序插入
                        currentNode.insertNewData2Node(data);
                        // 插入之后 while循环就可以结束了
                        break;
                    } else {
                        // 如果不是 需要找到应该向哪个方向进行查找
                        currentNode = currentNode.findNextChild(data);
                    }
                }
            }
        }
    }

    /**
     * 分裂根节点 如何分裂根节点呢？
     * 1. 先创建一个新的根节点
     * 2. 为根节点分出一个兄弟节点
     * 2. 将当前根节点的中间数据向新节点上升
     * 3. 将当前根节点的左数据节点作为上面新节点的左节点
     * 4. 将当前根节点的右数据节点放到兄弟节点里面
     * 5. 将兄弟节点作为新分出根节点的右子节点
     */
    private void splitRootNode() {
        // 先创建新的根节点
        Node<T> newRootNode = new Node<>();
        // 取出根节点中间的数据
        T middleData = this.root.getMiddleData();
        // 将中间数据插入到新的父节点中
        newRootNode.insertNewData2Node(middleData);
        // 创建新的兄弟节点
        Node<T> newBrotherNode = new Node<>();
        // 取出根节点右侧数据
        T lastNode = this.root.getLastData();
        // 将末位数据插入到兄弟节点
        newBrotherNode.insertNewData2Node(lastNode);
        // 将原有根节点中间的左子节点放到新的跟节点的左侧
        // 如果是满的2-3-4数据节点 那么肯定是4个子节点或者是空的节点
        List<Node<T>> rightSubList = CollUtil.newArrayList();
        if(CollUtil.isNotEmpty(this.root.nodeList)) {
            // 取出右侧子节点
            rightSubList = CollUtil.newArrayList(this.root.nodeList.subList(2, 4));
            // 从当前节点中移除右侧子节点
            this.root.nodeList.removeAll(rightSubList);
        }
        // 移除右侧数据
        this.root.removeLastData();
        // 移除中间数据
        this.root.removeMiddleData();
        // 为兄弟节点迁移新的子节点
        newBrotherNode.nodeList.addAll(rightSubList);
        // 为右节点修正父节点
        rightSubList.forEach(tNode -> tNode.parentNode = newBrotherNode);
        // 将当前根节点的数据放到新的根节点上
        newRootNode.nodeList.add(this.root);
        // 将新建的兄弟节点放到新的节点上
        newRootNode.nodeList.add(newBrotherNode);
        // 为新节点设置父节点
        this.root.parentNode = newRootNode;
        newBrotherNode.parentNode = newRootNode;
        // 重新指定新的跟节点
        this.root = newRootNode;
    }

    /**
     * 分裂遍历的当前节点 怎么分裂?
     * 1 为当前节点创建兄弟节点
     * 2 将当前节点的中间元素节点向父节点迁移
     * 3 当前节点的左侧节点维护不变 并且当前节点作为父节点的一个子节点存在
     * 4 兄弟节点带上当前节点的右节点部分数据
     * 5 为兄弟节点维护右侧子节点
     * 6 将兄弟节点加到父节点的子节点中
     */
    private void splitMiddleNode(Node<T> currentNode) {
        // 为当前节点创建兄弟节点
        Node<T> newBrotherNode = new Node<>();
        // 获取当前节点的中间节点
        T middleData = currentNode.getMiddleData();
        // 获取当前节点的右节点
        T lastData = currentNode.getLastData();
        // 找到当前节点的父节点
        Node<T> currParent = currentNode.parentNode;
        // 将当前节点的父节点中加入新的拆分的元素
        currParent.insertNewData2Node(middleData);
        // 将当前节点中的末尾元素进行移除
        currentNode.removeLastData();
        // 将当前节点中的中间元素进行移除
        currentNode.removeMiddleData();
        // 将末尾元素放入到新的兄弟节点中
        newBrotherNode.insertNewData2Node(lastData);
        // 将原有根节点中间的左子节点放到新的跟节点的左侧
        // 如果是满的2-3-4数据节点 那么肯定是4个子节点或者是空的节点
        List<Node<T>> rightSubList = CollUtil.newArrayList();
        if(CollUtil.isNotEmpty(currentNode.nodeList)) {
            // 取出右侧子节点
            rightSubList = CollUtil.newArrayList(currentNode.nodeList.subList(2, 4));
            // 从当前节点中移除右侧子节点
            currentNode.nodeList.removeAll(rightSubList);
        }
        // 将末尾子节点放到兄弟节点中
        newBrotherNode.nodeList.addAll(rightSubList);
        // 为右节点修正父节点
        rightSubList.forEach(tNode -> tNode.parentNode = newBrotherNode);
        // 将兄弟节点放入到父节点中
        currParent.nodeList.add(newBrotherNode);
        // 为兄弟节点修正父节点
        newBrotherNode.parentNode = currParent;
    }

    /**
     * 展示234树中的数据
     */
    public void display() {
        Node<T> currentNode = this.root;
        if(ObjectUtil.isNull(currentNode)) {
            log.info("tree is empty");
        } else {
            recursiveDisplay(currentNode);
        }
    }

    /**
     * 先跟模式下递归的进行树的展示
     */
    private void recursiveDisplay(Node<T> currentNode) {
        // 先打印当前节点的数据
        log.info("{}", currentNode);
        if(!currentNode.isLeaf()) {
            // 如果不是叶子节点 那么从左向右的递归
            for (Node<T> node : currentNode.nodeList) {
                recursiveDisplay(node);
            }
        }
    }
}
