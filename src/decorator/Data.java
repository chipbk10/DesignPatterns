package decorator;

import lib.Printer;

import static lib.Printer.*;

public class Data {

    public interface Shape {
        void draw();
    }

    public static class Circle implements Shape {

        @Override
        public void draw() {
            print("circle");
        }
    }

    public static class Rectangle implements Shape {

        @Override
        public void draw() {
            print("rectangle");
        }
    }

    // Decorate a shape with color
    public static class ShapeWithColor implements Shape {

        Shape shape;
        String color;

        public ShapeWithColor(Shape shape, String color) {
            this.shape = shape;
            this.color = color;
        }

        @Override
        public void draw() {
            shape.draw();
            print(" with " + color);
        }
    }

    public interface DataSource {
        int numberOfShapes();
        Shape shapeAtIndex(int index);
    }

    public interface Behaviour {
        void selectShape(Shape shape);
        void rotateShape(Shape shape);
    }

    // Storage contains a list of shapes.
    // but info about how to create the list, he doesn't know. He asks delegate.
    // behavior when interact on each element, he doesn't know either. Again, he asks delegate.
    // This way: we can separate datasource and behaviour logics out of storage.
    public static class Storage {

        Shape[] shapes;

        public DataSource dataSourceDelegate;
        public Behaviour behaviourDelegate;

        public void build() {
            int n = dataSourceDelegate.numberOfShapes();
            shapes = new Shape[n];
            for (int i = 0; i < n; i++) {
                shapes[i] = dataSourceDelegate.shapeAtIndex(i);
            }
        }

        public void iterate() {
            for (Shape shape : shapes) {
                behaviourDelegate.selectShape(shape);
                newLine();
                behaviourDelegate.rotateShape(shape);
                newLine();
                newLine();
            }
        }
    }
}
