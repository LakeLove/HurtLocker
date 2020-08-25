import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ShoppingListFormatter {
    private Map<String, String> shoppingList;
    private List<GroceryItem> groceryItems;

    public ShoppingListFormatter(List<GroceryItem> groceryItems) {
        this.shoppingList = new HashMap<>();
        this.groceryItems = groceryItems;
    }
}
