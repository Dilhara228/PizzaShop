import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name; // Name of the pizza
    private String size;
    private String crust;
    private String sauce;
    private String cheese;
    private List<String> toppings;
    private double basePrice;

    public Pizza(String name, String size, String crust, String sauce, String cheese, List<String> toppings) {
        this.name = name;
        this.size = size;
        this.crust = crust;
        this.sauce = sauce;
        this.cheese = cheese;
        this.toppings = toppings;
        this.basePrice = calculateBasePrice();
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getCrust() {
        return crust;
    }

    public String getSauce() {
        return sauce;
    }

    public String getCheese() {
        return cheese;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public double getBasePrice() {
        return basePrice;
    }

    // Calculate price based on the pizza size
    private double calculateBasePrice() {
        switch (size) {
            case "Small": return 8.0;
            case "Medium": return 10.0;
            case "Large": return 12.0;
            default: return 10.0;
        }
    }

    // Returns the details of the pizza as a string
    public String getDetails() {
        return "Size: " + size + ", Crust: " + crust + ", Sauce: " + sauce +
                ", Cheese: " + cheese + ", Toppings: " + String.join(", ", toppings) +
                " | Base Price: $" + basePrice;
    }

    public static class PizzaBuilder {
        private String name;
        private String size;
        private String crust;
        private String sauce;
        private String cheese;
        private List<String> toppings;

        public PizzaBuilder() {
            this.toppings = new ArrayList<>();
        }

        public PizzaBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PizzaBuilder setSize(String size) {
            this.size = size;
            return this;
        }

        public PizzaBuilder setCrust(String crust) {
            this.crust = crust;
            return this;
        }

        public PizzaBuilder setSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public PizzaBuilder setCheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public PizzaBuilder addTopping(String topping) {
            this.toppings.add(topping);
            return this;
        }

        public Pizza build() {
            return new Pizza(name, size, crust, sauce, cheese, toppings);
        }
    }
}
