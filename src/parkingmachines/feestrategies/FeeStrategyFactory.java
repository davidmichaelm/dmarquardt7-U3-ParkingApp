package parkingmachines.feestrategies;

/**
 * Factory Method interface for creating FeeStrategy objects
 */
public interface FeeStrategyFactory {

    /**
     * Creates a new FeeStrategy object based on the FeeStrategyType enum
     * @param type FeeStrategyType enum to base the FeeStrategy on
     * @return A new FeeStrategy object+
     */
    public FeeStrategy create(FeeStrategyType type);
}
