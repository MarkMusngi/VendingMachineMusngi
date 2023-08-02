public class Item {
    private String strName;
    private double dPrice;
    private int nCalories;

    public Item(String name, double price, int calories) {
        this.strName = name;
        this.dPrice = price;
        this.nCalories = calories;
    }

    public String getName() {
        return this.strName;
    }

    public double getPrice() {
        return this.dPrice;
    }

    public int getCalories() {
        return this.nCalories;
    }

    public void setPrice(double dPrice) {
        this.dPrice = dPrice;
    }

}