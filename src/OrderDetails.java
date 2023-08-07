import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetails {
    private Connection connection;

    // database connect
   public OrderDetails() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "rash23@");
    }
    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    // insert
    public void insertOrderDetail(int orderId, int itemId, int quantity, double subtotal) throws SQLException {
        String sql = "INSERT INTO orderdetails (order_id, item_id, quantity, subtotal) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            statement.setInt(2, itemId);
            statement.setInt(3, quantity);
            statement.setDouble(4, subtotal);
            statement.executeUpdate();
        }
    }

}
