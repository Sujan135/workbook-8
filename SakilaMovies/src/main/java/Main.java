import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUser("root");
        dataSource.setPassword("Nepal135!");

        try (
                Scanner scanner = new Scanner(System.in);
                Connection conn = dataSource.getConnection()
        ) {
            System.out.print("Enter actor's last name: ");
            String lastName = scanner.nextLine();

            String findActors = "SELECT actor_id, first_name, last_name FROM actor WHERE last_name = ?";
            try (PreparedStatement stmt = conn.prepareStatement(findActors)) {
                stmt.setString(1, lastName);
                ResultSet rs = stmt.executeQuery();

                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println(rs.getInt("actor_id") + ": " +
                            rs.getString("first_name") + " " + rs.getString("last_name"));
                }

                if (!found) {
                    System.out.println("No actors found with last name: " + lastName);
                }
            }

            System.out.print("\nEnter actor's first name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter actor's last name again: ");
            lastName = scanner.nextLine();

            String findMovies = """
                SELECT f.title
                FROM actor a
                JOIN film_actor fa ON a.actor_id = fa.actor_id
                JOIN film f ON fa.film_id = f.film_id
                WHERE a.first_name = ? AND a.last_name = ?
            """;

            try (PreparedStatement stmt = conn.prepareStatement(findMovies)) {
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                ResultSet rs = stmt.executeQuery();

                boolean foundMovies = false;
                while (rs.next()) {
                    foundMovies = true;
                    System.out.println("Film: " + rs.getString("title"));
                }

                if (!foundMovies) {
                    System.out.println("No films found for actor: " + firstName + " " + lastName);
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error:");
            e.printStackTrace();
        }
    }
}
