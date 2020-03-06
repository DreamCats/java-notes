package normal;

import java.util.ArrayList;
import java.util.List;

/**
 * @program JavaBooks
 * @description: 412. Fizz Buzz
 * @author: mf
 * @create: 2019/11/10 22:29
 */

/*
题目：https://leetcode-cn.com/problems/fizz-buzz/
难度：easy
 */

/*
n = 15,

返回:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
 */
public class FizzBuzz {
    public static void main(String[] args) {
        List<String> ans = fizzBuzz(15);
        System.out.println(ans.toString());
    }

    public static List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0) return ans;
        for (int i = 1; i <= n; i++) {
            if ((i % 3 == 0) && (i % 5 == 0)) {
                ans.add("FizzBuzz");
            } else if (i % 3 == 0) {
                ans.add("Fizz");
            } else if (i % 5 == 0) {
                ans.add("Buzz");
            } else {
                ans.add(String.valueOf(i));
            }
        }
        return ans;
    }
}
