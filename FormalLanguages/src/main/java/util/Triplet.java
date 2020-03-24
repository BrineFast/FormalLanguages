package util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Class to store rows of the transition matrix.
 * @author Ilya Slepov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Triplet {

    public String from;
    public String by;
    public List<String> to;

}
