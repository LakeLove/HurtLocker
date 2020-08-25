import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
class JerkSONParserTest {
    JerkSONParser<GroceryItem> testJerkSON;
    String jerkSONData;

    @BeforeEach
    void setUp() throws Exception {
        testJerkSON = new JerkSONParser<>((new Main()).readRawDataToString(), GroceryItem.class);
        jerkSONData = testJerkSON.getJerkSON();
    }

}