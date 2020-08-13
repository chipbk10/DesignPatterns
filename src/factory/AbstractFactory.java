package factory;

import static data.Data.*;
import static factory.Data.*;

public class AbstractFactory {

    public static void run() {

        ShapeFactoryProducer factoryProducer = new ShapeFactoryProducer();
        AbstractShapeFactory factory = factoryProducer.getFactory(false);

        // no border
        Shape circle = factory.getShape("CIRCLE");
        circle.draw();

        Shape rectangle = factory.getShape("RECTANGLE");
        rectangle.draw();

        Shape square = factory.getShape("SQUARE");
        square.draw();

        // with border
        factory = factoryProducer.getFactory(true);

        Shape circleWithBorder = factory.getShape("CIRCLE");
        circleWithBorder.draw();

        Shape rectangleWithBorder = factory.getShape("RECTANGLE");
        rectangleWithBorder.draw();

        Shape squareWithBorder = factory.getShape("SQUARE");
        squareWithBorder.draw();
    }
}
