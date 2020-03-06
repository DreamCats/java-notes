package books;

/**
 * @program JavaBooks
 * @description: 正则表达式
 * @author: mf
 * @create: 2019/08/31 13:42
 */

/*
请实现一个函数用来匹配包括'.'和'*'的正则表达式。
模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次(包含0次)。
在本题中，匹配是指字符串的所有字符匹配整个模式。例如
字符串'aaa'与模式'a.a'和'ab*ac*a'匹配，但是与'aa.a'和'ab*a'均不匹配
 */

/*
思路：
当模式中的的第二个字符不是'*'时：
    1、 如果字符串第一个字符和模式的一个字符相匹配，那么字符串和模式都往后移一个字符，然后匹配剩余的。
    2、 否则，直接返回false

当模式中的第二个字符是'*'时：
    1、 如果字符串第一个字符和模式第一个字符不匹配，则模式后移2个字符，继续匹配。
    2、 否则，有3种匹配方式：
        1、 字符串不变，模式后移2
        2、 字符串后移1，模式后移2
        3、 字符串后移1，模式不变
 */
public class T19 {
    public static void main(String[] args) {
        char[] s = {'a', 'a', 'a'};
        char[] p = {'a', 'b', '*', 'a', 'c', '*', 'a'};
        boolean res = match(s, p);
        System.out.println(res);
    }

    private static boolean match(char[] s, char[] p) {
        if (s == null || p == null) return false;
        int sIndex = 0;
        int pIndex = 0;
        return matchCore(s, sIndex, p, pIndex);
    }

    private static boolean matchCore(char[] s, int sIndex, char[] p, int pIndex) {
        // s 到尾，p 到尾，匹配成功
        if (sIndex == s.length && pIndex == p.length) return true;
        // p 先到尾, 匹配失败
        if (sIndex != s.length && pIndex == p.length) return  false;
        // p 第二字符是 *，且字符串第一个跟模式第一个匹配；如果不匹配，模式后移动两位
        if (pIndex + 1 < p.length && p[pIndex + 1] == '*') {
            if ((sIndex != s.length && p[pIndex] == s[sIndex]) || (p[pIndex] == '.' && sIndex != s.length)) {
                // 1. s不变，p后移2字符，相当于x*被忽略
                // 2. s后移1字符，p后移2字符
                // 3. s后移1字符，p不变
                return matchCore(s, sIndex, p, pIndex + 2) || matchCore(s, sIndex + 1, p, pIndex + 2)
                        || matchCore(s, sIndex + 1, p, pIndex);
            } else {
                return matchCore(s, sIndex, p, pIndex + 2);
            }
        }

        // p 第二个不是 *，且字符串第一个跟模式第一个匹配，则后都移一位，否则直接返回false
        if ((sIndex != s.length && p[pIndex] == s[sIndex]) || (p[pIndex] == '.' && sIndex != s.length)) {
            return matchCore(s, sIndex + 1, p, pIndex + 1);
        }
        return false;
    }
}
