/**
 * @program SpringBooks
 * @description: SimpleIOCTest
 * @author: mf
 * @create: 2020/02/04 02:07
 */


public class SimpleIOCTest {

    public static void main(String[] args) throws Exception {
        System.out.println(SimpleIOC.class.getClassLoader().getResource("ioc.xml").getFile());
        String location = SimpleIOC.class.getClassLoader().getResource("ioc.xml").getFile();
        SimpleIOC simpleIOC = new SimpleIOC(location);
        Wheel wheel = (Wheel) simpleIOC.getBean("wheel");
        System.out.println(wheel);
        Car car = (Car) simpleIOC.getBean("car");
        System.out.println(car);
    }
}
