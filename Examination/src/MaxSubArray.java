/**
 * @program JavaBooks
 * @description: 最大子序和
 * @author: mf
 * @create: 2019/05/20 16:56
 */

public class MaxSubArray {

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int res;
        SolutionMaxSubArray solutionMaxSubArray = new SolutionMaxSubArray();
        res = solutionMaxSubArray.maxSubArray(nums);
        System.out.println(res);
    }

}



class SolutionMaxSubArray {
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int temp = 0;
        for (int num : nums) {
            if (temp > 0) temp += num;
            else temp = num;
            res = Math.max(res, temp);
        }


        return res;
    }
}