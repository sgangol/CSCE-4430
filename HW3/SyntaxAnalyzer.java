/***********************************************************************
     CSCE 4430:     Programing Languages                              **
     ASSIGNMENT:    Assignment 3                                      **
     AUTHOR:        Srizan Gangol                                     **
     DESCRIPTION:   SyntaxAnalyzer.java                               **
                    Representing a recursive descent parser in        **
                    MicroScala                                        **
***********************************************************************/

import java.util.*;

public class SyntaxAnalyzer {

  protected MicroScalaLexer lexer;      // Lexical Analyzer
  protected Token token;                // Current Token

  public SyntaxAnalyzer () throws java.io.IOException {
    lexer = new MicroScalaLexer (System . in);
    getToken ();
  }

  protected void getToken () throws java.io.IOException {
    token = lexer . next_token ();
  }

// Main Program for MicroScala SyntaxAnalyzer Begins ----------
  public void program () throws java.io.IOException {
    String functionID = null;
    boolean mainDefinition;
    // OBJECT ID { --------------------------------------------
    if (token . symbol () != TokenClass . OBJECT)
      ErrorMessage . print ("OBJECT EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . ID)
      ErrorMessage . print ("ID EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . LBRACE)
      ErrorMessage . print ("{ EXPECTED");
    getToken ();
    mainDefinition = false;

    // while (DEF || VAR) & not main definition--------------
    while ((token . symbol () == TokenClass . DEF ||
        token . symbol () == TokenClass . VAR) && !mainDefinition) {
      // IF DEFINITION
      if (token . symbol () == TokenClass . DEF) {
        getToken ();
        // IF ID else IF MAIN
        if (token . symbol () == TokenClass . ID) {
          functionID = token . lexeme ();
          getToken ();
          // Call functionDef()
          functionDef (functionID);
        }
        else if (token . symbol () == TokenClass . MAIN) {
          getToken ();
          // call mainDefinition() -Main Definition
          mainDefinition ();
          mainDefinition = true;
        }
        else
          ErrorMessage . print ("ID EXPECTED");
      }
      else
        // call VarDef ()
        varDef ();
    }
    if (!mainDefinition)
      ErrorMessage . print ("MAIN DEFINITION EXPECTED");
    if (token . symbol () != TokenClass . RBRACE)
      ErrorMessage . print ("} EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . EOF)
      ErrorMessage . print ("END OF PROGRAM EXPECTED");
  }


  // functionDef: Function for function definitions
  // Input Var:   functionID

  public void functionDef (String functionID) throws java.io.IOException {
    // IF ( , ID, :
    if (token . symbol () != TokenClass . LPAREN)
      ErrorMessage . print ("( EXPECTED");
    getToken ();
    if (token . symbol () == TokenClass . ID) {
      getToken ();
    if (token . symbol () != TokenClass . COLON)
      ErrorMessage . print (": EXPECTED");
      getToken ();
      // call function type()
      type ();

      // while ,
      while (token . symbol () == TokenClass . COMMA) {
        getToken ();
        // IF ID :
        if (token . symbol () != TokenClass . ID)
          ErrorMessage . print ("id EXPECTED");
        getToken ();
        if (token . symbol () != TokenClass . COLON)
          ErrorMessage . print (": EXPECTED");
        getToken ();
        type ();
      }
    }
    // Validating PAREN closure
    if (token . symbol () != TokenClass . RPAREN)
      ErrorMessage . print (") EXPECTED");
    getToken ();
    // Validating COLON
    if (token . symbol () != TokenClass . COLON)
      ErrorMessage . print (": EXPECTED");
    getToken ();
    type ();
    if (token . symbol () != TokenClass . ASSIGN)
      ErrorMessage . print ("= EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . LBRACE)
      ErrorMessage . print ("{ EXPECTED");
    getToken ();
    while (token . symbol () == TokenClass . VAR)
      varDef ();
    while (token . symbol () != TokenClass . RETURN)
      statement ();
    getToken ();
    listExp (); 					// listExp
    if (token . symbol () != TokenClass . SEMICOLON)
      ErrorMessage . print ("; EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . RBRACE)
      ErrorMessage . print ("} EXPECTED");
    getToken ();
  }



  // Function to parse Type
  public void type () throws java.io.IOException {
    // INT
    // LIST [INT]
    // ERROR
    if (token . symbol () == TokenClass . INT)
      getToken ();
    else if (token . symbol () == TokenClass . LIST) {
      getToken ();
      if (token . symbol () != TokenClass . LBRACKET)
        ErrorMessage . print ("[ EXPECTED");
      getToken ();
      if (token . symbol () != TokenClass . INT)
        ErrorMessage . print ("STRING EXPECTED");
      getToken ();
      if (token . symbol () != TokenClass . RBRACKET)
        ErrorMessage . print ("] EXPECTED");
      getToken ();
    }
    else
      ErrorMessage . print ("TYPE EXPECTED");
  }


  // Function to parse List Expression
  public void listExp () throws java.io.IOException {
    addExp ();
    if (token . symbol () == TokenClass . CONS) {
      getToken ();
      listExp ();
    }
  }

    // Function to parse Add Operand
    public void addExp () throws java.io.IOException {
      multExp ();
      while (token . symbol () == TokenClass . ADDOPER) {
        getToken ();
        multExp ();
      }
    }

    // FUnction to parse Multiplication Expression
    public void multExp () throws java.io.IOException {
      prefixExp ();
      while (token . symbol () == TokenClass . MULOPER) {
        getToken ();
        prefixExp ();
      } 							// }
    }


    // Function to parse Prefix Expression
    public void prefixExp () throws java.io.IOException {
      // + simpleExp()
      //
      if (token . symbol () == TokenClass . ADDOPER)
        getToken ();
      simpleExp ();
      while (token . symbol () == TokenClass . PERIOD) {
        getToken ();
        if (token . symbol () != TokenClass . LISTOP)
          ErrorMessage . print ("LIST METHOD EXPECTED");
        getToken ();
      } 							// }
    }


    // Function to Parse Simple Expression
    public void simpleExp () throws java.io.IOException {
      //(Expression)
      // ID ()
      if (token . symbol () == TokenClass . LPAREN) {
        getToken ();
        expr (); 			 			// Expr
        if (token . symbol () != TokenClass . RPAREN)
          ErrorMessage . print (") EXPECTED");
        getToken ();
      }
      else if (token . symbol () == TokenClass . ID) {
        getToken ();
        if (token . symbol () == TokenClass . LPAREN) {
          getToken ();
          if (token . symbol () != TokenClass . RPAREN)  {
            listExp ();					// listExp
            while (token . symbol () == TokenClass . COMMA) {
              getToken ();
              listExp ();
            }
            if (token . symbol () != TokenClass . RPAREN)
              ErrorMessage . print (") EXPECTED");
          }
          getToken ();
        }
      }
      else
        literal ();
    }

    // Function to Parse Expressions
    public void expr () throws java.io.IOException {
      andExpr ();
      while (token . symbol () == TokenClass . OR) {
        getToken ();
        andExpr ();
      }
    }


    // Function to Parse AND
    public void andExpr () throws java.io.IOException {
      relExpr ();
      while (token . symbol () == TokenClass . AND) {
        getToken ();
        relExpr ();
      }
    }


    // Function to Parse relational Expressions
    public void relExpr () throws java.io.IOException {
      // ! listExp RelOPER listExp
      if (token . symbol () == TokenClass . NOT)
        getToken ();
      listExp (); 					// listExp
      if (token . symbol () == TokenClass . RELOPER) {
        getToken ();
        listExp ();
      }
    }

  // mainDefinition parses MicroScala main definitions.
  // Function to Parse Main Definition
  public void mainDefinition () throws java.io.IOException {
    // (Argument:ARRAY [STRING]){Statement}
    if (token . symbol () != TokenClass . LPAREN)
      ErrorMessage . print ("( EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . ARGS)
      ErrorMessage . print ("args EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . COLON)
      ErrorMessage . print (": EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . ARRAY)
      ErrorMessage . print ("Array EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . LBRACKET)
      ErrorMessage . print ("[ EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . STRING)
      ErrorMessage . print ("String EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . RBRACKET)
      ErrorMessage . print ("] EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . RPAREN)
      ErrorMessage . print (") EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . LBRACE)
      ErrorMessage . print ("{ EXPECTED");
    getToken ();
    while (token . symbol () == TokenClass . VAR)
      varDef ();
    statement ();
    while (token . symbol () != TokenClass . RBRACE)
      statement ();
    getToken ();
  }

  //  Function to parse variable definitions
  public void varDef () throws java.io.IOException {
    // VAR ID: TYPE = Literal ;
    if (token . symbol () != TokenClass . VAR)
      ErrorMessage . print ("VARIABLE EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . ID)
      ErrorMessage . print ("ID EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . COLON)
      ErrorMessage . print (": EXPECTED");
    getToken ();
    type ();
    if (token . symbol () != TokenClass . ASSIGN)
      ErrorMessage . print ("= EXPECTED");
    getToken ();
    literal ();
    if (token . symbol () != TokenClass . SEMICOLON)
      ErrorMessage . print ("; EXPECTED");
    getToken ();
  }


  // Function to Parse Statement {Statement}
  public void statement () throws java.io.IOException {

    switch (token . symbol ()) {

      case IF :
      // IF (Expression) Statement ELSE Statement
        getToken ();
        if (token . symbol () != TokenClass . LPAREN)
          ErrorMessage . print ("( EXPECTED");
        getToken ();
        expr ();
        if (token . symbol () != TokenClass . RPAREN)
          ErrorMessage . print (") EXPECTED");
        getToken ();
        statement ();
        if (token . symbol () == TokenClass . ELSE) {
	    getToken ();
	    statement ();
        }
        break;

      case WHILE :
      // While (Expression) Statement
        getToken ();
        if (token . symbol () != TokenClass . LPAREN)
          ErrorMessage . print ("( EXPECTED");
        getToken ();
        expr ();
        if (token . symbol () != TokenClass . RPAREN)
          ErrorMessage . print (") EXPECTED");
        getToken ();
        statement ();
        break;

      case ID  :
        // ID = EXPRESSION;
        // Validating ID is followed by = then expression then end by ;
        getToken ();
        if (token . symbol () != TokenClass . ASSIGN)
          ErrorMessage . print ("= EXPECTED");
        getToken ();
        listExp ();
        if (token . symbol () != TokenClass . SEMICOLON)
          ErrorMessage . print ("; EXPECTED");
        getToken ();
        break;

      case PRINTLN :
        // PRINTLN ( -> listExp() -> ) ;
        // Validating PRINTLN is followed by ( then expressions then )
        getToken ();
        if (token . symbol () != TokenClass . LPAREN)
          ErrorMessage . print ("( EXPECTED");
        getToken ();
        listExp ();
        if (token . symbol () != TokenClass . RPAREN)
          ErrorMessage . print (") EXPECTED");
        getToken ();
        if (token . symbol () != TokenClass . SEMICOLON)
          ErrorMessage . print ("; EXPECTED");
        getToken ();
        break;

      case LBRACE :
       	// Case LBRACE run until it gets RBRACE
        getToken ();
        statement ();
        while (token . symbol () != TokenClass . RBRACE) {
          statement ();
        }
        getToken ();
        break;

      default :
        ErrorMessage . print ("STATEMENT EXPECTED");
    }

  }

  // literal parses literals.

  public void literal () throws java.io.IOException {
    // if integer else if Nil else ERROR
    if (token . symbol () == TokenClass . INTEGER)
      getToken ();
    else if (token . symbol () == TokenClass . NIL)
      getToken ();
    else
      ErrorMessage . print ("LITERAL EXPECTED");
  }

}
