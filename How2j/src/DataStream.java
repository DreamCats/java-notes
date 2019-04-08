import java.io.*;

/**
 * @program JavaBooks
 * @description: 数据流
 * @author: mf
 * @create: 2019/04/08 09:43
 */

public class DataStream {
    // 使用数据流的writeUTF()和readUTF() 可以进行数据的格式化顺序读写
    public static void main(String[] args) {
        write();
        read();
    }


    private static void read() {
        File f = new File("./How2j/src/file.txt");
        try(
                FileInputStream fis = new FileInputStream(f);
                DataInputStream dis = new DataInputStream(fis);
                ) {
            boolean b = dis.readBoolean();
            int i = dis.readInt();
            String str = dis.readUTF();

            System.out.println("读取到的布尔值：" + b);
            System.out.println("读取到的整数：" + i);
            System.out.println("读取到的字符串：" + str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void write() {
     File f = new File("./How2j/src/file.txt");
     try (
             FileOutputStream fos = new FileOutputStream(f);
             DataOutputStream dos = new DataOutputStream(fos);
             ){
         dos.writeBoolean(true);
         dos.writeInt(300);
         dos.writeUTF("hello world");


     } catch (FileNotFoundException e) {
         e.printStackTrace();
     } catch (IOException e) {
         e.printStackTrace();
     }
    }
}
