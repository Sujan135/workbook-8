import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Manager dataManager = null;

        try {
            dataManager = new Manager();

            System.out.print("Enter actor's last name: ");
            String lastName = scanner.nextLine();

            List<Actor> actors = dataManager.findActorsByLastName(lastName);
            if (actors.isEmpty()) {
                System.out.println("No actors found with last name: " + lastName);
                return;
            } else {
                actors.forEach(System.out::println);
            }

            System.out.print("\nEnter actor ID to see their films: ");
            String input = scanner.nextLine();
            int actorId;
            try {
                actorId = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid actor ID. Please enter a number.");
                return;
            }

            List<Film> films = dataManager.getFilmsByActorId(actorId);
            if (films.isEmpty()) {
                System.out.println("No films found for actor ID: " + actorId);
            } else {
                films.forEach(System.out::println);
            }

        } catch (Exception e) {
            System.out.println("An error occurred:");
            e.printStackTrace();
        } finally {
            if (dataManager != null) {
                dataManager.close();
            }
            scanner.close();
        }
    }
}
