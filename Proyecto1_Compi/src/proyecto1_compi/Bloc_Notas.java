package proyecto1_compi;


import javax.swing.text.*;
import javax.swing.undo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.text.Caret;
import java.util.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.AbstractDocument.*;

public class Bloc_Notas extends javax.swing.JFrame implements ActionListener {
		
	public Bloc_Notas()
		{
			
//			super("Bloc de notas");
			this.setSize(600,600);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setLayout(new GridBagLayout());
			GridBagConstraints gbc=new GridBagConstraints();
			
			barra=new JMenuBar();
			archivo=new JMenu("Archivo");
			edicion=new JMenu("Edicion");
			formato=new JMenu("Formato");
			ver=new JMenu("Ver");
			ayuda=new JMenu("Ayuda");
			
			//instanciamos submenus de archivo
			nuevo=new JMenuItem("Nuevo");
			abrir=new JMenuItem("Abrir");
			guardar=new JMenuItem("Guardar");
//			imprimir=new JMenuItem("Imprimir");
		
			salir=new JMenuItem("Salir");
			
			//instanciamos submenus de edicion
			compilar=new JMenuItem("Compilar");
			cortar=new JMenuItem(new DefaultEditorKit.CutAction());
			copiar=new JMenuItem(new DefaultEditorKit.CopyAction());
			pegar=new JMenuItem(new DefaultEditorKit.PasteAction());
			
			
			cortar.setText("Cortar");
		
		
			copiar.setText("Copiar");
			pegar.setText("Pegar");
			
			
			eliminar=new JMenuItem("Eliminar");
			
			//instanciamos submenus de formato
			fuente=new JMenuItem("Fuente...");
			
			//instanciamos submenus de ayuda
			acerca_bloc_notas=new JMenuItem("Acerca del Bloc de Notas");
			
			//agregamos a cada menu su submenu
			archivo.add(nuevo);
			archivo.add(abrir);
			archivo.add(guardar);
//			archivo.add(imprimir);
			archivo.add(new JSeparator());
			archivo.add(salir);
			
                        edicion.add(compilar);
			edicion.add(new JSeparator());
			edicion.add(cortar);
			edicion.add(copiar);
			edicion.add(pegar);
                        edicion.add(eliminar);
                        
                        formato.add(fuente);
		    
                        ayuda.add(acerca_bloc_notas);
			
			//agregamos cada menu a la barra de menus
			barra.add(archivo);
			barra.add(edicion);
			barra.add(formato);
			barra.add(ayuda);
			
			
			
			this.setJMenuBar(barra);
//			area_texto=new JTextArea();
                        console=new JTextArea(25,80);
			
	scroll=new JScrollPane(area_texto);
        scroll2=new JScrollPane(console);
			
	//restricciones para el area de texto
   	gbc.gridx = 0;
   	gbc.gridy = 0;
   	
   	gbc.gridwidth=1;
   	gbc.gridheight=1;
   	
   	gbc.weightx = 1.0;
   	gbc.weighty = 1.0;
   	
   	gbc.fill = GridBagConstraints.BOTH;
   	
   	add(scroll,gbc);
        
	//restricciones para la consola
   	gbc.gridx = 0;
   	gbc.gridy = 0;
   	
   	gbc.gridwidth=5;
   	gbc.gridheight=5;
   	
   	gbc.weightx = 1.0;
   	gbc.weighty = 1.0;
   	
   	gbc.fill = GridBagConstraints.BOTH;
        
        add(scroll2,gbc);
			
//			this.setVisible(true);
			
			//ponemos a escuchar a todos los JMenuItem
			nuevo.addActionListener(this);
			abrir.addActionListener(this);
			guardar.addActionListener(this);
//		    imprimir.addActionListener(this);
		
			salir.addActionListener(this);
			
//			buscar.addActionListener(this);
                        compilar.addActionListener(this);
			cortar.addActionListener(this);
			copiar.addActionListener(this);
			pegar.addActionListener(this);
			eliminar.addActionListener(this);
			
			fuente.addActionListener(this);
			acerca_bloc_notas.addActionListener(this);
			
		}
        
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==nuevo)
		{
			String obtener_area_texto=area_texto.getText();
			
			if(obtener_area_texto.equals(""))
			{
			
			}else
			{
				
			int opcion=JOptionPane.showConfirmDialog(null,"Desea guardar los cambios hechos","Bloc de Notas",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
			if(opcion==JOptionPane.YES_OPTION)
			{
			  guardar_documento();	
			  area_texto.setText("");
			}else if(opcion==JOptionPane.NO_OPTION)
			  {
				area_texto.setText("");
			  }else if(opcion==JOptionPane.CANCEL_OPTION)
			  {
			  	
				
			  }
			    
			}
			
			
		}
			else if(e.getSource()==abrir)
                        {
                            JFileChooser fileChooser = new JFileChooser();
                            int seleccion = fileChooser.showOpenDialog(area_texto);
                    
                    
                    if (seleccion == JFileChooser.APPROVE_OPTION)
                        {
                          File fichero = fileChooser.getSelectedFile();
                           // y a trabajar con el fichero
                           try{
                               FileReader fr=new FileReader(fichero);
                               BufferedReader br = new BufferedReader(fr);
                               String linea;
                               String retorno_de_carro=System.getProperty("line.separator");
                                while((linea=br.readLine())!=null)
                                {
                                    area_texto.append(linea);
                                    area_texto.append(retorno_de_carro); 
                                }
                                fr.close();
                               }catch(Exception r)
                               {
                               	
                               	r.printStackTrace();
                               	
                               }
                           
                           		
                        }
				}
					else if(e.getSource()==guardar)
				{
					guardar_documento();
                     
				}
				
					else if(e.getSource()==salir)
				{
					System.exit(0);
				}
                                else if(e.getSource()==compilar){
				
                                }
                                
                                else if(e.getSource()==eliminar)
				{
				         
				          String texto_a_eliminar = area_texto.getSelectedText();
					      if(texto_a_eliminar== null)
					      {
						    JOptionPane.showMessageDialog(area_texto,"Selecciona el texto a eliminar");
						  }else{
						       Caret seleccion = area_texto.getCaret();
						       int posicion_inicial = 0;
						      
						                posicion_inicial=seleccion.getDot();
						                String todo_el_texto=area_texto.getText();
					  
						                int posicion=todo_el_texto.indexOf(texto_a_eliminar);
					     
					                    area_texto.setCaretPosition(posicion);
					                    area_texto.moveCaretPosition(posicion+texto_a_eliminar.length());
					                  
					                   
					         try{
					         	  Document document = area_texto.getDocument();
					         	  
					         	  int PosicionFinal=texto_a_eliminar.length();
					         	  document.remove(posicion,PosicionFinal);
					         	 
					         }catch(Exception m)
					         {
					         	m.printStackTrace();
					         }
					                    
					                    
					           }
                                
                                }
					else if(e.getSource()==fuente)
				{
                                    
                                    
                                    ventana_fuente=new JFrame("FUENTE");
				   
				    ventana_fuente.setSize(250,200);
				    ventana_fuente.setLayout(new GridBagLayout());
				    ventana_fuente.setResizable(false);
				    ventana_fuente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				    
				    
				    ventana_fuente.setLocationRelativeTo(ventana_fuente);
				    ventana_fuente.setVisible(true);
				    
				    
				    
				   
				   
				    //iniciamos el textfield
				    tamaño_letra=new JTextField(8);
				    
				    aceptar=new JButton("Aceptar");
				    cancelar=new JButton("Cancelar");
				    
				    aceptar.addActionListener(this);
				    cancelar.addActionListener(this);
				    
				    String [] estilos_de_letras={"Cursiva","Negrita","Cursiva y Negrita"};
				    estilo_letra=new JComboBox(estilos_de_letras);
				    GridBagConstraints gbc=new GridBagConstraints();
				    
				    Estilo_Letra=new JLabel("Estilo de la letra:");
				    Tamaño_Letra=new JLabel("Tamaño de la letra:");
				    
				    //cargamos a la etiquete 1
				    gbc.gridx = 0;
   	               gbc.gridy = 0;
   	
   	               gbc.gridwidth=1;
   	               gbc.gridheight=1;
   	
   	               gbc.weightx = 1;
   	               gbc.weighty = 0;
   	
   	               gbc.fill = GridBagConstraints.BOTH;
   	
   	               ventana_fuente.add(Estilo_Letra,gbc);
   	               
   	               //cargamos a la etiqueta 2
				    gbc.gridx = 1;
   	               gbc.gridy = 0;
   	
   	               gbc.gridwidth=1;
   	               gbc.gridheight=1;
   	
   	               gbc.weightx = 1;
   	               gbc.weighty = 0;
   	
   	               gbc.fill = GridBagConstraints.BOTH;
   	
   	               ventana_fuente.add(Tamaño_Letra,gbc);
				    
				    //cargamos a la ventana el combobox
				    gbc.gridx = 0;
   	               gbc.gridy = 1;
   	
   	               gbc.gridwidth=1;
   	               gbc.gridheight=1;
   	
   	               gbc.weightx = 1;
   	               gbc.weighty = 0;
   	
   	               gbc.fill = GridBagConstraints.BOTH;
   	
   	               ventana_fuente.add(estilo_letra,gbc);
   	
   	               //restricciones para el textfield
   	               gbc.gridx = 1;
   	               gbc.gridy = 1;
   	
   	               gbc.gridwidth=1;
   	               gbc.gridheight=1;
   	
   	               gbc.weightx = 1;
   	               gbc.weighty = 0;
   	
   	               gbc.fill = GridBagConstraints.BOTH;
   	
   	               ventana_fuente.add(tamaño_letra,gbc);
   	
                  //restricciones para el Boton Aceptar
   	
   	              gbc.gridx = 0;
   	              gbc.gridy = 3;
   	
   	              gbc.gridwidth=1;
   	              gbc.gridheight=1;
   	
   	              gbc.weightx = 0.0;
   	              gbc.weighty = 0.0;
   	
   	              gbc.fill = GridBagConstraints.NONE;
   	
   	              ventana_fuente.add(aceptar, gbc);
   	              
   	              //restricciones para el Boton Cancelar
   	
   	              gbc.gridx = 1;
   	              gbc.gridy = 3;
   	
   	              gbc.gridwidth=1;
   	              gbc.gridheight=1;
   	
   	              gbc.weightx = 0.0;
   	              gbc.weighty = 0.0;
   	
   	              gbc.fill = GridBagConstraints.NONE;
   	
   	              ventana_fuente.add(cancelar, gbc);
				    
				}
					else if(e.getSource()==acerca_bloc_notas)
				{
					
					
					JLabel info_editor=new JLabel("Copia barata del bloc de notas :)");
					
					JFrame acerca=new JFrame("Acerca del editor de textos");
					
				
					info_editor.setBounds(50,100,10,100);
					
					
					acerca.add(info_editor);
					acerca.setSize(280,150);
					acerca.setDefaultCloseOperation(acerca.DISPOSE_ON_CLOSE);
					acerca.setResizable(false);
					acerca.setLocationRelativeTo(acerca);
					
					
					acerca.setVisible(true);
				}
				else if(e.getSource()==aceptar)
				{
					
					String tipo_letra=(String)estilo_letra.getSelectedItem();
					
					String recibe_texto=tamaño_letra.getText();
					int tamaño_de_letra=Integer.parseInt(recibe_texto);
					
					if(tipo_letra.equals("Cursiva"))
					{
						fuente_texto=new Font("serief",Font.ITALIC,tamaño_de_letra);
						area_texto.setFont(fuente_texto);
					}else if(tipo_letra.equals("Negrita")){
						
						fuente_texto=new Font("serief",Font.BOLD,tamaño_de_letra);
						area_texto.setFont(fuente_texto);
						
					}else if(tipo_letra.equals("Cursiva y Negrita"))
					{
						
						fuente_texto=new Font("serief",Font.ITALIC|Font.BOLD,tamaño_de_letra);
						area_texto.setFont(fuente_texto);
					}
					
				}
				else if(e.getSource()==cancelar)
				{
				   ventana_fuente.setVisible(false);
				}
			   
			    
		
	}
	 public void guardar_documento()
			     {
			     
				JFileChooser fileChooser = new JFileChooser();
                    int seleccion = fileChooser.showSaveDialog(area_texto);
                    if (seleccion == JFileChooser.APPROVE_OPTION)
                        {
                        	File fichero=fileChooser.getSelectedFile();
                        	try{
                        	PrintWriter pw = new PrintWriter(fichero);
                            pw.println(area_texto.getText());
                            pw.close();
                        	}catch(Exception f)
                        	{
                        		f.printStackTrace();
                        	}
                        	
                        }
			     }
			    
				   
	public static void main(String args[])
	{
            
            java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
                public void run() {
                    new Bloc_Notas().setVisible(true);
                }
            });
	}

        private javax.swing.JFrame ventana_fuente=null;
	private javax.swing.JComboBox estilo_letra;
	private javax.swing.JTextField tamaño_letra;
	private javax.swing.JButton aceptar, cancelar;
	private javax.swing.JLabel Estilo_Letra,Tamaño_Letra;
						
	private Font fuente_texto=null;
	String texto_a_copiar;
	
	public String metodo_copiar()
	{
		String texto_que_seleccionamos=area_texto.getSelectedText();
		return texto_que_seleccionamos;
	}
	private javax.swing. JMenuBar barra;
	private javax.swing.JMenu archivo;
	private javax.swing.JMenu edicion;
	private javax.swing.JMenu formato;
	private javax.swing.JMenu ver;
	private javax.swing.JMenu ayuda;
	//Submenus de archivo
	private javax.swing.JMenuItem nuevo;
	private javax.swing.JMenuItem abrir;
	private javax.swing.JMenuItem guardar;
	private javax.swing.JMenuItem salir;
	//Submenus de edicion
	private javax.swing.JMenuItem cortar;
	private javax.swing.JMenuItem copiar;
	private javax.swing.JMenuItem pegar;
	private javax.swing.JMenuItem eliminar;
        private javax.swing.JMenuItem compilar;
	//Submenus de formato
	private javax.swing.JMenuItem fuente;
	//Submenus de ayuda
	private javax.swing.JMenuItem acerca_bloc_notas;
	
	
	private javax.swing.JTextArea area_texto;
        private javax.swing.JTextArea console;
	private javax.swing.JScrollPane scroll;
        private javax.swing.JScrollPane scroll2;
        

        
}