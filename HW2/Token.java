/***********************************************************************
     CSCE 4430:     Programing Languages                              **
     ASSIGNMENT:    Assignment 2                                      **
     AUTHOR:        Srizan Gangol                                     **
     DESCRIPTION:   Token.java                                        **
                    Class for Token Definition                        **
     DEPENDENCIES:  TokenClass.java                                   **
***********************************************************************/


public class Token {

  private TokenClass symbol;	   // CURRENT
  private String lexeme;

  public Token () { }

  public Token (TokenClass symbol) {
    this (symbol, null);
  }

  public Token (TokenClass symbol, String lexeme) {
    this . symbol = symbol;
    this . lexeme  = lexeme;
  }

  public TokenClass symbol () { return symbol; }

  public String lexeme () { return lexeme; }

  public String toString () {
    switch (symbol) {
      case ID :           return "(identifier, " + lexeme + ") ";
      case INTEGER :      return "(integer, " + lexeme + ") ";

      case ARGS :         return "(keyword, args) ";
      case ARRAY :        return "(keyword, Array) ";
      case DEF :          return "(keyword, def) ";
      case ELSE :         return "(keyword, else) ";
      case IF :           return "(keyword, if) ";
      case INT :          return "(keyword, Int) ";
      case LIST :         return "(keyword, List) ";
      case MAIN :         return "(keyword, main) ";
      case NIL :          return "(keyword, Nil) ";
      case OBJECT :       return "(keyword, object) ";
      case PRINTLN :      return "(keyword, println) ";
      case RETURN :       return "(keyword, return) ";
      case STRING :       return "(keyword, String) ";
      case VAR :          return "(keyword, var) ";

      case LPAREN :       return "(operator, () ";
      case RPAREN :       return "(operator, )) ";
      case ASSIGN :       return "(operator, =) ";
      case OR :           return "(operator, ||) ";
      case AND :          return "(operator, &&) ";
      case NOT :          return "(operator, !) ";
      case CONS :         return "(operator, ::) ";
      case RELOPER :      return "(operator, " + lexeme + ") ";
      case ADDOPER :      return "(operator, " + lexeme + ") ";
      case MULOPER :      return "(operator, " + lexeme + ") ";
      case LISTOP :       return "(operator, " + lexeme + ") ";

      case COMMA :        return "(punctuation, ,) ";
      case PERIOD :       return "(punctuation, .) ";
      case SEMICOLON :    return "(punctuation, ;) ";
      case COLON :        return "(punctuation, :) ";
      case LBRACE :       return "(punctuation, {) ";
      case RBRACE :       return "(punctuation, }) ";
      case LBRACKET :     return "(punctuation, [) ";
      case RBRACKET :     return "(punctuation, ]) ";

      case WHILE :        return "(keyword, while) ";
      default :           ErrorMessage . print (0, "Unrecognized token");
                          return null;
    }
  }

}
