package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses ( {
        tests.main.ParkingGarageTest.class,
        tests.parkingmachines.feestrategies.LostTicketTest.class,
        tests.parkingmachines.feestrategies.MinMaxTest.class,
        tests.parkingmachines.feestrategies.ParkingFeeFactoryTest.class,
        tests.parkingmachines.feestrategies.SpecialEventTest.class,
        tests.parkingmachines.files.CsvTicketFileManagerTest.class,
        tests.parkingmachines.RandomTimeTest.class
} )

public class TestSuite {
}
