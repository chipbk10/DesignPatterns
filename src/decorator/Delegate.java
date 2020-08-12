package decorator;

import data.Data.*;

import static decorator.Data.*;
import static lib.Printer.*;

public class Delegate implements DataSource, Behaviour {

    @Override
    public int numberOfShapes() {
        return 4;
    }

    @Override
    public Shape shapeAtIndex(int index) {
        switch (index) {
            case 0: return new Rectangle();
            case 1: return new Circle();
            case 2: return new ShapeWithColor(new Rectangle(), "blue");
            default: return new ShapeWithColor(new Circle(), "yellow");
        }
    }

    @Override
    public void selectShape(Shape shape) {
        print("select ");
        shape.draw();
    }

    @Override
    public void rotateShape(Shape shape) {
        print("rotate ");
        shape.draw();
    }

    public static void run() {
        Delegate delegate = new Delegate();
        Storage storage = new Storage();
        storage.behaviourDelegate = delegate;
        storage.dataSourceDelegate = delegate;
        storage.build();
        storage.iterate();
    }
}
