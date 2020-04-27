package automaton;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lexer.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Group of all machines with the same type.
 * @author Ilya Slepov
 */
@Data
@AllArgsConstructor
public class GroupOfFinalStateMachines {

    private List<FinalStateMachine> finalStateMachineList;
    private String type;

    /**
     * Creating a group from JSON.
     * @param file
     * @return
     * @throws IOException
     */
    public static GroupOfFinalStateMachines createGroup(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String input = "";
        String line;
        while ((line = reader.readLine()) != null) {
            input += line;
        }
        reader.close();
        ObjectMapper objectMapper = new ObjectMapper();
        GroupOfFinalStateMachines group =
                new GroupOfFinalStateMachines(objectMapper.readValue(input, new TypeReference<List<FinalStateMachine>>(){}), file.getName().substring(0,file.getName().length() - 5));
        for (int i = 0; i < group.getFinalStateMachineList().size(); i++){
            group.getFinalStateMachineList().get(i).resetMachine();
        }
        return group;
    }

    /**
     * Checking whether the entered word belongs to one of the machines.
     * @param inputString
     * @param group
     * @param startPosition
     * @return
     */
    public static Token maxString(String inputString, List<GroupOfFinalStateMachines> group, int startPosition) {
        List<Token> tokens = new ArrayList<>();
        int currentPriority = -1;
        int currentLength = 0;
        for (GroupOfFinalStateMachines group1 : group) {
            for (FinalStateMachine fsm : group1.getFinalStateMachineList()) {
                Pair<Boolean, Integer> result = fsm.maxString(inputString, startPosition);
                if (result.getKey() && (result.getValue() >= currentLength || (result.getValue() < currentLength && fsm.getPriority() > currentPriority))) {
                    tokens.add(new Token(group1.getType(), inputString.substring(startPosition, startPosition + result.getValue()), "good"));
                    currentLength = result.getValue();
                    currentPriority = fsm.getPriority();
                }
            }
        }
        if (!tokens.isEmpty())
            return tokens.get(tokens.size()-1);
        return null;
    }

}
