import java.util.*;
%%
%{
  private void echo ()
  { System . out . print (yytext ()); }

  public int position () { return yycolumn; }
%}

%class    MicroScalaLexer
%unicode
%line
%column
%function next_token
%type	  Token

%eofval{
  { return new Token (TokenClass . EOF); }
%eofval}

Comment	   = "//".*
WhiteSpace = [ \t\n]
Identifier = [:letter:]("_"?([:letter:] | [:digit:]))*
Integer    = [:digit:] [:digit:]*
%%

{Comment}	    { echo (); }
{WhiteSpace}	{ echo (); }
","		{ echo (); return new Token (TokenClass . COMMA); }
";"		{ echo (); return new Token (TokenClass . SEMICOLON); }
"{"		{ echo (); return new Token (TokenClass . LBRACE); }
"}"		{ echo (); return new Token (TokenClass . RBRACE); }
":"		{ echo (); return new Token (TokenClass . COLON); }
"."		{ echo (); return new Token (TokenClass . PERIOD); }
"("		{ echo (); return new Token (TokenClass . LPAREN); }
")"		{ echo (); return new Token (TokenClass . RPAREN); }
"["		{ echo (); return new Token (TokenClass . LBRACKET); }
"]"		{ echo (); return new Token (TokenClass . RBRACKET); }
"="		{ echo (); return new Token (TokenClass . ASSIGN); }
"||"	{ echo (); return new Token (TokenClass . OR); }
"&&"	{ echo (); return new Token (TokenClass . AND); }
"!"		{ echo (); return new Token (TokenClass . NOT); }

"+"		{ echo (); return new Token (TokenClass . ADDOPER, "+"); }
"-"		{ echo (); return new Token (TokenClass . ADDOPER, "-"); }
"*"		{ echo (); return new Token (TokenClass . MULOPER, "*"); }
"/"		{ echo (); return new Token (TokenClass . MULOPER, "/"); }


"<"		{ echo (); return new Token (TokenClass . RELOPER, "<"); }
"<="	{ echo (); return new Token (TokenClass . RELOPER, "<="); }
">"		{ echo (); return new Token (TokenClass . RELOPER, ">"); }
">="	{ echo (); return new Token (TokenClass . RELOPER, ">"); }
"=="	{ echo (); return new Token (TokenClass . RELOPER, "=="); }
"!="	{ echo (); return new Token (TokenClass . RELOPER, "!="); }

"::"	{ echo (); return new Token (TokenClass . CONS); }

args      { echo (); return new Token (TokenClass . ARGS); }
Array		  { echo (); return new Token (TokenClass . ARRAY); }
def		    { echo (); return new Token (TokenClass . DEF); }
else		  { echo (); return new Token (TokenClass . ELSE); }
head		  { echo (); return new Token (TokenClass . LISTOP, "head"); }
if		    { echo (); return new Token (TokenClass . IF); }
Int		    { echo (); return new Token (TokenClass . INT); }
isEmpty		{ echo (); return new Token (TokenClass . LISTOP, "isEmpty"); }
List		  { echo (); return new Token (TokenClass . LIST); }
main		  { echo (); return new Token (TokenClass . MAIN); }
Nil		    { echo (); return new Token (TokenClass . NIL); }
object		{ echo (); return new Token (TokenClass . OBJECT); }
println		{ echo (); return new Token (TokenClass . PRINTLN); }
return		{ echo (); return new Token (TokenClass . RETURN); }
String		{ echo (); return new Token (TokenClass . STRING); }
tail		  { echo (); return new Token (TokenClass . LISTOP, "tail"); }
var		    { echo (); return new Token (TokenClass . VAR); }
while		  { echo (); return new Token (TokenClass . WHILE); }
{Integer}	{ echo (); return new Token (TokenClass . INTEGER, yytext ()); }
{Identifier}	{ echo (); return new Token (TokenClass . ID, yytext ()); }
.		      { echo (); ErrorMessage . print (yychar, "Illegal character"); }
