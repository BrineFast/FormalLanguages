package util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class for storing two values at the same time.
 * @param <Key>
 * @param <Value>
 * @author Ilya Slepov
 */
@Data
@AllArgsConstructor
public class Pair<Key, Value> {

    public  Boolean Key;
    public  Integer Value;

}
