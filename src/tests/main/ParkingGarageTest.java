package tests.main;

import main.ParkingConfig;
import main.ParkingGarage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingGarageTest {

    private ParkingGarage parkingGarage;
    private ParkingConfig parkingConfig;

    @Before
    public void setUp() {
        parkingGarage = ParkingGarage.getInstance();
        parkingConfig = new ParkingConfig();
    }

    @Test
    public void getInstance() {
        ParkingGarage otherParkingGarage = ParkingGarage.getInstance();
        assertSame(parkingGarage, otherParkingGarage);
    }

    @Test
    public void setUpConfig() {
        parkingGarage.setUpConfig(parkingConfig);

        assertEquals(parkingConfig.getBusinessName(), parkingGarage.getBusinessName());
        assertEquals(parkingConfig.getParkingManager(), parkingGarage.getParkingManager());
    }
}