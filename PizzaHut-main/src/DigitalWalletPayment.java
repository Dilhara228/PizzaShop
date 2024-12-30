import java.util.Scanner;

// DigitalWalletPayment implements PaymentStrategy
public class DigitalWalletPayment implements PaymentStrategy {
    private String walletId;

    @Override
    public void pay(double amount) {
        Scanner scanner = new Scanner(System.in);

        // Collect digital wallet details
        System.out.println("Enter Digital Wallet ID (e.g., email or username): ");
        walletId = scanner.nextLine();

        // Mock payment process
        System.out.println("Processing payment of $" + amount + " using Digital Wallet...");
        System.out.println("Payment Successful! Wallet ID: " + walletId + " has been charged.");
    }

    @Override
    public void applyLoyaltyPoints(UserProfile userProfile, double amount) {
        int loyaltyPoints = (int) amount / 10; // Earn 1 point for every $10 spent
        userProfile.addLoyaltyPoints(loyaltyPoints);
        System.out.println("You have earned " + loyaltyPoints + " loyalty points!");
    }
}
