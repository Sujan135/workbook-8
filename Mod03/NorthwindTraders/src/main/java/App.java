import java.sql.*;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/northwind";
        String username = "root";
        String password = "Nepal135!";
        String query = "SELECT ProductName FROM products";

        try {
            Connection connection = DriverManager.getConnection(url,username,password);

            Statement statement = connection.createStatement();

            ResultSet resultSet=  statement.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString("ProductName");
                System.out.println(name);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
