/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author SCS
 */
import java.util.ArrayList;
import java.io.*;

public class ExpenseManager {
    
    //List to store all expenses
    private static ArrayList<Expense> expenses;
    
    //Counter to generate unique IDs for expenses
    private int idCounter = 1;

    // Constructor
    public ExpenseManager() {
        expenses = new ArrayList<>();
    }

    // Uses abstraction to generates unique ID for each expense
    public String generateID() {
        return String.format(" EXP%03d", idCounter++);
    }

    // Add an expense to the list
    public void addExpense(Expense e) {
        expenses.add(e);
    }

    // Delete an expense based on ID
    public boolean deleteExpense(String id) {
         for (Expense e: expenses){
           if(e.getId().trim().equalsIgnoreCase(id.trim())){
               expenses.remove(e);// Found id and removed
               return true;
           }
       }
       return false;//not found
    }     
    // Search expenses by category 
    public ArrayList<Expense> searchByCategory(String category) {
        ArrayList<Expense> results = new ArrayList<>();
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                results.add(e);
            }
        }
        return results;
    }
    
    // FInd an expense by ID
   public Expense findById(String id){
       for (Expense e: expenses){
           if(e.getId().trim().equalsIgnoreCase(id.trim())){
               return e;// found id
           }
       }
       return null;//not found
    }

    // Display all expenses for view/manage tab
    public String displayAllExpenses() { //For view tab
        String result = "";
        for(Expense e : expenses) {
            result += e.toString() + "\n\n";
        }
        return result;
    }

    // Calculate and display total number of records and total amount spent
    public String calculateTotal() {
        double total = 0;
        int count = expenses.size();
        
        for (Expense e : expenses) {
            total += e.getAmount();
        }         
        return  "Number of Records: " + count + 
                "\nTotal Expenses: RM " + total;
    }

    //Calculate total expenses by category
    public double calculateTotalByCategory(String category) {
    double total = 0;
    for (Expense e : expenses) {
        if (e.getCategory().equalsIgnoreCase(category)) {
            total += e.getAmount();
        }
    }
    return total;
}
    // Clear all expenses (used for Reset button)
    public void clearAllExpenses() {
        expenses.clear();
    }

    //  Save all expenses to a file
    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Expense e : expenses) {
                writer.println(e.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    //  Load expenses from a file
    public void loadFromFile(String filename) {
        expenses.clear(); // Clear current data before loading
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Expense e = Expense.fromFileString(line);
                    expenses.add(e);
                } catch (IllegalArgumentException ex) {
                    System.out.println("Skipping invalid expense line: " + ex.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    // Get full list of expenses 
    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

}
