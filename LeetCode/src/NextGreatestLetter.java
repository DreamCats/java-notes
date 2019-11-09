/**
 * @program JavaBooks
 * @description: 744. 寻找比目标字母大的最小字母
 * @author: mf
 * @create: 2019/11/09 10:10
 */

/*
题目：https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/
难度:easy
 */
/*
输入:
letters = ["c", "f", "j"]
target = "a"
输出: "c"

输入:
letters = ["c", "f", "j"]
target = "c"
输出: "f"
 */
public class NextGreatestLetter {
    public static void main(String[] args) {
        char[] letters = {'c', 'f', 'j'};
        char target = 'a';
        System.out.println(nextGreatestLetter(letters, target));
    }

    private static char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int l = 0, h = n - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (letters[m] <= target) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return l < n ? letters[l] : letters[0];
    }
}
