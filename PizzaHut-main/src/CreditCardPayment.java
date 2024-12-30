import java.util.Scanner;

// CreditCardPayment implements PaymentStrategy
public class CreditCardPayment implements PaymentStrategy {
    private String cardName;
    private String cardNumber;
    private String expiryDate;

    @Override
    public void pay(double amount) {
        Scanner scanner = new Scanner(System.in);

        // Collect credit card details
        System.out.println("Enter Cardholder Name: ");
        cardName = scanner.nextLine();

        System.out.println("Enter Card Number: ");
        cardNumber = scanner.nextLine();

        System.out.println("Enter Expiry Date (MM/YY): ");
        expiryDate = scanner.nextLine();

        // Mock payment process
        System.out.println("Processing payment of $" + amount + " using Credit Card...");
        System.out.println("Payment Successful! Thank you, " + cardName + ".");
    }

    @Override
    public void applyLoyaltyPoints(UserProfile userProfile, double amount) {
        int loyaltyPoints = (int) amount / 10; // Earn 1 point for every $10 spent
        userProfile.addLoyaltyPoints(loyaltyPoints);
        System.out.println("You have earned " + loyaltyPoints + " loyalty points!");
    }
}
