/***********************************************************************
     CSCE 4430:     Programing Languages                              **
     ASSIGNMENT:    Assignment 3                                      **
     AUTHOR:        Srizan Gangol                                     **
     DESCRIPTION:   TokenClass.java                                   **
                    Representing Lexical Token class in MicroScala    **
***********************************************************************/


public enum TokenClass {
  // EOF
  EOF,
  // IDs
  ID, INTEGER,
  // KEYWORDS
  ARRAY, ARGS,  DEF, ELSE, IF, INT, LIST, MAIN, NIL, OBJECT, PRINTLN, RETURN, STRING, VAR, WHILE,
  // OPERATORS
  LPAREN, RPAREN, ASSIGN, OR, AND, NOT, CONS, RELOPER, ADDOPER, MULOPER, LISTOP,
  // PUNCTUATIONs
  COMMA, PERIOD, SEMICOLON, COLON, LBRACE, RBRACE, LBRACKET, RBRACKET

}
