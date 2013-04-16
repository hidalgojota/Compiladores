package proyecto1_compi.scanner;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class reconocedor_tokens{
 public static String compilar (File f){
     String result = "";
     try{
         Scanner s = new Scanner(new FileReader(f));
         parser p= new parser(s);
            try {
                p.parse();
            } catch (Exception ex) {
                Logger.getLogger(reconocedor_tokens.class.getName()).log(Level.SEVERE, null, ex);
            }
         Symbol res;
         do{
             res= s.nextToken();
             result = result+res.sym;
             if (res.sym != sym.EOF)
              result = result + "   '\'"+res.value.toString()+"'\'"; // esto mandara el lexema de cada token
             result = result+ "            Line: " + res.left + "            Row: "+ res.right + "\n";
         }while(res.sym != sym.EOF);
     }
     catch(IOException e){
     }
     return result;
 }
 
 public static void main (String args[]){}
 
}