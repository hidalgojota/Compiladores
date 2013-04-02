
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import javax.swing.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import proyecto1_compi.scanner.*;

/**
 *
 * @author JOTAE
 */
public class Funciones {

    public Funciones() {
    }
    
    public File fichero;
    boolean guardado = true;
    
    /**
     * @description     Save document of the text area.
     * @param area      GUI Object of the text area, for getting the text or document.
     */
    public void guardar_documento(JTextArea area){
        if(fichero!=null){
            try{
                    PrintWriter pw = new PrintWriter(fichero);
                    pw.println(area.getText());
                    pw.close();
                }catch(Exception f)
                {
                    f.printStackTrace();
                }
        }
        else{
            this.guardado=true;
            JFileChooser fileChooser = new JFileChooser();
            int seleccion = fileChooser.showSaveDialog(area);
            if (seleccion == JFileChooser.APPROVE_OPTION)
            {
                File Fichero=fileChooser.getSelectedFile();
                try{
                    PrintWriter pw = new PrintWriter(Fichero);
                    pw.println(area.getText());
                    pw.close();
                }catch(Exception f)
                {
                    f.printStackTrace();
                }
            }
        }
    }
    
    /**
     * @description     Allows to search and open a document in the text area.
     * @param           GUI Object of the text area, for showing the document.
     */
    public void abrir_documento(JTextArea area){
        
            JFileChooser fileChooser = new JFileChooser();
            int seleccion = fileChooser.showOpenDialog(area);
            if (seleccion == JFileChooser.APPROVE_OPTION)
            {
                /*File*/ fichero = fileChooser.getSelectedFile();
                // y a trabajar con el fichero
                try{
                    FileReader FR=new FileReader(fichero);
                    BufferedReader br = new BufferedReader(FR);
                    String linea;
                    String retorno_de_carro=System.getProperty("line.separator");
                    while((linea=br.readLine())!=null)
                    {
                        area.append(linea);
                        area.append(retorno_de_carro); 
                    }
                    FR.close();
                }catch(Exception r)
                {
                    r.printStackTrace();
                }
            }
    }
    /**
     * @description     Allows to create a new document in the text area.
     * @param           GUI Object of the text area, for showing the new document.
     */
    public void nuevo_documento(JTextArea area){
        String obtener_area_texto=area.getText();
        if(obtener_area_texto.equals(""))
        {
            /* Do nothing, the document is just created*/
        }
        else
        {
            int opcion=JOptionPane.showConfirmDialog(null,"Desea guardar los cambios hechos","Bloc de Notas",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
            if(opcion==JOptionPane.YES_OPTION)
            {
                guardar_documento(area);	
                area.setText("");
            }
            else if(opcion==JOptionPane.NO_OPTION)
            {
                area.setText("");
            }else if(opcion==JOptionPane.CANCEL_OPTION)
            {}
        }
    }
    
    /**
     * @description     Get the 'x' (line) & 'y' (row) position of te caret.
     * @param           GUI Object of the text area.
     * @return          Caret position.
     */
    public String[] get_position(JTextArea area){
        int caretpos = area.getCaretPosition();
        String[] position = new String[2];
        try {
            int linenum = area.getLineOfOffset(caretpos);
            int columnnum = caretpos - area.getLineStartOffset(linenum);
            /*line*/position[0] = (""+linenum);
            /*row*/position[1] = (""+columnnum);
        } catch (BadLocationException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return position;
    }
    
    
    /**
     * @description     Compile File of the text area.
     */
    public void compilar(JTextArea comp){
        reconocedor_tokens rt = new reconocedor_tokens();
        
        comp.setText(rt.compilar(fichero));
    }
}
