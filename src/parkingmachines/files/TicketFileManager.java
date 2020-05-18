package parkingmachines.files;

import parkingmachines.Ticket;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface for how to save tickets to file.
 */
public interface TicketFileManager {

    /**
     * Reads tickets from a file.
     * @return A List of String arrays with ticket information
     */
    ArrayList<String[]> readTickets();

    /**
     * Writes tickets to a file.
     * @param ticketList A HashMap with all previous tickets
     */
    void writeTickets(HashMap<Integer, Ticket> ticketList);

    /**
     * Sets the file to write to.
     * @param fileName The name of the file to write to
     */
    void setFile(String fileName);
}
