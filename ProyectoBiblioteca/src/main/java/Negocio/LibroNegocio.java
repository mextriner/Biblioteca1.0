/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.LibroDao;
import DatosInterfaces.InterfaceLibro;

/**
 *
 * @author Alumno Mañana
 */
public class LibroNegocio {
    private final InterfaceLibro libroDao;

    public LibroNegocio() {
        this.libroDao = new LibroDao();
    }
}