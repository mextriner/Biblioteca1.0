 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Datos.AutorDao;
import DatosInterfaces.InterfaceAutor;
import ManejoArchivos.ManejoDeArchivos;
import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MaximoMestriner
 */
public class Autor implements Serializable{
    private int idautor;
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private Date fechaNac;

    public Autor() {
    }

    public Autor(String nombre, String apellido, String nacionalidad, Date fechaNac) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.fechaNac = fechaNac;
    }

    
    
    public Autor(int idautor, String nombre, String apellido, String nacionalidad, Date fechaNac) {
        this.idautor = idautor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.fechaNac = fechaNac;
    }

    public int getIdautor() {
        return idautor;
    }

    public void setIdautor(int idautor) {
        this.idautor = idautor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    @Override
    public String toString() {
        return "Autor:" + "ID: " + idautor + "| Nombre: " + nombre + "| Apellido: " + apellido + "| Nacionalidad: " + nacionalidad + "| Fecha Nacimiento: " + fechaNac + '|';
    }
    
    public String escribir() {
        return idautor + "º" + nombre + 'º' + apellido + 'º' + nacionalidad + 'º' + fechaNac + 'º'+'/' ;
    }
    
    
    
    

}
