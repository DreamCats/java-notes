/**
 * @program JavaBooks
 * @description: 字面值
 * @author: mf
 * @create: 2019/04/01 10:42
 */

public class LiteralValue {

    // 给基本变量赋值就是字面值

    public static void main(String[] args) {

        // 整数字面值
        long val = 26L; //以L结尾的字面值表示long型
        int decVal = 26; //默认就是int型
        int hexVal = 0x1a; //16进制
        int oxVal = 032; //8进制
        int binVal = 0b11010; //2进制
        System.out.println(oxVal);

        // 浮点型字面值
        float f1 = 3.14f;
        double d1 = 3.14;
        double d2 = 1.234e2; // 科学计数法表示double

        // 字符和字符串字面值

        String name = "盖伦";
        char a= 'c';

        //以下是转义字符
        char tab = '\t'; //制表符
        char carriageReturn = '\r'; //回车
        char newLine = '\n'; //换行
        char doubleQuote = '\"'; //双引号
        char singleQuote = '\''; //单引号
        char backslash = '\\'; //反斜杠


    }
}
