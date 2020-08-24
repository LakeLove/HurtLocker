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

    public void changeKeys() {
        changeName();
        changePrice();
    }

    public void changeName() {
        changeText(createMatcher("([nN][aA][M])\\w+"), "name");
    }

    public void changePrice() {
        changeText(createMatcher("([pP][rR][iI][cC])\\w+"), "Price");
    }

    public void changeValues() {
        changeMilk();
        changeBread();
        changeCookies();
    }

    public void changeMilk() {
        changeText(createMatcher("([M][i][lL])\\w+"), "Milk");
    }

    public void changeBread() {
        changeText(createMatcher("([B][r][eE][aA])\\w+"), "Bread");
    }

    public void changeCookies() {
        changeText(createMatcher("([cC][oO0])\\w+"), "Cookies");
    }

    public void getPrice() {
        //"(.\\.)\\w+";
    }
}
