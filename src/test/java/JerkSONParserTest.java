import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class JerkSONParserTest {
JerkSONParser testJerkSON;

    @BeforeEach
    void setUp() throws Exception {
        testJerkSON = new JerkSONParser((new Main()).readRawDataToString());
    }

    @Test
    void findName() {
    }

    @Test
    void createMatcher() {
    }

    @Test
    void changeName() {
    }

    @Test
    void getJerkSON() {

    }

    @Test
    void setJerkSON() {
    }
}