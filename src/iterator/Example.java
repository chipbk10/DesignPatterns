package iterator;

import static iterator.Data.DummyComplexObject;
import static iterator.Data.Iterator;
import static lib.Printer.println;

public class Example {

    public static void run() {
        DummyComplexObject complexObject = new DummyComplexObject();
        Iterator<Integer> iterator = complexObject.iterator();

        while (iterator.hasNext()) {
            println(iterator.next());
        }

        iterator.reset();
    }
}
