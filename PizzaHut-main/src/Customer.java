class Customer implements Observer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void update(String state) {
        System.out.println(name + ", your order status is now: " + state);
    }
}
