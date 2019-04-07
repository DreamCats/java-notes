import java.io.*;

/**
 * @program JavaBooks
 * @description: 字符流
 * @author: mf
 * @create: 2019/04/07 21:45
 */

public class CharStream {
    // Reader字符输入流
    // Writer字符输出流
    // 专门用于字符的形式读取和写入数据
    public static void main(String[] args) {
        // file

        File f = new File("./How2j/src/file.txt");

        try (FileReader fr = new FileReader(f)){
            // create array
            char[] all = new char[(int) f.length()];
            // use read
            fr.read(all);
            for (char c : all) {
                System.out.print(c);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // write

        try(FileWriter fr = new FileWriter(f)) {
            //
            String data = "I am maifeng";
            char[] cs = data.toCharArray();
            fr.write(cs);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
