/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazDatosFichero;

import Dominio.Usuario;

/**
 *
 * @author Alumno Mañana
 */
public interface InterfazTxtUsuario {
    
    
    public Usuario archivoPk(String primaryKey);
    
     public void actualizarArchivoUsu();
}
