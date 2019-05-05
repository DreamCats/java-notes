import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @program JavaBooks
 * @description: 搜索插入位置
 * @author: mf
 * @create: 2019/05/05 14:32
 */

public class SearchInsert {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int target = 0;
        SolutionSearchInsert solutionSearchInsert = new SolutionSearchInsert();
        int res = solutionSearchInsert.searchInsert(nums, target);
        System.out.println(res);
    }
}



class SolutionSearchInsert {
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] < target) low = mid + 1;
            else if (nums[mid] > target) high = mid - 1;
            else return mid;
        }
        if (high < 0 && low == 0) return (low + high) % 2 + 1;
        else return (low + high) / 2 + 1;


    }
}