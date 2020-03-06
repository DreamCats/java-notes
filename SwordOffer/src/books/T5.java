package books;

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

    /**
     * 双指针，从后往前遍历即可
     * @param str
     * @return
     */
    public static String replaceSpace(StringBuffer str) {
        int spaceNum = 0;
        // 检测空格数目
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') spaceNum++;
        }

        char[] s1 = new char[str.length() + 2 * spaceNum]; // 新数组的长度
        int p2 = s1.length - 1; // 定义新数组的指针
        for (int i = str.length() - 1; i>= 0; i--) { // 从后往前遍历
            if (str.charAt(i) == ' ') {
                s1[p2--] = '0';
                s1[p2--] = '2';
                s1[p2--] = '%';
            } else {
                s1[p2--] = str.charAt(i);
            }
        }
        return new String(s1);
    }
    // 第二种方法，Stringbuffer的特性
    public static String replaceSpace2(StringBuffer str) {
        StringBuffer s1 = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                s1.append("%20");
            } else {
                s1.append(str.charAt(i));
            }
        }
        return new String(s1);
    }
}
