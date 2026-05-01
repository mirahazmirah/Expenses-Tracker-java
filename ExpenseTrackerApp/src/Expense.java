

public class Expense { 
    // Declare private variables for each expense details
    private String id;
    private String date;
    private String category;
    private double amount;
    private String description;
 
    // Constructor to create an Expense objecr
    public Expense(String id, String date, String category, double amount, String description) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    // Getter methods to access private variables
    public String getId() { 
        return id; }
    public String getDate() { 
        return date; }
    public String getCategory() { 
        return category; }
    public double getAmount() { 
        return amount; }
    public String getDescription() { 
        return description; }

    // Setter methods to update values
    public void setDate(String date) { 
        this.date = date; }
    public void setCategory(String category) { 
        this.category = category; }
    public void setAmount(double amount) { 
        this.amount = amount; }
    public void setDescription(String description) { 
        this.description = description; }

    //Print Expense details as String
    @Override
    public String toString() {
        return "ID: " + id +
                "\nDate: " + date +
                "\nCategory: " + category +
                "\nAmount: RM " + amount +
                "\nDescription: " + description;
    }

    // Convert to string for saving to file
    public String toFileString() {
        return id + "|" + date + "|" + category + "|" + amount + "|" + description;
    }

    // Create an Expense object from a line of saved file data
    public static Expense fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid line format");
        }
        String id = parts[0];
        String date = parts[1];
        String category = parts[2];
        double amount = Double.parseDouble(parts[3]);
        String description = parts[4];
        return new Expense(id, date, category, amount, description);
    }
}