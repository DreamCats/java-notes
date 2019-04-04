/**
 * @program JavaBooks
 * @description: this的用法
 * @author: mf
 * @create: 2019/04/04 15:20
 */

public class ThisKeyword {
    // this这个关键字，相当于普通话里的“我”
    // 小明说 “我吃了” 这个时候，“我” 代表小明
    // 小红说 “我吃了” 这个时候，“我” 代表小红
    // "我"代表当前人物
    // this 代表当前对象

    String name; // 姓名
    float hp; // 血量
    float armor; // 护甲
    int moveSpeed; // 移动速度

    //打印内存中的虚拟地址
    public void showAddressInMemory(){
        System.out.println("打印this看到的虚拟地址："+this);
    }

    //参数名和属性名一样
    //在方法体中，只能访问到参数name
    public void setName1(String name){
        name = name;
    }


    //为了避免setName1中的问题，参数名不得不使用其他变量名
    public void setName2(String heroName){
        name = heroName;
    }

    //通过this访问属性
    public void setName3(String name){
        //name代表的是参数name
        //this.name代表的是属性name
        this.name = name;
    }


    public static void main(String[] args) {
        ThisKeyword garen =  new ThisKeyword();
        garen.name = "盖伦";
        //直接打印对象，会显示该对象在内存中的虚拟地址
        //格式：Hero@c17164 c17164即虚拟地址，每次执行，得到的地址不一定一样

        System.out.println("打印对象看到的虚拟地址："+garen);
        //调用showAddressInMemory，打印该对象的this，显示相同的虚拟地址
        garen.showAddressInMemory();

        ThisKeyword teemo =  new ThisKeyword();
        teemo.name = "提莫";
        System.out.println("打印对象看到的虚拟地址："+teemo);
        teemo.showAddressInMemory();


        ThisKeyword  h =new ThisKeyword();

        h.setName1("teemo");
        System.out.println(h.name);

        h.setName2("garen");
        System.out.println(h.name);

        h.setName3("死歌");
        System.out.println(h.name);
    }

}
