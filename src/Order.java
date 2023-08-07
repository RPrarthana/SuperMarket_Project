import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Order {
    private Connection connection;

    // database connect
     public Order() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "rash23@");
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    // insert
    public void insertOrder(int customerId, double total) throws SQLException {
        String sql = "INSERT INTO orders (customer_id, total) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            statement.setDouble(2, total);
            statement.executeUpdate();
        }
    }

    public int getId() {
        return 0;
    }

}
