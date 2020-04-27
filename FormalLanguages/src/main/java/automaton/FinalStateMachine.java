package automaton;

import lombok.Data;
import lombok.NoArgsConstructor;
import util.Pair;
import util.Triplet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Simple class with everything related to final state machine.
 * @author Ilya Slepov
 */

@Data
@NoArgsConstructor
public class FinalStateMachine {

    private Integer priority;
    private Set<String> alphabet;
    private Set<String> states;
    private Set<String> startStates;
    private Set<String> finalStates;
    private List<Triplet> transitions;
    private Set<String> currentStates;

    /**
     * Returns the machine to its initial state.
     */
    public void resetMachine() {
        currentStates = startStates;
    }

    /**
     * Transition function.
     * Converts the automaton to the next state if it meets the validation requirements.
     * @param signal
     */
    private boolean transition(String signal) {
        if (!alphabet.contains(signal))
            return false;
        Set<String> nextState = new HashSet<>();
        for (String state : currentStates)
            for (Triplet triplet : transitions)
                if (triplet.by.equals(signal) && triplet.from.equals(state))
                        nextState.addAll(triplet.to);
        currentStates = nextState;
        return !currentStates.isEmpty();
    }

    /**
     * Function for searching for the maximum substring starting
     * from a certain position of the original string.
     * @param initialString
     * @param startPosition
     * @return
     */
    public Pair<Boolean, Integer> maxString(String initialString, int startPosition) {
        if (startPosition >= initialString.length() || startPosition < 0) {
            return new Pair<>(false, 0);
        }
        int subStringLength = 0;
        boolean isSubstring = false;
        while (startPosition != initialString.length()) {
            if (transition(initialString.substring(startPosition, startPosition + 1))) {
                subStringLength++;
                isSubstring = true;
                startPosition++;
                for (String state : currentStates) {
                    if (finalStates.contains(state)) {
                        resetMachine();
                        return new Pair<>(isSubstring, subStringLength);
                    }
                }
            }
            else {
                resetMachine();
                return new Pair<>(false, subStringLength);
            }
        }
        return new Pair<>(isSubstring, subStringLength);
    }
}

