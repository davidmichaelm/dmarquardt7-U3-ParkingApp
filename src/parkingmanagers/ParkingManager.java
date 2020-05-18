package parkingmanagers;

/**
 * Interface for ParkingManager classes.
 */
public interface ParkingManager {
    /**
     * Opens the garage for check-ins and check-outs.
     */
    public void openGarage();

    /**
     * Closes the garage and performs any closing duties.
     */
    public void closeGarage();
}
