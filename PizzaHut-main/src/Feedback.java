public class Feedback {
    private String customerName;
    private double rating;  // Rating out of 5
    private String comments;

    public Feedback(String customerName, double rating, String comments) {
        this.customerName = customerName;
        this.rating = rating;
        this.comments = comments;
    }

    // Getters
    public String getCustomerName() {
        return customerName;
    }

    public double getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Customer: " + customerName + ", Rating: " + rating + " stars, Comments: " + comments;
    }

    public void submit() {
        System.out.println("Feedback submitted successfully!");
    }
}
