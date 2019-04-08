import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @program JavaBooks
 * @description: 控制台输入输出
 * @author: mf
 * @create: 2019/04/08 10:12
 */

public class ControllerInputOutput {
    // System.out 是常用的在控制台输出数据的
    // System.in 可以从控制台输入数据


    // Scanner读取字符串
    public static void main(String[] args) {
        // 控制台输入
//        try (InputStream is = System.in;) {
//            while (true) {
//                // 敲入a,然后敲回车可以看到
//                // 97 13 10
//                // 97是a的ASCII码
//                // 13 10分别对应回车换行
//                int i = is.read();
//                System.out.println(i);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Scanner s = new Scanner(System.in);

        while(true){
            String line = s.nextLine();
            System.out.println(line);
        }
    }
}
