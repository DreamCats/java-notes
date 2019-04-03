
import java.util.Arrays;


/**
 * @program JavaBooks
 * @description: 数组常用的方法
 * @author: mf
 * @create: 2019/04/03 14:02
 */

public class ArrayMethod {
    public static void main(String[] args) {

        // 数组赋值

        // copyOfRange
        int a[] = new int[] { 18, 62, 68, 82, 65, 9 };
        // copyOfRange(int[] original, int from, int to)
        // 第一个参数表示源数组
        // 第二个参数表示开始位置(取得到)
        // 第三个参数表示结束位置(取不到)
        int[] b = Arrays.copyOfRange(a, 0, 3);



        // 转换为字符串
        String content = Arrays.toString(b);
        System.out.println(content);


        // 排序

        System.out.println("排序之前 :");
        System.out.println(Arrays.toString(a));
        Arrays.sort(a);
        System.out.println("排序之后:");
        System.out.println(Arrays.toString(a));


        // 搜索

        //使用binarySearch之前，必须先使用sort进行排序
        System.out.println("数字 62出现的位置:"+Arrays.binarySearch(a, 62));

        // 判断是否相同

        int c[] = new int[] { 18, 62, 68, 82, 65, 8 };
        System.out.println(Arrays.equals(a, c));

        // 填充
        int d[] = new int[10];
        Arrays.fill(d, 5);
        System.out.println(Arrays.toString(d));


    }


}
