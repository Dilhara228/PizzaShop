interface Command {
    void execute();
}

class PlaceOrderCommand implements Command {
    private OrderContext order;

    public PlaceOrderCommand(OrderContext order) {
        this.order = order;
    }

    @Override
    public void execute() {
        order.next();
    }
}

class ProvideFeedbackCommand implements Command {
    private Feedback feedback;

    public ProvideFeedbackCommand(Feedback feedback) {
        this.feedback = feedback;
    }

    @Override
    public void execute() {
        feedback.submit();
    }
}

