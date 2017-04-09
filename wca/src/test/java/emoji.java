import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chan on 2017/1/15.
 */
public class emoji {
    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }

    public static void main(String[] args) throws IOException {
      String s=  unicode2String("\\\\u1f444");
        System.err.println(s);
String string="[\\s\\S]*";
        Pattern pattern = Pattern.compile(string);
        Matcher matcher = pattern.matcher("吴志航 ");
        // 字符串是否与正则表达式相匹]4
        System.out.println(matcher.matches());
//        Runtime.getRuntime().exec("cmd.exe /c start " + "http://www.google.com");
    }
}
