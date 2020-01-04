package com.zhangcy.java.data.structure.ch08;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 树本身的实例[目前在写的是一个二叉搜索树]
 * 在二插搜索树中找树的最小值和最大值是最简单的
 * 找最小值就是从根一直向左遍历，找到找到一个左节点为空的节点
 * 找最大值就是从根一直向右遍历，找到找到一个右节点为空的节点
 * @author zhangcy
 */
@Slf4j
public class Tree<T extends Comparable<T>> {

    /**
     * 树的根节点
     * 因为树是打算通过根节点进行数据的完全访问
     */
    private TreeNode<T> root;

    /**
     * 查询节点
     * 因为是个二插搜索树 默认的就是左边的数据比父节点小  右边的数据比父节点大
     */
    public void find(T key) {
        // 先找到父节点
        TreeNode<T> currNode = root;
        // 当前节点的数据不和想要找的数据相等并且不是叶子节点
        while(ObjectUtil.isNotNull(currNode) && currNode.getData().compareTo(key) != 0 && !currNode.isLeaf()) {
            // 如果当前要找的数据比节点数据要小
            if(currNode.getData().compareTo(key) > 0) {
                // 向左子树寻找
                currNode = currNode.getLeftChild();
            } else {
                // 向右子树寻找
                currNode = currNode.getRightChild();
            }
        }
        if(ObjectUtil.isNotNull(currNode)) {
            currNode.display();
        } else {
            log.error("find nothing");
        }
    }

    /**
     * 向目前已有的树中插入数据
     * 我们定义一下：当数据有重复的时候 直接重复的节点按照比当前元素小处理
     */
    public void insert(T data) {
        // 先找到父节点
        TreeNode<T> currNode = this.root;
        TreeNode<T> parentNode = null;
        // 应该插入到当前树的什么节点上
        byte lr = 0;
        // 找到插入该节点的位置
        // 什么时候插入呢 先匹配的找到它应该在的位置
        while(ObjectUtil.isNotNull(currNode)) {
            T currData = currNode.getData();
            if(currData.compareTo(data) >= 0) {
                // 要插入的元素比较小
                parentNode = currNode;
                currNode = currNode.getLeftChild();
                lr = 0;
            } else {
                // 要插入的元素比较大
                parentNode = currNode;
                currNode = currNode.getRightChild();
                lr = 1;
            }
        }
        TreeNode<T> newTreeNode = new TreeNode<>(data, null, null, parentNode);
        if(ObjectUtil.isNotNull(parentNode)) {
            // 如果不为空 那么表明以及找到了数据需要存储的位置
            // 判断一下应该放左边还是放右边
            // 应该放左边
            if(lr == 0) {
                parentNode.setLeftChild(newTreeNode);
            } else {
                parentNode.setRightChild(newTreeNode);
            }
        } else {
            // 如果parentNode是null 有两种可能 数据一样或者根节点也没有
            // 先暂时考虑是根节点不存在
            this.root = newTreeNode;
        }
    }

    /**
     * 树的中序遍历
     * 访问当前节点的左子树
     * 访问当前节点
     * 访问当前节点的右子树
     */
    public void inOrder() {
        recursiveInOrder(this.root);
    }

    /**
     * 递归的进行数的中序遍历
     * 什么时候递归终止呢?
     * 当遍历的节点为null时表明已经到头了
     */
    private void recursiveInOrder(TreeNode<T> currNode) {
        if(ObjectUtil.isNotNull(currNode)) {
            // 先遍历当前节点的左子树
            recursiveInOrder(currNode.getLeftChild());
            // 访问当前节点
            currNode.display();
            // 再遍历当前节点的右子树
            recursiveInOrder(currNode.getRightChild());
        }
    }

    /**
     * 树的先序遍历
     * 先访问根节点数据
     * 访问左子树
     * 访问右子树
     */
    public void preOrder() {
        recursivePreOrder(this.root);
    }

    /**
     * 递归的进行树的先序遍历
     */
    private void recursivePreOrder(TreeNode<T> currNode) {
        if(ObjectUtil.isNotNull(currNode)) {
            // 访问当前节点
            currNode.display();
            // 递归访问树的左子树
            recursivePreOrder(currNode.getLeftChild());
            // 递归的访问树的右子树
            recursivePreOrder(currNode.getRightChild());
        }
    }

    /**
     * 树的后序遍历
     * 先访问左子树
     * 再访问右子树
     * 最后访问节点自身
     */
    public void postOrder() {
        recursivePostOrder(this.root);
    }

    /**
     * 递归的进行树的后序遍历
     */
    private void recursivePostOrder(TreeNode<T> currNode) {
        if(ObjectUtil.isNotNull(currNode)) {
            // 先访问左子树
            recursivePostOrder(currNode.getLeftChild());
            // 后访问左子树
            recursivePostOrder(currNode.getRightChild());
            // 最后访问节点本身
            currNode.display();
        }
    }

    /**
     * 找到树中最小的元素
     */
    public T min() {
        TreeNode<T> currNode = this.root;
        while(ObjectUtil.isNotNull(currNode) && ObjectUtil.isNotNull(currNode.getLeftChild())) {
            currNode = currNode.getLeftChild();
        }
        if(ObjectUtil.isNotNull(currNode)) {
            return currNode.getData();
        } else {
            return null;
        }
    }

    /**
     * 找到树中最大的元素
     */
    public T max() {
        TreeNode<T> currNode = this.root;
        while(ObjectUtil.isNotNull(currNode) && ObjectUtil.isNotNull(currNode.getRightChild())) {
            currNode = currNode.getRightChild();
        }
        if(ObjectUtil.isNotNull(currNode)) {
            return currNode.getData();
        } else {
            return null;
        }
    }

    /**
     * 删除指定的元素的节点
     * 删除指定key的节点
     * 1、指定节点没有任何的子元素[只需要将父元素的左节点或者右节点置为null]
     */
    public void delete(T key) {
        // 先找要删除的节点的位置
        TreeNode<T> currNode = this.root;
        TreeNode<T> parentNode = null;
        while(ObjectUtil.isNotNull(currNode)) {
            if(currNode.getData().compareTo(key) > 0) {
                parentNode = currNode;
                currNode = currNode.getLeftChild();
            } else if(currNode.getData().compareTo(key) < 0) {
                parentNode = currNode;
                currNode = currNode.getRightChild();
            } else {
                // 已经找到数据了
                break;
            }
        }
        // 如果当前节点是一个叶子节点 那么表明找到最后也没有找到
        if(ObjectUtil.isNotNull(parentNode) && parentNode.isLeaf()) {
            log.error("can not find ele: {}", key);
        } else {
            // 已经找到了
            // 有三种情况
            if(currNode.isLeaf()) {
                // 1 如果找到的节点是一个叶子节点
                deleteNoChild(parentNode, currNode);
            } else {
                // 如果找到的节点自己有一个孩子节点的
                if(currNode.onlyOneChild()) {
                    // 如果只有一个孩子节点[左节点或者右节点]
                    // 直接吧找到的孩子节点放到父节点的下面
                    deleteOneChild(parentNode, currNode);
                } else {
                    // 左右两个孩子节点都有
                    // 先找到要删除的节点的中序后继元素
                    TreeNode<T> successor = getSuccessor(currNode);
                    // 判断获取到的中序后继节点 如果是要删除的右节点 那么直接将该右节点的进行移动
                    if(currNode.getRightChild() == successor) {
                        successorIsRight(parentNode, currNode, successor);
                    } else {
                        successorIsNotRight(parentNode, currNode, successor);
                    }
                }
            }
        }
    }

    /**
     * 找到的节点是叶子节点
     * 那么需要替换的就只有父节点
     * 还需要找到是要消除父节点的左节点还是右节点
     */
    private void deleteNoChild(TreeNode<T> parentNode, TreeNode<T> findNode) {
        // 如果不为空 那么表明不是根节点
        if(ObjectUtil.isNotNull(parentNode)) {
            if(parentNode.getLeftChild() == findNode) {
                parentNode.setLeftChild(null);
            } else {
                parentNode.setRightChild(null);
            }
        } else {
            // 如果这个叶子节点同时也是根节点
            this.root = null;
        }
    }

    /**
     * 当前节点目前只有一个元素
     * 需要将当前元素的子树放到父节点的对应位置
     */
    private void deleteOneChild(TreeNode<T> parentNode, TreeNode<T> findNode) {
        // 如果父节点不为空 那么表明不是根节点
        if(ObjectUtil.isNotNull(parentNode)) {
            if(ObjectUtil.isNotNull(findNode.getLeftChild())) {
                // 如果左节点不为空
                if(parentNode.getLeftChild() == findNode) {
                    parentNode.setLeftChild(findNode.getLeftChild());
                } else {
                    parentNode.setRightChild(findNode.getLeftChild());
                }
            } else {
                // 如果右节点不为空
                if(parentNode.getLeftChild() == findNode) {
                    parentNode.setLeftChild(findNode.getLeftChild());
                } else {
                    parentNode.setRightChild(findNode.getLeftChild());
                }
            }
        } else {
            if(ObjectUtil.isNotNull(findNode.getLeftChild())) {
                // 如果左节点不为空
                this.root = findNode.getLeftChild();
            } else {
                // 如果右节点不为空
                this.root = findNode.getRightChild();
            }
        }
    }

    /**
     * 获取当前节点的中序后继元素
     * 如何查找当前节点的中序后继元素?
     * 因为中序遍历整棵树的结果就是当前线索二叉树的有序排列
     * 所以要找元素的中序后继元素
     * 直接看当前节点的右节点
     * 1 有左节点 那么递归的进行最左边元素的查找 直到找到最左边的元素
     * 2 没有左节点 那么就直接是当前要删除的元素的右节点
     * @param currNode 当前节点元素
     */
    private TreeNode<T> getSuccessor(TreeNode<T> currNode) {
        // 要删除的节点的右节点
        TreeNode<T> rightNode = currNode.getRightChild();
        // 要删除的右节点的左节点
        TreeNode<T> leftNode = rightNode.getLeftChild();
        if(ObjectUtil.isNotNull(leftNode)) {
            TreeNode<T> parentNode = rightNode;
            // 如果有左节点 需要递归的找到第一个没有左节点节点
            while(ObjectUtil.isNotNull(leftNode.getLeftChild())) {
                parentNode = leftNode;
                leftNode = leftNode.getLeftChild();
            }
            // 直接在遍历的结果上直接修改
            parentNode.setLeftChild(leftNode.getRightChild());
            return leftNode;
        } else {
            // 如果没有左节点 那么就是当前要删除节点的右节点
            return rightNode;
        }
    }

    /**
     * 如果中序的后继节点就是要删除节点的右节点
     * 那么需要修改当前删除元素的父节点的左右指针
     * 中序后继的左子树为删除节点的左子树
     * @param parentNode 当前要删除节点的父节点
     * @param currNode 要删除的节点
     * @param successor 中序后继节点
     */
    private void successorIsRight(TreeNode<T> parentNode, TreeNode<T> currNode, TreeNode<T> successor) {
        // 如果跟节点为空 那么表明找到的是一个根节点进行的删除
        if(ObjectUtil.isNotNull(parentNode)) {
            if(parentNode.getRightChild() == currNode) {
                parentNode.setRightChild(successor);
            } else {
                parentNode.setLeftChild(successor);
            }
        } else {
            this.root = successor;
        }
        successor.setLeftChild(currNode.getLeftChild());
    }

    /**
     * 如果中序后继节点并不是删除节点的右结点
     * 那么就是一个右结点的子孙结点 那么需要对该中序后继节点的右子树进行迁移
     * 为什么只迁移右子树呢? 因为一个节点的中序后继节点肯定是不能有左子树的
     * 但凡有 中序后继结点肯定就是左子树
     * 所以需要做的事情如下
     * 1 将中序后继元素的右子树绑定到当前中序后继节点的左子树上[该步骤已经在找到中序后继元素时修改了]
     * 2 将中序后继元素的右子树指向删除节点的右子树
     * 3 将中序后继元素的左子树指定删除节点的左子树
     * 4 将删除元素的父节点的左右子树赋值中序后继元素
     */
    private void successorIsNotRight(TreeNode<T> parentNode, TreeNode<T> currNode, TreeNode<T> successor) {
        successor.setLeftChild(currNode.getLeftChild());
        successor.setRightChild(currNode.getRightChild());
        // 如果父节点不为空 那么不是删除的根节点
        if(ObjectUtil.isNotNull(parentNode)) {
            if(parentNode.getRightChild() == currNode) {
                parentNode.setRightChild(successor);
            } else {
                parentNode.setLeftChild(successor);
            }
        } else {
            this.root = successor;
        }
    }
}
