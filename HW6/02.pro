/*----------------------------------------------------------------
  Author: Srizan Gangol Course:CSCE4430 Homework#6 Title:02.pro
  ----------------------------------------------------------------
  A Prolog predicate hextodec(H, D), where H is a list of 
  hexadecimal digits and D is the equivalent decimal number
  
   USUAGE:
  $ gprolog
  ?- consult('02').
  ?- hextodec([a],D).
----------------------------------------------------------------*/
hexdigittodec(a, 10).
hexdigittodec(b, 11).
hexdigittodec(c, 12).
hexdigittodec(d, 13).
hexdigittodec(e, 14).
hexdigittodec(f, 15).
hexdigittodec(HexDigit, HexDigit) :- \+ HexDigit < 0, \+ HexDigit > 9.

/*---------------------------------------
Convert a list of hexadecimal digits 
into decimal number.
---------------------------------------*/

hextodec([HexDigit], Dec) :- hexdigittodec(HexDigit, Dec).
hextodec([HexDigit | HexDigits], Dec) :-
hexdigittodec(HexDigit, Dec1), length(HexDigits, NumberRemainingDigits),
expt(16, NumberRemainingDigits, Power16),
hextodec(HexDigits, Dec3), Dec is Dec1 * Power16 + Dec3.