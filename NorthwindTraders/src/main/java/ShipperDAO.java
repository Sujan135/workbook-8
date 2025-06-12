import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShipperDAO {
    private final DataSource dataSource;

    public ShipperDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int insertShipper(Shipper shipper) throws SQLException {
        String sql = "INSERT INTO shippers (CompanyName, Phone) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, shipper.getCompanyName());
            preparedStatement.setString(2, shipper.getPhone());
            preparedStatement.executeUpdate();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }

    public List<Shipper> getAllShippers() throws SQLException {
        List<Shipper> shippers = new ArrayList<>();
        String sql = "SELECT * FROM shippers";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                shippers.add(new Shipper(
                        resultSet.getInt("ShipperID"),
                        resultSet.getString("CompanyName"),
                        resultSet.getString("Phone")));
            }
        }
        return shippers;
    }


}
