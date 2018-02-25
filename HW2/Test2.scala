// Test2.scala

// List manipulatioon operators.

object Test2
{
  def main (args : Array [String]) {
    var my_list : List [Int] = Nil;
    var my_list_tl : List [Int] = Nil;
    var r : Int = 0;
    var h : Int = 0;
    r = 2;
    while (r < 5)
    {  
      my_list = r :: my_list;
      r = r + 1;
    }  
    h = my_list . head;
    my_list_tl = my_list . tail;
    println (h);
    println (my_list_tl . head);
  }
}
 
