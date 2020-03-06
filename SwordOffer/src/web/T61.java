package web; /**
 * @program LeetNiu
 * @description: 序列化二叉树
 * @author: mf
 * @create: 2020/01/17 21:13
 */

/**
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。
 * 序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。
 * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 */
public class T61 {
    String Serialize(TreeNode root) {
        if (null == root) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Serialize2(root, sb);
        return sb.toString();
    }

    void Serialize2(TreeNode root, StringBuffer sb) {
        if (null == root) {
            sb.append("#,");
            return;
        }
        sb.append(root.val);
        sb.append(",");
        Serialize2(root.left, sb);
        Serialize2(root.right, sb);
    }
    int index = -1;

    TreeNode Deserialize(String str) {
        if (str.length() == 0) {
            return null;
        }
        String[] strings = str.split(",");
        return Deserialize2(strings);
    }
    TreeNode Deserialize2(String[] strings) {
        index++;
        if (!strings[index].equals("#")) {
            TreeNode root = new TreeNode(0);
            root.val = Integer.parseInt(strings[index]);
            root.left = Deserialize2(strings);
            root.right = Deserialize2(strings);
            return root;
        }
        return null;
    }
}
