package web; /**
 * @program LeetNiu
 * @description: 替换空格
 * @author: mf
 * @create: 2020/01/07 20:11
 */

/**
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * 思路：
 * 1。 检测空格数目
 * 2。 创建新数组
 * 3。 遍历检测' '，则替换相应字符
 */
public class T2 {
    public String replaceSpace(StringBuffer str) {
        // 检测空格数目
        int spaceNum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') spaceNum++;
        }
        // 创建新数组
        char[] ans = new char[str.length() + 2 * spaceNum];
        int p1 = ans.length - 1;
        for (int i = str.length() - 1; i >= 0; i++) {
            if (str.charAt(i) == ' ') {
                ans[p1--] = '0';
                ans[p1--] = '2';
                ans[p1--] = '%';
            } else {
                ans[p1--] = str.charAt(i);
            }
        }
        return new String(ans);
    }
}
