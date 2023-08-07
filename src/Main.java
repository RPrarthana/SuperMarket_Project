import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        System.out.println("Select an option:");
        System.out.println("1. Manage Items");
        System.out.println("2. Manage Customers");
        System.out.println("3. Place Order");
        System.out.print("Enter your choice (1, 2 or 3): ");

        int choice = input.nextInt();
        input.nextLine(); 

        if (choice == 1) {
            try {
                Items items = new Items();


                items.closeConnection();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } else if (choice == 2) {
            try {
                Customers customerHandler = new Customers();


                customerHandler.closeConnection();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }



        }  else if (choice == 3) {
            try {
                Items items = new Items();
                Customers customerHandler = new Customers();
                PlaceOrder.placeOrder(items, customerHandler);
                items.closeConnection();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Invalid choice. Please enter either 1 or 2.");
        }

        if (choice == 1) {
            try {
                Items items = new Items();

                // Insert an item
                System.out.print("Input Item Code: ");
                String itemId = input.nextLine();

                System.out.print("Input Item Name: ");
                String itemName = input.nextLine();

                System.out.print("Input Item Price: ");
                double price = input.nextDouble();

                System.out.print("Input Item Quantity: ");
                int quantity = input.nextInt();

                items.insertItem(itemId, itemName, price, quantity);

                // Read all items
                System.out.println("All items in the database:");
                items.readItems();

                // Update an item
                input.nextLine(); 
                System.out.print("Input Item ID to update: ");
                itemId = input.nextLine();

                System.out.print("Input updated Item Name: ");
                itemName = input.nextLine();

                System.out.print("Input updated Item Price: ");
                price = input.nextDouble();

                System.out.print("Input updated Item Quantity: ");
                quantity = input.nextInt();

                items.updateItem(itemId, itemName, price, quantity);

                // Delete an item
                System.out.print("Input Item ID to delete: ");
                itemId = input.next();

                items.deleteItem(itemId);

                items.closeConnection();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } else if (choice == 2) {
            try {
                Customers customerHandler = new Customers();

                // Customer
                System.out.print("Input Customer ID: ");
                int customerId = input.nextInt();
                input.nextLine(); 

                System.out.print("Input Customer Name: ");
                String customerName = input.nextLine();

                System.out.print("Input Customer Email: ");
                String email = input.nextLine();

                System.out.print("Input Quantity Bought: ");
                int quantityBought = input.nextInt();

                customerHandler.insertCustomer(customerId, customerName, email, quantityBought);

                // Read all customers
                System.out.println("All customers in the database:");
                customerHandler.readCustomers();

                // Update a customer
                input.nextLine(); 
                System.out.print("Input Customer ID to update: ");
                customerId = input.nextInt();
                input.nextLine(); 

                System.out.print("Input Updated Customer Name: ");
                customerName = input.nextLine();

                System.out.print("Input Updated Customer Email: ");
                email = input.nextLine();

                System.out.print("Input Updated Quantity Bought: ");
                quantityBought = input.nextInt();

                customerHandler.updateCustomer(customerId, customerName, email, quantityBought);

                // Delete a customer
                System.out.print("Input Customer ID to delete: ");
                customerId = input.nextInt();

                customerHandler.deleteCustomer(customerId);

                customerHandler.closeConnection();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

          
        }
    
    }}
    

        

