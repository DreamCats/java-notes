/**
 * @program JavaBooks
 * @description: 字符串比较
 * @author: mf
 * @create: 2019/04/07 14:38
 */

public class StringCompare {
    // 是否是同一个对象
    public static void main(String[] args) {

        String str1 = "the light";

        String str2 = new String(str1);

        //==用于判断是否是同一个字符串对象
        System.out.println( str1  ==  str2);

        // 是否是同一个对象-特例

        String str3 = "the light";
        System.out.println( str1  ==  str3);

        // 内容是否相同

        System.out.println(str1.equals(str2));//完全一样返回true

        // 是否以子字符串开始或者结束

        String start = "the";
        String end = "Ight";

        System.out.println(str1.startsWith(start));//以...开始
        System.out.println(str1.endsWith(end));//以...结束



    }
}
