package lexer;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class for storing information on the token.
 */
@Data
@AllArgsConstructor
public class Token {

    private String type;
    private String value;
    private String status;
}
