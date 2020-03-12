package util;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Class to store rows of the transition matrix.
 * @author Ilya Slepov
 */
@Data
@Builder
public class Triplet {

    public String from;
    public String by;
    public List<String> to;

}
