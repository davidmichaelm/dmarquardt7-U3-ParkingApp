package parkingmachines.inputs;

import parkingmachines.displays.Display;

import java.util.Scanner;
import java.util.Set;

/**
 * Implementation for getting user input from a regular keyboard.
 */
public class Keyboard implements Input {
    private Scanner keyboard = new Scanner(System.in);
    private Display display;

    /**
     * Returns a new Keyboard object.
     * @param display The screen the user will be reacting to
     */
    public Keyboard(Display display) {
        this.display = display;
    }

    /**
     * Gets the user's response to a set of menu options.
     * @param options The Strings the user may select from
     * @return Which String the user selected
     */
    public String getUserMenuOption(Set<String> options) {
        if (options != null) {
            boolean keepRunning = true;
            String result = "";
            while (keepRunning) {
                result = keyboard.nextLine();
                if (keepRunning = !options.contains(result)) {
                    display.showMessage("Invalid input. Enter a number from one of the available options\n");
                }
            }

            return result;
        } else {
            throw new IllegalArgumentException("Must provide at least one menu option");
        }
    }
}
