package decorator;

import lib.Printer;

import static decorator.Data.*;

public class Decorator {

    public static void run() {
        Shape rectangle = new Rectangle();
        Shape circle = new Circle();

        Shape blueRectangle = new ShapeWithColor(rectangle, "blue");
        blueRectangle.draw();
        Printer.newLine();

        Shape yellowCircle = new ShapeWithColor(circle, "yellow");
        yellowCircle.draw();
    }
}
