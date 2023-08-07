import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Items {
    private Connection conn;

    // database connect
    public Items() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "rash23@");
    }

    //insert
    public void insertItem(String itemId, String itemName, double price, int quantity) throws SQLException {
        String sql = "INSERT INTO items (id, name, price, quantity) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, itemId);
        statement.setString(2, itemName);
        statement.setDouble(3, price);
        statement.setInt(4, quantity);

        int result = statement.executeUpdate();

        System.out.println(result > 0 ? "Success" : "Fail");

        statement.close();
    }
    
    // Read
    public void readItems() throws SQLException {
        String sql = "SELECT * FROM items";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.println("Item ID: " + resultSet.getString("id"));
            System.out.println("Item Name: " + resultSet.getString("name"));
            System.out.println("Item Price: " + resultSet.getDouble("price"));
            System.out.println("Item Quantity: " + resultSet.getInt("quantity"));
            System.out.println("------------");
        }

        resultSet.close();
        statement.close();
    }

    // Update
    public void updateItem(String itemId, String itemName, double price, int quantity) throws SQLException {
        String sql = "UPDATE items SET name = ?, price = ?, quantity = ? WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, itemName);
        statement.setDouble(2, price);
        statement.setInt(3, quantity);
        statement.setString(4, itemId);

        int result = statement.executeUpdate();

        System.out.println(result > 0 ? "Update Success" : "Update Fail");

        statement.close();
    }

    // Delete
    public void deleteItem(String itemId) throws SQLException {
        String sql = "DELETE FROM items WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, itemId);

        int result = statement.executeUpdate();

        System.out.println(result > 0 ? "Delete Success" : "Delete Fail");

        statement.close();
    }

    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public Items getItemById(int itemId) {
        return null;
    }

    public int getQuantity() {
        return 0;
    }

    public int getPrice() {
        return 0;
    }

    public void updateItemQuantity(int itemId, int i) {
    }
}
