/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.EditorialDao;
import DatosInterfaces.InterfaceEditorial;

/**
 *
 * @author Alumno Mañana
 */
public class EditorialNegocio {
    private final InterfaceEditorial editorialDao;

    public EditorialNegocio() {
        this.editorialDao = new EditorialDao();
    }
    
    
}
