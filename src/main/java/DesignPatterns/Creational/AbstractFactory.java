package DesignPatterns.Creational;


interface AbstractFactoryInterface {
    Car getInstance(int price);
}

class EconomicCarFactory implements AbstractFactoryInterface {

    @Override
    public Car getInstance(int price) {
        if(price <= 300000) {
            return new EconomicCar1();
        } else if(price > 300000) {
            return new EconomicCar2();
        } else {
            return null;
        }
    }
}

class LuxuryCarFactory implements AbstractFactoryInterface {

    @Override
    public Car getInstance(int price) {
        if(price <= 3000000) {
            return new LuxuryCar1();
        } else if(price > 3000000) {
            return new LuxuryCar2();
        } else {
            return null;
        }
    }
}

interface Car {
    int speed();
}

class EconomicCar1 implements Car {

    @Override
    public int speed() {
        return 100;
    }
}
class EconomicCar2 implements Car {
    @Override
    public int speed() {
        return 120;
    }
}

class LuxuryCar1 implements Car {
    @Override
    public int speed() {
        return 150;
    }
}

class LuxuryCar2 implements Car {
    @Override
    public int speed() {
        return 200;
    }
}

class AbstractFactoryProducer {
    AbstractFactoryInterface getFactoryInstance(String value) {
        if(value.equalsIgnoreCase("Economic")) {
            return new EconomicCarFactory();
        } else if(value.equalsIgnoreCase("Luxury")) {
            return new LuxuryCarFactory();
        } else {
            return null;
        }
    }
}



public class AbstractFactory {


    public static void main(String[] args) {
        AbstractFactoryProducer abstractFactoryProducer = new AbstractFactoryProducer();
        AbstractFactoryInterface abstractFactoryInterface = abstractFactoryProducer.getFactoryInstance("Luxury");
        Car car = abstractFactoryInterface.getInstance(3000000);
        System.out.println(car.speed());
    }
}
