package madlang;

import org.antlr.v4.runtime.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Error: missing input file.");
            System.err.println("usage: make run FILE=filename.madl");
            System.exit(1);
            return;
        }

        String source;
        try {
            source = Files.readString(Path.of(args[0]));
        } catch (IOException e) {
            System.err.println("Error: failed to read input file: " + args[0]);
            System.exit(1);
            return;
        }



        // 1. Feed source to ANTLR
        CharStream input = CharStreams.fromString(source);

        // Create error listener for better debugging
        MadLangErrorListener errorListener = new MadLangErrorListener();

        // 2. Lex
        MadLangLexer lexer = new MadLangLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 3. Parse
        MadLangParser parser = new MadLangParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        try {
            MadLangParser.ProgramContext tree = parser.program();
            // 4. Build AST
            ASTBuilder builder = new ASTBuilder();
            List<Stmt> stmts = (List<Stmt>) builder.visit(tree);
            // 5. Interpret
            Interpreter interpreter = new Interpreter();
            interpreter.interpretProgram(stmts);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
