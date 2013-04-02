package proyecto1_compi.scanner;

import java.io.FileReader;
import java.io.IOException;
import javax.naming.spi.DirStateFactory.Result;
import java_cup.runtime.Symbol;

public class reconocedor_tokens{
 
 public static void escanear(FileReader file){
     //String result = "";
   Scanner s;
      try{
//           s = new Scanner(file);
           s = new Scanner(new FileReader("prueba.txt"));
           System.out.println("Si está haciendo el try");
           Symbol actual_token;
       do{
           
          actual_token = s.next_token();
          //result = result + res.sym + "\n";
          System.out.println(actual_token.sym);
          System.out.println("Si está haciendo el do");
          
    /*    if (res.sym != sym.EOF)
          System.out.println(res.value.toString()); // esto mandara el lexema de cada token
          */
       }
       while(actual_token.sym != sym.EOF);
      }
      catch(IOException e){
          System.out.println("No está haciendo el try");
      }
      //return result;
 }
 
 public static void main (String args[]){
     escanear(null);
 }
 
}