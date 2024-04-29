package DesignPatterns.Creational;

//Defines an interface for creating objects but allows subclasses to alter the type of objects that will be created.
// Widely used in libraries and frameworks for object creation.

// when all the object creation and business logic needs to keep at one place


interface Shape {
    void computeArea();
}

class Square implements Shape {

    @Override
    public void computeArea() {
        System.out.println("Calc area of Square");
    }
}

class Circle implements Shape {

    @Override
    public void computeArea() {
        System.out.println("Calc area of Circle");
    }
}

class Rectangle implements Shape {

    @Override
    public void computeArea() {
        System.out.println("Calc area of Rectangle");
    }
}

enum ShapeType {
    Square,
    Rectangle,
    Circle
}

class ShapeInstanceFactory {

    Shape getShapeInstance(ShapeType type) {
        switch (type) {
            case Circle:
                return new Circle();
            case Square:
            case Rectangle:
                return new Square();
            default:
                return null;
        }
    }
}

public class Factory {
        public static void main(String[] args) {
            ShapeInstanceFactory shapeInstanceFactory = new ShapeInstanceFactory();
            shapeInstanceFactory.getShapeInstance(ShapeType.Circle).computeArea();
            shapeInstanceFactory.getShapeInstance(ShapeType.Rectangle).computeArea();
            shapeInstanceFactory.getShapeInstance(ShapeType.Square).computeArea();
        }
}
