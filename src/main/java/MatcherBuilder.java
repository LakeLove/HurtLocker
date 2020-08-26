import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class MatcherBuilder {
    public static Matcher createMatcher(String pattern, String string) {
        return Pattern.compile(pattern).matcher(string);
    }

    public static String createPattern(String string) {
        String[] splitString = string.split("");
        StringBuilder pattern = new StringBuilder("(");
        Arrays.stream(splitString).forEach(character -> {pattern.append(buildCharacterSet(character));});
        pattern.append(")");
        return pattern.toString();
    }

    public static String buildCharacterSet(String character) {
        return "[" + character.toLowerCase() + character.toUpperCase() + addL33T(character) + "]";
    }

    public static String addL33T(String character) {
        switch(character.toLowerCase()) {
            case "a":
                return "4";
            case "e":
                return "3";
            case "o":
                return "0";
            case "s":
                return "5";
            case "t":
                return "7";
            default:
                return "";
        }
    }
}
