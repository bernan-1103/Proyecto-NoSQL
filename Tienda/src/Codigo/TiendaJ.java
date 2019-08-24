package Codigo;

import Interface.MenuPrincipal;
import Interface.ServicioTecnico;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Laboratorios
 */
abstract class TiendaJ{

    public static void main(String[] args) {
       new MenuPrincipal().setVisible(true);
       
       ServicioTecnico.conexionServidor();
    }
}
