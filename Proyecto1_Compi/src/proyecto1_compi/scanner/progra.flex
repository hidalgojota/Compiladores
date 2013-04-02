/**
 * Scanner para la gramática de prueba del curso de Compiladores e Intérpretes.
 */
%%

%class Scanner
%unicode
//%cup
%line
%column
%type Symbol
%function nextToken

%eofval{
 return symbol(sym.EOF);
%eofval}

%{
  StringBuffer string = new StringBuffer();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} 

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}

Identifier = [:jletter:] [:jletterdigit:]*  | [:jletter:] [:jletterdigit:]* [_|\$|\@] [:jletterdigit:]*   | [_|\$] [:jletter:] [:jletterdigit:]* [_|\$|\@]  [:jletterdigit:]*   

DecIntegerLiteral = 0 | [1-9][0-9]*
 
//Floatl=  [1-9]*+ "." +[1-9]*

//Char= \'[:jletter:]\'





%state STRING

%%

/* Palabras reservadas */

<YYINITIAL> "int"          { return symbol(sym.TINT); }
<YYINITIAL> "Strig"            { return symbol(sym.TSTRING); }
<YYINITIAL> "class"             { return symbol(sym.CLASS); }
<YYINITIAL> "static"            { return symbol(sym.STATIC); }
<YYINITIAL> "boolean"           { return symbol(sym.BOOL); }
<YYINITIAL> "void"              { return symbol(sym.VOID); }
<YYINITIAL> "main"              { return symbol(sym.MAIN); }
<YYINITIAL> "extends"           { return symbol(sym.EXTENDS); }
<YYINITIAL> "implements"        { return symbol(sym.IMPLEMENTS); }
<YYINITIAL> "return"            { return symbol(sym.RETURN); }
<YYINITIAL> "if"                { return symbol(sym.IF); }
<YYINITIAL> "while"             { return symbol(sym.WHILE); }
<YYINITIAL> "else"              { return symbol(sym.ELSE); }
<YYINITIAL> "true"              { return symbol(sym.TRUE); }
<YYINITIAL> "false"             { return symbol(sym.FALSE); }
<YYINITIAL> "this"              { return symbol(sym.THIS); }
<YYINITIAL> "new"               { return symbol(sym.NEW); }
<YYINITIAL> "public"            { return symbol(sym.PUBLIC); }
<YYINITIAL> "System"            { return symbol(sym.SYSTEM); }
<YYINITIAL> "out"               { return symbol(sym.OUT); }
<YYINITIAL> "println"           { return symbol(sym.PRINTln); }
<YYINITIAL> "read"           { return symbol(sym.READ); }
<YYINITIAL> "exit"           { return symbol(sym.EXIT); }
<YYINITIAL> "read"           { return symbol(sym.READ); }
<YYINITIAL> "in"           { return symbol(sym.IN); }
//<YYINITIAL> "FLOAT"           { return symbol(sym.TFLOAT); }
//<YYINITIAL> "CHAR"           { return symbol(sym.TCHAR); }





<YYINITIAL> {
  /* identificadores */ 
  {Identifier}                   { return symbol(sym.ID,yytext()); }
 
  /* literales enteros positivos */
  {DecIntegerLiteral}            { return symbol(sym.INT); }
  \"                             { string.setLength(0); yybegin(STRING); }

//  /* literales con decimales */
//  {Floatl}            { return symbol(sym.FLOAT); }
//  \"                             { string.setLength(0); yybegin(STRING); }
//
//
//  /*CHAR*/
//  {Char}            { return symbol(sym.CHAR); }
//  \"                             { string.setLength(0); yybegin(STRING); }

  /* operadores */
  "="                            { return symbol(sym.ASIGN); }
  "*"                           { return symbol(sym.MULT); }
  "+"                            { return symbol(sym.SUMA); }
  "/"                            { return symbol(sym.DIV); }
  "=="                            { return symbol(sym.IGIG); }
  "!="                            { return symbol(sym.DIF); }
  ">="                            { return symbol(sym.MAYIG); }
  "<="                            { return symbol(sym.MENIG); }
  ">"                            { return symbol(sym.MAY); }
  "<"                            { return symbol(sym.MEN); }
  "-"                            { return symbol(sym.RESTA); }
  "!"                            { return symbol(sym.NEG); }



  /* otros simbolos válidos */
  "("                            { return symbol(sym.PARENi); }
  ")"                           { return symbol(sym.PARENd); }
  ";"                            { return symbol(sym.PyCOMA); }
  ","                            { return symbol(sym.COMA); }
  "."                            { return symbol(sym.PUNTO); }  
  /* commentarios */
  {Comment}                      { /* ignore */ }
 
  /* espacios en blanco */
  {WhiteSpace}                   { /* ignore */ }
 "["                            { return symbol(sym.CORCHi); }
 "]"                            { return symbol(sym.CORCHd; }
 "||"                            { return symbol(sym.OR); }
 "&&"                            { return symbol(sym.AND); }
 "{"                            { return symbol(sym.LLAVEi); }
 "}"                            { return symbol(sym.LLAVEd); }





}

<STRING> {
  \"                             { yybegin(YYINITIAL); 
                                   return symbol(sym.STRING, string.toString()); }
  [^\n\r\"\\]+                   { string.append( yytext() ); }
  \\t                            { string.append('\t'); }
  \\n                            { string.append('\n'); }

  \\r                            { string.append('\r'); }
  \\\"                           { string.append('\"'); }
  \\                             { string.append('\\'); }
}

/* caracteres no válidos */
.|\n                             { System.out.println("Error caracter inválido: <" + yytext() + "> en fila: " + yyline + " columna: " + yycolumn );
       /*throw new Error("Caracter no permitido <"+
                                                    yytext()+">");*/ }
