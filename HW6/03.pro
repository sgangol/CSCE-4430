/*----------------------------------------------------------------
  Author: Srizan Gangol Course:CSCE4430 Homework#6 Title:03.pro
  ----------------------------------------------------------------
  A Prolog predicate perfect(N, F),  determines if integer N is 
  perfect and if so, return it's list of factors F. If N is not 
  perfect, the predicate fails.
  
  USUAGE:
  $ gprolog
  ?- consult('03').
  ?- perfect(6,Factors).
----------------------------------------------------------------*/


perfect(N, Factors) :-
divisible(N, 2),
Ndiv2 is N // 2,
factors(N, Ndiv2, Factors),
sumlist(Factors, N).
perfect(N, Factors) :-
\+ divisible(N, 2),
Ndiv2 is (N - 1) // 2,
factors(N, Ndiv2, Factors),
sumlist(Factors, N).
perfect(N, nil).

/*---------------------------------------
RETURN true if N is evenly divisible by M
---------------------------------------*/

divisible(N, M) :- ModNM is N mod M, ModNM = 0.

/*---------------------------------------
RETURN all factors of N up to M.
---------------------------------------*/

factors(N, 1, [1]).
factors(N, M, Factors) :-
M > 1,
divisible(N, M),
Mminus1 is M - 1,
factors(N, Mminus1, Factors1),
append(Factors1, [M], Factors).
factors(N, M, Factors) :-
M > 1,
\+ divisible(N, M),
Mminus1 is M - 1,
factors(N, Mminus1, Factors).

/*---------------------------------------
Compute sum of a list
---------------------------------------*/

sumlist([N], N).
sumlist([Number | Numberlist], Sum) :-
sumlist(Numberlist, Sum1),
Sum is Number + Sum1.