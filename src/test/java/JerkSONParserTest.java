import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
class JerkSONParserTest {
    JerkSONParser testJerkSON;
    String jerkSONData;

    @BeforeEach
    void setUp() throws Exception {
        testJerkSON = new JerkSONParser((new Main()).readRawDataToString());
        jerkSONData = testJerkSON.getJerkSON();
    }

    @Test
    void createMatcher() {
    }

    @Test
    void changeText() {
    }

    @Test
    void getJerkSON() {
    }

    @Test
    void setJerkSON() {
    }

    @Test
    void changeName() {
        jerkSONData = jerkSONData.replaceAll("([nN][aA][M])\\w+", "name");
        testJerkSON.changeName();
        assertEquals(jerkSONData, testJerkSON.getJerkSON());
        System.out.println(testJerkSON.getJerkSON());
    }

    @Test
    void changePrice() {
        jerkSONData = jerkSONData.replaceAll("([pP][rR][iI][cC])\\w+", "Price");
        testJerkSON.changePrice();
        assertEquals(jerkSONData, testJerkSON.getJerkSON());
        System.out.println(testJerkSON.getJerkSON());
    }

    @Test
    void changeKeys() {
        jerkSONData = jerkSONData.replaceAll("([nN][aA][M])\\w+", "name");
        jerkSONData = jerkSONData.replaceAll("([pP][rR][iI][cC])\\w+", "Price");
        testJerkSON.changeName();
        testJerkSON.changePrice();
        assertEquals(jerkSONData, testJerkSON.getJerkSON());
        System.out.println(testJerkSON.getJerkSON());
    }

    @Test
    void changeBread() {
        jerkSONData = jerkSONData.replaceAll("([B][r][eE][aA])\\w+", "Bread");
        testJerkSON.changeBread();
        assertEquals(jerkSONData, testJerkSON.getJerkSON());
        System.out.println(testJerkSON.getJerkSON());
    }

    @Test
    void changeCookies() {
        jerkSONData = jerkSONData.replaceAll("([cC][oO0])\\w+", "Cookies");
        testJerkSON.changeCookies();
        assertEquals(jerkSONData, testJerkSON.getJerkSON());
        System.out.println(testJerkSON.getJerkSON());
    }

    @Test
    void changeMilk() {
        jerkSONData = jerkSONData.replaceAll("([M][i][lL])\\w+", "Milk");
        testJerkSON.changeMilk();
        assertEquals(jerkSONData, testJerkSON.getJerkSON());
        System.out.println(testJerkSON.getJerkSON());
    }

    @Test
    void changeValues() {
        jerkSONData = jerkSONData.replaceAll("([B][r][eE][aA])\\w+", "Bread");
        jerkSONData = jerkSONData.replaceAll("([cC][oO0])\\w+", "Cookies");
        jerkSONData = jerkSONData.replaceAll("([M][i][lL])\\w+", "Milk");
        testJerkSON.changeBread();
        testJerkSON.changeCookies();
        testJerkSON.changeMilk();
        assertEquals(jerkSONData, testJerkSON.getJerkSON());
        System.out.println(testJerkSON.getJerkSON());
    }
}