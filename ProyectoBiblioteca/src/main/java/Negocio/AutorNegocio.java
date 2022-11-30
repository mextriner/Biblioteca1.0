/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.AutorDao;
import DatosInterfaces.InterfaceAutor;

/**
 *
 * @author Alumno Mañana
 */
public class AutorNegocio {
    private final InterfaceAutor autorDao;

    public AutorNegocio() {
        this.autorDao = new AutorDao();
    }
    
    
}
