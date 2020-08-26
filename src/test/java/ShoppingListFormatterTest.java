import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ShoppingListFormatterTest {
    JerkSONParser<GroceryItem> testJerkSON;
    ShoppingListFormatter testFormatter;
    GroceryItem testGroceryItem;

    @BeforeEach
    void setUp() throws Exception {
        testJerkSON = new JerkSONParser<>((new Main()).readRawDataToString(), GroceryItem.class);
        testFormatter = new ShoppingListFormatter(testJerkSON.parseJerkSON(), testJerkSON.getErrors());
        testGroceryItem = new GroceryItem("Co0kieS", "2.25", "Food", "1/25/2016");
    }

    @Test
    void ShoppingListFormatter_ShoppingList() {
        assertNotNull(testFormatter.getShoppingList());
    }

    @Test
    void ShoppingListFormatter_GroceryCount() {
        assertNotNull(testFormatter.getGroceryCount());
    }

    @Test
    void populateShoppingList() {
        assertEquals(4, testFormatter.getShoppingList().size());
    }

    @Test
    void populateGroceryCount() {
        assertEquals(4, testFormatter.getGroceryCount().size());
    }

    @Test
    void parseItem() {
        String expected = "Cookies";
        String actual = testFormatter.parseItem(testGroceryItem);
        assertEquals(expected, actual);
    }

    @Test
    void parseItem_Null() {
        testGroceryItem.setName("Juice");
        assertNull(testFormatter.parseItem(testGroceryItem));
    }

    @Test
    void formatList() {
        testFormatter.createShoppingList();
        String expected = "name:    Milk \t\t seen: 6 times\n============= \t \t =============\n" + "Price:   3.23 \t\t seen: 5 times\n-------------\t\t -------------\nPrice:   1.23 \t\t seen: 1 time\n\n" + "name:   Bread \t\t seen: 6 times\n============= \t \t =============\nPrice:   1.23 \t\t seen: 6 times\n" + "-------------\t\t -------------\n\nname: Cookies \t\t seen: 8 times\n============= \t \t =============\n" + "Price:   2.25 \t\t seen: 8 times\n-------------\t\t -------------\n\nname:  Apples \t\t seen: 4 times\n" + "============= \t \t =============\nPrice:   0.25 \t\t seen: 2 times\n-------------\t\t -------------\n" + "Price:   0.23 \t\t seen: 2 times\n\nErrors         \t \t seen: 4 times";
        String actual = testFormatter.formatList();
        assertEquals(expected, actual);
    }

    @Test
    void formatGroceries() {
        testFormatter.createShoppingList();
        String expected = "name: Cookies \t\t seen: 8 times\n============= \t \t =============\n" + "Price:   2.25 \t\t seen: 8 times\n-------------\t\t -------------\n\n";
        String actual = testFormatter.formatGroceries("Cookies");
        assertEquals(expected, actual);
    }

    @Test
    void formatPrices_OnePrice() {
        testFormatter.createShoppingList();
        String expected = "Price:   2.25 \t\t seen: 8 times";
        String actual = testFormatter.formatPrices("Cookies");
        assertEquals(expected, actual);
    }

    @Test
    void formatPrices_TwoPrices() {
        testFormatter.createShoppingList();
        String expected = "Price:   0.25 \t\t seen: 2 times\n-------------\t\t -------------\n" + "Price:   0.23 \t\t seen: 2 times";
        String actual = testFormatter.formatPrices("Apples");
        assertEquals(expected, actual);
    }

    @Test
    void formatErrors() {
        String expected = "Errors         \t \t seen: 4 times";
        String actual = testFormatter.formatErrors();
        assertEquals(expected, actual);
    }
}