/**
 * Scanner para la gram�tica de prueba del curso de Compiladores e Int�rpretes.
 */
%%

%class Scanner
%unicode
%cup
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

<YYINITIAL> "int"               { return symbol(sym.TINT,yytext()); }
<YYINITIAL> "Strig"             { return symbol(sym.TSTRING,yytext()); }
<YYINITIAL> "class"             { return symbol(sym.CLASS,yytext()); }
<YYINITIAL> "static"            { return symbol(sym.STATIC,yytext()); }
<YYINITIAL> "boolean"           { return symbol(sym.BOOL,yytext()); }
<YYINITIAL> "void"              { return symbol(sym.VOID,yytext()); }
<YYINITIAL> "main"              { return symbol(sym.MAIN,yytext()); }
<YYINITIAL> "extends"           { return symbol(sym.EXTENDS,yytext()); }
<YYINITIAL> "implements"        { return symbol(sym.IMPLEMENTS,yytext()); }
<YYINITIAL> "import"            { return symbol(sym.IMPORT,yytext()); }
<YYINITIAL> "return"            { return symbol(sym.RETURN,yytext()); }
<YYINITIAL> "if"                { return symbol(sym.IF,yytext()); }
<YYINITIAL> "while"             { return symbol(sym.WHILE,yytext()); }
<YYINITIAL> "else"              { return symbol(sym.ELSE,yytext()); }
<YYINITIAL> "true"              { return symbol(sym.TRUE,yytext()); }
<YYINITIAL> "false"             { return symbol(sym.FALSE,yytext()); }
<YYINITIAL> "this"              { return symbol(sym.THIS,yytext()); }
<YYINITIAL> "new"               { return symbol(sym.NEW,yytext()); }
<YYINITIAL> "public"            { return symbol(sym.PUBLIC,yytext()); }
<YYINITIAL> "System"            { return symbol(sym.SYSTEM,yytext()); }
<YYINITIAL> "out"               { return symbol(sym.OUT,yytext()); }
<YYINITIAL> "println"           { return symbol(sym.PRINTln,yytext()); }
<YYINITIAL> "read"           { return symbol(sym.READ,yytext()); }
<YYINITIAL> "exit"           { return symbol(sym.EXIT,yytext()); }
<YYINITIAL> "read"           { return symbol(sym.READ,yytext()); }
<YYINITIAL> "in"           { return symbol(sym.IN,yytext()); }
//<YYINITIAL> "FLOAT"           { return symbol(sym.TFLOAT,yytext()); }
//<YYINITIAL> "CHAR"           { return symbol(sym.TCHAR,yytext()); }





<YYINITIAL> {
  /* identificadores */ 
  {Identifier}                   { return symbol(sym.ID,yytext(),yytext()); }
 
  /* literales enteros positivos */
  {DecIntegerLiteral}            { return symbol(sym.INT,yytext()); }
  \"                             { string.setLength(0); yybegin(STRING); }

//  /* literales con decimales */
//  {Floatl}            { return symbol(sym.FLOAT,yytext()); }
//  \"                             { string.setLength(0); yybegin(STRING); }
//
//
//  /*CHAR*/
//  {Char}            { return symbol(sym.CHAR); }
//  \"                             { string.setLength(0); yybegin(STRING); }

  /* operadores */
  "="                            { return symbol(sym.ASIGN,yytext()); }
  "*"                           { return symbol(sym.MULT,yytext()); }
  "+"                            { return symbol(sym.SUMA,yytext()); }
  "/"                            { return symbol(sym.DIV,yytext()); }
  "=="                            { return symbol(sym.IGIG,yytext()); }
  "!="                            { return symbol(sym.DIF,yytext()); }
  ">="                            { return symbol(sym.MAYIG,yytext()); }
  "<="                            { return symbol(sym.MENIG,yytext()); }
  ">"                            { return symbol(sym.MAY,yytext()); }
  "<"                            { return symbol(sym.MEN,yytext()); }
  "-"                            { return symbol(sym.RESTA,yytext()); }
  "!"                            { return symbol(sym.NEG,yytext()); }



  /* otros simbolos v�lidos */
  "("                            { return symbol(sym.PARENi,yytext()); }
  ")"                           { return symbol(sym.PARENd,yytext()); }
  ";"                            { return symbol(sym.PyCOMA,yytext()); }
  ","                            { return symbol(sym.COMA,yytext()); }
  "."                            { return symbol(sym.PUNTO,yytext()); }  
  /* commentarios */
  {Comment}                      { /* ignore */ }
 
  /* espacios en blanco */
  {WhiteSpace}                   { /* ignore */ }
 "["                            { return symbol(sym.CORCHi,yytext()); }
 "]"                            { return symbol(sym.CORCHd,yytext()) }
 "||"                            { return symbol(sym.OR,yytext()); }
 "&&"                            { return symbol(sym.AND,yytext()); }
 "{"                            { return symbol(sym.LLAVEi,yytext()); }
 "}"                            { return symbol(sym.LLAVEd,yytext()); }





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

/* caracteres no v�lidos */
.|\n       { System.out.println("Error caracter inv�lido: <" + yytext() + "> en fila: " + yyline + " columna: " + yycolumn );
       throw new Error("Caracter no permitido <"+  yytext()+">");
   }
