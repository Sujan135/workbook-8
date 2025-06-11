import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    private Connection connection;

    public Manager() throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername("root");
        dataSource.setPassword("Nepal135!");
        this.connection = dataSource.getConnection();
    }

    public List<Actor> findActorsByLastName(String lastName) throws SQLException {
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT actor_id, first_name, last_name FROM actor WHERE last_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, lastName);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                actors.add(new Actor(
                        rs.getInt("actor_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                ));
            }
        }
        return actors;
    }

    public List<Film> getFilmsByActorId(int actorId) throws SQLException {
        List<Film> films = new ArrayList<>();
        String query = """
                SELECT f.film_id, f.title, f.description, f.release_year, f.length
                FROM film f
                JOIN film_actor fa ON f.film_id = fa.film_id
                WHERE fa.actor_id = ?
                """;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, actorId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                films.add(new Film(
                        rs.getInt("film_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("release_year"),
                        rs.getInt("length")
                ));
            }
        }
        return films;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}