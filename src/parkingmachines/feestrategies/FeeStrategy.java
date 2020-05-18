package parkingmachines.feestrategies;

import java.time.LocalDateTime;

/**
 * Strategy pattern for different methods of calculating ticket fees.
 */
public interface FeeStrategy {
    /**
     * Gets the FeeStrategyType enum matching the FeeStrategy.
     * @return FeeStrategyType enum
     */
    FeeStrategyType getFeeStrategyType();

    /**
     * Calculates the fee for the ticket.
     * @param checkInTime Time the vehicle checked in
     * @param checkOutTime Time the vehicle checked out
     * @return The total fee for the ticket
     */
    double calculateFee(LocalDateTime checkInTime, LocalDateTime checkOutTime);

    /**
     * Gets a String describing the FeeStrategy for display purposes.
     * @param checkInTime Time the vehicle checked in
     * @param checkOutTime Time the vehicle checked out
     * @return String describing the FeeStrategy
     */
    String print(LocalDateTime checkInTime, LocalDateTime checkOutTime);
}
