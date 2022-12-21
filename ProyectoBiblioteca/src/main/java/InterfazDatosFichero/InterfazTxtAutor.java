/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazDatosFichero;

import Dominio.Autor;

/**
 *
 * @author Alumno Mañana
 */
public interface InterfazTxtAutor {
    public Autor archivoPk(int primaryKey);
    
    public void actualizarFicheroAutor();
    
    
}
