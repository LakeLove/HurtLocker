import org.apache.commons.io.IOUtils;

public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        JerkSONParser<GroceryItem> jerkSONParser = new JerkSONParser<>(output, GroceryItem.class);
        ShoppingListFormatter shoppingList =
            new ShoppingListFormatter(jerkSONParser.parseJerkSON(), jerkSONParser.getErrors());
        shoppingList.createShoppingList();
        System.out.println(shoppingList.formatList());
    }
}
