public class Ingredient {
    private String name;
    // private double amount; 
    private String amount; 
    
    public Ingredient(String name) {
        this.name = name.toLowerCase();
        // this.amount = 0;
        this.amount = "0";
    }

    // public Ingredient(String name, double amount) {
    public Ingredient(String name, String amount) {
        this.name = name.toLowerCase();
        this.amount = amount;
    }

    public String getName() {
        return name;
    }
    
    // public double getAmount() {
    public String getAmount() {
        return amount;
    }
}
