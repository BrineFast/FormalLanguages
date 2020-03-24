package deserializers;

import automaton.GroupOfFinalStateMachines;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Deserializer for a group of machines by type.
 * @author Ilya SLepov
 */
public class GroupDeserializer{

    public static List<GroupOfFinalStateMachines> deserialize(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String input = "";
        String line;
        while ((line = reader.readLine()) != null) {
            input += line;
        }
        reader.close();
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> paths = objectMapper.readValue(input, new TypeReference<List<String>>(){});
        List<GroupOfFinalStateMachines> group = new ArrayList<>();
        for (String path : paths){
            File newFile = new File(path);
            group.add(GroupOfFinalStateMachines.createGroup(newFile));
        }
        return group;
    }

}
