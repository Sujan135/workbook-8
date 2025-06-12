import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("Nepal135!");

        ShipperDAO dao = new ShipperDAO(dataSource);

        System.out.print("Enter company name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        int newId = dao.insertShipper(new Shipper(name, phone));
        System.out.println("Inserted shipper with ID: " + newId);

        List<Shipper> shippers = dao.getAllShippers();
        for (Shipper s : shippers) {
            System.out.println("ID: " + s.getShipperId() +
                    " | Company: " + s.getCompanyName() +
                    " | Phone: " + s.getPhone());
        }

        System.out.print("\nEnter Shipper ID to update phone: ");
        int idToUpdate = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new phone: ");
        String newPhone = scanner.nextLine();
        dao.updateShipperPhone(idToUpdate, newPhone);

        System.out.println("\nUpdated Shippers:");
        shippers = dao.getAllShippers();
        for (Shipper s : shippers) {
            System.out.println("ID: " + s.getShipperId() +
                    " | Company: " + s.getCompanyName() +
                    " | Phone: " + s.getPhone());
        }

        System.out.print("\nEnter ID to delete (NOT 1-3): ");
        int deleteId = Integer.parseInt(scanner.nextLine());
        if (deleteId > 3) {
            dao.deleteShipper(deleteId);
            System.out.println("Deleted shipper with ID: " + deleteId);
        } else {
            System.out.println("Cannot delete shippers with ID 1-3.");
        }

        System.out.println("\nFinal Shipper List:");
        shippers = dao.getAllShippers();
        for (Shipper s : shippers) {
            System.out.println("Shipper ID: " + s.getShipperId());
            System.out.println("Company Name: " + s.getCompanyName());
            System.out.println("Phone Number: " + s.getPhone());
            System.out.println("----------------------------");
        }
    }
}
