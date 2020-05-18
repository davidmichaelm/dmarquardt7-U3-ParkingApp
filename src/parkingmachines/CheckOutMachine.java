package parkingmachines;

/**
 * Interface for objects simulating a check out machine.
 */
public interface CheckOutMachine {
    /**
     * Checks out a ticket.
     * @param ticket The ticket to be checked out
     */
    void checkOut(Ticket ticket);
}
