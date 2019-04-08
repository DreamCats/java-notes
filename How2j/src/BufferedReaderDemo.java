import java.io.*;

/**
 * @program JavaBooks
 * @description: 缓存流
 * @author: mf
 * @create: 2019/04/08 09:34
 */

public class BufferedReaderDemo {
    // 字节流和字符流的弊端：
    // 在每一次读写的时候，都会访问硬盘。
    // 缓存流在读取的时候，会一次性读较多的数据到缓存中，
    // 以后每一次的读取，都是在缓存中访问，直到缓存中的数据读取完毕，再到硬盘中读取。
    public static void main(String[] args) {

        File f = new File("./How2j/src/file.txt");


        // 创建文件字符流
        try (
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                ){
            while (true) {
                // 一次读一行
                String line = br.readLine();
                if (null == line) break;
                System.out.println(line);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                FileWriter fr = new FileWriter(f);
                PrintWriter pw = new PrintWriter(fr);
                ){
            pw.println("我是哈哈哈");
            pw.println("你是谁");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 有的时候，需要立即把数据写入到硬盘
        // flush

    }
}
