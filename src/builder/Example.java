package builder;

import static builder.Data.*;

public class Example {

    public static void run() {
        Pizza customPizza = new Pizza.Builder(4)
                                        .bacon(false)
                                        .pepperoni(true)
                                        .cheese(true)
                                        .build();
        customPizza.show();
    }
}
