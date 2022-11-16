/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Dominio.Libro;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Maximo
 */
public interface InterfaceLibro {
    public List <Libro> seleccionar() throws SQLException ;
    
    
    
    //MÉTODO QUE INSERTA UNA PERSONA EN EL SISTEMA
    public int insertar (Libro libro);
    
    public int actualizar (Libro libro);
    
    public int eliminar (Libro libro);
}
