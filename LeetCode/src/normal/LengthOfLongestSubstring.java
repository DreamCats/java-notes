package normal; /**
 * @program JavaBooks
 * @description: 无重复字符的最长子串
 * @author: mf
 * @create: 2019/10/17 17:18
 */

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 */

/**
 * 滑动窗口
 * 时间复杂度: O(len(s))
 * 空间复杂度: O(len(charset))
 */

public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        int length = lengthOfLongestSubstring("abccacbb");
        System.out.println(length);
    }

    private static int lengthOfLongestSubstring(String s) {
        int[] freq = new int[256];
        int l = 0;
        int r = -1; // 滑动窗口为s[l...r]
        int res = 0;
        // 整个循环从 l == 0 ; r == -1 这个空窗口开始
        // 到l == s.size(); r == s.szie() - 1这个空窗口截止
        // 在每次循环里逐渐改变窗口，维护frep，并记录当前窗口是否找到了一个新的最优值
        while (l < s.length()) {
            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                r++;
                freq[s.charAt(r)]++;
            } else {
                // r已经到头 || freq[s.charAt(r + 1)] == 1
                freq[s.charAt(l)]--;
                l++;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
