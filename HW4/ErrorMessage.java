/***********************************************************************
     CSCE 4430:     Programing Languages                              **
     ASSIGNMENT:    Assignment 4                                      **
     AUTHOR:        Srizan Gangol                                     **
     DESCRIPTION:   ErrorMessage.java                                 **
                    Class for Error Message output                    **
***********************************************************************/

public class ErrorMessage {

  public static void print (String message) {
    System.out.println ("\n\n>>>> Error: " + message + " <<<<\n\n");
    System.exit (0);
  }

  public static void print (int position, String message) {
    System.out.println ();
    for (int i = 0; i < position; i++)
      System.out.print (" ");
    System.out.println ("^");
    print (message);
  }

}
