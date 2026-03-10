package madlang;
import java.util.*;

public class LexerPractice {
  private static final Map<String, TokenType> keywords;

  static {
    keywords = new HashMap<>();
    keywords.put("true", TokenType.TRUE);
    keywords.put("false", TokenType.FALSE);
    keywords.put("fn", TokenType.FN);
    keywords.put("if", TokenType.IF);
    keywords.put("else", TokenType.ELSE);
    keywords.put("while", TokenType.WHILE);
    keywords.put("return", TokenType.RETURN);
    keywords.put("int", TokenType.INT);
    keywords.put("bool", TokenType.BOOL);
  }

  // Fields
  private final String source;
  private final List<TokenPractice> tokens = new ArrayList<>();
  private int start = 0;
  private int current = 0;
  private int line = 1;

  LexerPractice(String source) {
    this.source = source;
  }

  // Helpers
  private void addToken(TokenType type) {
    String text = source.substring(start, current);
    tokens.add(new TokenPractice(type, text, line));
  }

  private boolean isAtEnd() {
    return current >= source.length();
  }

  private char advance() {
    return source.charAt(current++);
  }

  private char peek() {
    if (isAtEnd()) {
      return '\0';
    }
    return source.charAt(current);
  }

  private char peekNext() {
    if (current + 1 >= source.length()) {
      return '\0';
    }
    return source.charAt(current + 1);
  }

  List<TokenPractice> scanTokens() {
    while (!isAtEnd()) {
        start = current;
        scanToken();
    }
    addToken(TokenType.EOF);
    return tokens;
}

  private void scanToken() {
    char c = advance();
    switch (c) {
        case '(':
          addToken(TokenType.LEFT_PAREN);
          break;
        case ')':
          addToken(TokenType.RIGHT_PAREN);
          break;
        case '{':
          addToken(TokenType.LEFT_BRACE);
          break;
        case '}':
          addToken(TokenType.RIGHT_BRACE);
          break;
        case ',':
          addToken(TokenType.COMMA);
          break;
        case ':':
          addToken(TokenType.COLON);
          break;
        case ';':
          addToken(TokenType.SEMICOLON);
          break;
        case '+':
          addToken(TokenType.PLUS);
          break;
        case '-':
          addToken(TokenType.MINUS);
          break;
        case '/':
          if (match('/')) {
            while (peek() != '\n' && !isAtEnd()) {
              advance();
            }
          } else if (match('*')) {
              blockComment();
          } else {
              addToken(TokenType.SLASH);
          } 
          break;
        case '*':
          addToken(TokenType.STAR);
          break;
        case '%':
          addToken(TokenType.PERCENT);
          break;
        
        // Two character keywords
        case '!':
          if (match('=')) {
            addToken(TokenType.BANG_EQUAL);
          } else {
            addToken(TokenType.BANG); 
          }
          break;
        case '=':
          if (match('=')) {
          addToken(TokenType.EQUAL_EQUAL);
          } else {
            addToken(TokenType.EQUAL); 
          }
          break;
        case '>':
          if (match('=')) {
            addToken(TokenType.GREATER_EQUAL);
          } else {
            addToken(TokenType.GREATER); 
          }
          break;
        case '<':
          if (match('=')) {
            addToken(TokenType.LESS_EQUAL);
          } else {
            addToken(TokenType.LESS); 
          }
          break;
        case '&':
          if (match('&')) {
            addToken(TokenType.AND);
          } else {
            throw new LexerException("Single '&' is not valid, did you mean '&&'?", line);
          }
          break;
        case '|':
          if (match('|')) {
            addToken(TokenType.OR);
          } else {
            throw new LexerException("Single '|' is not valid, did you mean '||'?'", line);
          }
          break;

        // Whitespace
        case ' ':
        case '\r':
        case '\t':
          break;
        case '\n':
          line++;
          break;

        default:
          if (isDigit(c)) { // has to be int lit
            intLit();
          } else if (isAlpha(c)) { // has to be identifier
              identifier();
          } else {
            throw new LexerException("Unexpected character '" + c + "'", line);
          }
    }
    
  }
  // Helper to see if next character is expected token
  private boolean match(char expected) {
    if (isAtEnd()) {
      return false;
    }
    if (peek() != expected) {
      return false;
    }

    current++;
    return true;
  }

  // Helper to deal with block comments
  private void blockComment() {
    while (!isAtEnd() && !(peek() == '*' && peekNext() == '/')) { 
        if (peek() == '\n') {
          line++;
        }
        advance();
    }
    // If we've reached the end
    if (isAtEnd()) {
        throw new LexerException("Unterminated comment", line);
    } else {
      advance(); // consume *
      advance(); // consume /
    }
  }

  private void identifier() {
    while (isAlphaNumeric(peek()))
      advance();

    String text = source.substring(start, current);
    TokenType type = keywords.get(text);
    if (type == null)
      type = TokenType.IDENTIFIER;
    addToken(type);
  }

  private void intLit() {
    while (isDigit(peek()))
      advance();

    if (source.charAt(start) == '0' && current - start > 1) {
        throw new LexerException("Invalid integer literal: leading zeros are not allowed", line);
    }

    addToken(TokenType.INT_LIT);
  }

  private boolean isAlpha(char c) {
    return (c >= 'a' && c <= 'z') ||
        (c >= 'A' && c <= 'Z') ||
        c == '_';
  }

  private boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }

  private boolean isAlphaNumeric(char c) {
    return isAlpha(c) || isDigit(c);
  }
}
