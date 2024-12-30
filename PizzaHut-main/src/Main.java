import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    private static List<Feedback> feedbackList = new ArrayList<>();


    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // Step 0: Create User Profile
        System.out.println("Welcome to the Pizza Shop!");
        System.out.println("Enter your name to create a profile: ");
        String userName = scanner.nextLine();
        UserProfile userProfile = new UserProfile(userName);
        System.out.println("Hello, " + userProfile.getName() + "!");


        boolean exit = false;

        while (!exit) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. User Profiles and Favorites");
            System.out.println("2. Pizza Customization");
            System.out.println("3. Seasonal Specials and Promotions");
            System.out.println("4. Feedback and Ratings");
            System.out.println("5. Exit");

            // Validate user input
            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine();
                continue;
            }

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
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    break;
            }
        }

        scanner.close();

    }

    private static void handleUserProfileAndFavorites(Scanner scanner, UserProfile userProfile) throws InterruptedException {
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
            // Display available pizzas for selection
            String[] availablePizzas = {"Margherita", "Pepperoni", "Veggie Supreme", "BBQ Chicken", "Mushroom Delight"};
            System.out.println("\nChoose a pizza from the following list to add to your favorites:");
            for (int i = 0; i < availablePizzas.length; i++) {
                System.out.println((i + 1) + ". " + availablePizzas[i]);
            }
            System.out.print("Enter the number corresponding to your choice: ");
            int pizzaChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            String selectedPizza = availablePizzas[pizzaChoice - 1];

            // Add to favorites
            if (!userProfile.getFavorites().contains(selectedPizza)) {
                userProfile.addFavorite(selectedPizza);
                System.out.println(selectedPizza + " has been added to your favorites.");
            } else {
                System.out.println(selectedPizza + " is already in your favorites.");
            }


            // Reorder a favorite pizza
            System.out.println("\nWould you like to reorder a favorite pizza? (yes/no): ");
            String reorderFavorite = scanner.nextLine();
            if (reorderFavorite.equalsIgnoreCase("yes")) {
                // Display current favorite pizzas
                if (userProfile.getFavorites().isEmpty()) {
                    System.out.println("You have no favorite pizzas to reorder.");
                } else {
                    System.out.println("Your favorite pizzas:");
                    for (int i = 0; i < userProfile.getFavorites().size(); i++) {
                        System.out.println((i + 1) + ". " + userProfile.getFavorites().get(i));
                    }


                    System.out.println("\nEnter the number of the pizza you want to reorder: ");
                    pizzaChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (pizzaChoice > 0 && pizzaChoice <= userProfile.getFavorites().size()) {
                        String chosenPizza = userProfile.getFavorites().get(pizzaChoice - 1);
                        System.out.println("You have selected: " + chosenPizza);

                        // Enter quantity
                        System.out.print("Enter the quantity: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        // Choose delivery or pickup
                        System.out.print("Would you like Delivery or Pickup? (Delivery/Pickup): ");
                        String orderType = scanner.nextLine();

                        String deliveryAddress = "";
                        String phoneNumber = "";
                        if (orderType.equalsIgnoreCase("Delivery")) {
                            System.out.print("Enter your delivery address: ");
                            deliveryAddress = scanner.nextLine();
                            System.out.print("Enter your phone number: ");
                            phoneNumber = scanner.nextLine();
                        }

                        // Payment process
                        double pizzaPrice = 15.0; // Price per pizza
                        double totalPrice = pizzaPrice * quantity;
                        System.out.println("The price per pizza is $" + pizzaPrice + ". Total amount: $" + totalPrice);

                        int paymentChoice = 0;
                        while (paymentChoice != 1 && paymentChoice != 2) {
                            System.out.println("Choose payment method (1. Credit Card, 2. Digital Wallet): ");
                            if (scanner.hasNextInt()) {
                                paymentChoice = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                if (paymentChoice != 1 && paymentChoice != 2) {
                                    System.out.println("Invalid choice. Please enter 1 or 2.");
                                }
                            } else {
                                System.out.println("Invalid input. Please enter a number (1 or 2).");
                                scanner.nextLine(); // Consume invalid input
                            }
                        }

                        PaymentStrategy paymentMethod = paymentChoice == 1 ? new CreditCardPayment() : new DigitalWalletPayment();
                        paymentMethod.pay(totalPrice);

                        // Step 6: Order Tracking

                        System.out.println("\n========== Order Tracking ==========");
                        OrderTracker tracker = new OrderTracker();
                        tracker.attach(new Customer(userProfile.getName()));
                        tracker.setState("Order placed.");
                        Thread.sleep(2000);
                        tracker.setState("Order is being prepared.");
                        Thread.sleep(3000);
                        tracker.setState(orderType.equalsIgnoreCase("Delivery") ? "Order is out for delivery." : "Order is ready for pickup.");
                        Thread.sleep(2000);
                        System.out.println("\n====================================");



                        // Invoice generation
                        System.out.println("\n========== INVOICE ==========");
                        String orderId = UUID.randomUUID().toString();
                        String dateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
                        System.out.println("Order ID: " + orderId);
                        System.out.println("Date: " + dateTime);
                        System.out.println("Customer: " + userProfile.getName());
                        System.out.println("Pizza: " + chosenPizza);
                        System.out.println("Quantity: " + quantity);
                        if (orderType.equalsIgnoreCase("Delivery")) {
                            System.out.println("Delivery Address: " + deliveryAddress);
                            System.out.println("Phone Number: " + phoneNumber);
                        }
                        System.out.println("Total Amount: $" + (15.0 * quantity));
                        System.out.println("Thank you for ordering with us!");
                    } else {
                        System.out.println("Invalid choice. Returning to main menu.");
                    }
                }
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

        double[] pizzaPrices = {8.00, 9.50, 10.00, 11.00, 8.50}; // Base prices for each pizza

        for (int i = 0; i < availablePizzas.length; i++) {
            System.out.println((i + 1) + ". " + availablePizzas[i] + " - $" + pizzaPrices[i]);
        }

        int pizzaChoice;
        while (true) {
            System.out.println("Enter the number corresponding to your pizza choice: ");
            if (scanner.hasNextInt()) {
                pizzaChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (pizzaChoice >= 1 && pizzaChoice <= availablePizzas.length) {
                    break;
                }
            }
            System.out.println("Invalid choice. Please enter a number between 1 and " + availablePizzas.length + ".");
            scanner.nextLine(); // Clear invalid input
        }
        String selectedPizzaName = availablePizzas[pizzaChoice - 1];
        System.out.println("You chose: " + selectedPizzaName);

        // Step 2: Pizza Customization
        Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder();
        builder.setName(selectedPizzaName);

        System.out.println("Select your pizza size (1. Small - $8, 2. Medium - $10, 3. Large - $12): ");
        int sizeChoice;
        while (true) {
            if (scanner.hasNextInt()) {
                sizeChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (sizeChoice >= 1 && sizeChoice <= 3) {
                    break;
                }
            }
            System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            scanner.nextLine(); // Clear invalid input
        }
        switch (sizeChoice) {
            case 1 -> builder.setSize("Small");
            case 2 -> builder.setSize("Medium");
            case 3 -> builder.setSize("Large");
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
                    case "1" -> builder.addTopping("Pepperoni");
                    case "2" -> builder.addTopping("Mushrooms");
                    case "3" -> builder.addTopping("Olives");
                    case "4" -> builder.addTopping("Bell Peppers");
                    case "5" -> builder.addTopping("Pineapple");
                    case "6" -> builder.addTopping("Sausage");
                    case "7" -> builder.addTopping("Jalapenos");
                    case "8" -> builder.addTopping("Onions");
                    default -> System.out.println("Invalid topping choice: " + topping);
                }
            }
        }

        System.out.println("Choose your crust: (1. Thin Crust - $2, 2. Thick Crust - $2.5, 3. Stuffed Crust - $3, 4. None): ");
        int crustChoice;
        while (true) {
            if (scanner.hasNextInt()) {
                crustChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (crustChoice >= 1 && crustChoice <= 4) {
                    break;
                }
            }
            System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            scanner.nextLine(); // Clear invalid input
        }
        switch (crustChoice) {
            case 1 -> builder.setCrust("Thin Crust");
            case 2 -> builder.setCrust("Thick Crust");
            case 3 -> builder.setCrust("Stuffed Crust");
            case 4 -> builder.setCrust("None");
        }


        System.out.println("Choose your sauce: (1. Tomato - $0, 2. Alfredo - $1, 3. Pesto - $1.50, 4. None): ");
        int sauceChoice;
        while (true) {
            if (scanner.hasNextInt()) {
                sauceChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (sauceChoice >= 1 && sauceChoice <= 4) {
                    break;
                }
            }
            System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            scanner.nextLine(); // Clear invalid input
        }
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
            case 4 -> {
                builder.setSauce("None");
                yield 0.0;
            }
            default -> 0.0;
        };


        System.out.println("Choose your cheese: (1. Mozzarella - $0, 2. Cheddar - $0.5, 3. Parmesan - $1, 4. None): ");
        int cheeseChoice;
        while (true) {
            if (scanner.hasNextInt()) {
                cheeseChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (cheeseChoice >= 1 && cheeseChoice <= 4) {
                    break;
                }
            }
            System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            scanner.nextLine(); // Clear invalid input
        }
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
            case 4 -> {
                builder.setCheese("None");
                yield 0.0;
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
        double totalPrice = 20.0 * quantity;

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

        // Apply loyalty points
        paymentMethod.applyLoyaltyPoints(userProfile, totalPrice);

        // Step 5: Redeem loyalty points
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
                    case 1:
                        builder.addTopping("Pepperoni");
                        break;
                    case 2:
                        builder.addTopping("Mushrooms");
                        break;
                    case 3:
                        builder.addTopping("Olives");
                        break;
                    case 4:
                        builder.addTopping("Bell Peppers");
                        break;
                    default:
                        System.out.println("Invalid choice. No topping added.");
                }
            } else {
                System.out.println("Sorry, you need at least 10 loyalty points to redeem for a free topping.");
            }
        }


        // Step 6: Order Tracking
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
        System.out.println("\n=== Feedback and Ratings ===");

        System.out.print("Please enter your name: ");
        String customerName = scanner.nextLine();

        double rating;
        while (true) {
            System.out.print("Please rate your experience (1-5): ");
            rating = scanner.nextDouble();
            scanner.nextLine();
            if (rating >= 1 && rating <= 5) {
                break;
            }
            System.out.println("Invalid rating. Please provide a rating between 1 and 5.");
        }

        System.out.print("Would you like to leave additional comments? (yes/no): ");
        String additionalComments = scanner.nextLine();

        String comments = "";
        if (additionalComments.equalsIgnoreCase("yes")) {
            System.out.print("Please enter your comments: ");
            comments = scanner.nextLine();
        }

        Feedback feedback = new Feedback(customerName, rating, comments);
        feedbackList.add(feedback);

        System.out.println("Feedback submitted successfully!");

        // Ask to view feedback
        System.out.println("\nWould you like to view feedback? (1. All Feedback, 2. Highly Rated Feedback, 3. None): ");
        int feedbackOption = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (feedbackOption) {
            case 1 -> showAllFeedback();
            case 2 -> showHighlyRatedFeedback();
            case 3 -> System.out.println("Thank you for your feedback!");
            default -> System.out.println("Invalid option.");
        }
    }

    private static void showAllFeedback() {
        System.out.println("\n=== All Customer Feedback ===");
        if (feedbackList.isEmpty()) {
            System.out.println("No feedback available.");
        } else {
            for (Feedback feedback : feedbackList) {
                System.out.println(feedback);
                System.out.println("----------------------------");
            }
        }
    }

    private static void showHighlyRatedFeedback() {
        System.out.println("\n=== Highly Rated Feedback (4+ stars) ===");
        boolean found = false;
        for (Feedback feedback : feedbackList) {
            if (feedback.getRating() >= 4) {
                System.out.println(feedback);
                System.out.println("----------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No highly-rated feedback available.");
        }
    }
}