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
    }


}
