package web; /**
 * @program LeetNiu
 * @description: 字符流中第一个不重复的字符
 * @author: mf
 * @create: 2020/01/16 14:10
 */

/**
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 */
public class T54 {

    int count[] = new int[256];

    int index = 1;

    public void Insert(char ch)
    {
        if (count[ch] == 0) {
            count[ch] = index++;
        } else {
            count[ch] = -1;
        }
    }

    public char FirstAppearingOnce()
    {
        int temp = Integer.MAX_VALUE;
        char ch = '#';
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0 && count[i] != -1 && count[i] < temp) {
                temp = count[i];
                ch = (char)i;
            }
        }
        return ch;
    }
}
