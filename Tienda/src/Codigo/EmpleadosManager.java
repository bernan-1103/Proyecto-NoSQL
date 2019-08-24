/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codigo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Laboratorios
 */
public class EmpleadosManager{
    
    private static File FEmpleado;
    MetodosGlobales EF=new MetodosGlobales();
    
    public EmpleadosManager() {
       FEmpleado=new File("Datos/Empleados.txt");
    }  
    
    
   
    public void eliminar(int eliminar) {
        String[] parts;        
         try {
            /*Si existe el fichero*/
            if (FEmpleado.exists()) {
                /*Abre un flujo de lectura a el fichero*/               
                String Slinea;
                File file= File.createTempFile("CopiaTemporal.txt", null);
                FileInputStream in = new FileInputStream(FEmpleado);
	        FileOutputStream out = new FileOutputStream(file);                
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }                
                BufferedWriter bw = new BufferedWriter(new FileWriter(FEmpleado));
                bw.write("");                
                BufferedReader Flee = new BufferedReader(new FileReader(file));                
                
                while ((Slinea = Flee.readLine()) != null) {
                    parts = Slinea.split(";");//Crear un vector con el contenido de la linea leida
                    if (Integer.parseInt(parts[0])!=eliminar) {
                        EF.escribirFichero(Slinea, FEmpleado);
                    }                                    
                }
                /*Cierra el flujo*/
                Flee.close();                
            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (Exception ex) {
            System.err.println("Error al leer el fichero");            
        }
    }

    public void editar(String datos) {
        String[] parts1;
        parts1= datos.split(";");
       String[] parts;        
         try {
            /*Si existe el fichero*/
            if (FEmpleado.exists()) {
                /*Abre un flujo de lectura a el fichero*/               
                String Slinea;
                File file= File.createTempFile("CopiaTemporal.txt", null);
                FileInputStream in = new FileInputStream(FEmpleado);
	        FileOutputStream out = new FileOutputStream(file);                
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }                
                BufferedWriter bw = new BufferedWriter(new FileWriter(FEmpleado));
                bw.write("");                
                BufferedReader Flee = new BufferedReader(new FileReader(file));                
                
                while ((Slinea = Flee.readLine()) != null) {
                    parts = Slinea.split(";");//Crear un vector con el contenido de la linea leida
                    if (Integer.parseInt(parts[0])==Integer.parseInt(parts1[0])) {
                        agregar(datos);
                    }else{
                        EF.escribirFichero(Slinea, FEmpleado);
                    } 
                    
                }
                /*Cierra el flujo*/
                Flee.close();                
            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (Exception ex) {
            System.err.println("Error al leer el fichero");            
        } 
    }


    public void agregar(String datos) {
        EF.escribirFichero(datos, FEmpleado);
    }

  
    public String[] buscar(int busca) {
        String[] parts;        
         try {
            /*Si existe el fichero*/
            if (FEmpleado.exists()) {
                /*Abre un flujo de lectura a el fichero*/
                BufferedReader Flee = new BufferedReader(new FileReader(FEmpleado));
                String Slinea;
                /*Lee el fichero line a linea hasta llegar a la ultima*/
                while ((Slinea = Flee.readLine()) != null) {
                    parts = Slinea.split(";");//Crear un vector con el contenido de la linea leida
                    if (Integer.parseInt(parts[0])==busca) {
                        return parts;
                    }                                    
                }
                /*Cierra el flujo*/
                Flee.close();               
            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (Exception ex) {
            System.err.println("Error al leer el fichero");            
        } 
         return null;
    }
    
        
    public DefaultTableModel cargarModelo(DefaultTableModel modelo1){
        String[] parts;        
         try {
            /*Si existe el fichero*/
            if (FEmpleado.exists()) {
                /*Abre un flujo de lectura a el fichero*/
                BufferedReader Flee = new BufferedReader(new FileReader(FEmpleado));
                String Slinea;
                /*Lee el fichero line a linea hasta llegar a la ultima*/
                while ((Slinea = Flee.readLine()) != null) {
                    parts = Slinea.split(";");//Crear un vector con el contenido de la linea leida
                    String cod = parts[0];
                    String nombre = parts[1];
                    String telefono = parts[2];
                    String correo = parts[3];
                    String salario = parts[4];
                    
                    modelo1.addRow(new Object[]{cod, nombre, telefono, correo,salario});//Cargar tabla                    
                }
                /*Cierra el flujo*/
                Flee.close();                
                return modelo1;
            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (Exception ex) {
            System.err.println("Error al leer el fichero");            
        }
         return modelo1;
    }  
           
    public boolean existe(int busca){
         String[] parts;        
         try {
            /*Si existe el fichero*/
            if (FEmpleado.exists()) {
                /*Abre un flujo de lectura a el fichero*/
                BufferedReader Flee = new BufferedReader(new FileReader(FEmpleado));
                String Slinea;
                /*Lee el fichero line a linea hasta llegar a la ultima*/
                while ((Slinea = Flee.readLine()) != null) {
                    parts = Slinea.split(";");//Crear un vector con el contenido de la linea leida
                    if (Integer.parseInt(parts[0])==busca) {
                        return true;
                    }                                   
                }
                /*Cierra el flujo*/
                Flee.close();               
            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (Exception ex) {
            System.err.println("Error al leer el fichero");            
        } 
         return false;
     }
}