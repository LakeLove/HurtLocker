import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JerkSONParser {
    private String jerkSON;

    public JerkSONParser(String jerkSON) {
        this.jerkSON = jerkSON;
    }

    public Matcher createMatcher(String pattern) {
        return Pattern.compile(pattern).matcher(getJerkSON());
    }

    public void changeText(Matcher matcher, String nameReplacement) {
        setJerkSON(matcher.replaceAll(nameReplacement));
    }

    public String getJerkSON() {
        return jerkSON;
    }

    public void setJerkSON(String jerkSON) {
        this.jerkSON = jerkSON;
    }

    public void changeName() {
        changeText(createMatcher("([n|N])\\w+"), "name");
    }

    public void changePrice() {
        changeText(createMatcher("([p|P][r|R])\\w+"), "Price");
    }
}
