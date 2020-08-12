package data;

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

    public static class Square implements Shape {

        @Override
        public void draw() {
            print("square");
        }
    }
}