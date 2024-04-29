package DesignPatterns.Structural;

//Dynamically adds responsibilities to objects by wrapping them with additional behavior.
// Useful for adding features to objects without altering their structure.

//Add more functionality into existing object without changing its structure

abstract class BasePizza {
    abstract int cost();
}

class FarmHouse extends BasePizza { //IS-A
    @Override
    int cost() {
        return 100;
    }
}

class Margherita extends BasePizza { // IS-A
    @Override
    int cost() {
        return 200;
    }
}

abstract class ToppingDecorator extends BasePizza {

}

class ExtraCheese extends ToppingDecorator{

    BasePizza basePizza;

    public ExtraCheese(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    int cost() {
        return basePizza.cost() + 20;
    }
}

class Mushroom extends ToppingDecorator {

    BasePizza basePizza;

    Mushroom(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    int cost() {
        return this.basePizza.cost() + 10;
    }
}

public class Decorator {
    public static void main(String[] args) {
        BasePizza basePizza = new Mushroom(new ExtraCheese(new FarmHouse()));
        System.out.println(basePizza.cost());
    }
}
