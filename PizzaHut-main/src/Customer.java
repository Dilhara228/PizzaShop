public class Customer implements Observer {
    private String customerName;

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public void update(String state) {
        System.out.println("Order update for " + customerName + ": Your order status has changed.");
    }
}
