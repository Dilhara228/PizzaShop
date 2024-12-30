class ExtraToppingDecorator extends PizzaDecorator {
    private String topping;
    private double toppingPrice = 1.5;

    public ExtraToppingDecorator(Pizza pizza, String topping) {
        super(pizza);
        this.topping = topping;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Extra " + topping;
    }

    @Override
    public double getPrice() {
        return super.getPrice() + toppingPrice;
    }
}
