public interface PaymentStrategy {
    void pay(double amount);

    default void applyLoyaltyPoints(UserProfile userProfile, double amount) {
        int pointsEarned = (int) (amount / 10); // Example: $1 = 1 point for every $10 spent
        userProfile.addLoyaltyPoints(pointsEarned);
    }
}

