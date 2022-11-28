/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.UsuarioDao;
import DatosInterfaces.InterfaceUsuario;

/**
 *
 * @author Alumno Mañana
 */
public class UsuarioNegocio {
    
    private final InterfaceUsuario usuarioDao;

    public UsuarioNegocio() {
        this.usuarioDao = new UsuarioDao();
    }
    
    
}
