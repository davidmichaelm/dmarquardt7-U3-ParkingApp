package tests.parkingmachines.feestrategies;

import com.sun.source.tree.AssertTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parkingmachines.feestrategies.FeeStrategyType;
import parkingmachines.feestrategies.LostTicket;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

public class LostTicketTest {

    private LostTicket lostTicket;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    @Before
    public void setUp() {
        lostTicket = new LostTicket(25);
        checkInTime = LocalDateTime.of(2020, Month.MARCH, 18, 9, 0);
        checkOutTime = LocalDateTime.of(2020, Month.MARCH, 18, 10, 0);
    }

    @Test
    public void getFeeStrategyType() {
        assertEquals(lostTicket.getFeeStrategyType(), FeeStrategyType.LOST_TICKET);
    }

    @Test
    public void calculateFee() {
        assertEquals(lostTicket.calculateFee(checkInTime, checkOutTime), 25.0, 0);
    }

    @Test
    public void print() {
        assertEquals(lostTicket.print(checkInTime, checkOutTime), "Lost Ticket");
    }
}