import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
class JerkSONParserTest {
    JerkSONParser<GroceryItem> testJerkSON;
    GroceryItem testGroceryItem;
    String testSingleEntry;

    @BeforeEach
    void setUp() throws Exception {
        testJerkSON = new JerkSONParser<>((new Main()).readRawDataToString(), GroceryItem.class);
        testSingleEntry = "naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016";
        testGroceryItem = new GroceryItem("Co0kieS", "2.25", "Food", "1/25/2016");
    }

    @Test
    void createObject() throws IllegalAccessException, InstantiationException {
        GroceryItem expected = testGroceryItem;
        GroceryItem actual = testJerkSON.createObject(testSingleEntry);
        assertEquals(expected, actual);
    }

    @Test
    void parseObject() throws InstantiationException, IllegalAccessException {
        List<String> testValues = new ArrayList<>(Arrays.asList("Co0kieS", "2.25", "Food", "1/25/2016"));
        GroceryItem expected = testGroceryItem;
        GroceryItem actual = testJerkSON.parseObject(testValues);
        assertEquals(expected, actual);
    }

    @Test
    void parseObject_Null() throws InstantiationException, IllegalAccessException {
        List<String> testValues = new ArrayList<>(Arrays.asList("2.25", "Food", "1/25/2016"));
        assertNull(testJerkSON.parseObject(testValues));
    }

    @Test
    void getErrors_0() {
        Integer expected = 0;
        Integer actual = testJerkSON.getErrors();
        assertEquals(expected, actual);
    }

    @Test
    void getErrors_All() throws InstantiationException, IllegalAccessException {
        testJerkSON.parseJerkSON();
        Integer expected = 4;
        Integer actual = testJerkSON.getErrors();
        assertEquals(expected, actual);
    }
}