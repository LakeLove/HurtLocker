import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JerkSONParser {
    private String jerkSON;

    public JerkSONParser(String jerkSON) {
        this.jerkSON = jerkSON;
    }

    public void findName() {
        changeName(createMatcher(""), "");
    }

    public Matcher createMatcher(String pattern) {
        return Pattern.compile(pattern).matcher(getJerkSON());
    }

    public void changeName(Matcher matcher, String nameReplacement) {
        setJerkSON(matcher.replaceAll(nameReplacement));
    }

    public String getJerkSON() {
        return jerkSON;
    }

    public void setJerkSON(String jerkSON) {
        this.jerkSON = jerkSON;
    }
}
