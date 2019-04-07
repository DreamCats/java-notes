import java.io.File;
import java.util.Date;

/**
 * @program JavaBooks
 * @description: 文件操作
 * @author: mf
 * @create: 2019/04/07 20:42
 */

public class FileDemo {
    public static void main(String[] args) {

        // 绝对路径
        File f1 = new File("/User/mf/Desktop");
        System.out.println("f1的绝对路径： " + f1.getAbsolutePath());

        // 相对路径
        File f2 = new File("file.txt");
        System.out.println("f2的绝对路径： " + f2.getAbsolutePath());

        // 把f1当作父目录创建文件对象
        File f3 = new File(f1, "file.txt");
        System.out.println("f3的绝对路径： " + f3.getAbsolutePath());

        //
        File anmie_spider = new File("/Users/mf/Desktop/spider_anime.md");

        System.out.println("当前文件是：" + anmie_spider);
        System.out.println("判断是否存在：" + anmie_spider.exists());
        System.out.println("判断是否是文件夹：" + anmie_spider.isDirectory());
        System.out.println("判断是否是文件：" + anmie_spider.isFile());
        System.out.println("获取文件的长度：" + anmie_spider.length());

        // 获取文件最后修改的时间
        long time = anmie_spider.lastModified();
        Date d = new Date(time);
        System.out.println("文件最后修改的时间是：" + d);


        // 文件重命名
        // f.rename




    }
}
