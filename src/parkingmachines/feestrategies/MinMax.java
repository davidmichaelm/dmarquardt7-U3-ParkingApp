package parkingmachines.feestrategies;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * FeeStrategy with a minimum fee and maximum fee.
 */
public class MinMax implements FeeStrategy {
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    private int minimumHours;
    private double minimumFee;
    private double feePerHour;
    private double maximumFee;

    /**
     * RReturns a new instance of the MinMax FeeStrategy
     * @param minimumHours The number of hours before extra fees occur
     * @param minimumFee The base fee with no extra fees added
     * @param feePerHour The fee per hour over the minimum hours
     * @param maximumFee The maximum fee for a 24-hour period
     */
    public MinMax(int minimumHours, double minimumFee, double feePerHour, double maximumFee) {
        this.minimumHours = minimumHours;
        this.minimumFee = minimumFee;
        this.feePerHour = feePerHour;
        this.maximumFee = maximumFee;
    }

    /**
     * Gets the FeeStrategyType enum
     * @return The FeeStrategyType enum
     */
    public FeeStrategyType getFeeStrategyType() {
        return FeeStrategyType.MIN_MAX;
    }

    /**
     * Gets the number of hours parked
     * @param checkInTime When the vehicle checked in
     * @param checkOutTime When the vehicle checked out
     * @return The number of hours parked
     */
    private int getHoursParked(LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        Duration timeParked = Duration.between(checkInTime, checkOutTime);
        int hoursParked = (int) timeParked.toHours();
        return timeParked.toMinutes() % 60 > 0 ? hoursParked + 1 : hoursParked; // rounding up for part of an hour
    }

    /**
     * Calculates the fee for the ticket.
     * @param checkInTime Time the vehicle checked in
     * @param checkOutTime Time the vehicle checked out
     * @return The total fee for the ticket
     */
    public double calculateFee(LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        /*
         * The fee charged for parked vehicles is based on a $5.00 minimum fee to park for up to three hours.
         * After that there is an additional $1.00 per hour charge for each hour (or part of an hour) parked.
         * The maximum charge for any given 24-hour period is $15.00. Assume that no vehicle parks for longer than 24 hours.
         * */

        int hoursParked = getHoursParked(checkInTime, checkOutTime);

        double fee;
        if (hoursParked <= minimumHours) {
            fee = minimumFee;
        } else {
            fee = ((hoursParked - minimumHours) * feePerHour) + minimumFee;

            if (fee > maximumFee) {
                fee = maximumFee;
            }
        }

        return fee;
    }

    /**
     * Gets a String describing the FeeStrategy for display purposes.
     * @param checkInTime Time the vehicle checked in
     * @param checkOutTime Time the vehicle checked out
     * @return String describing the FeeStrategy
     */
    public String print(LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ha");
        return getHoursParked(checkInTime, checkOutTime) + " hours parked " +
                checkInTime.format(formatter) + " - " +
                checkOutTime.format(formatter) + "\n\n";
    }
}
