class PizzaDecorator {
    private Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    public String getDescription() {
        return pizza.toString();
    }

    public double getPrice() {
        return pizza.getBasePrice();
    }
}