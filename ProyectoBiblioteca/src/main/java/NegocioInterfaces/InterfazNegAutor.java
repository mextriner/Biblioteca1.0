/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NegocioInterfaces;

import Dominio.Autor;
import java.util.List;

/**
 *
 * @author Alumno Mañana
 */
public interface InterfazNegAutor {
    public List <Autor> autores();
    
    public Autor autor(int id);
    
    public void buscarAutorNombre(String titulo);
    
    public void buscarAutorApellido(String titulo);
    
    public Autor darAlta();
    
    public void eliminarAutor();
    
    public void autorActualizar();
    
    public void actualizarTxt();
}
