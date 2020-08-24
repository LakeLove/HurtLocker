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
        jerkSONData = jerkSONData.replaceAll("([n|N])\\w+", "name");
        testJerkSON.changeName();
        assertEquals(jerkSONData, testJerkSON.getJerkSON());
        System.out.println(testJerkSON.getJerkSON());
    }

    @Test
    void changePrice() {
        jerkSONData = jerkSONData.replaceAll("([p|P][r|R])\\w+", "Price");
        testJerkSON.changePrice();
        assertEquals(jerkSONData, testJerkSON.getJerkSON());
        System.out.println(testJerkSON.getJerkSON());
    }
}