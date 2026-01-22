package LowLevelDesigns.Elevator;

public class Main {

    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController(2);
        controller.handleExternalRequest(2,7);
        controller.handleExternalRequest(5, 1);

        //Simulate time
        for (int i = 0; i < 15; i++) {
            controller.stepAll();
        }
    }

}
