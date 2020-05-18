package parkingmanagers;

import parkingmachines.*;
import parkingmachines.feestrategies.FeeStrategyFactory;
import parkingmachines.feestrategies.FeeStrategyType;
import parkingmachines.files.TicketFileManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 * A ParkingManager that runs automatically, alternating between check-in and check-out.
 */
public class AutoRunParkingManager implements ParkingManager {
    private CheckInMachine checkInMachine;
    private CheckOutMachine checkOutMachine;

    private TicketFileManager fileManager;

    private HashMap<Integer, Ticket> currentTickets = new HashMap<>();
    private HashMap<Integer, Ticket> completedTickets = new HashMap<>();

    private HashMap<FeeStrategyType, String> feeStrategies;

    private FeeStrategyFactory feeStrategyFactory;

    /**
     * Creates a new instance of AutoRunParkingManager.
     * @param checkInMachine The CheckInMachine used to check-in vehicles
     * @param checkOutMachine The CheckOutMachine used to check-out vehicles
     * @param feeStrategies HashMap of FeeStrategyType objects used to calculate fees
     * @param fileManager The FileManager used to save and load tickets
     * @param feeStrategyFactory The FeeStrategyFactory used to create fee strategies
     */
    public AutoRunParkingManager(CheckInMachine checkInMachine, CheckOutMachine checkOutMachine, HashMap<FeeStrategyType, String> feeStrategies, TicketFileManager fileManager, FeeStrategyFactory feeStrategyFactory) {
        this.checkInMachine = checkInMachine;
        this.checkOutMachine = checkOutMachine;
        this.feeStrategies = feeStrategies;
        this.fileManager = fileManager;
        this.feeStrategyFactory = feeStrategyFactory;
    }

    /**
     * Loads saved tickets and alternates between check-in and check-out.
     */
    public void openGarage() {
        processSavedTickets(fileManager.readTickets());

        while (runCheckIn()) {
            runCheckOut();
        }
        closeGarage();
    }

    /**
     * Gets a new ticket or closes the garage.
     * @return True if a new ticket is created, false to close the garage
     */
    public boolean runCheckIn() {
        Ticket ticket = checkInMachine.run();
        if (ticket == null) {
            return false;
        } else {
            currentTickets.put(ticket.getId(), ticket);
            return true;
        }
    }

    /**
     * Checks out a random ticket and adds it to the completedTickets collection.
     */
    public void runCheckOut() {
        Ticket ticket = getRandomTicket();

        checkOutMachine.checkOut(ticket);
        currentTickets.remove(ticket.getId());
        completedTickets.put(ticket.getId(), ticket);
    }

    /**
     * Shows the activity to date and saves tickets to file.
     */
    public void closeGarage() {
        checkInMachine.showActivityToDate(completedTickets, feeStrategies);
        fileManager.writeTickets(completedTickets);
    }

    /**
     * Gets a random open ticket.
     * @return Ticket from the currentTickets collection
     */
    private Ticket getRandomTicket() {
        int randomInt = new Random().nextInt(currentTickets.size());
        int randomId = (int)currentTickets.keySet().toArray()[randomInt];
        return currentTickets.get(randomId);
    }

    /**
     * Converts string arrays with ticket information into tickets and adds them to the completedTickets list.
     * @param ticketStringArrays A String array in the format [id, checkInTime, checkOutTime, FeeStrategy]
     */
    private void processSavedTickets(ArrayList<String[]> ticketStringArrays) {
        ticketStringArrays.forEach((array) -> {
            Ticket ticket = new Ticket(array[0], array[1], array[2], feeStrategyFactory.create(FeeStrategyType.valueOf(array[3])));
            completedTickets.put(ticket.getId(), ticket);
        });
    }
}
