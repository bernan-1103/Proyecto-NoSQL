/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codigo;

import dao.ClienteDao;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import model.cliente;

/**
 *
 * @author Laboratorios
 */
@XmlRootElement(name = "clientes")
@XmlAccessorType(XmlAccessType.FIELD)
public final class ClientesManager {

    private static final ClientesManager clientesM = new ClientesManager();

    @XmlElement(name = "cliente")
    private List<cliente> ClientesList;

    private ClientesManager() {

    }

    public static ClientesManager getClienteMC() {
        return clientesM;
    }

    public List<cliente> getClientesList() {
        return ClientesList;
    }

    public void setClientesList(List<cliente> ClientesList) {
        this.ClientesList = ClientesList;
    }

    public String metodo() {
        return "Insertar";
    }

    public void agregar() {
        cliente CM = cliente.getCliente();
        if (!existeCliente(CM.getIdCliente())) {
            System.out.println(CM.getIdCliente() + CM.getNombre() + CM.getPrimerApellido() + CM.getSegundoApellido() + CM.getTelefono());
            String Insertar = "<cliente> \n"
                    + "<idCliente>" + CM.getIdCliente() + "</idCliente> \n"
                    + "<nombre>" + CM.getNombre() + "</nombre> \n"
                    + "<primerApellido>" + CM.getPrimerApellido() + "</primerApellido> \n"
                    + "<segundoApellido>" + CM.getSegundoApellido() + "</segundoApellido> \n"
                    + "<telefono>" + CM.getTelefono() + "</telefono> \n"
                    + "<correo>" + CM.getCorreo() + "</correo> \n"
                    + "</cliente>";
            System.out.println(Insertar);

            ClienteDao c = new ClienteDao();
            c.create_XML(Insertar);
            c.close();
        } else {
            JOptionPane.showMessageDialog(null, "El cliente a agregar ya existe");
        }
    }

    public void consutaClientesApi() throws JAXBException, IOException {
        ClientesList = null;
        JAXBContext jaxbContext = JAXBContext.newInstance(ClientesManager.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        File tempClient = File.createTempFile("Clientes", ".xml");
        try (BufferedWriter out = new BufferedWriter(new FileWriter(tempClient))) {
            ClienteDao cliente = new ClienteDao();
            out.write(cliente.findAll_XML(String.class));
            cliente.close();
        }

        //We had written this file in marshalling example
        ClientesManager client = (ClientesManager) jaxbUnmarshaller.unmarshal(tempClient);
        ClientesList = client.ClientesList;
    }

    public DefaultTableModel cargarModelo(DefaultTableModel modelo1) throws JAXBException, IOException {
        consutaClientesApi();
        if (ClientesList != null) {
            ClientesList.forEach((C) -> {
                modelo1.addRow(new Object[]{C.getIdCliente(), C.getNombre(), C.getTelefono(), C.getCorreo()});//Cargar tabla                    
            });
        }
        return modelo1;
    }

    public cliente buscar(int busca) {
        for (cliente c : ClientesList) {
            if (c.getIdCliente() == busca) {
                return c;
            }
        }
        return null;
    }

    public void editar() {
//        ClienteDao c = new ClienteDao();
//        c.edit_XML(ClientesList, );
    }

    public boolean eliminarCliente(String idEliminar) {
        if (existeCliente(Integer.parseInt(idEliminar))) {
            ClienteDao c = new ClienteDao();
            c.remove(idEliminar);
            c.close();
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Cliente no existe");
            return false;
        }

    }

    public boolean existeCliente(int idEliminar) {
        if (ClientesList != null) {
            for (cliente c : ClientesList) {
                if (c.getIdCliente() == idEliminar) {
                    return true;
                }
            }
        }
        return false;
    }
}

/* 
    @Override
    public String[] buscar(int busca){
        String[] parts;        
         try {
            /*Si existe el fichero
            if (FClientes.exists()) {
                /*Abre un flujo de lectura a el fichero
                BufferedReader Flee = new BufferedReader(new FileReader(FClientes));
                String Slinea;
                /*Lee el fichero line a linea hasta llegar a la ultima
                while ((Slinea = Flee.readLine()) != null) {
                    parts = Slinea.split(";");//Crear un vector con el contenido de la linea leida
                    if (Integer.parseInt(parts[0])==busca) {
                        return parts;
                    }                                   
                }
                /*Cierra el flujo
                Flee.close();               
            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (Exception ex) {
            System.err.println("Error al leer el fichero");            
        } 
         return null;
    }
    
       
    @Override
    public void eliminar(int eliminar){
         String[] parts;        
         try {
            /*Si existe el fichero
            if (FClientes.exists()) {
                /*Abre un flujo de lectura a el fichero             
                String Slinea;
                File file= File.createTempFile("CopiaTemporal.txt", null);
                FileInputStream in = new FileInputStream(FClientes);
	        FileOutputStream out = new FileOutputStream(file);                
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }                
                BufferedWriter bw = new BufferedWriter(new FileWriter(FClientes));
                bw.write("");                
                BufferedReader Flee = new BufferedReader(new FileReader(file));                
                
                while ((Slinea = Flee.readLine()) != null) {
                    parts = Slinea.split(";");//Crear un vector con el contenido de la linea leida
                    if (Integer.parseInt(parts[0])!=eliminar) {
                        EF.escribirFichero(Slinea, FClientes);
                    }                                    
                }
                /*Cierra el flujo
                Flee.close();                
            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (Exception ex) {
            System.err.println("Error al leer el fichero");            
        } 
         
    }
    
    @Override
     public void editar(String datos){
         String[] parts1;
         parts1=datos.split(";");
         String[] parts;        
         try {
            /*Si existe el fichero
            if (FClientes.exists()) {
                /*Abre un flujo de lectura a el fichero               
                String Slinea;
                File file= File.createTempFile("CopiaTemporal.txt", null);
                FileInputStream in = new FileInputStream(FClientes);
	        FileOutputStream out = new FileOutputStream(file);                
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }                
                BufferedWriter bw = new BufferedWriter(new FileWriter(FClientes));
                bw.write("");                
                BufferedReader Flee = new BufferedReader(new FileReader(file));                
                
                while ((Slinea = Flee.readLine()) != null) {
                    parts = Slinea.split(";");//Crear un vector con el contenido de la linea leida
                    if (Integer.parseInt(parts[0])==Integer.parseInt(parts1[0])) {
                        agregar(datos);
                    }else{
                        EF.escribirFichero(Slinea, FClientes);
                    } 
                    
                }
                /*Cierra el flujo
                Flee.close();                
            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (Exception ex) {
            System.err.println("Error al leer el fichero");            
        } 
         
    }
     
     public boolean existe(int busca){
         String[] parts;        
         try {
            /*Si existe el fichero
            if (FClientes.exists()) {
                /*Abre un flujo de lectura a el fichero
                BufferedReader Flee = new BufferedReader(new FileReader(FClientes));
                String Slinea;
                /*Lee el fichero line a linea hasta llegar a la ultima
                while ((Slinea = Flee.readLine()) != null) {
                    parts = Slinea.split(";");//Crear un vector con el contenido de la linea leida
                    if (Integer.parseInt(parts[0])==busca) {
                        return true;
                    }                                   
                }
                /*Cierra el flujo
                Flee.close();               
            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (Exception ex) {
            System.err.println("Error al leer el fichero");            
        } 
         return false;
     }

    @Override
    void agregar(String datos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
