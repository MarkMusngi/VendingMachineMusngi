import java.util.ArrayList;

public class Ramen {
    private ArrayList<Item> chosenItems;
    private int totalCalories;

    /**
     * Constructor to create a new Ramen object.
     * Initializes the chosenItems collection as an empty ArrayList and
     * totalCalories as 0.
     */
    public Ramen() {
        chosenItems = new ArrayList<Item>();
        totalCalories = 0;
    }

    /**
     * Retrieves the collection of chosen items for the ramen.
     *
     * @return ArrayList<Item> The collection of chosen items for the ramen.
     */
    public ArrayList<Item> getChosenItems() {
        return chosenItems;
    }

    /**
     * Sets the collection of chosen items for the ramen.
     *
     * @param chosenItems The ArrayList of Item objects representing the chosen
     *                    items for the ramen.
     */
    public void setChosenItems(ArrayList<Item> chosenItems) {
        this.chosenItems = chosenItems;
    }

    /**
     * Retrieves the total number of calories in the customized ramen.
     *
     * @return int The total number of calories in the customized ramen.
     */
    public int getTotalCalories() {
        return totalCalories;
    }

    /**
     * Sets the total number of calories in the customized ramen.
     *
     * @param totalCalories The total number of calories in the customized ramen.
     */
    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }
}
