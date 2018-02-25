/***********************************************************************
     CSCE 4430:     Programing Languages                              **
     ASSIGNMENT:    Assignment 4                                      **
     AUTHOR:        Srizan Gangol                                     **
     DESCRIPTION:   ParserAST.java                                    **
                    Class to represent a recursive descent parser     **
                    & also onstructs AST
***********************************************************************/

import java.util.*;

public class ParserAST {

  protected MicroScalaLexer lexer;       // Micro Scala lexical analyzer
  protected Token token;                // Current token

  public ParserAST () throws java.io.IOException {
    lexer = new MicroScalaLexer (System . in);
    getToken ();
  }


  protected void getToken () throws java.io.IOException {
    token = lexer . next_token ();
  }


  // MicroScala parser
  public Program program () throws java.io.IOException {
    Program function, program = null;
    String funcId = null;
    boolean maindef;
    if (token . symbol () != TokenClass . OBJECT) 	// object
      ErrorMessage . print ("object EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . ID)    	// id
      ErrorMessage . print ("ID EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . LBRACE)    // {
      ErrorMessage . print ("{ EXPECTED");
    getToken ();
    maindef = false;
    while ((token . symbol () == TokenClass . DEF ||
        token . symbol () == TokenClass . VAR) && !maindef) {
      if (token . symbol () == TokenClass . DEF) {  	// def
        getToken ();
        if (token . symbol () == TokenClass . ID) {	// id
          funcId = token . lexeme ();
          getToken ();
          function = functionDef (funcId); 		// function definition
        }
        else if (token . symbol () == TokenClass . MAIN) { // main
          getToken ();
          program = mainDef (); 			// main definition
          maindef = true;
        }
        else
          ErrorMessage . print ("ID EXPECTED");
      }
      else
        varDef (); 					// VarDef
    }
    if (!maindef)
      ErrorMessage . print ("main DEFINITION EXPECTED");
    if (token . symbol () != TokenClass . RBRACE)   // }
      ErrorMessage . print ("} EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . EOF)
      ErrorMessage . print ("END OF PROGRAM EXPECTED");
    return program;
  }

  // mainDef parses MicroScala main definitions.

  public Program mainDef () throws java.io.IOException {
    Program program;
    Statement stmt1, stmt2;
    if (token . symbol () != TokenClass . LPAREN)    // (
      ErrorMessage . print ("( EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . ARGS)         // args
      ErrorMessage . print ("args EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . COLON)        // :
      ErrorMessage . print (": EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . ARRAY)        // Array
      ErrorMessage . print ("Array EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . LBRACKET)  // [
      ErrorMessage . print ("[ EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . STRING)       // String
      ErrorMessage . print ("String EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . RBRACKET) // ]
      ErrorMessage . print ("] EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . RPAREN)   // )
      ErrorMessage . print (") EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . LBRACE)    // {
      ErrorMessage . print ("{ EXPECTED");
    getToken ();
    while (token . symbol () == TokenClass . VAR)  	// { VarDef }
      varDef ();
    stmt1 = statement (); 				// Statement
    while (token . symbol () != TokenClass . RBRACE) { // { Statement }
      stmt2 = statement ();
      stmt1 = new Statement (stmt1, stmt2);
    }
    getToken (); 					// }
    program = new Program ("main", stmt1);
    return program;
  }

  // functionDef parses MicroScala function definitions.

  public Program functionDef (String funcId) throws java.io.IOException {
    Expression listExp;
    Program program;
    Statement returnTree, stmt1, stmt2;
    if (token . symbol () != TokenClass . LPAREN)    // (
      ErrorMessage . print ("( EXPECTED");
    getToken ();
    if (token . symbol () == TokenClass . ID) {         // [ id
      getToken ();
      if (token . symbol () != TokenClass . COLON)      // :
        ErrorMessage . print (": EXPECTED");
      getToken ();
      type (); 						// Type
      while (token . symbol () == TokenClass . COMMA) {	// { ,
        getToken ();
        if (token . symbol () != TokenClass . ID)       // id
          ErrorMessage . print ("id EXPECTED");
        getToken ();
        if (token . symbol () != TokenClass . COLON)    // :
          ErrorMessage . print (": EXPECTED");
        getToken ();
        type (); 					// Type
      } 						// }
    } 							// ]
    if (token . symbol () != TokenClass . RPAREN)   // )
      ErrorMessage . print (") EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . COLON)    	// :
      ErrorMessage . print (": EXPECTED");
    getToken ();
    type (); 						// Type
    if (token . symbol () != TokenClass . ASSIGN)       // =
      ErrorMessage . print ("= EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . LBRACE)    // {
      ErrorMessage . print ("{ EXPECTED");
    getToken ();
    while (token . symbol () == TokenClass . VAR)  	// { VarDef }
      varDef ();
    stmt1 = null;
    while (token . symbol () != TokenClass . RETURN) {	// { Statement }
      stmt2 = statement ();
      if (stmt1 == null)
        stmt1 = stmt2;
      else
        stmt1 = new Statement (stmt1, stmt2);
    }
    getToken (); 					// return
    listExp = listExpr (); 			        // ListExpr
    returnTree = new Return (listExp);
    if (stmt1 == null)
      stmt1 = returnTree;
    else
      stmt1 = new Statement (stmt1, returnTree);
    if (token . symbol () != TokenClass . SEMICOLON)    // ;
      ErrorMessage . print ("; EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . RBRACE)   // }
      ErrorMessage . print ("} EXPECTED");
    program = new Program (funcId, stmt1);
    System . out . println (program);
    getToken ();
    return program;
  }

  // The function parses variable definitions
  public void varDef () throws java.io.IOException {
    Value value;
    // VAR ID:<Type> = Literal ;
    if (token . symbol () != TokenClass . VAR)
      ErrorMessage . print ("var EXPECTED");
    getToken ();

    if (token . symbol () != TokenClass . ID)
      ErrorMessage . print ("id EXPECTED");
    getToken ();
    if (token . symbol () != TokenClass . COLON)
      ErrorMessage . print (": EXPECTED");
    getToken ();
    type ();
    if (token . symbol () != TokenClass . ASSIGN)
      ErrorMessage . print ("= EXPECTED");
    getToken ();
    value = literal ();
    if (token . symbol () != TokenClass . SEMICOLON)
      ErrorMessage . print ("; EXPECTED");
    getToken ();
  }

  // The function parses types
  public void type () throws java.io.IOException {
    // INT | LIST[INT]
    if (token . symbol () == TokenClass . INT)
      getToken ();
    else if (token . symbol () == TokenClass . LIST) {
      getToken ();
      if (token . symbol () != TokenClass . LBRACKET)
        ErrorMessage . print ("[ EXPECTED");
      getToken ();
      if (token . symbol () != TokenClass . INT)
        ErrorMessage . print ("String EXPECTED");
      getToken ();
      if (token . symbol () != TokenClass . RBRACKET)
        ErrorMessage . print ("] EXPECTED");
      getToken ();
    }
    else
      ErrorMessage . print ("TYPE EXPECTED");
  }

  // The function parses statement
  public Statement statement () throws java.io.IOException {
    Expression exp;
    Statement statement = null, stmt1, stmt2;
    Variable id;

    switch (token . symbol ()) {

      case IF :
      // if (EXPR) <Statement>
      // else <statement>
        getToken ();
        if (token . symbol () != TokenClass . LPAREN)
          ErrorMessage . print ("( EXPECTED");
        getToken ();
        exp = expr ();
        if (token . symbol () != TokenClass . RPAREN)
          ErrorMessage . print (") EXPECTED");
        getToken ();
        stmt1 = statement ();
        if (token . symbol () == TokenClass . ELSE) {
	    getToken ();
	    stmt2 = statement ();
        }
        else
          stmt2 = null;
        statement = new Conditional (exp, stmt1, stmt2);
        break;

      case WHILE :
      // while (EXPR) <statement>
        getToken ();
        if (token . symbol () != TokenClass . LPAREN)
          ErrorMessage . print ("( EXPECTED");
        getToken ();
        exp = expr ();
        if (token . symbol () != TokenClass . RPAREN)
          ErrorMessage . print (") EXPECTED");
        getToken ();
        stmt1 = statement ();
        statement = new Loop (exp, stmt1);
        break;

      case ID  :
      // id = listExpr ;
        id = new Variable (token . lexeme ());
        getToken ();
        if (token . symbol () != TokenClass . ASSIGN)
          ErrorMessage . print ("= EXPECTED");
        getToken ();
        exp = listExpr ();
        statement = new Assignment (id, exp);
        if (token . symbol () != TokenClass . SEMICOLON)
          ErrorMessage . print ("; EXPECTED");
        getToken ();
        break;

      case LBRACE :
      // {<statement>}
        getToken ();
        stmt1 = statement ();
        while (token . symbol () != TokenClass . RBRACE) {
          stmt2 = statement ();
          stmt1 = new Statement (stmt1, stmt2);
        }
        statement = stmt1;
        getToken ();
        break;


      case PRINTLN :
      // println (listExpr);
        getToken ();
        if (token . symbol () != TokenClass . LPAREN)
          ErrorMessage . print ("( EXPECTED");
        getToken ();
        exp = listExpr (); 			  	  // ListExpr
        if (token . symbol () != TokenClass . RPAREN)
          ErrorMessage . print (") EXPECTED");
        getToken ();
        statement = new Print (exp);
        if (token . symbol () != TokenClass . SEMICOLON)
          ErrorMessage . print ("; EXPECTED");
        getToken ();
        break;

      default :
        ErrorMessage . print ("STATEMENT EXPECTED");
    }

    return statement;
  }

  // The function parses expressions
  public Expression expr () throws java.io.IOException {
    Expression andExp, exp;
    exp = andExpr ();
    // AndExpr|| AndExpr
    while (token . symbol () == TokenClass . OR) {

      getToken ();
      andExp = andExpr ();
      exp = new Binary ("||", exp, andExp);
    }
    return exp;
  }

  // The function parses expressions

  public Expression andExpr () throws java.io.IOException {
    Expression exp, relExp;
    exp = relExpr ();
    // RelExpr && RelExpr
    while (token . symbol () == TokenClass . AND) {
      getToken ();
      relExp = relExpr ();
      exp = new Binary ("&&", exp, relExp);
    }
    return exp;
  }

  // The function parses relational expression

  public Expression relExpr () throws java.io.IOException {
    boolean notExpr;
    String reloper;
    Expression exp, listExp;
    notExpr = false;
    // !
    if (token . symbol () == TokenClass . NOT) {
      getToken ();
      notExpr = true;
    }
    exp = listExpr ();
    // RelOper
    if (token . symbol () == TokenClass . RELOPER) {
      reloper = token . lexeme ();
      getToken ();
      listExp = listExpr ();
      exp = new Binary (reloper, exp, listExp);
    }
    if (notExpr)
      exp = new Unary ("!", exp);
    return exp;
  }

  // The function parses list exp

  public Expression listExpr () throws java.io.IOException {
    Expression exp, listExp;
    exp = addExpr ();
    // AddExpr :: ListExpr
    if (token . symbol () == TokenClass . CONS) {
      getToken ();
      listExp = listExpr ();
      exp = new Binary ("::", exp, listExp);
    }
    return exp;
  }

  // The function parses addition expression

  public Expression addExpr () throws java.io.IOException {
    Expression exp, mulExp;
    String addoper;
    exp = mulExpr ();
    // MulExpr + MulExpr
    while (token . symbol () == TokenClass . ADDOPER) {
      addoper = token . lexeme ();
      getToken ();
      mulExp = mulExpr ();
      exp = new Binary (addoper, exp, mulExp);
    } 							// }
    return exp;
  }

  // The function parses Mul Expr

  public Expression mulExpr () throws java.io.IOException {
    Expression exp, prefixExp;
    String muloper;
    exp = prefixExpr ();
    // prefixExpr * prefixExpr
    while (token . symbol () == TokenClass . MULOPER) {
      muloper = token . lexeme ();
      getToken ();
      prefixExp = prefixExpr ();
      exp = new Binary (muloper, exp, prefixExp);
    }
    return exp;
  }

  // The function parses prefixExpr expressions.
  public Expression prefixExpr () throws java.io.IOException {
    Expression exp = null;
    String addoper, listop;
    addoper = null;
    if (token . symbol () == TokenClass . ADDOPER) {
      addoper = token . lexeme ();
      getToken ();
    }
    exp = simpleExpr ();
    while (token . symbol () == TokenClass . PERIOD) {
      getToken ();
      if (token . symbol () != TokenClass . LISTOP)
        ErrorMessage . print ("LIST METHOD EXPECTED");
      listop = token . lexeme ();
      getToken ();
      exp = new Unary (listop, exp);
    }
    if (addoper != null)
      exp = new Unary (addoper, exp);
    return exp;
  }

  // The function parses simple expression
  public Expression simpleExpr () throws java.io.IOException {
    ArrayList<Expression> expList;
    Expression exp = null, listExp;
    String id;
    if (token . symbol () == TokenClass . LPAREN) {
      getToken ();
      exp = expr ();
      if (token . symbol () != TokenClass . RPAREN)
        ErrorMessage . print (") EXPECTED");
      getToken ();
    }
    else if (token . symbol () == TokenClass . ID) {
      id = token . lexeme ();
      getToken ();
      if (token . symbol () != TokenClass . LPAREN)
        exp = new Variable (id);
      else {
        getToken ();
        expList = new ArrayList<Expression> ();
        if (token . symbol () != TokenClass . RPAREN)  {
          listExp = listExpr ();
          expList . add (listExp);
          while (token . symbol () == TokenClass . COMMA) {
            getToken ();
            listExp = listExpr ();
            expList . add (listExp);
          }
          if (token . symbol () != TokenClass . RPAREN)
            ErrorMessage . print (") EXPECTED");
        }
        getToken ();
        exp = new FunctionCall (id, expList);
      }
    }
    else
      exp = literal ();
    return exp;
  }

  // The function parses literal
  public Value literal () throws java.io.IOException {
    Value val = null;
    // integer | NIL
    if (token . symbol () == TokenClass . INTEGER) {
      val = new IntValue (token . lexeme ());
      getToken ();
    }
    else if (token . symbol () == TokenClass . NIL) {
      val = new NilValue ();
      getToken ();
    }
    else
      ErrorMessage . print ("LITERAL EXPECTED");
    return val;
  }

}
