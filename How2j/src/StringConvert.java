/**
 * @program JavaBooks
 * @description: 字符串转换
 * @author: mf
 * @create: 2019/04/07 11:48
 */

public class StringConvert {
    // 数字转字符串
    // 使用String类的静态方法valueOf
    // 先把基本类型装箱为对象，然后调用对象的toString
    public static void main(String[] args) {
        int i = 5;

        //方法1
        String str = String.valueOf(i);

        //方法2
        Integer it = i;
        String str2 = it.toString();


        // 字符串转数字
        // 调用Integer的静态方法parseInt
        String str3 = "999";

        int i3= Integer.parseInt(str3);

        System.out.println(i3);

    }
}
