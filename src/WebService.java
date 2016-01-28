import static spark.Spark.*;

import main.org.jackace.jawascriptparser.AST;
import main.org.jackace.jawascriptparser.Parser;
import main.org.jackace.jawascriptparser.Token;
import main.org.jackace.jawascriptparser.Tokenizer;
import main.org.jackace.jawascriptparser.Serializer;
import main.org.jackace.jawascriptparser.UnexpectedTokenException;

import java.util.ArrayList;

/**
 * Created by jackace on 2016/1/5.
 */
public class WebService {
    public static void main(String[] args) {
        post("/parse", (request, response) -> {
            Tokenizer tn = new Tokenizer();
            String program = request.body();
            try {
                ArrayList<Token> tokens = tn.tokenize(program);
                Parser ps = new Parser();
                AST tree = ps.parse(tokens);
                String output = Serializer.toJSON(tree);
                return output;
            } catch (UnexpectedTokenException e) {
                response.status(400);
                return e.toString();
            }
        });

        get("/ping", (request, response) -> {
           return "";
        });
    }
}
