import automaton.GroupOfFinalStateMachines;
import deserializers.GroupDeserializer;
import lexer.Lexer;
import lexer.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * FSM tests.
 * @author Ilya Slepov
 */
public class Tests {

    File file = new File(Main.JSON_INPUT_DIRECTIVE);

    /**
     * Enters a keyword and a space.
     * And `while` is also represented as a variable,
     * but it has priority 1 in the variable, and priority 2 in the keyword.
     */
    @Test
    public void positiveLexerTest() throws IOException {

        List<GroupOfFinalStateMachines> group = GroupDeserializer.deserialize(file);
        String inputString= "while begin";
        List<Token> tokens = Lexer.tokenize(inputString, group);

        Assertions.assertEquals("keyword", tokens.get(0).getType());
        Assertions.assertEquals("whitespace", tokens.get(1).getType());
        Assertions.assertEquals("keyword", tokens.get(2).getType());
    }

    /**
     * Enters a non-existing keyword.
     * @throws IOException
     */
    @Test
    public void negativeLexerTest() throws IOException{

        List<GroupOfFinalStateMachines> group = GroupDeserializer.deserialize(file);
        String inputString= "whole";
        List<Token> tokens = Lexer.tokenize(inputString, group);

        Assertions.assertEquals(true, tokens.isEmpty());

    }
}