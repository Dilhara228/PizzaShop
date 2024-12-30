import java.util.ArrayList;

public class UserProfile {
    private String name;
    private ArrayList<String> favorites;
    private int loyaltyPoints;

    public UserProfile(String name) {
        this.name = name;
        this.favorites = new ArrayList<>();
        this.loyaltyPoints = 0;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getFavorites() {
        return favorites;
    }

    public void addFavorite(String pizza) {
        if (!favorites.contains(pizza)) {
            favorites.add(pizza);
        }
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        loyaltyPoints += points;
    }

    public void redeemLoyaltyPoints(int points) {
        if (loyaltyPoints >= points) {
            loyaltyPoints -= points;
        } else {
            System.out.println("Not enough loyalty points.");
        }
    }
}
