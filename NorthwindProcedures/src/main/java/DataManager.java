import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;

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
}
