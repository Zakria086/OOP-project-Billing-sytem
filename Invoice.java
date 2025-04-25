import java.util.ArrayList;

public class Invoice {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Integer> quantities = new ArrayList<>();
    private double discount;
    private static final double TAX_RATE = 0.1;

    public void addItem(Product product, int quantity) {
        products.add(product);
        quantities.add(quantity);
    }

    public void applyDiscount(double discountAmount) {
        this.discount = discountAmount;

    }

    public double calculateTotal() {
        double subtotal = 0;
        for (int i = 0; i < products.size(); i++) {
            subtotal += products.get(i).getPrice() * quantities.get(i);
        }
        double discount= subtotal*0.1;
        double tax = subtotal * TAX_RATE;
        return subtotal + tax - discount;

    }

    public void printInvoice() {
        System.out.println("\n--- Invoice ---");
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i).getName() + " - Rs" + products.get(i).getPrice());
            System.out.println("Quantity: " + quantities.get(i));
        }
        System.out.println("Discount: 10%" + discount);
        System.out.printf("Total (with tax): $%.2f%n", calculateTotal());
        System.out.println("---------------------");
    }
}

