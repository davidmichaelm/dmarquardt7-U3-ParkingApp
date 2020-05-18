package main;

/**
 * The entry point for the application.
 */
public class Startup {
    public static void main(String[] args) {
        ParkingConfig config = new ParkingConfig();
        ParkingGarage garage = ParkingGarage.getInstance();
        garage.setUpConfig(config);
        garage.run();
    }
}
