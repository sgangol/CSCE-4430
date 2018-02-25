// Test1.scala

// Compute quotient and remainder by repeated subtraction.

object Test1
{
  def main (args : Array [String]) {
    var q : Int = 0;
    var r : Int = 0;
    var x : Int = 0;
    var y : Int = 0;
    x = 32; y = 5;
    r = x;
    while (r >= y)
    {  
      q = q + 1;
      r = r - y;
    }  
    println (q);
    println (r);
  }
}
