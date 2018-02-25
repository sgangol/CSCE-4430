/***********************************************************************
     CSCE 4430:     Programing Languages                              **
     ASSIGNMENT:    Assignment 3                                      **
     AUTHOR:        Srizan Gangol                                     **
     DESCRIPTION:   HW3.java                                          **
                    A recursive-descent syntax analyzer for           **
                    MicroScala which will parse programsaccording to  **
                    the prescribed grammar                           **
     DEPENDENCIES:  MicroScalaLexer.java                              **
     USUAGE:        $ jflex MicroScala.jflex                          **
                    $ javac MicroScalaLexer.java                      **
                    $ javac HW3.java                                  **
                    $ java HW3 < Test_.scala                          **
                                                                      **
 ***********************************************************************/
public class HW3 {

//  private static final int MAX_TOKENS = 5000;

  public static void main (String args []) throws java.io.IOException {
/*
    int i, n;
    Token [] token = new Token [MAX_TOKENS];
    MicroScalaLexer lexer = new MicroScalaLexer (System . in);
*/
    System.out.println ("\n\n\n");
    System.out.println ("------------");
    System.out.println ("|Scala File|");
    System.out.println ("------------");
    System.out.println ();
/*
    n = -1;
    do {
      if (n < MAX_TOKENS)
        token [++n] = lexer . next_token ();
      else
	ErrorMessage . print (0, "MAX TOKEN length exceeded!");
    } while (token [n] . symbol () != TokenClass . EOF);

    System.out.println ("\n\n\n");
    System.out.println ("--------");
    System.out.println ("|Tokens|");
    System.out.println ("--------");

    for (i = 0; i < n; i++)
      System.out.println (token [i]);
    System.out.println ();
    System.out.println (">>>> End of the Program <<<<\n\n");
*/

    SyntaxAnalyzer microscala = new SyntaxAnalyzer ();
    microscala . program ();

    System . out . println ();
    System . out . println ("> THE PARSING WAS SUCCESSFUL!!!");
  }

}
