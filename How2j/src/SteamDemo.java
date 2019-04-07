import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @program JavaBooks
 * @description: java 流
 * @author: mf
 * @create: 2019/04/07 21:15
 */

public class SteamDemo {
    //流就是一系列的数据

    public static void main(String[] args) {
        // 当不同的介质之间有数据交互的时候，JAVA就使用流来实现。
        // 数据源可以是文件，还可以是数据库，网络甚至是其他的程序
        // 输入流： InputStream
        // 输出流：OutputStream

        try {
            File f = new File("./How2j/src/file.txt");
            System.out.println(f.exists());

            // 创建文件的输入流
            FileInputStream fis = new FileInputStream(f);
            // 读取内容

            byte[] fileContent = new byte[(int) f.length()];

            fis.read(fileContent);

            for (byte b : fileContent) {
                System.out.println(b);
            }

            fis.close();

            // 写入内容 ASCII

            byte[] byteData = {88, 89};
            FileOutputStream flo = new FileOutputStream(f);
            flo.write(byteData);
            flo.close();

        } catch (Exception e) {
            System.out.println("file");
            e.printStackTrace();
        }


        // 流关闭
        //把流定义在try()里,try,catch或者finally结束的时候，会自动关闭
        //
    }

}
