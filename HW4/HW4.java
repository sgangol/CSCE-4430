/***********************************************************************
     CSCE 4430:     Programing Languages                              **
     ASSIGNMENT:    Assignment 4                                      **
     AUTHOR:        Srizan Gangol                                     **
     DESCRIPTION:   HW4.java                                          **
                    A recursive-descent syntax analyzer for           **
                    MicroScala which will parse programsaccording to  **
                    the prescribed grammar                            **
     DEPENDENCIES:  MicroScalaLexer.java                              **
     USUAGE:        $ jflex MicroScala.jflex                          **
                    $ javac MicroScalaLexer.java                      **
                    $ javac HW4.java AbstractSyntaxTree.java          **
                    $ java HW4 < Test_.scala                          **
                                                                      **
 ***********************************************************************/

public class HW4 {

public static void main (String args []) throws java.io.IOException {

  System.out.println ("\n|Scala File|\n");

  ParserAST parser = new ParserAST ();
  Program program = parser . program ();
  System . out . println (program);

  /*
  // Lexer Begins --------------------- HW2----------------------
  int i, n;
  Token [] token = new Token [MAX_TOKENS];
  MicroScalaLexer lexer = new MicroScalaLexer (System . in);


  n = -1;
  do {
    if (n < MAX_TOKENS)
      token [++n] = lexer . next_token ();
    else

  System.out.println ("\n\n\n");
  System.out.println ("--------");
  System.out.println ("|Tokens|");
  System.out.println ("--------");

  for (i = 0; i < n; i++)
    System.out.println (token [i]);
  System.out.println ();
  System.out.println (">>>> End of the Program <<<<\n\n");
  //-------------------------------------------------------------
  */

  /*
  // SyntaxAnalyzer Begins------------- HW3----------------------
  SyntaxAnalyzer microscala = new SyntaxAnalyzer ();
  microscala . program ();

  System . out . println ();
  System . out . println ("> THE PARSING WAS SUCCESSFUL!!!");
  //-------------------------------------------------------------
  */
}

}
