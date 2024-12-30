import java.util.ArrayList;
import java.util.List;

public class SeasonalPromotion {
    private String promotionName;
    private String description;
    private double discountPercentage;
    private String applicableTo;

    // Constructor
    public SeasonalPromotion(String promotionName, String description, double discountPercentage, String applicableTo) {
        this.promotionName = promotionName;
        this.description = description;
        this.discountPercentage = discountPercentage;
        this.applicableTo = applicableTo; // Can be "size", "topping", or "orderAmount"
    }

    // Getter methods
    public String getPromotionName() {
        return promotionName;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public String getApplicableTo() {
        return applicableTo;
    }

    // Method to apply the discount to the price based on the type of promotion
    public double applyPromotion(double price, String applicableItem) {
        if (this.applicableTo.equalsIgnoreCase("size") && applicableItem.equals("Large")) {
            return price * (1 - discountPercentage);
        } else if (this.applicableTo.equalsIgnoreCase("topping") && applicableItem.equals("Mushrooms")) {
            return price * (1 - discountPercentage);
        } else if (this.applicableTo.equalsIgnoreCase("orderAmount") && price > 30) {
            return price * (1 - discountPercentage);
        }
        return price; // No discount if conditions are not met
    }

    @Override
    public String toString() {
        return promotionName + ": " + description + " (Discount: " + (discountPercentage * 100) + "%)";
    }

    // Static method to return a list of sample promotions
    public static List<SeasonalPromotion> getSamplePromotions() {
        List<SeasonalPromotion> promotions = new ArrayList<>();
        promotions.add(new SeasonalPromotion("Large Pizza Special", "20% off on Large Pizzas", 0.20, "size"));
        promotions.add(new SeasonalPromotion("Mushroom Lover", "10% off on Mushrooms topping", 0.10, "topping"));
        promotions.add(new SeasonalPromotion("Big Order Discount", "15% off for orders over $30", 0.15, "orderAmount"));
        return promotions;
    }
}

