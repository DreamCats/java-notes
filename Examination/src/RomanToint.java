import java.util.HashMap;
import java.util.Map;

/**
 * @program JavaBooks
 * @description: 罗马数字转数字
 * @author: mf
 * @create: 2019/04/09 11:16
 */

public class RomanToint {
    public static void main(String[] args) {
        SolutionRomanToint s = new SolutionRomanToint();
        int res = s.romanToInt("MCMXCIV");
        System.out.println(res);

    }
}


class SolutionRomanToint {
    public int romanToInt(String s) {
        int res = 0;
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        int b = 0;
        int c = 0;
        char[] romanArray = s.toCharArray();
        for (int i = 0; i < romanArray.length; i++) {
            b = romanMap.get(romanArray[i]);
            if (i + 1 != romanArray.length) {
                c = romanMap.get(romanArray[i + 1]);
                if (c > b) b = -b;
            }
            res += b;
        }
        if (res < 0 || res > 3999) return 0;
        return res;
    }
}