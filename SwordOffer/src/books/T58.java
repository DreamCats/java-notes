package books;

/**
 * @program JavaBooks
 * @description: 翻转字符串
 * @author: mf
 * @create: 2019/10/08 15:39
 */

/*
输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变，
为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student."
则输出"student. a am I"
 */
public class T58 {
    public static void main(String[] args) {
        String s = "I am a student.";
        System.out.println(reverseSentence(s));
    }

    private static String reverseSentence(String s) {
        if (s == null) return null;
        if (s.trim().equals("")) return s;
        String[] strs = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = strs.length - 1; i >= 0; i--) {
            sb.append(strs[i]).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
