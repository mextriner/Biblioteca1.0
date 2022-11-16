/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Dominio.Autor;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Maximo
 */
public interface InterfaceAutor {
    
    
    public List <Autor> seleccionar() throws SQLException ;
    
    //MÉTODO QUE INSERTA UNA PERSONA EN EL SISTEMA
    public int insertar (Autor autor);
    
    public int actualizar (Autor autor);
    
    public int eliminar (Autor autor);
}

