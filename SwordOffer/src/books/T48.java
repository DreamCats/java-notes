package books;

/**
 * @program JavaBooks
 * @description: 最长不含重复字符的子字符串
 * @author: mf
 * @create: 2019/10/01 15:20
 */

/*
请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。假设字符串中只包含从'a'到'z'的字符。
 */

/*
思路：动态规划
（1）当第i个字符之前未出现过，则有：f(i)=f(i-1)+1
（2）当第i个字符之前出现过，记该字符与上次出现的位置距离为d
    1）如果d<=f(i-1)，则有f(i)=d；
    2）如果d>f(i-1)，则有f(i)=f(i-1)+1；

 */
public class T48 {
    public static void main(String[] args) {
        System.out.println(maxLength("arabcacfr"));
    }

    private static int maxLength(String str) {
        if (str == null || str.length() < 0) return 0;
        int preLength = 0; // f(i-1)
        int curLength = 0; // f(i)
        int maxLength = 0;
        int[] pos = new int[26];
        for (int i = 0; i < pos.length; i++) {
            pos[i] = -1;
        }
        for (int i = 0; i < str.length(); i++) {
            int letterNumber = str.charAt(i)-'a';
            if(pos[letterNumber]< 0 || i-pos[letterNumber]>preLength) {
                curLength=preLength+1;
            } else {
                curLength=i-pos[letterNumber];
            }
            pos[letterNumber]=i;
            if(curLength>maxLength)
                maxLength=curLength;
            preLength=curLength;
        }
        return maxLength;
    }
}
