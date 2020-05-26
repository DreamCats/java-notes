# 手写SpringIoc代码

## 举个例子

> 容器注册bean(Car和Wheel)

### Wheel
```java
public class Wheel {

    private String brand;

    private String specification;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "brand='" + brand + '\'' +
                ", specification='" + specification + '\'' +
                '}';
    }
}
```

### Car
```java
public class Car {

    private String name;

    private Wheel wheel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", wheel=" + wheel +
                '}';
    }
}
```

### xml注册
```xml
<beans>
    <bean id="wheel" class="Wheel">
        <property name="brand" value="heheda" />
        <property name="specification" value="265/60 R18" />
    </bean>

    <bean id="car" class="Car">
        <property name="name" value="falali" />
        <property name="wheel" ref="wheel" />
    </bean>
</beans>
```

### SimpleIOC
```java
public class SimpleIOC {
    //需要hashmap
    private Map<String, Object> beanMap = new HashMap<>();

    public SimpleIOC(String location) throws Exception {
        loadBean(location);
    }

    public Object getBean(String name) {
        Object bean = beanMap.get(name);
        if (bean == null) {
            throw new IllegalArgumentException("there is no bean with name" + name);
        }
        return bean;
    }

    private void loadBean(String location) throws Exception {
        // 加载xml配置文件
        InputStream inputStream = new FileInputStream(location);
        // docbuilder factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // doc builder
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        // doc
        Document document = documentBuilder.parse(inputStream);
        // element
        Element element = document.getDocumentElement();
        // nodes
        NodeList nodes = element.getChildNodes();
        // 遍历<bean>标签获取bean节点信息
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                String id = ele.getAttribute("id");
                String className = ele.getAttribute("class");
                // 加载beanClass
                Class beanClass = null;
                try {
                    beanClass = Class.forName(className);// 反射
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return;
                }
                // 创建bean
                Object bean = beanClass.newInstance(); // new实例
                // 遍历<property>标签
                NodeList propertyNodes = ele.getElementsByTagName("property");
                for (int j = 0; j < propertyNodes.getLength(); j++) {
                    Node propertyNode = propertyNodes.item(j);
                    if (propertyNode instanceof Element) {
                        Element propertyElement = (Element) propertyNode;
                        String name = propertyElement.getAttribute("name");
                        String value = propertyElement.getAttribute("value");
                        // 利用反射将 bean 相关字段访问权限设为可访问 // 暴力访问
                        Field declaredField = bean.getClass().getDeclaredField(name);
                        declaredField.setAccessible(true);

                        if (value != null && value.length() > 0) {
                            // 将属性值填充到相关字段中
                            declaredField.set(bean, value);
                        } else {
                            String ref = propertyElement.getAttribute("ref");
                            if (ref == null || ref.length() == 0) {
                                throw new IllegalArgumentException("ref config error");
                            }
                            // 将引用填充到相关字段中
                            declaredField.set(bean, getBean(ref));
                        }
                    }
                    // 将 bean 注册到 bean 容器中
                    registerBean(id, bean);
                }
            }
        }
    }

    private void registerBean(String id, Object bean) {
        beanMap.put(id, bean);
    }
}
```

### SimpleIOCTest
```java
public class SimpleIOCTest {
    public static void main(String[] args) throws Exception {
        System.out.println(SimpleIOC.class.getClassLoader().getResource("ioc.xml").getFile());
        String location = SimpleIOC.class.getClassLoader().getResource("ioc.xml").getFile();
        SimpleIOC simpleIOC = new SimpleIOC(location);
        Wheel wheel = (Wheel) simpleIOC.getBean("wheel"); // 获取wheel bean
        System.out.println(wheel);
        Car car = (Car) simpleIOC.getBean("car"); // 获取 bean
        System.out.println(car);
    }
}
```