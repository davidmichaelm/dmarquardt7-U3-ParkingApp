package parkingmachines;

import parkingmachines.feestrategies.FeeStrategyType;

import java.util.HashMap;

/**
 * Interface for objects simulating a check in machine.
 */
public interface CheckInMachine {

    /**
     * Presents the user with options for checking in.
     * @return A Ticket with check-in information, or null to quit
     */
    Ticket run();

    /**
     * Shows the totals for all completed tickets.
     * @param completedTickets Collection of completed tickets so far
     * @param feeStrategies Collection of FeeStrategyTypes and their description strings
     */
    void showActivityToDate(HashMap<Integer, Ticket> completedTickets, HashMap<FeeStrategyType, String> feeStrategies);
}
