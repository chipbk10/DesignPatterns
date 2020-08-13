package factory;

import data.Data.Shape;

import static data.Data.*;
import static lib.Printer.print;

public class Data {

    public interface AbstractShapeFactory {
        Shape getShape(String shapeType);
    }

    // hide the logic of shape creation
    public static class ShapeFactory implements AbstractShapeFactory {

        public Shape getShape(String shapeType) {
            if(shapeType == null)
                return null;

            if(shapeType.equalsIgnoreCase("CIRCLE"))
                return new Circle();

            if(shapeType.equalsIgnoreCase("RECTANGLE"))
                return new Rectangle();

            if(shapeType.equalsIgnoreCase("SQUARE"))
                return new Square();

            return null;
        }
    }

    // hide the logic of shape with border creation
    public static class ShapeWithBorderFactory implements AbstractShapeFactory {

        public Shape getShape(String shapeType) {
            if(shapeType == null)
                return null;

            if(shapeType.equalsIgnoreCase("CIRCLE"))
                return new CircleWithBorder();

            if(shapeType.equalsIgnoreCase("RECTANGLE"))
                return new RectangleWithBorder();

            if(shapeType.equalsIgnoreCase("SQUARE"))
                return new SquareWithBorder();

            return null;
        }
    }

    // hide the logic of factory creation
    public static class ShapeFactoryProducer {

        public AbstractShapeFactory getFactory(boolean withBorder){
            return (withBorder) ? new ShapeWithBorderFactory() : new ShapeFactory();
        }
    }

    // Another family of objects
    public static class CircleWithBorder implements Shape {

        @Override
        public void draw() {
            print("circle with border");
        }
    }

    public static class RectangleWithBorder implements Shape {

        @Override
        public void draw() {
            print("rectangle with border");
        }
    }

    public static class SquareWithBorder implements Shape {

        @Override
        public void draw() {
            print("square with border");
        }
    }
}
