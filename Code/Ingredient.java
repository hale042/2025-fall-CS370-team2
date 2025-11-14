public class Ingredient {
    private String name;
    private double amount; 
    
    public Ingredient(String name) {
        this.name = name.toLowerCase();
        this.amount = 0;
    }

    public Ingredient(String name, double amount) {
        this.name = name.toLowerCase();
        this.amount = amount;
    }

    public String getName() {
        return name;
    }
    
    public double getAmount() {
        return amount;
    }
}
