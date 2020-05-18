package parkingmachines;

import parkingmachines.displays.Display;
import parkingmachines.feestrategies.FeeStrategyFactory;
import parkingmachines.feestrategies.FeeStrategyType;
import parkingmachines.inputs.Input;

import java.util.HashMap;

public class CommandLineCheckOutMachine extends CommandLineMachine implements CheckOutMachine {

    /**
     * Returns a new instance of CommandLineCheckOutMachine
     * @param input The user input class
     * @param display Where to display text
     * @param feeStrategyFactory Which FeeStrategyFactory to use
     * @param garageName The name of the garage
     */
    public CommandLineCheckOutMachine(Input input, Display display, FeeStrategyFactory feeStrategyFactory, String garageName) {
        setInput(input);
        setDisplay(display);
        setFeeStrategyFactory(feeStrategyFactory);
        setGarageName(garageName);

        HashMap<String, String> menuOptions = new HashMap<>();
        menuOptions.put("1", "Check-Out");
        menuOptions.put("2", "Lost Ticket");
        setMenuOptions(menuOptions);
    }

    /**
     *
     * @param ticket The ticket to be checked out
     */
    public void checkOut(Ticket ticket) {
        display.showMenu(getGarageName(), getMenuOptions());
        String userMenuOption = input.getUserMenuOption(getMenuOptions().keySet());

        if (userMenuOption.equals("2")) {
            ticket.setFeeStrategy(getFeeStrategyFactory().create(FeeStrategyType.LOST_TICKET));
        }

        ticket.setCheckOutTime(RandomTime.makeRandomCheckOutTime());
        showBill(ticket);
    }

    /**
     * Shows the bill for the current ticket.
     * @param ticket The ticket to bill
     */
    public void showBill(Ticket ticket) {
        if (ticket != null) {
            display.showHeader(getGarageName());
            display.showMessage("Receipt for a vehicle id " + ticket.getId() + "\n\n\n\n");
            display.showMessage(ticket.print() + "\n\n");
            display.showMessage(String.format("$%.2f\n\n", ticket.getFee()));
        } else {
            throw new IllegalArgumentException("Ticket cannot be null");
        }
    }
}
