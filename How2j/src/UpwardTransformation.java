/**
 * @program JavaBooks
 * @description: 向上转型 or 向下转型
 * @author: mf
 * @create: 2019/04/05 11:36
 */

public class UpwardTransformation {
    public static void main(String[] args) {

        Hero h = new Hero();

        ADHero ad = new ADHero();

        //类型转换指的是把一个引用所指向的对象的类型，转换为另一个引用的类型

        //把ad引用所指向的对象的类型是ADHero
        //h引用的类型是Hero
        //把ADHero当做Hero使用，一定可以

        h = ad;




        Hero h1 =new Hero();
        ADHero ad1 = new ADHero();
//        Support s1 =new Support(); // Support extents Hero

        h1 = ad1; //把ad当做Hero使用，一定可以
        ad1 = (ADHero) h1; // 在这个例子里，h指向的是一个ad对象，所以转换成ADHero类型，是可以的
//        h1 = s1; // 把一个support对象当做Hero使用，一定可以
        ad1 = (ADHero)h1; // h指向的是一个support对象，所以转换成ADHero类型，会失败。


//        ADHero ad = new ADHero();

//        APHero ap = new APHero();

        // 没有继承关系的类型进行互相转换一定会失败，所以会出现编译错误
//        ad = (ADHero) ap;
    }
}


// 父类转子类，有的时候行，有的时候不行，所以必须进行强制转换。

// 没有继承关系的两个类，互相转换，一定会失败
// 虽然ADHero和APHero都继承了Hero，但是彼此没有互相继承关系

// instanceof
// instanceof Hero 判断一个引用所指向的对象，是否是Hero类型，或者Hero的子类
// h1 instanceof ADHero
//判断引用h1指向的对象，是否是ADHero类型