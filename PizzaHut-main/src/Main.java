import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // Step 0: Create User Profile
        System.out.println("Welcome to the Pizza Shop!");
        System.out.println("Enter your name to create a profile: ");
        String userName = scanner.nextLine();
        UserProfile userProfile = new UserProfile(userName);
        System.out.println("Hello, " + userProfile.getName() + "!");

        // Main Menu Loop
        boolean exit = false;
        while (!exit) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. User Profiles and Favorites");
            System.out.println("2. Pizza Customization");
            System.out.println("3. Seasonal Specials and Promotions");
            System.out.println("4. Feedback and Ratings");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // User Profiles and Favorites
                    handleUserProfileAndFavorites(scanner, userProfile);
                    break;
                case 2:
                    // Pizza Customization
                    handlePizzaCustomization(scanner, userProfile);
                    break;
                case 3:
                    // Seasonal Specials and Promotions
                    handleSeasonalSpecialsAndPromotions(scanner);
                    break;
                case 4:
                    // Feedback and Ratings
                    handleFeedbackAndRatings(scanner);
                    break;
                case 5:
                    // Exit
                    System.out.println("Thank you for visiting the Pizza Shop!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static void handleUserProfileAndFavorites(Scanner scanner, UserProfile userProfile) {
        System.out.println("\nUser Profile and Favorites:");

        // Display User Profile
        System.out.println("Profile Name: " + userProfile.getName());

        // Display Favorites
        if (userProfile.getFavorites().isEmpty()) {
            System.out.println("You have no favorite pizzas yet.");
        } else {
            System.out.println("Your favorite pizzas: ");
            for (String favorite : userProfile.getFavorites()) {
                System.out.println("- " + favorite);
            }
        }

        // Add to Favorites
        System.out.println("\nWould you like to add a pizza to your favorites? (yes/no): ");
        String addToFavorites = scanner.nextLine();
        if (addToFavorites.equalsIgnoreCase("yes")) {
            // Display current favorite pizzas
            if (userProfile.getFavorites().isEmpty()) {
                System.out.println("You have no favorite pizzas yet.");
            } else {
                System.out.println("Your current favorite pizzas are:");
                for (String favoritePizza : userProfile.getFavorites()) {
                    System.out.println("- " + favoritePizza);
                }
            }

            // Ask the user for the pizza they want to add
            System.out.println("\nEnter the name of the pizza you want to add to favorites: ");
            String pizzaName = scanner.nextLine();

            // Check if the pizza is already in favorites
            if (!userProfile.getFavorites().contains(pizzaName)) {
                userProfile.addFavorite(pizzaName);
                System.out.println(pizzaName + " has been added to your favorites.");
            } else {
                System.out.println(pizzaName + " is already in your favorites.");
            }
        }
    }

    private static void handlePizzaCustomization(Scanner scanner, UserProfile userProfile) throws InterruptedException {
        System.out.println("\nLet's customize your pizza!");

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

        // Prompt the user to name their custom pizza
        System.out.print("Enter a name for your custom pizza: ");
        String pizzaName = scanner.nextLine();
        builder.setName(pizzaName); // Set the pizza name in the builder



        // Step 2: Quantity Selection
        System.out.println("How many pizzas would you like to order?");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        double totalPrice = 20.0 * quantity;  // Example pricing, adjust according to your customization

        // Step 3: Delivery or Pickup
        System.out.println("Would you like Pickup or Delivery? (Pickup / Delivery): ");
        String orderType = scanner.nextLine();
        String deliveryAddress = "", phoneNumber = "";
        if (orderType.equalsIgnoreCase("Delivery")) {
            System.out.println("Enter your delivery address: ");
            deliveryAddress = scanner.nextLine();
            System.out.println("Enter your phone number: ");
            phoneNumber = scanner.nextLine();
        }

        // Step 4: Order Review
        System.out.println("\n========== Order Review ==========");
        System.out.println("Customer: " + userProfile.getName());
        System.out.println("Pizza Name: " + selectedPizzaName);
        System.out.println("Quantity: " + quantity);
        if (orderType.equalsIgnoreCase("Delivery")) {
            System.out.println("Delivery Address: " + deliveryAddress);
            System.out.println("Phone Number: " + phoneNumber);
        }
        System.out.println("Total Amount: $" + totalPrice);
        System.out.println("\n======================");

        // Step 5: Payment
        System.out.println("Choose payment method (1. Credit Card, 2. Digital Wallet): ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        PaymentStrategy paymentMethod = paymentChoice == 1 ? new CreditCardPayment() : new DigitalWalletPayment();
        paymentMethod.pay(totalPrice);

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
        System.out.println("Total Amount: $" + totalPrice);

        }

    private static void handleSeasonalSpecialsAndPromotions(Scanner scanner) {
        System.out.println("\nSeasonal Specials and Promotions:");
        // Example promotional offers
        System.out.println("Current Special: 20% off on all Large Pizzas!");
        System.out.println("Would you like to apply this promotion to your order? (yes/no): ");
        String applyPromotion = scanner.nextLine();
        if (applyPromotion.equalsIgnoreCase("yes")) {
            System.out.println("Promotion applied! 20% off on your next large pizza.");
        } else {
            System.out.println("No promotion applied.");
        }
    }

    private static void handleFeedbackAndRatings(Scanner scanner) {
        System.out.println("\nFeedback and Ratings:");
        System.out.println("Please rate your experience (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating. Please provide a rating between 1 and 5.");
        } else {
            System.out.println("Thank you for your feedback! You rated us: " + rating + " stars.");
            System.out.println("Would you like to leave additional comments? (yes/no): ");
            String additionalComments = scanner.nextLine();
            if (additionalComments.equalsIgnoreCase("yes")) {
                System.out.println("Please enter your comments: ");
                String comments = scanner.nextLine();
                System.out.println("Thank you for your comments: " + comments);
            }

        }
    }
}
