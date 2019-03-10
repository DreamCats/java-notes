/**
 * @program JavaBooks
 * @description: final和private关键字
 * @author: mf
 * @create: 2019/03/10 10:17
 */

public class FinalOverridingIllusion {
    public static void main(String[] args) {
        OverridingPrivate2 op2 = new OverridingPrivate2();
        op2.f();
        op2.g();
        // you can upcast: 向上抛
        OverridingPrivate op = op2;
        // but you can't call the methods;
        // !op.f();
        // !op.g();
        // same here;
        WithFinals wf = op2;
        //! wf.f()
        //! wf.g()
    }
}

class WithFinals {
    // Identiacal to "private" alone;
    private final void f() {
        System.out.println("WithFinal.f()");
    }
    // Also automatically "final";
    private void g() {
        System.out.println("WithFinal.g()");
    }
}


class OverridingPrivate extends WithFinals {
    private final void f() {
        System.out.println("OverridingPrivate.f()");
    }
    private void g() {
        System.out.println("overridingPrivate.g()");
    }
}

class OverridingPrivate2 extends OverridingPrivate {
    public final void f() {
        System.out.println("OverridingPrivate2.f()");
    }
    public void g() {
        System.out.println("OverridingPrivate2.g()");
    }
}
// private 只是隐藏了， 后面的导出类中的public和protected 并非覆盖了，只是产生了新的方法。
