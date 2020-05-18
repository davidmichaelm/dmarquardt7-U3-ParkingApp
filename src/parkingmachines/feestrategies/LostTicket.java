package parkingmachines.feestrategies;

import java.time.LocalDateTime;

/**
 * FeeStrategy for tickets that have been lost.
 */
public class LostTicket implements FeeStrategy {

    private double lostTicketFee;

    /**
     * Returns a new instance of the LostTicket FeeStrategy
     * @param lostTicketFee How much the fee is for a lost ticket
     */
    public LostTicket(double lostTicketFee) {
        this.lostTicketFee = lostTicketFee;
    }

    /**
     * Gets the FeeStrategyType enum
     * @return The FeeStrategyType enum
     */
    public FeeStrategyType getFeeStrategyType() {
        return FeeStrategyType.LOST_TICKET;
    }

    /**
     * Returns the fee set for lost tickets
     * @param checkInTime Time the vehicle checked in
     * @param checkOutTime Time the vehicle checked out
     * @return The ticket fee
     */
    public double calculateFee(LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        return lostTicketFee;
    }

    /**
     * Gets a String describing the FeeStrategy for display purposes.
     * @param checkInTime Time the vehicle checked in
     * @param checkOutTime Time the vehicle checked out
     * @return String describing the FeeStrategy
     */
    public String print(LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        return "Lost Ticket";
    }
}
