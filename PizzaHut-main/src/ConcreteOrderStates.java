class PlacedState implements OrderState {
    @Override
    public void handleOrder(OrderContext context) {
        System.out.println("Order is now placed.");
        context.setState(new PreparationState());
    }
}

class PreparationState implements OrderState {
    @Override
    public void handleOrder(OrderContext context) {
        System.out.println("Order is being prepared.");
        context.setState(new OutForDeliveryState());
    }
}

class OutForDeliveryState implements OrderState {
    @Override
    public void handleOrder(OrderContext context) {
        System.out.println("Order is out for delivery.");
        context.setState(new CompletedState());
    }
}

class CompletedState implements OrderState {
    @Override
    public void handleOrder(OrderContext context) {
        System.out.println("Order is completed.");
    }
}
