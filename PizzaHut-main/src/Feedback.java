class Feedback {
        private String comments;
        private int rating;

        public Feedback(String comments, int rating) {
            this.comments = comments;
            this.rating = rating;
        }

        public void submit() {
            System.out.println("Feedback submitted: " + comments + " | Rating: " + rating);
        }
    }


