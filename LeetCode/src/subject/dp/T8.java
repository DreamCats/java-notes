/**
 * @program JavaBooks
 * @description: 最大乘积子序列
 * @author: mf
 * @create: 2020/04/18 10:32
 */

package subject.dp;

/**
 *
 */
public class T8 {
    public int maxProduct(int[] nums) {
        if(nums==null || nums.length==0) return 0;
        //返回值，用来存放阶段性最大值
        int res = Integer.MIN_VALUE;
        //创建两个数组，分别存放阶段性最大值和最小值的累乘
        int[] dp_max = new int[nums.length+1];
        int[] dp_min = new int[nums.length+1];
        dp_max[0] = 1;
        dp_min[0] = 1;
        //dp的循环
        for(int i=1; i<=nums.length; i++){
            //如果当前的nums[i-1]小于0，那么
            if(nums[i-1]<0){
                int temp = dp_max[i-1];
                dp_max[i-1] = dp_min[i-1];
                dp_min[i-1] = temp;
            }
            dp_max[i] = Math.max(dp_max[i-1]*nums[i-1], nums[i-1]);
            dp_min[i] = Math.min(dp_min[i-1]*nums[i-1], nums[i-1]);
            res = Math.max(res, dp_max[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        T8 t8 = new T8();
        System.out.println(t8.maxProduct(new int[]{0, 2}));
        System.out.println(t8.maxProduct(new int[]{-2, 3, -4}));
    }
}
