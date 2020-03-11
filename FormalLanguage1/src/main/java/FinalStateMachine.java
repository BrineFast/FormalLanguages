import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.util.Pair;

import java.util.*;

/**
 * Final state machine
 * @author Ilya Slepov
 */
public class FinalStateMachine {

    private Map<String, String> alphabet;
    private Set<String> states;
    private Set<String> initialStates;
    private Set<String> finalStates;
    private Map<Pair<String, String>, String> transitionMatrix;
    private Set<String> currentState;

    public FinalStateMachine(){}

    public FinalStateMachine(Map<String, String> alphabet,
                             Set<String> states,
                             Set<String> initialStates,
                             Set<String> finalStates,
                             Map<Pair<String, String>, String> transitionMatrix)
    {
        this.alphabet = alphabet;
        this.states = states;
        this.initialStates = initialStates;
        this.finalStates = finalStates;
        this.transitionMatrix = transitionMatrix;
    }
    public Map<Boolean, Integer> maxString(String initialString, int initialPosition){
        currentState = initialStates;
        String requiredString = "";
        Map<Boolean, Integer> resultMap = new HashMap<>();
        if (initialString.length() == 0 && isFinalState(currentState))
            resultMap.put(true, 0);
        else{
            String stringBuffer = "";
            for (int i = initialPosition; i < initialString.length(); i++){
                stringBuffer += initialString.charAt(i);
                currentState = transition(currentState, initialString.charAt(i));
                if (isFinalState(currentState)){
                    resultMap.put(true, stringBuffer.length());
                    requiredString = stringBuffer;
                }
            }
        }
        return resultMap;
    }

    public Boolean isFinalState(Set<String> finalStates){
        for (String finalState : this.finalStates)
            for (String state : finalStates)
                if (finalState.equals(state))
                    return true;
         return false;
    }

    public Set<String> transition(Set<String> states, char currentInStringPosition){
        Set<String> stringBuffer = new HashSet<>();
        for (String state : states)
            transitionMatrix.entrySet().forEach(pairSetEntry -> {
                if (pairSetEntry.getKey().equals(state)
                        && pairSetEntry.getValue().equals(currentInStringPosition))
                    stringBuffer.add(pairSetEntry.getValue());
            });
        return stringBuffer;
    }

}