package automaton;

import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import util.Triplet;

import java.lang.reflect.Type;
import java.util.*;

/**
 * Custom Deserializer for creating a final state machine. *
 * @author Ilya SLepov
 */

public class Deserializer implements JsonDeserializer<FinalStateMachine> {

    @Override
    public FinalStateMachine deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jdc) throws JsonParseException {

        JsonObject json = jsonElement.getAsJsonObject();

        Set<String> alphabet = jdc.deserialize(json.getAsJsonArray("alphabet"), Set.class);
        Set<String> startStates = jdc.deserialize(json.getAsJsonArray("start"), Set.class);
        Set<String> states = jdc.deserialize(json.getAsJsonArray("states"), Set.class);
        Set<String> finalStates = jdc.deserialize(json.getAsJsonArray("final"), Set.class);
        List<LinkedTreeMap> matrix = jdc.deserialize(json.getAsJsonArray("matrix"), List.class);

        List<Triplet> transitions = new ArrayList<>();
        matrix.forEach(triplet -> {
            Triplet triplet1 = Triplet
                    .builder()
                    .by(triplet.get("by").toString())
                    .from(triplet.get("from").toString())
                    .to((List<String>)triplet.get("to"))
                    .build();
            transitions.add(triplet1);
        });

        return FinalStateMachine.builder()
                .alphabet(alphabet)
                .startStates(startStates)
                .states(states)
                .finalStates(finalStates)
                .transitions(transitions)
                .currentStates(startStates)
                .build();
    }
}
