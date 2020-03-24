package lexer;

import automaton.GroupOfFinalStateMachines;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Lexer {

    /**
     * Splitting a string into tokens.
     * @param inputString
     * @param group
     * @return
     */
    public static List<Token> tokenize(String inputString, List<GroupOfFinalStateMachines> group) {
        List<Token> tokens = new ArrayList<>();
        int pos = 0;
        while (pos <= inputString.length()) {
            Token token = GroupOfFinalStateMachines.maxString(inputString, group, pos);
            if (token != null) {
                tokens.add(token);
                pos += token.getValue().length();
            } else return tokens;
        }
        return tokens;
    }
}
