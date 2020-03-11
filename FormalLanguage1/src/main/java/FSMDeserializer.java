import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javafx.util.Pair;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

/**
 * Custom JSON deserializer
 *
 * @author Ilya Slepov
 */
public class FSMDeserializer implements JsonDeserializer<FinalStateMachine> {

    // simple JSON parsing
    @Override
    public FinalStateMachine deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        JsonObject json = jsonElement.getAsJsonObject();

        //All possible states of the machine
        Set<String> states = jdc.deserialize(json.getAsJsonArray("states"), Set.class);

        //Machine alphabet
        Map<String, String> alphabet = jdc.deserialize(json.getAsJsonArray("alphabet"), Set.class);

        //All possible initial states of the machine
        Set<String> initialStates = jdc.deserialize(json.getAsJsonArray("start"), Set.class);

        //All possible final states of the machine
        Set<String> finalStates = jdc.deserialize(json.getAsJsonArray("final"), Set.class);

        //Transition matrix
        Map<Pair<String, String>, String> transition = jdc.deserialize(json.getAsJsonArray("matrix"),
                new TypeToken<Map<Pair<String, String>, String>>() {}.getType());

        FinalStateMachine finalStateMachine = new FinalStateMachine(alphabet, states, initialStates, finalStates, transition);

        return finalStateMachine;
    }
}
