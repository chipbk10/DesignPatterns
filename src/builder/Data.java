package builder;

import static lib.Printer.print;

public class Data {

    public static class Pizza {
        private int size;
        private boolean cheese;
        private boolean pepperoni;
        private boolean bacon;

        // must use builder to initiate
        private Pizza() {}

        public void show() {
            StringBuilder sb = new StringBuilder();
            sb.append("You have ordered a pizza: \n");
            sb.append("size : " + size + "\n");
            if (cheese) sb.append("with cheese \n");
            if (pepperoni) sb.append("with pepperoni \n");
            if (bacon) sb.append("with bacon \n");
            print(sb.toString());
        }

        public static class Builder {

            // required
            private final int size;

            // optional
            private boolean cheese = false;
            private boolean pepperoni = false;
            private boolean bacon = false;

            public Builder(int size) {
                this.size = size;
            }

            public Builder cheese(boolean value) {
                cheese = value;
                return this;
            }

            public Builder pepperoni(boolean value) {
                pepperoni = value;
                return this;
            }

            public Builder bacon(boolean value) {
                bacon = value;
                return this;
            }

            public Pizza build() {
                Pizza pizza = new Pizza();
                pizza.size = size;
                pizza.bacon = bacon;
                pizza.cheese = cheese;
                pizza.pepperoni = pepperoni;
                return pizza;
            }
        }
    }


}
