package automaton;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinalStateMachine {

    private Set<String> alphabet;
    private Set<String> states;
    private Set<String> startStates;
    private Set<String> finalStates;
    private List<Triplet> transitions;
    private Set<String> currentStates;

    /**
     * Transition function.
     * Converts the automaton to the next state if it meets the validation requirements.
     * @param signal
     */
    private void transition(String signal) {
        Set<String> nextState = new HashSet<>();
        for (String state : currentStates) {
            for (Triplet triplet : transitions) {
                if(triplet.by.equals(signal)
                        && triplet.from.equals(state)
                        && alphabet.contains(triplet.by.toString()))
                    nextState.add(triplet.from);
            }
        }
        currentStates = nextState;
    }

    /**
     * Function for searching for the maximum substring starting
     * from a certain position of the original string.
     * @param initialString
     * @param startPosition
     * @return
     */
    public Pair<Boolean, Integer> maxString(String initialString, int startPosition) {
        if (startPosition >= initialString.length())
            return new Pair<>(false, 0);
        int subStringLength = 0;
        boolean isSubstring = false;
        while (startPosition != initialString.length()) {
            transition(initialString.substring(startPosition, startPosition + 1));
            for (String state : currentStates) {
                if (finalStates.contains(state))
                    return new Pair<>(isSubstring, subStringLength);
            }
            subStringLength++;
            isSubstring = true;
            startPosition++;
        }
        return new Pair<>(isSubstring, subStringLength);
    }
}

