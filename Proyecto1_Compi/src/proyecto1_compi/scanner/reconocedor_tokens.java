package proyecto1_compi.scanner;

import java.io.*;

public class reconocedor_tokens{
 public static String compilar (File f){
     String result = "";
     try{
         Scanner s = new Scanner(new FileReader(f));
         Symbol res;
         do{
             res= s.nextToken();
             result = result+res.sym+ "---  Line: " + res.left + "---  Row: "+ res.right + "\n";
             
             //        System.out.println(res.sym);
             //if (res.sym != sym.EOF)
             //  System.out.println(res.value.toString()); // esto mandara el lexema de cada token
         }while(res.sym != sym.EOF);
     }
     catch(IOException e){
     }
     return result;
 }
 
 public static void main (String args[]){}
 
}