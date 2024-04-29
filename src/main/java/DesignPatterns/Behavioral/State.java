package DesignPatterns.Behavioral;


//Allow an object to alter its behavior when its internal state changes.

class VendingProduct {
    VendingState vendingState;

    public VendingState getVendingState() {
        return vendingState;
    }

    public void setMachineState(VendingState vendingState) {
        this.vendingState = vendingState;
    }
}

interface VendingState {
    void insertCoin(VendingProduct vendingProduct);
    void dispenseItem(VendingProduct vendingProduct);
}

class IdealState implements VendingState {

    @Override
    public void insertCoin(VendingProduct vendingProduct) {
        System.out.println("Coin inserted");
        vendingProduct.setMachineState(new WorkingState());;
    }

    @Override
    public void dispenseItem(VendingProduct vendingProduct) {
        //not doing anything here
    }
}

class WorkingState implements VendingState {

    @Override
    public void insertCoin(VendingProduct vendingProduct) {
        //not doing anything here
    }

    @Override
    public void dispenseItem(VendingProduct vendingProduct) {
        System.out.println("Product dispensed");
        //set any other state if applicable
    }
}

public class State {
    public static void main(String[] args) {
        VendingProduct vendingProduct = new VendingProduct();
        VendingState idealState = new IdealState();
        vendingProduct.setMachineState(idealState);
        vendingProduct.getVendingState().insertCoin(vendingProduct);
    }
}
