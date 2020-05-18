package parkingmachines;

import parkingmachines.feestrategies.FeeStrategy;
import parkingmachines.feestrategies.FeeStrategyType;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents the ticket the customer receives and inputs at a parking garage.
 */
public class Ticket {
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private FeeStrategy feeStrategy;
    private int id;
    private static int idCount = 0;

    /**
     * Returns a new Ticket instance. Used when check out time is unknown.
     * @param feeStrategy Which FeeStrategy to use to calculate fees
     * @param checkInTime When the ticket was created
     */
    public Ticket(FeeStrategy feeStrategy, LocalDateTime checkInTime) {
        setFeeStrategy(feeStrategy);
        setCheckInTime(checkInTime);
        id = ++idCount;
    }

    /**
     * Returns a new Ticket instance. Used to import already completed tickets.
     * @param id The ticket's id number
     * @param checkInTime When the ticket was created
     * @param checkOutTime When the ticket was checked out
     * @param feeStrategy Which FeeStrategy to use to calculate fees
     */
    public Ticket(String id, String checkInTime, String checkOutTime, FeeStrategy feeStrategy) {
        setId(id);
        idCount++;
        setCheckInTime(checkInTime);
        setCheckOutTime(checkOutTime);
        setFeeStrategy(feeStrategy);
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        if (checkInTime != null) {
            this.checkInTime = checkInTime;
        } else {
            throw new IllegalArgumentException("Check in time cannot be null");
        }
    }

    public void setCheckInTime(String checkInTime) {
        if (checkInTime != null && !checkInTime.isEmpty()) {
            try {
                this.checkInTime = LocalDateTime.parse(checkInTime);
            } catch (DateTimeParseException e) {
                System.out.println("Unable to set Check in time\n");
            }
        }
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        if (checkOutTime != null) {
            this.checkOutTime = checkOutTime;
        } else {
            throw new IllegalArgumentException("Check out time cannot be null");
        }
    }

    public void setCheckOutTime(String checkOutTime) {
        if (checkOutTime != null && !checkOutTime.isEmpty()) {
            try {
                this.checkOutTime = LocalDateTime.parse(checkOutTime);
            } catch (DateTimeParseException e) {
                System.out.println("Unable to set Check out time\n");
            }
        }
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public FeeStrategyType getFeeStrategyType() {
        return feeStrategy.getFeeStrategyType();
    }

    /**
     * Calculates the ticket's fee based on its FeeStrategy object
     * @return The ticket's fee
     */
    public double getFee() {
        return this.feeStrategy.calculateFee(this.checkInTime, this.checkOutTime);
    }

    public void setId(String id) {
        try {
            this.id = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            System.out.println("Unable to set id\n");
        }
    }

    public int getId() {
        return id;
    }

    /**
     * Gets a String representing the kind of FeeStrategy the ticket uses.
     * @return The String for display purposes
     */
    public String print() {
        return feeStrategy.print(checkInTime, checkOutTime);
    }

    /**
     * Gets a String with the important data to reconstruct the ticket
     * @return The String in CSV format
     */
    public String toString() {
        return id + "," + checkInTime + "," + checkOutTime + "," + feeStrategy.getFeeStrategyType();
    }
}
