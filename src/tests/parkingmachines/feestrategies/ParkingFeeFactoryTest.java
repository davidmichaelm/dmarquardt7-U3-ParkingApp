package tests.parkingmachines.feestrategies;

import org.junit.Test;
import parkingmachines.feestrategies.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class ParkingFeeFactoryTest {

    @Test
    public void create() {
        FeeStrategyFactory feeStrategyFactory = new ParkingFeeFactory(25, 20, 3, 5, 1, 15);
        assertThat(feeStrategyFactory.create(FeeStrategyType.LOST_TICKET), instanceOf(LostTicket.class));
        assertThat(feeStrategyFactory.create(FeeStrategyType.MIN_MAX), instanceOf(MinMax.class));
        assertThat(feeStrategyFactory.create(FeeStrategyType.SPECIAL_EVENT), instanceOf(SpecialEvent.class));

    }
}