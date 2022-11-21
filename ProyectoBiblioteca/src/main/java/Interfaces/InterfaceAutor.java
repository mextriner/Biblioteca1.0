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
    
    //DEVUELVE UN LIST CON TODOS LOS AUTORES DE LA BASE DE DATOS
    public List <Autor> seleccionar() throws SQLException ;
    
    //MÉTODO QUE INSERTA UNA PERSONA EN EL SISTEMA
    public int insertar (Autor autor);
    
    //ACTUALIZA LA ENTRADA DE UN AUTOR EN LA BASE DE DATOS
    public int actualizar (Autor autor);
    
    //ELIMINA LA ENTRADA DE UN AUTOR
    public int eliminar (Autor autor);
}

