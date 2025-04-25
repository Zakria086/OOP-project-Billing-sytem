import java.util.ArrayList;
import java.util.Scanner;

public class BillingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Product> storeProducts = new ArrayList<>();
        storeProducts.add(new Product("Shampoo", 5.99));
        storeProducts.add(new Product("Toothpaste", 2.49));
        storeProducts.add(new Product("Soap", 1.99));
        storeProducts.add(new Product("Lotion", 4.50));

        System.out.print("Enter number of customers: ");
        int numCustomers = scanner.nextInt();
        scanner.nextLine(); // consume newline

        ArrayList<Thread> threads = new ArrayList<>();

        for (int c = 0; c < numCustomers; c++) {
            System.out.println("\nEnter details for customer " + (c + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();

            Customer customer = new Customer(name, email);
            Invoice invoice = new Invoice();

            System.out.println("\nAvailable Products:");
            for (int i = 0; i < storeProducts.size(); i++) {
                System.out.print((i + 1) + ". ");
                storeProducts.get(i).displayProduct();
            }

            while (true) {
                System.out.print("Enter product number to add to invoice (0 to finish): ");
                int choice = scanner.nextInt();
                if (choice == 0) break;
                if (choice < 1 || choice > storeProducts.size()) {
                    System.out.println("Invalid choice. Try again.");
                    continue;
                }

                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();
                invoice.addItem(storeProducts.get(choice - 1), quantity);
            }

//            System.out.print("Enter discount amount: ");
//           double discount = scanner.nextDouble();
//            scanner.nextLine(); // consume newline
//           invoice.applyDiscount(discount);

            Thread t = new TransactionThread(customer, invoice);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n=== All Transactions Completed ===");
        scanner.close();
    }
}
