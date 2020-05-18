package parkingmachines;

import parkingmachines.displays.Display;
import parkingmachines.feestrategies.FeeStrategy;
import parkingmachines.feestrategies.FeeStrategyFactory;
import parkingmachines.feestrategies.FeeStrategyType;
import parkingmachines.inputs.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CommandLineCheckInMachine extends CommandLineMachine implements CheckInMachine {

    /**
     * Returns a new instances of CommandLineCheckInMachine
     * @param input The user input class
     * @param display Where to display text
     * @param feeStrategyFactory Which FeeStrategyFactory to use
     * @param garageName The name of the garage
     */
    public CommandLineCheckInMachine(Input input, Display display, FeeStrategyFactory feeStrategyFactory, String garageName) {
        setInput(input);
        setDisplay(display);
        setFeeStrategyFactory(feeStrategyFactory);
        setGarageName(garageName);

        HashMap<String, String> menuOptions = new HashMap<>();
        menuOptions.put("1", "Check-In");
        menuOptions.put("2", "Special Event");
        menuOptions.put("3", "Close Garage");
        setMenuOptions(menuOptions);
    }

    /**
     * Presents the user with options for checking in.
     * @return A Ticket with check-in information, or null to quit
     */
    public Ticket run() {
        display.showMenu(getGarageName(), getMenuOptions());
        String userMenuOption = input.getUserMenuOption(getMenuOptions().keySet());

        switch (userMenuOption) {
            case "1": // check in
                return createTicket(getFeeStrategyFactory().create(FeeStrategyType.MIN_MAX));
            case "2": // special event
                return createTicket(getFeeStrategyFactory().create(FeeStrategyType.SPECIAL_EVENT));
            case "3": // close garage
            default:
                return null;
        }
    }

    /**
     * Helper function for creating tickets
     * @param feeStrategy Which FeeStrategy the Ticket will use
     * @return A new ticket
     */
    private Ticket createTicket(FeeStrategy feeStrategy) {
        return new Ticket(feeStrategy, RandomTime.makeRandomCheckInTime());
    }

    /**
     * Sorts the tickets by type, counts and totals them, and prints to screen
     * @param completedTickets Collection of completed tickets so far
     * @param feeStrategies Collection of FeeStrategyTypes and their description strings
     */
    public void showActivityToDate(HashMap<Integer, Ticket> completedTickets, HashMap<FeeStrategyType, String> feeStrategies) {

        HashMap<FeeStrategyType, List<Ticket>> feeStrategyLists = new HashMap<>();
        HashMap<FeeStrategyType, Integer> feeStrategyCounts = new HashMap<>();
        HashMap<FeeStrategyType, Double> feeStrategyTotals = new HashMap<>();
        AtomicReference<Double> total = new AtomicReference<>(0.0); // suggested by IntelliJ to update the local variable inside lambda

        // Populate lists with each FeeStrategyType being used
        feeStrategies.forEach((key, value) -> {
            feeStrategyLists.put(key, new ArrayList<Ticket>());
            feeStrategyTotals.put(key, 0.0);
        });

        // sort tickets into lists by FeeStrategyType
        completedTickets.forEach((key, ticket) -> feeStrategyLists.get(ticket.getFeeStrategyType()).add(ticket));

        // Count how many tickets are in each list
        feeStrategyLists.forEach((key, ticket) -> feeStrategyCounts.put(key, feeStrategyLists.get(key).size()));

        // Add up how much we made in each category, and how much we made overall
        feeStrategyLists.forEach((key, list) -> {
            list.forEach((ticket) -> {
                feeStrategyTotals.put(key, feeStrategyTotals.get(key) + ticket.getFee());
                total.updateAndGet(v -> v + ticket.getFee());
            });
        });

        display.showHeader(getGarageName());
        display.showMessage("Activity to Date\n\n\n");

        feeStrategies.forEach((type, feeStrategyName) -> {
            display.showMessage("$" + feeStrategyTotals.get(type) + " was collected from " + feeStrategyCounts.get(type) + " " + feeStrategyName + "s\n\n");
        });

        display.showMessage("\n\n\n$" + total + " was collected overall");
    }
}
