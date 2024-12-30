public class OrderContext {
    private OrderState state;

    public OrderContext() {
        this.state = new PlacedState();
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void next() {
        state.handleOrder(this);
    }
}
