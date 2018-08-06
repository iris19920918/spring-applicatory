package com.wang.tree;

/**
 * Created by wangdd on 2018/8/6.
 */
public class MyNode {

    private int value;//根据value值对节点进行排序

    private MyNode leftNode;//左子节点

    private MyNode rightNode;//右子节点

    private MyNode parentNode;//父节点

    public MyNode(int value) {
        this.value = value;
    }

    public MyNode(int value, MyNode leftNode, MyNode rightNode) {
        this.value = value;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public MyNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(MyNode leftNode) {
        this.leftNode = leftNode;
    }

    public MyNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(MyNode rightNode) {
        this.rightNode = rightNode;
    }

    public MyNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(MyNode parentNode) {
        this.parentNode = parentNode;
    }
}
