package chainOfResponsibility;

import java.util.Arrays;

public class Data {

    public static class MoneyPile {

        private int value;
        private int quantity;
        private MoneyPile next;

        public MoneyPile(int value, int quantity) {
            this.value = value;
            this.quantity = quantity;
        }

        public boolean canWithdraw(int amount) {
            int maxQuantity = Math.min(quantity, amount/value);
            int left = amount - maxQuantity*value;

            if (left == 0) return true;
            if (next == null) return false;
            return next.canWithdraw(left);
        }
    }

    public static class ATM {

        private MoneyPile startPile;

        public ATM(MoneyPile... piles) {

            Arrays.sort(piles, (p1, p2) -> p2.value - p1.value);

            startPile = piles[0];
            MoneyPile p = startPile;
            for (int i = 1; i < piles.length; i++) {
                p.next = piles[i];
                p = p.next;
            }
        }

        public boolean canWithdraw(int amount) {
            return startPile.canWithdraw(amount);
        }
    }

}
