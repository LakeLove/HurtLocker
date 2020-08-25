import org.apache.commons.io.IOUtils;

public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        //System.out.println(output);
        JerkSONParser<GroceryItem> jerkSONParser = new JerkSONParser<>(output, GroceryItem.class);
        jerkSONParser.parseJerkSON();
//        System.out.println(jerkSONParser.getShoppingList());
        for (Object obj : jerkSONParser.getParsedObjects()){
            System.out.println(obj);
        }
        //System.out.println(jerkSONParser.getParsedObjects());
        System.out.println(jerkSONParser.getErrors());
        ShoppingListFormatter shoppingList = new ShoppingListFormatter(jerkSONParser.getParsedObjects());
    }
}
