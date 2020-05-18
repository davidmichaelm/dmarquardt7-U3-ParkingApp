package parkingmachines.displays;

import java.util.HashMap;

/**
 * Displays messages to the command line.
 */
public class CommandLineDisplay implements Display {

    /**
     * Prints the message with no new line.
     * @param message The message to display
     */
    public void showMessage(String message) {
        if (message != null && !message.isEmpty()) {
            System.out.print(message);
        } else {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
    }

    /**
     * Prints the header for the current screen
     * @param garageName The garage name to display
     */
    public void showHeader(String garageName) {
        if (garageName != null && !garageName.isEmpty()) {
            showMessage(garageName + "\n\n" +
                    "=========================\n\n");
        } else {
            throw new IllegalArgumentException("Garage name cannot be null or empty");
        }
    }

    /**
     * Prints the header, then prints menu options for the user to choose from
     * @param garageName The garage name to display
     * @param menuOptions A collection of options available to choose from
     */
    public void showMenu(String garageName, HashMap<String, String> menuOptions) {
        if (garageName != null && !garageName.isEmpty()
        && menuOptions != null && !menuOptions.isEmpty()) {
            showHeader(garageName);
            menuOptions.forEach((key, value) -> {
                showMessage(key + ": " + value + "\n\n");
            });
            showMessage(("=>"));
        } else {
            throw new IllegalArgumentException("Garage name and menu options cannot be null or empty");
        }
    }
}
