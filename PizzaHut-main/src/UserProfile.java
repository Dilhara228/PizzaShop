import java.util.ArrayList;
import java.util.List;

public class UserProfile {
    private String name;
    private List<String> favorites = new ArrayList<>();
    private int loyaltyPoints = 0;

    // Constructor to create a user profile with a name
    public UserProfile(String name) {
        this.name = name;
    }

    // Get the user's name
    public String getName() {
        return name;
    }

    // Get the list of favorite pizzas
    public List<String> getFavorites() {
        return favorites;
    }

    // Add a pizza to the user's favorites
    public void addFavorite(String pizzaDetails) {
        favorites.add(pizzaDetails);
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }

    public void redeemLoyaltyPoints(int points) {
        if (points <= loyaltyPoints) {
            this.loyaltyPoints -= points;
        } else {
            System.out.println("Not enough loyalty points to redeem.");
        }
    }
}
