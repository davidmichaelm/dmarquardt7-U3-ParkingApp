package tests.parkingmachines;

import org.junit.Before;
import org.junit.Test;
import parkingmachines.RandomTime;

import static org.junit.Assert.*;

public class RandomTimeTest {

    @Test
    public void makeRandomCheckInTime() {
        assertTrue(RandomTime.makeRandomCheckInTime().getHour() >= 7);
        assertTrue(RandomTime.makeRandomCheckInTime().getHour() <= 12);
    }

    @Test
    public void makeRandomCheckOutTime() {
        assertTrue(RandomTime.makeRandomCheckOutTime().getHour() >= 13);
    }
}