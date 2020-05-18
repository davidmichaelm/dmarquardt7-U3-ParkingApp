package parkingmachines.displays;

import java.util.HashMap;

/**
 * Interface for parking garage machines
 */
public interface Display {
    /**
     * Displays a message.
     * @param message The message to display
     */
    public void showMessage(String message);

    /**
     * Displays a screen header.
     * @param garageName The garage name to display
     */
    public void showHeader(String garageName);

    /**
     * Displays options for the user to choose from.
     * @param garageName The garage name to display
     * @param menuOptions A collection of options available to choose from
     */
    public void showMenu(String garageName, HashMap<String, String> menuOptions);
}
