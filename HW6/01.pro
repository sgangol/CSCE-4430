/*----------------------------------------------------------------
  Author: Srizan Gangol Course:CSCE4430 Homework#6 Title:01.pro
  ----------------------------------------------------------------
  A Prolog predicate expt(M, N, E), where M, N and E are integers, 
  N >= 0, and E = M^N.
  
  USUAGE:
  $ gprolog
  ?- consult('01').
  ?- expt(10,0,E).
----------------------------------------------------------------*/

expt(M, 0, 1).
expt(M, N, E) :- N >= 0, N_1 is N - 1, expt(M, N_1, E_1), E is M * E_1.