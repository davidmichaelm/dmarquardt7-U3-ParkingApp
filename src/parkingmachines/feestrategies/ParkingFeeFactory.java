package parkingmachines.feestrategies;

/**
 * Factory method object that creates FeeStrategy objects.
 */
public class ParkingFeeFactory implements FeeStrategyFactory {

    private double lostTicketFee;
    private double specialEventFee;
    private int minimumHours;
    private double minimumFee;
    private double feePerHour;
    private double maximumFee;

    /**
     * Returns a new instance of a ParkingFeeFactory.
     * @param lostTicketFee How much to charge for a lost ticket
     * @param specialEventFee How much to charge for a special event
     * @param minimumHours For MinMax, the number of hours before extra fees occur
     * @param minimumFee For MinMax, the base fee with no extra fees added
     * @param feePerHour For MinMax, the fee per hour over the minimum hours
     * @param maximumFee For MinMax, the maximum fee for a 24-hour period
     */
    public ParkingFeeFactory(double lostTicketFee, double specialEventFee, int minimumHours, double minimumFee, double feePerHour, double maximumFee) {
        this.lostTicketFee = lostTicketFee;
        this.specialEventFee = specialEventFee;
        this.minimumHours = minimumHours;
        this.minimumFee = minimumFee;
        this.feePerHour = feePerHour;
        this.maximumFee = maximumFee;
    }

    /**
     * Creates a new instance of a FeeStrategy based on the enum type passed.
     * @param type FeeStrategyType enum to base the FeeStrategy on
     * @return A new FeeStrategy
     */
    public FeeStrategy create(FeeStrategyType type) {
        switch (type) {
            case LOST_TICKET:
                return new LostTicket(lostTicketFee);
            case MIN_MAX:
                return new MinMax(minimumHours, minimumFee, feePerHour, maximumFee);
            case SPECIAL_EVENT:
                return new SpecialEvent(specialEventFee);
            default:
                return null;
        }
    }
}
