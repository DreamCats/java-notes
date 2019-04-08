import java.io.*;

/**
 * @program JavaBooks
 * @description: 对象流
 * @author: mf
 * @create: 2019/04/08 09:55
 */

public class ObjectStream {
    // 对象流指的是可以直接把一个对象以流的形式传输给其他的介质，比如硬盘
    // 一个对象以流的形式进行传输，叫做序列化
    // 必须是实现Serializable接口

    public static void main(String[] args) {
        ObjectStreamDemo o = new ObjectStreamDemo();
        o.name = "hello";
        o.hp = 300;
        //
        File f = new File("./How2j/src/file.txt");

        try (
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ){
            oos.writeObject(o);
            ObjectStreamDemo o1 = (ObjectStreamDemo) ois.readObject();
            System.out.println(o1.name);
            System.out.println(o1.hp);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

class ObjectStreamDemo implements Serializable {
    //表示这个类当前的版本，如果有了变化，比如新设计了属性，就应该修改这个版本号
    private static final long serialVersionUID = 1L;
    public String name;
    public float hp;
}