package main;

import parkingmachines.*;
import parkingmachines.displays.CommandLineDisplay;
import parkingmachines.displays.Display;
import parkingmachines.feestrategies.FeeStrategyFactory;
import parkingmachines.feestrategies.FeeStrategyType;
import parkingmachines.feestrategies.ParkingFeeFactory;
import parkingmachines.files.CsvTicketFileManager;
import parkingmachines.files.TicketFileManager;
import parkingmachines.inputs.Input;
import parkingmachines.inputs.Keyboard;
import parkingmanagers.AutoRunParkingManager;
import parkingmanagers.ParkingManager;

import java.util.HashMap;

/**
 * Config class for ParkingGarage.
 * Change the values of the final fields to change functionality.
 */
public class ParkingConfig {

    private final String BUSINESS_NAME = "The Best Parking Garage";

    private final HashMap<FeeStrategyType, String> FEE_STRATEGIES = new HashMap<>() {{
        put(FeeStrategyType.MIN_MAX, "Check-In");
        put(FeeStrategyType.SPECIAL_EVENT, "Special Event");
        put(FeeStrategyType.LOST_TICKET, "Lost Ticket");
    }};
    private final int MINIMUM_HOURS = 3;
    private final double MINIMUM_FEE = 5.0;
    private final double FEE_PER_HOUR = 1.0;
    private final double MAXIMUM_FEE = 15.0;
    private final double SPECIAL_EVENT_FEE = 20.0;
    private final double LOST_TICKET_FEE = 25.0;
    private final FeeStrategyFactory FEE_STRATEGY_FACTORY = new ParkingFeeFactory(LOST_TICKET_FEE, SPECIAL_EVENT_FEE, MINIMUM_HOURS, MINIMUM_FEE, FEE_PER_HOUR, MAXIMUM_FEE);

    private final Display DISPLAY = new CommandLineDisplay();
    private final Input INPUT = new Keyboard(DISPLAY);
    private final CheckInMachine CHECK_IN_MACHINE = new CommandLineCheckInMachine(INPUT, DISPLAY, FEE_STRATEGY_FACTORY, BUSINESS_NAME);
    private final CheckOutMachine CHECK_OUT_MACHINE = new CommandLineCheckOutMachine(INPUT, DISPLAY, FEE_STRATEGY_FACTORY, BUSINESS_NAME);

    private final String SAVE_FILE_NAME = "tickets.csv";
    private final TicketFileManager FILE_MANAGER = new CsvTicketFileManager(SAVE_FILE_NAME);
    private final ParkingManager PARKING_MANAGER = new AutoRunParkingManager(CHECK_IN_MACHINE, CHECK_OUT_MACHINE, FEE_STRATEGIES, FILE_MANAGER, FEE_STRATEGY_FACTORY);

    /**
     * Gets the name of the business.
     * @return The name of the business
     */
    public String getBusinessName() {
        return BUSINESS_NAME;
    }

    /**
     * Gets the assigned ParkingManager.
     * @return An instance of a class that implements ParkingManager
     */
    public ParkingManager getParkingManager() {
        return PARKING_MANAGER;
    }
}
