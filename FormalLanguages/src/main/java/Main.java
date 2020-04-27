import automaton.GroupOfFinalStateMachines;
import automaton.FinalStateMachine;
import deserializers.GroupDeserializer;
import lexer.Lexer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static String JSON_INPUT_DIRECTIVE = "F:\\FormalLanguages\\src\\main\\resources\\group.json";

    public static void main(String[] args) throws IOException {

        File file = new File(JSON_INPUT_DIRECTIVE);
        List<GroupOfFinalStateMachines> group = GroupDeserializer.deserialize(file);

        Scanner inputString = new Scanner(System.in);
        System.out.print("Input a required string: ");
        String requiredString = inputString.nextLine();
        inputString.close();
        System.out.println(Lexer.tokenize(requiredString,group));

    }

}
