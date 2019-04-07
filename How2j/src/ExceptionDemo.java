import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @program JavaBooks
 * @description: 异常
 * @author: mf
 * @create: 2019/04/07 19:49
 */

public class ExceptionDemo {
    public static void main(String[] args) {

        File f= new File("d:/LOL.exe");

        try{
            System.out.println("试图打开 d:/LOL.exe");
            new FileInputStream(f);
            System.out.println("成功打开");
        }
//        catch(FileNotFoundException e){
//            System.out.println("d:/LOL.exe不存在");
//            e.printStackTrace();
//        }
        catch (Exception e) {
            System.out.println("d:/LOL.exe不存在");
            e.printStackTrace();
        }
        finally{
            System.out.println("无论文件是否存在， 都会执行的代码");
        }



        // throw和throws的区别
        // throws 出现在方法声明上，而throw通常都出现在方法体内。
        //  throws 表示出现异常的一种可能性，并不一定会发生这些异常；
        // throw则是抛出了异常，执行throw则一定抛出了某个异常对象。

        // 异常分三类
        // 1。错误
        // 2。 运行时异常
        // 3。 可检查异常
        
    }


}
