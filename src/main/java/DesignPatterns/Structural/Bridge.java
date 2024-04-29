package DesignPatterns.Structural;

//decouple abstraction from its implementations

abstract class LivingThings {
    BreathImpl breathImpl;

    LivingThings(BreathImpl breathImpl) {
        this.breathImpl = breathImpl;
    }
    abstract void breathProcess();
}

interface BreathImpl { //HAS-A relationship with Living things
    void breathProcess();
}

class LandBreath implements BreathImpl {

    @Override
    public void breathProcess() {
        System.out.println("Breath through nose, inhale O2S, exhale CO2");
    }
}

class AirBreath implements BreathImpl {

    @Override
    public void breathProcess() {
        System.out.println("Breath through leaves, inhale CO2, exhale O2S");
    }
}

class WaterBreath implements BreathImpl {

    @Override
    public void breathProcess() {
        System.out.println("Breath through Gills, absorb O2S, exhale CO2");
    }
}


class Dog extends LivingThings {

    Dog(BreathImpl breathImpl) {
        super(breathImpl);
    }

    @Override
    void breathProcess() {
        breathImpl.breathProcess();
    }
}

class Fish extends LivingThings {

    Fish(BreathImpl breathImpl) {
        super(breathImpl);
    }

    @Override
    void breathProcess() {
        breathImpl.breathProcess();
    }
}

class Tree extends LivingThings {

    Tree(BreathImpl breathImpl) {
        super(breathImpl);
    }

    @Override
    void breathProcess() {
        breathImpl.breathProcess();
    }
}

public class Bridge {
    public static void main(String[] args) {
        LivingThings fish = new Fish(new WaterBreath());
        fish.breathProcess();


        LivingThings tree = new Tree(new AirBreath());
        tree.breathProcess();
    }
}
