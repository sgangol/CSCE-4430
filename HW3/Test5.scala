// Test5.scala

// A simple recursive function on lists.

object Test5 {

  var my_list : List [Int] = Nil;

  def cons_my_list (r : Int) : List [Int] =
  {
    if (r <= 10) {
      my_list = r :: my_list;
      my_list = cons_my_list (r + 1);
    }
    return my_list;
  }

  def main (args : Array [String])
  {
    var r : Int = 0;
    r = 1;
    my_list = Nil;
    my_list = cons_my_list (r);
    println (my_list . head);
  }

}
