/*----------------------------------------------------------------
  Author: Srizan Gangol Course:CSCE4430 Homework#6 Title:04.pro
  ----------------------------------------------------------------
  A Prolog predicate delete(Atom, List1, List2) that deletes all 
  occurrences, no matter how deep, of Atom in List1 and returns 
  the result in List2. The returned list will not contain anything 
  in place of the deleted atoms. 
  
  USUAGE:
  $ gprolog
  ?- consult('04').
  ?- deleteAtom(b, [no, bs, here], List).  
-----------------------------------------------------------------*/
deleteAtom(Atom, [], []).
deleteAtom(Atom, [Atom | Tail], NewTail) :-
atom(Atom), deleteAtom(Atom, Tail, NewTail).
deleteAtom(Atom1, [Atom2 | Tail], [Atom2 | NewTail]) :-
atom(Atom2), \+ =(Atom1, Atom2), deleteAtom(Atom1, Tail, NewTail).
deleteAtom(Atom, [Head | Tail], [NewHead | NewTail]) :-
\+ atom(Head), deleteAtom(Atom, Head, NewHead),
deleteAtom(Atom, Tail, NewTail).