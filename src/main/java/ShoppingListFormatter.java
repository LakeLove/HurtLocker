import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ShoppingListFormatter {
    private final List<GroceryItem> groceryItems;
    private final Integer errors;
    private final Map<String, Map<Double, Integer>> shoppingList;
    private final Map<String, Integer> groceryCount;

    public ShoppingListFormatter(List<GroceryItem> groceryItems, Integer errors) {
        this.groceryItems = groceryItems;
        this.errors = errors;
        this.shoppingList = populateShoppingList();
        this.groceryCount = populateGroceryCount();
    }

    public Map<String, Map<Double, Integer>> populateShoppingList() {
        Map<String, Map<Double, Integer>> shoppingList = new LinkedHashMap<>();
        shoppingList.put("Milk", new LinkedHashMap<>());
        shoppingList.put("Bread", new LinkedHashMap<>());
        shoppingList.put("Cookies", new LinkedHashMap<>());
        shoppingList.put("Apples", new LinkedHashMap<>());
        return shoppingList;
    }

    public Map<String, Integer> populateGroceryCount() {
        Map<String, Integer> groceryCount = new HashMap<>();
        groceryCount.put("Milk", 0);
        groceryCount.put("Bread", 0);
        groceryCount.put("Cookies", 0);
        groceryCount.put("Apples", 0);
        return groceryCount;
    }

    public void createShoppingList() {
        groceryItems.forEach(item -> {//iterates through each GroceryItem in ArrayList
            String foodItem = getItemName(item);//gets name of GroceryItem
            double price = Double.parseDouble(item.getPrice());//gets double value of GroceryItem price
            addToShoppingList(foodItem, price);//add GroceryItem name and its price to shoppingList
            groceryCount.merge(foodItem, 1, Integer::sum);//increase count of GroceryItem occurrence
        });
    }

    public void addToShoppingList(String foodItem, double price) {
        if (shoppingList.get(foodItem).containsKey(price)) {//checks if the key already has a price associated with it
            shoppingList.get(foodItem).merge(price, 1, Integer::sum);//if yes, increases the count of that price
        } else {
            shoppingList.get(foodItem).putIfAbsent(price, 1);//if no, adds the price and increases the count of
        } //occurrences
    }

    public String getItemName(GroceryItem item) {//parses the name of the GroceryItem
        for (String foodItem : shoppingList.keySet()) {
            if (!parseItemName(foodItem, item).equals("")) return foodItem;
        }
        return null;
    }

    public String parseItemName(String foodItem, GroceryItem item) {//parses the name of the GroceryItem
        return MatcherBuilder.createMatcher(MatcherBuilder.createPattern(foodItem), item.getName()).find() ?
                   foodItem : "";
    }

    public String formatList() {
        StringBuilder list = new StringBuilder();
        shoppingList.keySet().forEach(item -> list.append(formatGroceries(item)));//formats and adds GroceryItems to list
        list.append(formatErrors());//formats and adds errors to list
        return list.toString();
    }

    public String formatGroceries(String item) {
        StringBuilder list = new StringBuilder();
        list.append(String.format("name:%8s \t\t seen: %d time", item, groceryCount.get(item)));
        if (groceryCount.get(item) > 1) list.append("s");
        list.append("\n============= \t \t =============\n");
        list.append(formatPrices(item));
        if (shoppingList.get(item).size() == 1) list.append("\n-------------\t\t -------------");
        list.append("\n\n");
        return list.toString();
    }

    public String formatPrices(String item) {
        List<String> prices = new ArrayList<>();
        shoppingList.get(item).keySet().forEach(price -> {
            String s = shoppingList.get(item).get(price) > 1 ? "s" : "";
            prices.add(String.format("Price:%7.2f \t\t seen: %d time%s", price,
                shoppingList.get(item).get(price), s));
        });
        return String.join("\n-------------\t\t -------------\n", prices);
    }

    public String formatErrors() {
        StringBuilder errorCount = new StringBuilder(String.format("Errors         \t \t seen: %d time", errors));
        if (errors > 1) errorCount.append("s");
        return errorCount.toString();
    }

    public Map<String, Map<Double, Integer>> getShoppingList() {
        return shoppingList;
    }

    public Map<String, Integer> getGroceryCount() {
        return groceryCount;
    }
}