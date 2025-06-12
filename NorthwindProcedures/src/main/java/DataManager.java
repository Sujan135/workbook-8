import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataManager {
    private DataSource dataSource;

    public DataManager() {
        BasicDataSource dataSource1 = new BasicDataSource();
        dataSource1.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource1.setUsername("root");
        dataSource1.setPassword("Nepal135!");
        this.dataSource = dataSource1;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void getCustomerOrderHistory(String customerId) {
        String query = "{CALL CustOrderHist(?)}";
        try (Connection conn = dataSource.getConnection();
             CallableStatement statement = conn.prepareCall(query)) {

            statement.setString(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.printf("%s - %d\n",
                        resultSet.getString("ProductName"),
                        resultSet.getInt("TOTAL"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getSalesByYear(String year) {
        String query = "{CALL `Sales by Year`(?)}";
        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(query)) {

            statement.setString(1, year);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.printf("%s - %.2f\n",
                        resultSet.getString("ShipperName"),
                        resultSet.getDouble("TotalPurchase"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getSalesByCategory(int categoryId, String categoryName) {
        String query = "{CALL SalesByCategory(?, ?)}";
        try (Connection connection = dataSource.getConnection();
             CallableStatement statement = connection.prepareCall(query)) {

            statement.setInt(1, categoryId);
            statement.setString(2, categoryName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.printf("%s - %s - %d\n",
                        resultSet.getString("ProductName"),
                        resultSet.getString("ProductDescription"),
                        resultSet.getInt("TotalPurchase"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
