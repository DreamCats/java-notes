public class Main {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        Integer a = new Integer(5);
        Integer b = new Integer(5);
        if (a == b)
            System.out.println("true");
        else
            System.out.println("false， 比的是内存地址啊，兄弟");

        if (a.equals(b))
            System.out.println("True，这就对了嘛，equals干嘛使用？");
        else
            System.out.println("False");
    }


}


/**
 * nk没有Pair的包
 * 自己提前写一个，方便使用
 */
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
