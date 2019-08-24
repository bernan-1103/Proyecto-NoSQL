/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codigo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JPanel;


public class FacturaManager {
    
    private static File FFacturas;
    private static File FDetalles;
    private int buscaFactura;
    
    private static JPanel Factura;
    
    MetodosGlobales EF=new MetodosGlobales();

    public FacturaManager() {
        FFacturas=new File("Datos/Ventas.txt");
        FDetalles=new File("Datos/Detalles.txt");
    }

    public void setBuscaFactura(int buscaFactura) {
        this.buscaFactura = buscaFactura;
    }
        
    
    public void agregar(String datos) {   
        String[] parts;
        parts= datos.split(";");
        String[] codp=parts[4].split("_");
        String[] nombre=parts[5].split("_");
        String[] cantidad=parts[6].split("_");
        String[] precio=parts[7].split("_");
        String[] total=parts[8].split("_");
        
            EF.escribirFichero(parts[0]+";"+parts[1]+";"+parts[2]+";"+parts[3]
                    +";"+parts[9]+";"+parts[10]+";"+parts[11]+";"+parts[12]
                    +";"+parts[13],FFacturas); 
            
            for (int i = 0; i < codp.length; i++) {
            EF.escribirFichero(parts[0]+";"+codp[i]+";"+nombre[i]+";"+cantidad[i]
                    +";"+precio[i]+";"+total[i], FDetalles);
        }
            
    }
    
    
    public int NumFac() {
        String[] parts=null;        
        try {
            /*Si existe el fichero*/
            if (FFacturas.exists()) {
                /*Abre un flujo de lectura a el fichero*/
                BufferedReader Flee = new BufferedReader(new FileReader(FFacturas));
                String Slinea;                
                while ((Slinea = Flee.readLine()) != null) {
                    parts=Slinea.split(";");                    
                }                        
                /*Cierra el flujo*/
                Flee.close();
            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (Exception ex) {
            System.err.println("Error al leer el fichero");
        }
        
        if (parts==null) {
            return 1;
        }else{
            return Integer.parseInt(parts[0]);
        }
       
    }
    
    public String fecha(){
        Calendar fecha = Calendar.getInstance();
        int dia=fecha.get(Calendar.DATE);
        int mes=fecha.get(Calendar.MONTH)+1;
        int ano=fecha.get(Calendar.YEAR);        
        return dia+"/"+mes+"/"+ano;
    }
    
    

    public DefaultTableModel cargarModelo(DefaultTableModel modelo1) {
        String[] parts;        
         try {
            /*Si existe el fichero*/
            if (FDetalles.exists()) {
                /*Abre un flujo de lectura a el fichero*/
                BufferedReader Flee = new BufferedReader(new FileReader(FDetalles));
                String Slinea;
                /*Lee el fichero line a linea hasta llegar a la ultima*/
                while ((Slinea = Flee.readLine()) != null) {
                    parts = Slinea.split(";");//Crear un vector con el contenido de la linea leida
                    if (Integer.parseInt(parts[0])==buscaFactura) {
                        modelo1.addRow(new Object[]{parts[1],parts[2],parts[3],
                        parts[4],parts[5]});
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
         return modelo1;
    }
    
    

    public String[] buscar(int busca) {
        String[] parts;        
         try {
            /*Si existe el fichero*/
            if (FFacturas.exists()) {
                /*Abre un flujo de lectura a el fichero*/
                BufferedReader Flee = new BufferedReader(new FileReader(FFacturas));
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

        
    public String cargarModelo(int cod,int cantidad){
        Codigo.ProductosManager P=new Codigo.ProductosManager();
        String[] parts;
        parts=P.buscar(cod);
        int Total;
        Total=cantidad*Integer.parseInt(parts[6]);
        return parts[0]+";"+parts[1]+";"+parts[2]+";"+
                cantidad+";"+parts[6]+";"+Total;                
    }
    
    
    public DefaultTableModel cargarFacturas(DefaultTableModel modelo){
        String[] parts;        
         try {
            /*Si existe el fichero*/
            if (FFacturas.exists()) {
                /*Abre un flujo de lectura a el fichero*/
                BufferedReader Flee = new BufferedReader(new FileReader(FFacturas));
                String Slinea;
                /*Lee el fichero line a linea hasta llegar a la ultima*/
                while ((Slinea = Flee.readLine()) != null) {
                    parts = Slinea.split(";");//Crear un vector con el contenido de la linea leida
                    
                        modelo.addRow(new Object[]{parts[0],parts[1],parts[2],
                        parts[3],parts[4]});
                                                         
                }
                /*Cierra el flujo*/
                Flee.close(); 
            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (Exception ex) {
            System.err.println("Error al leer el fichero");            
        }
         return modelo;
    }
        
    
}
