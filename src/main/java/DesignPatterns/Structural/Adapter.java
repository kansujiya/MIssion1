package DesignPatterns.Structural;

//Allows incompatible interfaces to work together by wrapping an interface around an existing class.
// Frequently used to integrate new code with existing systems.

//Think like different charging adaptor

//Client  ---------------> Adaptor --------------> Adaptee
//        <---------------         <--------------


//Adaptee
interface WeightMachine {
    int getWeightInPound();
}

class WeightMachineImpl implements WeightMachine {

    @Override
    public int getWeightInPound() {
        return 20;
    }
}

//client class
interface WeightingMachineAdaptor {
    public double getWeightInKG();
}

class WeightingMachineAdaptorImpl implements WeightingMachineAdaptor {
    WeightMachine weightMachine;

    WeightingMachineAdaptorImpl(WeightMachine weightMachine) {
        this.weightMachine = weightMachine;
    }

    @Override
    public double getWeightInKG() {
        int weightInPound = weightMachine.getWeightInPound();
        return weightInPound*.45;
    }
}

public class Adapter {

    public static void main(String[] args) {
        WeightingMachineAdaptor weightingMachineAdaptor = new WeightingMachineAdaptorImpl(new WeightMachineImpl());
        System.out.println(weightingMachineAdaptor.getWeightInKG());
    }

}
