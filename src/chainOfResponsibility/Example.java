package chainOfResponsibility;

import static chainOfResponsibility.Data.*;
import static lib.Printer.*;

public class Example {

    public static void run() {
        MoneyPile   hundred = new MoneyPile(100, 2),
                    fifty   = new MoneyPile(50, 3),
                    twenty  = new MoneyPile(20, 1),
                    ten     = new MoneyPile(10, 7),
                    five    = new MoneyPile(5, 5),
                    two     = new MoneyPile(2, 13),
                    one     = new MoneyPile(1, 17);

        ATM atm = new ATM(ten, five, hundred, fifty, twenty, two, one);

        println(atm.canWithdraw(317));   // true
        println(atm.canWithdraw(513));   // false
    }
}
