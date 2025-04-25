public class TransactionThread extends Thread {
    private Customer customer;
    private Invoice invoice;

    public TransactionThread(Customer customer, Invoice invoice) {
        this.customer = customer;
        this.invoice = invoice;
    }

    @Override
    public void run() {
        System.out.println("\nProcessing transaction for: " + customer.name);
        invoice.printInvoice();
        System.out.println("Transaction completed for: " + customer.name);
    }
}

