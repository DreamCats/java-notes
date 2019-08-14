import java.util.Arrays;

/**
 * @program JavaBooks
 * @description: 堆排序
 * @author: mf
 * @create: 2019/08/14 15:24
 */
/*
- 创建一个堆 H[0……n-1]；
- 创建一个大根堆
- 堆顶和堆尾交换
- 重复第三步骤
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {3, 5, 1 , 4, 2};
        System.out.println("Heap Sort...");
        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i); // 依次从0～i形成大根堆
        }
        int size = arr.length;
        swap(arr, 0, --size);
        while (size > 0) {
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
    }

    public static void heapInsert(int[] arr, int index) {
        // 建立大根堆
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        // 调整成大根堆
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left]
                    ? left + 1
                    : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break; // 自己已经是最大了， 直接跳出
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

}
