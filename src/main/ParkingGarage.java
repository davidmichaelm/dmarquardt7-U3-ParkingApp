package main;

import parkingmanagers.ParkingManager;

/**
 * A singleton that represents the Parking Garage and starts the ParkingManager.
 * Must be setup with a ParkingConfig class.
 */
public class ParkingGarage {

    private static ParkingGarage instance;
    private static boolean setUp = false;

    private String businessName;
    private ParkingManager parkingManager;

    /**
     * Private constructor for Parking Garage.
     */
    private ParkingGarage() {

    }

    /**
     * Call to get the one and only instance of ParkingGarage.
     * @return The one and only instance of ParkingGarage
     */
    public static ParkingGarage getInstance() {
        if (instance == null) {
            synchronized (ParkingGarage.class) {
                if (instance == null) {
                    instance = new ParkingGarage();
                }
            }
        }

        return instance;
    }

    /**
     * Uses the ParkingManager to open the garage for business.
     */
    public void run() {
        if (parkingManager != null) {
            parkingManager.openGarage();
        }
    }

    /**
     * Gets the business name and ParkingManager from the ParkingConfig. Can only be run once.
     * @param config ParkingConfig class with business name and ParkingManager inside
     */
    public void setUpConfig(ParkingConfig config) {
        if (!setUp) {
            setBusinessName(config.getBusinessName());
            setParkingManager(config.getParkingManager());
            setUp = true;
        }
    }

    /**
     * Sets the business name.
     * @param businessName The name of the parking garage
     */
    private void setBusinessName(String businessName) {
        if (businessName != null && !businessName.isEmpty()) {
            this.businessName = businessName;
        } else {
            throw new IllegalArgumentException("Business name cannot be null or empty");
        }
    }

    /**
     * Sets the instance of ParkingManager.
     * @param parkingManager The class that will manage check-ins and check-outs
     */
    private void setParkingManager(ParkingManager parkingManager) {
        if (parkingManager != null ) {
            this.parkingManager = parkingManager;
        } else {
            throw new IllegalArgumentException("Parking manager cannot be null");
        }
    }

    public String getBusinessName() {
        return businessName;
    }

    public ParkingManager getParkingManager() {
        return parkingManager;
    }

}
