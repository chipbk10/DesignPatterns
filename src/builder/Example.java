package builder;

import static builder.Data.*;

public class Example {

    public static void run() {

        // build different immutable objects using same object building process.
        Pizza.Builder builder = new Pizza.Builder(4);

        // (1)
        Pizza pizzaWithBacon = builder.bacon(true)
                                      .build();
        pizzaWithBacon.getDetails();

        // (2)
        Pizza pizzaWithPepperoni = builder.bacon(false)
                                          .pepperoni(true)
                                          .build();
        pizzaWithPepperoni.getDetails();

        // (3)
        Pizza pizzaWithCheese = builder.bacon(false)
                                       .pepperoni(false)
                                       .cheese(true)
                                       .build();
        pizzaWithCheese.getDetails();
    }
}
