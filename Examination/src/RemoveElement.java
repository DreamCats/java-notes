import java.util.Arrays;

/**
 * @program JavaBooks
 * @description: 移除元素
 * @author: mf
 * @create: 2019/04/21 13:19
 */

public class RemoveElement {

    public static void main(String[] args) {
        SolutionRemoveElement sre = new SolutionRemoveElement();
        int[] nums = {1,2,2,3,4,5,2};
        int val = 2;
        int res = sre.removeElement(nums, val);
        System.out.println(res);
    }
}

class SolutionRemoveElement {
    public int removeElement(int[] nums, int val) {
        int slow = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                slow += 1;
                nums[slow] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return  slow + 1;
    }
}
