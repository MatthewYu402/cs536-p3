package madlang;

import org.antlr.v4.runtime.*;

public class MadLangErrorListener extends BaseErrorListener {

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
            Object offendingSymbol,
            int line,
            int charPositionInLine,
            String msg,
            RecognitionException e) {
        throw new RuntimeException("Parse error at line " + line + ", column, " + charPositionInLine + ": " + msg);
    }
}
