package madlang;

public class TokenPractice {
  final TokenType type;
  final String lexeme;
  final int line;

  TokenPractice(TokenType type, String lexeme, int line) {
    this.type = type;
    this.lexeme = lexeme;
    this.line = line;
  }

  @Override
  public String toString() {
      return type + " " + lexeme;
  }

  
}
