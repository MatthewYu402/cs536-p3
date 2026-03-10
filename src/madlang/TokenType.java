package madlang;

enum TokenType {
  // Single character tokens
  LEFT_PAREN, RIGHT_PAREN, 
  LEFT_BRACE, RIGHT_BRACE, COMMA, COLON, SEMICOLON,
  
  PLUS, MINUS, SLASH, STAR, PERCENT,

  // One or two more character tokens
  AND, OR,
  BANG, BANG_EQUAL,
  EQUAL, EQUAL_EQUAL,
  GREATER, GREATER_EQUAL,
  LESS, LESS_EQUAL,

  // Literals
  IDENTIFIER, INT_LIT,

  // Booleans
  TRUE, FALSE,

  // Keywords
  FN, IF, ELSE, WHILE, RETURN, 
  
  // Type Keywords
  INT, BOOL,

  // End of file
  EOF
}
