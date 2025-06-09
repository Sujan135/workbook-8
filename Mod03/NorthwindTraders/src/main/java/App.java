import java.sql.*;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/northwind";
        String username = "root";
        String password = "Nepal135!";
        String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM products";

        try {

            Connection connection = DriverManager.getConnection(url,username,password);

            Statement statement = connection.createStatement();

            ResultSet resultSet=  statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("ProductID");
                String name = resultSet.getString("ProductName");
                double price = resultSet.getDouble("UnitPrice");
                int stock = resultSet.getInt("UnitsInStock");


                System.out.println("Product Id: " + id);
                System.out.println("Name: " + name);
                System.out.println("Price: " + price);
                System.out.println("Stock: " + stock);
                System.out.println("--------------------");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
