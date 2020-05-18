package parkingmachines.inputs;

import java.util.Set;

/**
 * Interface for methods of getting user input.
 */
public interface Input {

    /**
     * Gets the user's response to a set of menu options.
     * @param options The Strings the user may select from
     * @return Which String the user selected
     */
    public String getUserMenuOption(Set<String> options);
}
