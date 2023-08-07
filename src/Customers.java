import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customers {
    private Connection conn;

    // database connect
    public Customers() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "rash23@");
    }

    //insert
    public void insertCustomer(int customerId, String customerName, String email, int quantityBought) throws SQLException {
        String sql = "INSERT INTO customer (customer_id, customer_name, email, quantity_bought) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, customerId);
        statement.setString(2, customerName);
        statement.setString(3, email);
        statement.setInt(4, quantityBought);
    
        int result = statement.executeUpdate();
    
        System.out.println(result > 0 ? "Insert Customer Success" : "Insert Customer Fail");
    
        statement.close();
    }
    

    //read
    public void readCustomers() throws SQLException {
        String sql = "SELECT * FROM customer";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.println("Customer ID: " + resultSet.getInt("id"));
            System.out.println("Customer Name: " + resultSet.getString("name"));
            System.out.println("Customer Email: " + resultSet.getString("email"));
            System.out.println("Quantity Bought: " + resultSet.getInt("quantity_bought"));
            System.out.println("------------");
        }

        resultSet.close();
        statement.close();
    }

    //update
    public void updateCustomer(int customerId, String customerName, String email, int quantityBought) throws SQLException {
        String sql = "UPDATE customer SET name = ?, email = ?, quantity_bought = ? WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, customerName);
        statement.setString(2, email);
        statement.setInt(3, quantityBought);
        statement.setInt(4, customerId);

        int result = statement.executeUpdate();

        System.out.println(result > 0 ? "Update Customer Success" : "Update Customer Fail");

        statement.close();
    }

    //delete
    public void deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM customer WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, customerId);

        int result = statement.executeUpdate();

        System.out.println(result > 0 ? "Delete Customer Success" : "Delete Customer Fail");

        statement.close();
    }

    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
