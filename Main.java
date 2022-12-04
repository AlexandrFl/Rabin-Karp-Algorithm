import java.util.ArrayList;
import java.util.List;

public class Main {

    public static String asterik = "*";

    public static void main(String[] args) {
        String source = "Alibaba or Alibubab? I do not know!";
        String pattern = "b*b";

        List<Integer> result = defSearch(source, pattern);

        System.out.print("Совпадение строк начинается с позиций -> ");
        System.out.println(result);
    }

    public static List<Integer> defSearch(String source, String pattern) {
        if (source.length() < pattern.length()) {
            System.out.println("Такой подстроки нет");
        }
        List<Integer> found = new ArrayList<>();
        int patternHash = sum(pattern);
        int asterikPosition = pattern.indexOf(asterik);
        int windowHash = 0;
        for (int i = 0; i < source.length() - pattern.length() + 1; i++) {
            if (i == 0) {
                windowHash = sum(source.substring(i, pattern.length() + i));
                windowHash -= source.charAt(asterikPosition);
            } else {
                windowHash -= source.charAt(i - 1);
                windowHash += source.charAt(i + pattern.length() - 1);
                windowHash -= source.charAt(i + asterikPosition);
            }
            if (windowHash == patternHash) {
                for (int j = 0; j < pattern.length(); j++) {
                    if (pattern.charAt(j) != asterik.charAt(0) && source.charAt(i + j) != pattern.charAt(j)) {
                        System.out.println("Не подходит");
                    }
                }
                found.add(i);
            }
            windowHash += source.charAt(i + asterikPosition);
        }
        return found;
    }

    public static int sum(String string) {
        int sum = 0;
        for (int i = 0; i < string.length(); i++) {
            if (asterik.charAt(0) != string.charAt(i)) {
                sum += string.charAt(i);
            }
        }
        return sum;
    }
}
