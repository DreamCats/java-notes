/**
 * @program JavaBooks
 * @description: StringBuffer
 * @author: mf
 * @create: 2019/04/07 14:43
 */

public class StringBufferMethods {
    // StringBuffer是可变长的字符串
    // 追加 删除 插入 反转

    public static void main(String[] args) {
        String str1 = "let there ";

        StringBuffer sb = new StringBuffer(str1); //根据str1创建一个StringBuffer对象
        sb.append("be light"); //在最后追加

        System.out.println(sb);

        sb.delete(4, 10);//删除4-10之间的字符

        System.out.println(sb);

        sb.insert(4, "there ");//在4这个位置插入 there

        System.out.println(sb);

        sb.reverse(); //反转

        System.out.println(sb);


        // 和String内部是一个字符数组一样，StringBuffer也维护了一个字符数组。 但是，这个字符数组，留有冗余长度

        // new StringBuffer("the")
        // 其内部的字符数组的长度，是19，而不是3
        // 这样调用插入和追加，在现成的数组的基础上就可以完成了。
        // 如果追加的长度超过了19，就会分配一个新的数组，长度比原来多一些，把原来的数据复制到新的数组中，看上去 数组长度就变长了

        String str2 = "the";

        StringBuffer sb1 = new StringBuffer(str1);

        System.out.println(sb1.length()); //内容长度

        System.out.println(sb1.capacity());//总空间

        // String与StringBuffer的性能区别?
        // 1. 生成10位长度的随机字符串
        // 2. 然后,先使用String的+,连接10000个随机字符串,计算消耗的时间  耗时1625
        // 3. 然后,再使用StringBuffer连接10000个随机字符串,计算消耗的时间 耗时187




    }
}
