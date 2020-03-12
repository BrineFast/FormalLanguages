import automaton.Deserializer;
import automaton.FinalStateMachine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static String JSON_INPUT_DIRECTIVE = "F:\\FormalLanguages\\src\\main\\resources\\fsm.json";

    public static void main(String[] args) throws IOException {

        //Json input
        Gson gson =
                new GsonBuilder()
                        .enableComplexMapKeySerialization()
                        .registerTypeAdapter(FinalStateMachine.class, new Deserializer())
                        .create();
        File file = new File(JSON_INPUT_DIRECTIVE);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String input = "";
        String line;
        while ((line = reader.readLine()) != null) {
            input += line;
        }
        reader.close();

        FinalStateMachine fsm = gson.fromJson(input, FinalStateMachine.class);
        Scanner in = new Scanner(System.in);
        System.out.print("Input a required string: ");
        String requiredString = in.next();
        System.out.print("Input the initial position: ");
        int initialPosition = in.nextInt();
        in.close();
        System.out.println(fsm.maxString(requiredString, initialPosition));
    }

}
