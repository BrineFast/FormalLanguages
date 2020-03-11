import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {
        Gson gson =
                new GsonBuilder()
                        .setPrettyPrinting()
                        .enableComplexMapKeySerialization()
                        .registerTypeAdapter(FinalStateMachine.class, new FSMDeserializer())
                        .create();
        File automaton = new File("D:\\Study\\FormalLang\\src\\main\\resources\\serializeFSM.txt");
        BufferedReader reader = new BufferedReader(new FileReader(automaton));
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
