package tests.parkingmachines.feestrategies;

import org.junit.Before;
import org.junit.Test;
import parkingmachines.feestrategies.FeeStrategyType;
import parkingmachines.feestrategies.LostTicket;
import parkingmachines.feestrategies.MinMax;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

public class MinMaxTest {

    private MinMax minMax;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    @Before
    public void setUp() {
        minMax = new MinMax(3, 5, 1, 15);
        checkInTime = LocalDateTime.of(2020, Month.MARCH, 18, 9, 0);
        checkOutTime = LocalDateTime.of(2020, Month.MARCH, 18, 10, 0);
    }


    @Test
    public void getFeeStrategyType() {
        assertEquals(minMax.getFeeStrategyType(), FeeStrategyType.MIN_MAX);
    }

    @Test
    public void calculateFee() {
        assertEquals(minMax.calculateFee(checkInTime, checkOutTime), 5.0, 0);
        assertEquals(minMax.calculateFee(checkInTime, checkOutTime.plusHours(5)), 8.0, 0);
        assertEquals(minMax.calculateFee(checkInTime, checkOutTime.plusHours(20)), 15.0, 0);
    }
}