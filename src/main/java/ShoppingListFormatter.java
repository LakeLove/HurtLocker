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
        this.shoppingList = new LinkedHashMap<>();
        populateShoppingList();
        this.groceryCount = new HashMap<>();
        populateGroceryCount();
    }

    public void populateShoppingList() {
        shoppingList.put("Milk", new LinkedHashMap<>());
        shoppingList.put("Bread", new LinkedHashMap<>());
        shoppingList.put("Cookies", new LinkedHashMap<>());
        shoppingList.put("Apples", new LinkedHashMap<>());
    }

    public void populateGroceryCount() {
        groceryCount.put("Milk", 0);
        groceryCount.put("Bread", 0);
        groceryCount.put("Cookies", 0);
        groceryCount.put("Apples", 0);
    }

    public void createShoppingList() {
        for (GroceryItem item : groceryItems) {
            String foodItem = parseItem(item);
            double price = Double.parseDouble(item.getPrice());
            addToShoppingList(foodItem, price);
            groceryCount.merge(foodItem, 1, Integer::sum);
        }
    }

    public void addToShoppingList(String foodItem, double price) {
        if (shoppingList.get(foodItem).containsKey(price)) {
            shoppingList.get(foodItem).merge(price, 1, Integer::sum);
        } else {
            shoppingList.get(foodItem).putIfAbsent(price, 1);
        }
    }

    public String parseItem(GroceryItem item) {
        if (createMatcher("([aA][pP])\\w+", item.getName()).find()) {
            return "Apples";
        } else if (createMatcher("([B][r][eE][aA])\\w+", item.getName()).find()) {
            return "Bread";
        } else if (createMatcher("([cC][oO0])\\w+", item.getName()).find()) {
            return "Cookies";
        } else if (createMatcher("([M][i][lL])\\w+", item.getName()).find())
            return "Milk";
        return null;
    }

    public Matcher createMatcher(String pattern, String string) {
        return Pattern.compile(pattern).matcher(string);
    }

    public String formatList() {
        StringBuilder list = new StringBuilder();
        for (String item : shoppingList.keySet()) {
            formatGroceries(list, item);
        }
        formatErrors(list);
        return list.toString();
    }

    public void formatGroceries(StringBuilder list, String item) {
        list.append(String.format("name:%8s \t\t seen: %d time", item, groceryCount.get(item)));
        if (groceryCount.get(item) > 1) list.append("s");
        list.append("\n============= \t \t =============\n");
        list.append(String.join("\n-------------\t\t -------------\n", formatPrices(item)));
        if (shoppingList.get(item).size() == 1) list.append("\n-------------\t\t -------------");
        list.append("\n\n");
    }

    public List<String> formatPrices(String item) {
        List<String> prices = new ArrayList<>();
        for (Double price : shoppingList.get(item).keySet()) {
            String s = shoppingList.get(item).get(price) > 1 ? "s" : "";
            prices.add(String.format("Price:%7.2f \t\t seen: %d time%s", price,
                shoppingList.get(item).get(price), s));
        }
        return prices;
    }

    public void formatErrors(StringBuilder list) {
        list.append(String.format("Errors         \t \t seen: %d time", errors));
        if (errors > 1) list.append("s");
    }
}
