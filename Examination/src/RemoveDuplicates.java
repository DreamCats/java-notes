/**
 * @program JavaBooks
 * @description: 删除排序数组中的重复项
 * @author: mf
 * @create: 2019/04/16 09:08
 */

public class RemoveDuplicates {
    public static void main(String[] args) {
        SolutionRemoveDuplicates solutionRemoveDuplicates = new SolutionRemoveDuplicates();
        int[] nums = {1, 1, 2};
        int res = solutionRemoveDuplicates.removeDuplicates(nums);
        System.out.println(res);
    }
}



class SolutionRemoveDuplicates {
    public int removeDuplicates (int[] nums) {
        if (nums.length <= 1) return nums.length;
        int slow = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[slow]) {
                slow += 1;
                nums[slow] = nums[i];
            }
        }
        return slow + 1;
    }
}