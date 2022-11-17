/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Dominio.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Alumno Mañana
 */
public interface InterfaceUsuario {
    
    
    //DEVUELVE UNA LIST DE USUARIOS CON LA CLAVE ENCRIPTADA
    public List<Usuario> seleccionar() throws SQLException;
    
    
    //DEVUELVE UNA LIST DE USUARIOS CON LA CLAVE DESENCRIPTADA
    public List<Usuario> seleccionarUsuarioClave() throws SQLException;
    
    
    //INSERTA UN USUARIO EN LA BASE DE DATOS
    public int insertar (Usuario usuario);
    
    //INSERTA UN USUARIO EN LA BASE DE DATOS CON TODOS LOS CAMPOS
    public int insertarAll (Usuario usuario);
    
    //APLICA EL UPDATE SOBRE UN USUARIO YA EXISTENTE EN LA BASE DE DATOS
    public int actualizarId (Usuario usuario, String prevUsuario);
    
    public int actualizar (Usuario usuario);
     
    //ELIMINA UN USUARIO DE LA BASE DE DATOS 
    public int eliminar (Usuario usuario);
    
}
