package madlang;

public class LexerException extends RuntimeException {
  final int line;

  public LexerException(String message, int line) {
    super("Lexer error at line " + line + ": " + message);
    this.line = line;
  }
  
}
