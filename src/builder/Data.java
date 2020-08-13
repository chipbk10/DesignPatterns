package builder;

import static lib.Printer.print;
import static lib.Printer.println;

public class Data {

    public static class Pizza {
        private int size;
        private boolean cheese;
        private boolean pepperoni;
        private boolean bacon;

        /*
            We've all at some point encountered a class with a list of constructors
            where each addition adds a new option parameter:

            The problem with this pattern is that once constructors are 4 or 5 parameters long
            it becomes difficult to remember the required order of the parameters
            as well as what particular constructor you might want in a given situation.
         */
//        Pizza(int size) { ... }
//        Pizza(int size, boolean cheese) { ... }
//        Pizza(int size, boolean cheese, boolean pepperoni) { ... }
//        Pizza(int size, boolean cheese, boolean pepperoni, boolean bacon) { ... }

        /*
            The problem here is that because the object is created over several calls
            it may be in an inconsistent state partway through its construction.
            This also requires a lot of extra effort to ensure thread safety.
         */
//        Pizza pizza = new Pizza(12);
//        pizza.setCheese(true);
//        pizza.setPepperoni(true);
//        pizza.setBacon(true);

        // must use builder to initiate
        private Pizza() {}

        public void getDetails() {
            StringBuilder sb = new StringBuilder();
            sb.append("You have ordered a pizza: \n");
            sb.append("size : " + size + "\n");
            if (cheese) sb.append("with cheese \n");
            if (pepperoni) sb.append("with pepperoni \n");
            if (bacon) sb.append("with bacon \n");
            println(sb.toString());
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
