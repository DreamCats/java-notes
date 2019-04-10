/**
 * @program JavaBooks
 * @description: 二叉树
 * @author: mf
 * @create: 2019/04/10 10:27
 */

public class BinaryTree {
    // 二叉树由各种节点组成
    // 每个节点都可以有左子节点，右子节点
    // 每一个节点都有一个值
    public static void main(String[] args) {
        int randoms[] = new int[] { 67, 7, 30, 73, 10, 0, 78, 81, 10, 74 };
        Node roots = new Node();
        for (int random : randoms) {
            roots.add(random);
        }
    }


}

class Node {
    // 左子节点
    public Node leftNode;
    // 右子节点
    public Node rightNode;

    // 当前节值
    public Object value;

    public void add (Object v) {
        // 如果当前节点没有值，就把数据放在当前节点上
        if (null == value)
            value = v;
        // 如果当前节点有值，就进行判断，新增的值与当前值的大小关系
        else {
            // 新增的值, 比当前值小或者相同
            if ((Integer) v - (Integer) value <= 0) {
                if (null == leftNode) leftNode = new Node();
                leftNode.add(v);
            }
            // 新增的值，比当前值大
            else {
                if (null == rightNode)
                    rightNode = new Node();
                rightNode.add(v);
            }
        }
    }
}
