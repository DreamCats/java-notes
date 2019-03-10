/**
 * @program JavaBooks
 * @description: 多态
 * @author: mf
 * @create: 2019/03/10 11:07
 */



/*public class Wine {
    public void fun1(){
        System.out.println("Wine 的Fun.....");
        fun2();
    }

    public void fun2(){
        System.out.println("Wine 的Fun2...");
    }
}

class JNC extends Wine{
    *//**
 * @desc 子类重载父类方法
 *        父类中不存在该方法，向上转型后，父类是不能引用该方法的
 * @param a
 * @return void
 *//*
    // 多态... 父类并没有fun1（String a）
    public void fun1(String a){
        System.out.println("JNC 的 Fun1...");
        fun2();
    }

    *//**
 * 子类重写父类方法
 * 指向子类的父类引用调用fun2时，必定是调用该方法
 *//*
    public void fun2(){
        System.out.println("JNC 的Fun2...");
    }
}

class Test {
    public static void main(String[] args) {
        Wine a = new JNC(); // 已经向上转型了， 父类引用， 对象为JNC
        a.fun1();
    }
}*/

/*
 * 从程序的运行结果中我们发现，a.fun1()首先是运行父类Wine中的fun1().然后再运行子类JNC中的fun2()。
 * 分析：在这个程序中子类JNC重载了父类Wine的方法fun1()，重写fun2()，
 * 而且重载后的fun1(String a)与 fun1()不是同一个方法，由于父类中没有该方法，向上转型后会丢失该方法，
 * 所以执行JNC的Wine类型引用是不能引用fun1(String a)方法。
 * 而子类JNC重写了fun2() ，那么指向JNC的Wine引用会调用JNC中fun2()方法。
 */

/*
 * 指向子类的父类引用由于向上转型了，它只能访问父类中拥有的方法和属性，
 * 而对于子类中存在而父类中不存在的方法，该引用是不能使用的，尽管是重载该方法。
 * 若子类重写了父类中的某些方法，在调用该些方法的时候，必定是使用子类中定义的这些方法（动态连接、动态调用）。*/


public class Wine {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Wine() {}
    public String drink() {
        return "喝的是 " + getName();
    }
    /**
     * @Description: 重写toString()
     * @Param:
     * @return:
     */
    public String toString() {
        return null;
    }
}

class JNC extends Wine {
    public JNC() {
        setName("JNC");
    }
    /**
     * @Description: 重写父类方法，实现多态
     * @Param:
     * @return:
     */
    public String drink() {
        return "喝的是 " + getName();
    }
    /**
     * @Description: 重写toString()
     * @Param:
     * @return:
     */
    public String toString() {
        return "Wine : " + getName();
    }
}

class JGJ extends Wine {
    public JGJ () {
        setName("JGJ");
    }
    // 同理
    public String drink() {
        return "喝的是 " + getName();
    }
    public String toString() {
        return "Wine : " + getName();
    }
}

class Test {
    public static void main(String[] args) {
        //定义父类数组
        Wine[] wines = new Wine[2];
        //定义两个子类
        JNC jnc = new JNC();
        JGJ jgj = new JGJ();

        //父类引用子类对象
        wines[0] = jnc;
        wines[1] = jgj;

        for(int i = 0 ; i < 2 ; i++){
            System.out.println(wines[i].toString() + "--" + wines[i].drink());
        }
        System.out.println("-------------------------------");

    }
}