package com.wang.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdd on 2018/8/6.
 */
public class BTreeDemo {
    public int maxDepth = 0;

    public MyNode[] inits = {new MyNode(6), new MyNode(7), new MyNode(4), new MyNode(2), new MyNode(5), new MyNode(1)};

    public static void main(String[] args) {

        BTreeDemo demo = new BTreeDemo();

//        MyNode node = demo.sorting2Tree();
//
//        demo.printNode(node);


        MyNode myNode = new MyNode(2, new MyNode(1), new MyNode(1));
        int depth = demo.getTreeDepth(myNode);
        System.out.println("depth=" + depth);

    }

    /**
     * 获取一个二叉树的深度
     *
     * @return
     * @author 刘
     * @author 2018年8月5日
     * @parameter
     */

    public int getTreeDepth(MyNode node) {

        int depth = 0;//定义二叉树的深度

        if (node == null) {

            return 0;

        }

        List<MyNode> list = new ArrayList<MyNode>();

        List<MyNode> tmpList = new ArrayList<MyNode>();

        list.add(node);

//如果第L层有节点，则depth++，继续获取L+1层的所有子节点

        while (true) {

            if (list == null || list.size() <= 0) {

                break;

            }

            depth++;

            for (int j = 0; j < list.size(); j++) {

                if (list.get(j).getLeftNode() != null) {

                    tmpList.add(list.get(j).getLeftNode());

                }

                if (list.get(j).getRightNode() != null) {

                    tmpList.add(list.get(j).getRightNode());

                }

            }

            list.clear();

            list.addAll(tmpList);

            tmpList.clear();

        }

        return depth;

    }

    /**
     * 获取二叉树某一层有效节点数
     *
     * @param node  二叉树
     * @param level 第几层
     * @return List<MyNode>
     */

    public List<MyNode> getLevelNodeList(MyNode node, int level) {

        List<MyNode> list = new ArrayList<MyNode>();

        if (level <= 0 || node == null) {

            return list;

        }

        list.add(node);

        List<MyNode> tmpList = new ArrayList<MyNode>();

//因为层数是从1开始计算，所以这里需要将level-1。

        for (int i = 0; i < level - 1; i++) {

            for (int j = 0; j < list.size(); j++) {

                if (list.get(j).getLeftNode() != null) {

                    tmpList.add(list.get(j).getLeftNode());

                }

                if (list.get(j).getRightNode() != null) {

                    tmpList.add(list.get(j).getRightNode());

                }

            }

            list.clear();

            list.addAll(tmpList);

            tmpList.clear();

        }

        return list;

    }

    /**
     * 将目标二叉树结构以value值的形式打印出来，不存在节点用#代替，打印的结果放到txt文件，然后改成csv，会看到二叉树的结构，如下
     *
     * @param node 目标二叉树 ,,,,,,,6,,,,,,,
     *             <p>
     *             ,,,4,,,,,,,,7,,,
     *             <p>
     *             ,2,,,,5,,,,#,,,,#,
     *             <p>
     *             1,,3,,#,,#,,#,,#,,#,,#
     */

    public void printNode(MyNode node) {

        StringBuffer sb = new StringBuffer();

        int maxDepth = getTreeDepth(node);

        if (maxDepth > 0) {

            for (int i = 0; i < maxDepth; i++) {

//i层第一个节点的左边/最后一个节点的右边需要几个逗号分隔符

                int maxJ = (int) Math.pow(2, maxDepth - i - 1) - 1;

                for (int j = 0; j < maxJ; j++) {

                    sb.append(",");

                }

//i层的最大节点数

                int levelCount = (int) Math.pow(2, i);

//i层相邻两个节点中间的间隔数

                int gapCount = (int) (Math.pow(2, maxDepth - i) - 1);

                for (int j = 0; j < levelCount; j++) {

                    sb.append(getNodeValue(node, i + 1, j + 1, maxDepth));

                    if (levelCount > 1 && j < levelCount - 1) {

                        for (int k = 0; k <= gapCount; k++) {

                            sb.append(",");

                        }

                    }

                }

                for (int j = 0; j < maxJ; j++) {

                    sb.append(",");

                }

                sb.append(" ");

            }

            System.out.println(sb.toString());

        }

    }

    /**
     * 将一组MyNode排序成二叉树（非平衡）
     *
     * @return
     */

    public MyNode sorting2Tree() {

        MyNode node = inits[0];

        for (int i = 1; i < inits.length; i++) {

            interateCreateNode(node, inits[i]);

        }

        return node;

    }

    /**
     * 递归方法生成有序二叉树（非平衡）
     *
     * @param node    节点
     * @param newNode 新节点
     */

    public void interateCreateNode(MyNode node, MyNode newNode) {

/**

 * 如果新节点的value<=原节点的value值：

 * ①：如果原节点的左子节点不为空，则用原节点的【左子节】点和【newNode】进行比较（递归调用本方法）

 * ②：如果原节点的左子节点为空，则将newNode设置为原节点的左子节点

 */

        if (newNode.getValue() <= node.getValue()) {

            if (node.getLeftNode() != null) {

                interateCreateNode(node.getLeftNode(), newNode);

            } else {

                newNode.setParentNode(node);

                node.setLeftNode(newNode);

            }

        } else {

//原理同上

            if (node.getRightNode() != null) {

                interateCreateNode(node.getRightNode(), newNode);

            } else {

                newNode.setParentNode(node);

                node.setRightNode(newNode);

            }

        }

    }

    /**
     * 根据层数和序号，获取二叉树对应节点的value值
     *
     * @param node  二叉树
     * @param level 层数（根节点level=1）
     * @param index 序号（从左到右，从1开始）
     * @return对应节点的value值，如果不存在该节点，则返回#
     */

    public String getNodeValue(MyNode node, int level, int index, int maxDepth) {

        if (maxDepth == 0) {

            return "请先计算深度";

        }

        if (level > maxDepth) {

            return "超出最大层数！";

        }

        if (index < 1 || index > Math.pow(2, level - 1)) {

            return "超出序号范围！";

        }

        if (node == null) {

            return "树不能为空！";

        }

        return this.interateGetNodeValue(node, level, index);

    }

    /**
     * 根据层数和序号，获取二叉树对应节点的value值
     *
     * @param node  二叉树
     * @param level 层数（根节点level=1）
     * @param index 序号（从左到右，从1开始）
     * @return对应节点的value值，如果不存在该节点，则返回#
     */

    private String interateGetNodeValue(MyNode node, int level, int index) {

//如果只有一层，则直接返回node的value值

        if (level == 1) {

            return String.valueOf(node == null ? "#" : node.getValue());

        }

//如果是取第二层的节点，则判断是否为空后，直接返回value值。

        if (level == 2) {

            if (index == 1) {

                return String.valueOf(node.getLeftNode() == null ? "#" : node.getLeftNode().getValue());

            } else {

                return String.valueOf(node.getRightNode() == null ? "#" : node.getRightNode().getValue());

            }

        }

/**

 * 当层数>=3时，需要迭代执行本方法，将层数执行到第二层，用level=2返回value值

 * ①：先判断目标节点在二叉树的左边还是右边（以根节点位置为竖轴，判断目标节点在竖轴的左边还是右边）

 * ②：如果在左边，则将【根节点的左子节点，level-1,newIndex】座位参数，调用本方法。

 */

//目标层的最大节点数

        int levelCount = (int) (Math.pow(2, level - 1));

//level-1后，新的序号（从左到右，从1开始）

        int newIndex = 0;

//level-1后，新的根节点

        MyNode newNode = null;

        if (index > levelCount / 2) {

            newIndex = index - levelCount / 2;

            newNode = node.getRightNode();

        } else {

            newIndex = index;

            newNode = node.getLeftNode();

        }

        if (newNode == null) {

            return "#";

        }

        return interateGetNodeValue(newNode, level - 1, newIndex);

    }

}
