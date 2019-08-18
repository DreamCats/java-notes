/**
 * @program JavaBooks
 * @description: 替换空格
 * @author: mf
 * @create: 2019/08/18 18:27
 */

/*
请实现一个函数，把字符串中的每个空格替换成"%20"，例如
输入"We are happy."，则输出"We%20are%20happy."
 */
public class T5 {
    public static void main(String[] args) {
        StringBuffer s = new StringBuffer("We are happy.");
//        String s1 = replaceSpace(s);
        String s1 = replaceSpace2(s);
        System.out.println(s1);
    }
    // 数组和指针
    public static String replaceSpace(StringBuffer s) {

        int spaceNum = 0;
        // 检测空格数目
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') spaceNum++;
        }

        char[] s1 = new char[s.length() + 2 * spaceNum]; // 新数组的长度
        int p2 = s1.length - 1; // 定义新数组的指针
        for (int i = s.length() - 1; i>= 0; i--) { // 从后往前遍历
            if (s.charAt(i) == ' ') {
                s1[p2--] = '0';
                s1[p2--] = '2';
                s1[p2--] = '%';
            } else {
                s1[p2--] = s.charAt(i);
            }
        }
        return new String(s1);
    }
    // 第二种方法，Stringbuffer的特性
    public static String replaceSpace2(StringBuffer s) {
        StringBuffer s1 = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                s1.append("%20");
            } else {
                s1.append(s.charAt(i));
            }
        }
        return new String(s1);
    }
}
