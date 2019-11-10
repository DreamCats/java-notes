import java.util.HashMap;

/**
 * @program JavaBooks
 * @description: 13. 罗马数字转整数
 * @author: mf
 * @create: 2019/11/10 15:18
 */

/*
题目：https://leetcode-cn.com/problems/roman-to-integer/
难度：easy
 */
/*
输入: "IV"
输出: 4
输入: "IX"
输出: 9
输入: "MCMXCIV"
输出: 1994
解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class RomanToInt {
    public static void main(String[] args) {
        String s = "MCMXCIV";
        System.out.println(romanToInt(s));
    }
    public static int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int ans = 0, lastValue = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int value = map.get(s.charAt(i));
            if (value < lastValue) {
                ans -= value;
            } else {
                ans += value;
            }
            lastValue = value;
        }
        return ans;
    }
}
