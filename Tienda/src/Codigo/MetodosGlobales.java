/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codigo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author Jonathan
 */
public class MetodosGlobales {
    
    
    public void escribirFichero(String SCadena,File file) {
        try {
            //Si no Existe el fichero lo crea
            if (!file.exists()) {
                file.createNewFile();
            }
            //Abre un Flujo de escritura,sobre el fichero con codificacion utf-8. Ademas   en
            //el pedazo de sentencia "FileOutputStream(Ffichero,true)", true es por si existe el fichero
            //segir añadiendo texto y no borrar lo que tenia 
            BufferedWriter Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));
            //Escribe en el fichero la cardena que recibe la funcion. la cadena "\r\n" significa salto de linea
            Fescribe.write(SCadena + "\r\n");
            //Cierra el flujo de escritura
            Fescribe.close();
        } catch (Exception ex) {
            //Captura un posible error y le imprime en pantalla 
            // System.out.println(ex.getMessage());
            System.err.println("Error al escribir fichero");
        }

    }
    
}
