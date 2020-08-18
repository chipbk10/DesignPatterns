package adapter;

import static adapter.Data.*;
import static lib.Printer.println;

public class Example {

    public static void run() {
        IReverseString reverseString = new ReverseString();
        AdapterReverseNumber adapter = new AdapterReverseNumber(reverseString);
        println( adapter.reverse(123) );
        println( adapter.reverse(123456789) );
    }
}