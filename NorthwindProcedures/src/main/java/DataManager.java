import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataManager {
    private DataSource dataSource;

    public DataManager() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/northwind");
        ds.setUsername("root");
        ds.setPassword("your_password");
        this.dataSource = ds;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void getCustomerOrderHistory(String customerId) {
        String query = "{CALL CustOrderHist(?)}";
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.printf("%s - %d\n",
                        rs.getString("ProductName"),
                        rs.getInt("TOTAL"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
