// Test6.scala

// Two recursive functions operating on lists.

object Test6 {

  def cons_a_list (r : Int, l : List [Int]) : List [Int] =
  {
    var my_list : List [Int] = Nil;
    if (r != 0) {
      my_list = r :: my_list;
      my_list = cons_a_list (r - 1, my_list);
    }
    return my_list;
  }

  def equal (list1 : List [Int], list2 : List [Int]) : Int =
  {
    var l1 : List [Int] = Nil;
    var l2 : List [Int] = Nil;
    var my_flag : Int = 0;
    l1 = list1; l2 = list2;
    while (!l1 . isEmpty && !l2 . isEmpty && my_flag == 0) {
      if (l1 . head != l2 . head)
        my_flag=1;
      else {
        l1 = l1 . tail;
        l2 = l2 . tail;
        my_flag = equal (l1, l2);
      }
    }
    if (l1 . isEmpty && l2 . isEmpty)
      my_flag = 0;
    else
      my_flag = 1;
    return my_flag;
  }

  def main (args : Array [String])
  {
    var r : Int = 0; 
    var h : Int = 0;
    var l1 : List [Int] = Nil; 
    var l2 : List [Int] = Nil; 
    var l3 : List [Int] = Nil;
    var my_list : List [Int] = Nil;
    r = 10;
    l1 = cons_a_list (r, my_list);    
    l2 = cons_a_list (r, my_list);  
    r = r - 1;  
    l3 = cons_a_list (r, my_list);  
    if (equal (l1, l2) == 0)
      h = 1; 
    else 
      h = 0;
    if (equal (l1, l3) != 0) 
      r = 1;
    else 
      r = 0;
    println (h);
    println (r);
  }

}
