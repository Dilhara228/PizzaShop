import java.util.Scanner;

// CreditCardPayment implements PaymentStrategy
public class CreditCardPayment implements PaymentStrategy {
    private String cardName;
    private String cardNumber;
    private String expiryDate;

    @Override
    public void pay(double amount) {
        Scanner scanner = new Scanner(System.in);

        // Collect credit card details with validation
        while (true) {
            System.out.println("Enter Cardholder Name: ");
            cardName = scanner.nextLine().trim();
            if (!cardName.isEmpty() && cardName.matches("[a-zA-Z ]+")) {
                break;
            }
            System.out.println("Invalid name. Please enter a valid cardholder name (letters and spaces only).");
        }

        while (true) {
            System.out.println("Enter Card Number: ");
            cardNumber = scanner.nextLine().trim();
            if (cardNumber.matches("\\d{16}")) {
                break;
            }
            System.out.println("Invalid card number. Please enter a valid 16-digit card number.");
        }

        while (true) {
            System.out.println("Enter Expiry Date (MM/YY): ");
            expiryDate = scanner.nextLine().trim();
            if (expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
                break;
            }
            System.out.println("Invalid expiry date. Please enter a valid date in MM/YY format.");
        }

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
