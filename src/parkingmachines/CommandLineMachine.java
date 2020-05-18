package parkingmachines;

import parkingmachines.displays.Display;
import parkingmachines.feestrategies.FeeStrategyFactory;
import parkingmachines.inputs.Input;

import java.util.HashMap;

/**
 * Base class providing shared functions for CheckIn or CheckOut machines using the command line as input and output.
 */
public abstract class CommandLineMachine {

    protected Input input;
    protected Display display;
    private FeeStrategyFactory feeStrategyFactory;
    private HashMap<String, String> menuOptions;
    private String garageName;

    /**
     * Gets the menu options.
     * @return Collection of selection strings and menu options
     */
    public HashMap<String, String> getMenuOptions() {
        return menuOptions;
    }

    /**
     * Sets the menu options.
     * @param menuOptions Key is what the user types to select, value is the option description
     */
    public void setMenuOptions(HashMap<String, String> menuOptions) {
        if (menuOptions != null) {
            this.menuOptions = menuOptions;
        } else {
            throw new IllegalArgumentException("Menu options cannot be null");
        }

    }

    /**
     * Sets the input class to use.
     * @param input The input class to use
     */
    public void setInput(Input input) {
        if (input != null) {
            this.input = input;
        } else {
            throw new IllegalArgumentException("Input cannot be null");
        }
    }

    /**
     * Sets the display class to use.
     * @param display The display class to use
     */
    public void setDisplay(Display display) {
        if (display != null) {
            this.display = display;
        } else {
            throw new IllegalArgumentException("Display cannot be null");
        }
    }

    /**
     * Gets the name of the garage
     * @return The name of the garage
     */
    public String getGarageName() {
        return garageName;
    }

    /**
     * Sets the name of the garage
     * @param garageName The name of the garage
     */
    public void setGarageName(String garageName) {
        if (garageName != null && !garageName.isEmpty()) {
            this.garageName = garageName;
        } else {
            throw new IllegalArgumentException("Garage name cannot be null or empty");
        }
    }

    /**
     * Sets the FeeStrategyFactory for creating FeeStrategy objects
     * @param feeStrategyFactory The FeeStrategyFactory to use
     */
    public void setFeeStrategyFactory(FeeStrategyFactory feeStrategyFactory) {
        if (feeStrategyFactory != null) {
            this.feeStrategyFactory = feeStrategyFactory;
        } else {
            throw new IllegalArgumentException("Fee strategy factory cannot be null");
        }
    }

    /**
     * Gets the FeeStrategyFactory
     * @return The FeeStrategyFactory
     */
    public FeeStrategyFactory getFeeStrategyFactory() {
        return feeStrategyFactory;
    }
}
