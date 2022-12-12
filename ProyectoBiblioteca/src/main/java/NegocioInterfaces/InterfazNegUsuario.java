/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NegocioInterfaces;

import Dominio.Usuario;
import java.util.List;

/**
 *
 * @author Alumno Mañana
 */
public interface InterfazNegUsuario {
    
    
    public List <Usuario> listarUsuarios();
     
    public List <Usuario> listarUsuariosClave();
      
    public Usuario comprobarId(String primaryKey);
    
    public boolean entrada(String nom);
    
    public void eliminarUsuario();
    
    public void eliminarUsuario(Usuario usuraio);
    
    public void iniciarSesion(String admin);
    
    public void actualizarArchivoUsuarios();
    
    public Usuario darAlta();
    
    public void actualizarUsuario(String usu);
    
    public void buscarUsuario(String usur);
    
    public void buscarNombre(String usur);
    
    public void buscarApellido(String usur);
}
