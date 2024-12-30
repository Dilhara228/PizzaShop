import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // Step 0: Create User Profile
        System.out.println("Welcome to the Pizza Shop!");
        System.out.println("Enter your name to create a profile: ");
        String userName = scanner.nextLine();
        UserProfile userProfile = new UserProfile(userName);
        System.out.println("Hello, " + userProfile.getName() + "!");

        // Step 1: Pizza Selection
        System.out.println("Please choose a pizza name from the following list:");
        String[] availablePizzas = {
                "Margherita",
                "Pepperoni",
                "Veggie Supreme",
                "BBQ Chicken",
                "Mushroom Delight"
        };


        for (int i = 0; i < availablePizzas.length; i++) {
            System.out.println((i + 1) + ". " + availablePizzas[i]);
        }
        System.out.println("Enter the number corresponding to your pizza choice: ");
        int pizzaChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        String selectedPizzaName = availablePizzas[pizzaChoice - 1];
        System.out.println("You chose: " + selectedPizzaName);



        // Step 2: Pizza Customization
        Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder();
        builder.setName(selectedPizzaName);
        System.out.println("Select your pizza size (1. Small - $8, 2. Medium - $10, 3. Large - $12): ");
        int sizeChoice = scanner.nextInt();
        scanner.nextLine();
        switch (sizeChoice) {
            case 1: builder.setSize("Small"); break;
            case 2: builder.setSize("Medium"); break;
            case 3: builder.setSize("Large"); break;
            default: builder.setSize("Medium");
        }

        // Toppings Selection
        System.out.println("Would you like to customize your pizza with extra toppings? (yes/no): ");
        String toppingsChoice = scanner.nextLine();

        if (toppingsChoice.equalsIgnoreCase("yes")) {
            System.out.println("Available toppings and their prices:");
            System.out.println("1. Pepperoni - $1.50");
            System.out.println("2. Mushrooms - $1.00");
            System.out.println("3. Olives - $1.20");
            System.out.println("4. Bell Peppers - $1.00");
            System.out.println("5. Pineapple - $1.50");
            System.out.println("6. Sausage - $2.00");
            System.out.println("7. Jalapenos - $1.20");
            System.out.println("8. Onions - $1.00");
            System.out.println("Enter topping numbers separated by commas (e.g., 1,3,5): ");
            String toppingInput = scanner.nextLine();
            String[] toppingChoices = toppingInput.split(",");
            for (String topping : toppingChoices) {
                switch (topping.trim()) {
                    case "1": builder.addTopping("Pepperoni"); break;
                    case "2": builder.addTopping("Mushrooms"); break;
                    case "3": builder.addTopping("Olives"); break;
                    case "4": builder.addTopping("Bell Peppers"); break;
                    case "5": builder.addTopping("Pineapple"); break;
                    case "6": builder.addTopping("Sausage"); break;
                    case "7": builder.addTopping("Jalapenos"); break;
                    case "8": builder.addTopping("Onions"); break;
                    default: System.out.println("Invalid topping choice: " + topping);
                }
            }
        }

        System.out.println("Choose your crust (1. Thin Crust - $2, 2. Thick Crust - $2.5, 3. Stuffed Crust - $3): ");
        int crustChoice = scanner.nextInt();
        scanner.nextLine();
        switch (crustChoice) {
            case 1: builder.setCrust("Thin Crust"); break;
            case 2: builder.setCrust("Thick Crust"); break;
            case 3: builder.setCrust("Stuffed Crust"); break;
            default: builder.setCrust("Thin Crust");
        }

        // Sauce Selection
        System.out.println("Choose your sauce: (1. Tomato - $0, 2. Alfredo - $1, 3. Pesto - $1.50)");
        int sauceChoice = scanner.nextInt();
        scanner.nextLine();
        double basePrice = switch (sauceChoice) {
            case 1 -> {
                builder.setSauce("Tomato");
                yield 0.0;
            }
            case 2 -> {
                builder.setSauce("Alfredo");
                yield 1.0;
            }
            case 3 -> {
                builder.setSauce("Pesto");
                yield 1.50;
            }
            default -> 0.0;
        };

        // Cheese Selection
        System.out.println("Choose your cheese: (1. Mozzarella - $0, 2. Cheddar - $0.5, 3. Parmesan - $1)");
        int cheeseChoice = scanner.nextInt();
        scanner.nextLine();
        basePrice += switch (cheeseChoice) {
            case 1 -> {
                builder.setCheese("Mozzarella");
                yield 0.0;
            }
            case 2 -> {
                builder.setCheese("Cheddar");
                yield 0.50;
            }
            case 3 -> {
                builder.setCheese("Parmesan");
                yield 1.0;
            }
            default -> 0.0;
        };

        Pizza pizza = builder.build();



        // Step 3: Quantity Selection
        System.out.println("How many pizzas would you like to order?");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        double totalPrice = pizza.getBasePrice() * quantity;

        // Step 4: Delivery or Pickup
        System.out.println("Would you like Pickup or Delivery? (Pickup / Delivery): ");
        String orderType = scanner.nextLine();
        String deliveryAddress = "", phoneNumber = "";
        if (orderType.equalsIgnoreCase("Delivery")) {
            System.out.println("Enter your delivery address: ");
            deliveryAddress = scanner.nextLine();
            System.out.println("Enter your phone number: ");
            phoneNumber = scanner.nextLine();
        }

        // Step 5: Order Review
        System.out.println("\n========== Order Review ==========");
        System.out.println("Customer: " + userProfile.getName());
        System.out.println("Pizza Name: " + pizza.getName());
        System.out.println("Pizza Details: " + pizza.getDetails());
        System.out.println("Quantity: " + quantity);
        if (orderType.equalsIgnoreCase("Delivery")) {
            System.out.println("Delivery Address: " + deliveryAddress);
            System.out.println("Phone Number: " + phoneNumber);
        }
        System.out.println("Total Amount: $" + totalPrice);

        // Step 6: Payment
        System.out.println("Choose payment method (1. Credit Card, 2. Digital Wallet): ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        PaymentStrategy paymentMethod = paymentChoice == 1 ? new CreditCardPayment() : new DigitalWalletPayment();
        paymentMethod.pay(totalPrice);

        // Apply loyalty points
        paymentMethod.applyLoyaltyPoints(userProfile, totalPrice);

        // Step 7: Redeem loyalty points
        System.out.println("You have earned " + userProfile.getLoyaltyPoints() + " loyalty points!");
        System.out.println("Do you want to redeem loyalty points? (yes/no): ");
        String redeemChoice = scanner.nextLine();
        if (redeemChoice.equalsIgnoreCase("yes")) {
            if (userProfile.getLoyaltyPoints() >= 10) {
                System.out.println("You have redeemed 10 loyalty points for a free topping!");
                userProfile.redeemLoyaltyPoints(10);

                // Free topping redemption
                System.out.println("Choose your free topping (1. Pepperoni, 2. Mushrooms, 3. Olives, 4. Bell Peppers): ");
                int freeToppingChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (freeToppingChoice) {
                    case 1: builder.addTopping("Pepperoni"); break;
                    case 2: builder.addTopping("Mushrooms"); break;
                    case 3: builder.addTopping("Olives"); break;
                    case 4: builder.addTopping("Bell Peppers"); break;
                    default: System.out.println("Invalid choice. No topping added.");
                }
            } else {
                System.out.println("Sorry, you need at least 10 loyalty points to redeem for a free topping.");
            }
        }

        // Step 8: Order Tracking
        OrderTracker tracker = new OrderTracker();
        tracker.attach(new Customer(userProfile.getName()));
        tracker.setState("Order placed.");
        Thread.sleep(2000);
        tracker.setState("Order is being prepared.");
        Thread.sleep(3000);
        tracker.setState(orderType.equalsIgnoreCase("Delivery") ? "Order is out for delivery." : "Order is ready for pickup.");
        Thread.sleep(2000);

        // Invoice
        String orderId = UUID.randomUUID().toString();
        String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("\n========== BILL ==========");
        System.out.println("Order ID: " + orderId);
        System.out.println("Date: " + dateTime);
        System.out.println("Customer: " + userProfile.getName());
        System.out.println("Order Type: " + orderType);
        if (orderType.equalsIgnoreCase("Delivery")) {
            System.out.println("Delivery Address: " + deliveryAddress);
            System.out.println("Phone Number: " + phoneNumber);
        }
        System.out.println("Order Details: " + pizza.getDetails());
        System.out.println("Total Amount: $" + totalPrice);

        scanner.close();
    }
}
