// Test4.scala

// Recursive factorial function.

object Test4
{
  def facto (x : Int) : Int =
  {
    var s : Int = 0;
    if (x == 1)
      s = 1;
    else
      s = x * facto (x - 1);
    return s;
  }

  def main (args : Array [String])
  {
    var i : Int = 0;
    var fac : Int = 0;
    i = 4;
    fac = facto (i);
    println (fac);
  }
}
