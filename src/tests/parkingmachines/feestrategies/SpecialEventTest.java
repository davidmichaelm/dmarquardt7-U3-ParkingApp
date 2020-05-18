package tests.parkingmachines.feestrategies;

import org.junit.Before;
import org.junit.Test;
import parkingmachines.feestrategies.FeeStrategyType;
import parkingmachines.feestrategies.LostTicket;
import parkingmachines.feestrategies.SpecialEvent;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

public class SpecialEventTest {

    private SpecialEvent specialEvent;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    @Before
    public void setUp() {
        specialEvent = new SpecialEvent(20);
        checkInTime = LocalDateTime.of(2020, Month.MARCH, 18, 9, 0);
        checkOutTime = LocalDateTime.of(2020, Month.MARCH, 18, 10, 0);
    }

    @Test
    public void getFeeStrategyType() {
        assertEquals(specialEvent.getFeeStrategyType(), FeeStrategyType.SPECIAL_EVENT);
    }

    @Test
    public void calculateFee() {
        assertEquals(specialEvent.calculateFee(checkInTime, checkOutTime), 20.0, 0);
    }

    @Test
    public void print() {
        assertEquals(specialEvent.print(checkInTime, checkOutTime), "Special Event");
    }
}