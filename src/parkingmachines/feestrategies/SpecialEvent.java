package parkingmachines.feestrategies;

import java.time.LocalDateTime;

/**
 * FeeStrategy for special events.
 */
public class SpecialEvent implements FeeStrategy {

    private double specialEventFee;

    /**
     * Returns a new instance of the LostTicket FeeStrategy
     * @param specialEventFee How much the fee is for a lost ticket
     */
    public SpecialEvent(double specialEventFee) {
        this.specialEventFee = specialEventFee;
    }

    /**
     * Gets the FeeStrategyType enum
     * @return The FeeStrategyType enum
     */
    public FeeStrategyType getFeeStrategyType() {
        return FeeStrategyType.SPECIAL_EVENT;
    }

    /**
     * Calculates the fee for the ticket.
     * @param checkInTime Time the vehicle checked in
     * @param checkOutTime Time the vehicle checked out
     * @return The total fee for the ticket
     */
    public double calculateFee(LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        return specialEventFee;
    }

    /**
     * Gets a String describing the FeeStrategy for display purposes.
     * @param checkInTime Time the vehicle checked in
     * @param checkOutTime Time the vehicle checked out
     * @return
     */
    public String print(LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        return "Special Event";
    }
}
