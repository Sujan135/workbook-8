import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("1) Display all products");
            System.out.println("2) Display all customers");
            System.out.println("0) Exit");
            System.out.println("Select an option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    displayAllProducts();
                    break;
                case "2":
                    displayAllCustomers();
                    break;
                case "0":
                    System.out.println("Exiting...");
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid option. ");
            }
        }
    }

    private  static void displayAllProducts() {

        String url = "jdbc:mysql://localhost:3306/northwind";
        String username = "root";
        String password = "Nepal135!";
        String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM products";


        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            statement = connection.createStatement();
            resultSet=  statement.executeQuery(query);

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

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private  static void displayAllCustomers() {

        String url = "jdbc:mysql://localhost:3306/northwind";
        String username = "root";
        String password = "Nepal135!";
        String query = "SELECT ContactName, CompanyName, City, Country, Phone" +
                " FROM customers ORDER BY Country";


        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            statement = connection.createStatement();
            resultSet=  statement.executeQuery(query);

            while (resultSet.next()) {
                String contactName = resultSet.getString("ContactName");
                String companyName = resultSet.getString("CompanyName");
                String city = resultSet.getString("City");
                String country = resultSet.getString("Country");
                String phone = resultSet.getString("Phone");


                System.out.println("Contact Name: " + contactName);
                System.out.println("Company Name: " + companyName);
                System.out.println("City: " + city);
                System.out.println("Country: " + country);
                System.out.println("Phone: " + phone);
                System.out.println("--------------------");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
