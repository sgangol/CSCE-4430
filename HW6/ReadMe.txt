
##################################################################
CSCE 4430:   Programing Languages
ASSIGNMENT:  Assignment 6
AUTHOR:      Srizan Gangol
DESCRIPTION: Contains 4 ProLog files & 1 java file
		01.pro
		02.pro
		03.pro
		04.pro
		ColorBoxes.java
		output.txt
		report.txt
		AverageTime.xlsx
		ReadMe.txt
##################################################################

USUAGE: [1-4]
	$ gprolog
	?- consult('01').
	?- expt(10,0,E).

	?- consult('02').
	?- hextodec([a],D).
	
	?- consult('03').
	?- perfect(6,Factors).

	?- consult('04').
	?- deleteAtom(b, [no, bs, here], List).  
	
USUAGE: [5- ColorBoxes]
	$ javac ColorBoxes.java
	$ java ColorBoxes
	
Please review output file for further details
	

	