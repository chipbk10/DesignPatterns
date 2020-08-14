package strategy;

import static lib.Printer.print;
import static strategy.Data.*;

public class Example {

    public static void run() {
        TransformerTool tool = new TransformerTool();
        String res = tool.transform("acba");
        print(res);
    }
}
