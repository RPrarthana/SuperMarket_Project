import java.sql.SQLException;
import java.util.Scanner;

public class PlaceOrder {

    public static void placeOrder(Items items, Customers customers) throws SQLException, ClassNotFoundException {
        
        Scanner input = new Scanner(System.in);

        // Display available items to the customer
        System.out.println("Available items:");
        items.readItems();

        // Get the customer ID
        System.out.print("Input Customer ID: ");
        int customerId = input.nextInt();
        input.nextLine();

        // Create a new order record
        Order orders = new Order();

        // Calculate the total price for the order
        double totalPrice = 0.0;

        while (true) {
            System.out.print("Input Item ID to add to the order (or 0 to finish): ");
            int itemId = input.nextInt();
            input.nextLine();

            if (itemId == 0) {
                break; 
            }

            // Get the item details from the database based on the item ID
            Items item = items.getItemById(itemId);

            if (item == null) {
                System.out.println("Invalid item ID. Please enter a valid item ID.");
                continue;
            }

            // Get the quantity to be ordered
            System.out.print("Input quantity to order: ");
            int quantityToOrder = input.nextInt();
            input.nextLine();

            if (quantityToOrder <= 0) {
                System.out.println("Invalid quantity. Please enter a valid quantity.");
                continue;
            }

            // Check item availability in the inventory
            if (quantityToOrder > item.getQuantity()) {
                System.out.println("Insufficient quantity available in the inventory for this item.");
                continue;
            }

            // Calculate the subtotal for the item
            double subtotal = item.getPrice() * quantityToOrder;

            // Update the item quantity in the inventory
            items.updateItemQuantity(itemId, item.getQuantity() - quantityToOrder);

            // Update the total price for the order
            totalPrice += subtotal;

            // Insert order details into the orderdetails table
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.insertOrderDetail(orders.getId(), itemId, quantityToOrder, subtotal);
        }

        // Insert the order into the orders table
        orders.insertOrder(customerId, totalPrice);

        System.out.println("Total Price of the Order: " + totalPrice);
        System.out.println("Order successfully placed!");
    }
}
