import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private String size;
    private List<String> toppings;
    private String crust;
    private String sauce;
    private String cheese;
    private double basePrice;
    private List<Feedback> feedbackList;  // List to store feedback

    // Private constructor, called by the PizzaBuilder
    private Pizza(PizzaBuilder builder) {
        this.name = builder.name;
        this.size = builder.size;
        this.toppings = builder.toppings;
        this.crust = builder.crust;
        this.sauce = builder.sauce;
        this.cheese = builder.cheese;
        this.basePrice = builder.basePrice;
        this.feedbackList = builder.feedbackList;  // Initialize feedback list
    }

    // Getters for each field
    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public List<String> getToppings() {
        return toppings;
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

    public double getBasePrice() {
        return basePrice;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    // Method to add feedback
    public void addFeedback(Feedback feedback) {
        feedbackList.add(feedback);
    }

    // Method to calculate average rating
    public double calculateAverageRating() {
        if (feedbackList.isEmpty()) {
            return 0.0;
        }
        double total = 0;
        for (Feedback feedback : feedbackList) {
            total += feedback.getRating();
        }
        return total / feedbackList.size();
    }

    // Method to display pizza details along with average rating
    public String getDetails() {
        return "Name: " + name + ", Size: " + size + ", Crust: " + crust + ", Sauce: " + sauce + ", Cheese: " + cheese + ", Toppings: " + String.join(", ", toppings) + ", Average Rating: " + calculateAverageRating() + " stars";
    }

    // Static inner class for PizzaBuilder
    public static class PizzaBuilder {
        private String name;
        private String size;
        private List<String> toppings = new ArrayList<>();
        private String crust;
        private String sauce;
        private String cheese;
        private double basePrice;
        private List<Feedback> feedbackList = new ArrayList<>();  // Initialize feedback list

        // Setters for builder pattern
        public PizzaBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PizzaBuilder setSize(String size) {
            this.size = size;
            setBasePriceBasedOnSize(size);
            return this;
        }

        public PizzaBuilder addTopping(String topping) {
            this.toppings.add(topping);
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

        // Method to set base price based on pizza size
        private void setBasePriceBasedOnSize(String size) {
            switch (size) {
                case "Small":
                    this.basePrice = 8.0;
                    break;
                case "Medium":
                    this.basePrice = 10.0;
                    break;
                case "Large":
                    this.basePrice = 12.0;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid size: " + size);
            }
        }

        // Build method to create a Pizza object
        public Pizza build() {
            return new Pizza(this);
        }
    }

    // Feedback class to represent customer feedback and ratings
    public static class Feedback {
        private String customerName;
        private double rating;  // Rating out of 5
        private String comments;

        public Feedback(String customerName, double rating, String comments) {
            this.customerName = customerName;
            this.rating = rating;
            this.comments = comments;
        }

        // Getters for feedback details
        public String getCustomerName() {
            return customerName;
        }

        public double getRating() {
            return rating;
        }

        public String getComments() {
            return comments;
        }

        @Override
        public String toString() {
            return "Customer: " + customerName + ", Rating: " + rating + " stars, Comments: " + comments;
        }
    }
}
