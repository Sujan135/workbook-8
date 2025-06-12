import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1) Search for customer order history");
            System.out.println("2) Search for sales by year");
            System.out.println("3) Search for sales by category");
            System.out.println("0) Exit");
            System.out.print("Select option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) break;

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Customer ID: ");
                    String customerId = scanner.nextLine();
                    dataManager.getCustomerOrderHistory(customerId);
                }
                case 2 -> {
                    System.out.print("Enter Year (YYYY): ");
                    String year = scanner.nextLine();
                    dataManager.getSalesByYear(year);
                }
                case 3 -> {
                    System.out.print("Enter Category ID: ");
                    int catId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Category Name: ");
                    String catagoryName = scanner.nextLine();
                    dataManager.getSalesByCategory(catId, catagoryName);
                }
                default -> System.out.println("Invalid option");
            }
        }
        scanner.close();
    }
}
