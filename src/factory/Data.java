package factory;

import data.Data.Shape;

import static data.Data.*;

public class Data {

    public static class ShapeFactory {

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
}
