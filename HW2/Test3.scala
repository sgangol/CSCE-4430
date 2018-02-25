// Test3.scala

// Non-recursive function.

object Test3
{
  var h : Int = 0;  // global variable

  def area (x : Int, y : Int) : Int =
  {
     var z : Int = 0;
     z = 2 * (x * y + (x * h) + y * h);
     return z;
  }

  def main (args : Array [String]) {
    var a : Int = 0; 
    var b : Int = 0; 
    var s : Int = 0;
    a = 3; b = 4;
    h = 5;
    s = area (a, b);
    println (s);
  }
}
