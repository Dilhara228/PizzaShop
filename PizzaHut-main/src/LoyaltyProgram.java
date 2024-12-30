public class LoyaltyProgram {
        private int points;

        public LoyaltyProgram() {
            this.points = 0;
        }

        public void addPoints(int earnedPoints) {
            this.points += earnedPoints;
            System.out.println("Earned " + earnedPoints + " points! Total Points: " + points);
        }

        public boolean redeemPoints(int pointsToRedeem) {
            if (points >= pointsToRedeem) {
                points -= pointsToRedeem;
                System.out.println("Redeemed " + pointsToRedeem + " points. Remaining Points: " + points);
                return true;
            } else {
                System.out.println("Not enough points to redeem.");
                return false;
            }
        }

        public int getPoints() {
            return points;
        }
    }


