/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Datos.UsuarioDao;
import DatosInterfaces.InterfaceUsuario;
import ManejoArchivos.ManejoDeArchivos;
import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author MaximoMestriner
 */
public class Usuario implements Serializable{
    private String usuario;
    private String clave;
    private Date fechaAlt;
    private String nombre;
    private String apellido;
    private Date fechaNac;

    public Usuario() {
    }

    public Usuario(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
        this.fechaAlt = Date.valueOf(LocalDate.now());
    }

    public Usuario(String usuario, String clave, Date fechaAlt) {
        this.usuario = usuario;
        this.clave = clave;
        this.fechaAlt = fechaAlt;
    }
    

    public Usuario(String usuario, String clave, Date fechaAlt, String nombre, String apellido, Date fechaNac) {
        this.usuario = usuario;
        this.clave = clave;
        this.fechaAlt = fechaAlt;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
    }

    
    
    public Usuario(String usuario, String clave, String nombre, String apellido,  Date fechaNac) {
        
        this.usuario = usuario;
        this.clave = clave;
        this.fechaAlt = Date.valueOf(LocalDate.now());
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaAlt() {
        return fechaAlt;
    }

    public void setFechaAlt(Date fechaAlt) {
        this.fechaAlt = fechaAlt;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    @Override
    public String toString() {
        return  "USUARIO: " + usuario + "| pswrd: " + clave + "| fechaAlt: " 
                + fechaAlt + "| nombre: " + nombre + "| apellido: " + apellido 
                + "| fechaNac: " + fechaNac + '|';
    }
    
    public String escribir() {
        return  usuario + "º" + clave + "º" + fechaAlt + "º" + nombre
                + "º" + apellido + "º" + fechaNac + "º" +"/"  ;
    }
    
}

