/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import ManejoArchivos.ManejoDeArchivos;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Alumno Mañana
 */
public class Usuario {
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
        return  "usuario=" + usuario + ", clave=" + clave + ", fechaAlt=" 
                + fechaAlt + ", nombre=" + nombre + ", apellido=" + apellido 
                + ", fechaNac=" + fechaNac + '}';
    }
    
    public String escribir() {
        return  usuario + "º" + clave + "º" + fechaAlt + "º" + nombre
                + "º" + apellido + "º" + fechaNac + "º" +"/"  ;
    }
    
    public static List <Usuario> usuarios(){
        UsuarioDao usuarioDao = new UsuarioDao();
        List <Usuario> usuario = null;
        try{
            List <Usuario> u = usuarioDao.seleccionar();
            usuario = u;
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
        return usuario;
    }
    
    
    //Devuelve un usuario comprobando col[0], la primary key a partir
    //del archivo de texto de usaurios.
    public static Usuario archivoPk(String primaryKey){
        Usuario fileUsur = null;
        
        String nombre [] = ManejoDeArchivos.cadenaArchivo("usuario.txt").split("/");
        for (int i = 0; i < nombre.length; i++) {
            System.out.println(nombre[i]);
            String col [] = nombre[i].split("º");
            if (col[0].equals(primaryKey)){
                fileUsur = new Usuario (col[0],col[1],Date.valueOf(col[2]));
            }else{
                System.out.println("ENTRADA NO ENCONTRADA");

            }
        }
        return fileUsur;
    }
    
    
    //Comprueba si el nombre introducido ya existe
    public static boolean entrada(String usuario){
        Usuario usr = null;
        boolean existe = false;
        for (int i = 0; i < usr.usuarios().size() ; i++) {
            if(usuario.equals(usr.usuarios().get(i).usuario)){
                existe = true;
            }else{
                existe = false;
            }
        }
        return existe;
    }
    
    public static void iniciarSesion(){
        Usuario usr = null;
        String usuario = "";
        Scanner in = new Scanner (System.in);
        System.out.println("Introduzca su usuario:");
        usuario = in.nextLine();
        if(usr.entrada(usuario)){
            System.out.println("Introduzca la contraseña:");
            String clave = in.nextLine();
            while(!clave.equals(usr.archivoPk(usuario).clave)){
                System.out.println("Contraseña incorrecta, pruebe de nuevo:");
                clave = in.nextLine();
            }
        }else{
            System.out.println("Este usuario no existe ¿Desea darse de alta?");
            //Funcion dardealta
        }
    }
    
    public static void darAlta(){
        Usuario usr = null;
        Scanner in = new Scanner (System.in);
        UsuarioDao usuarioDao = new UsuarioDao();
        System.out.println("Introduzca su usuario");
        String usar = in.nextLine();
        usr.setUsuario(usar);
        System.out.println("Introduzca su contraseña:");
        String clave = in.nextLine();
        usr.setClave(clave);
        usr.setFechaAlt(Date.valueOf(LocalDate.now()));
        usuarioDao.insertar(usr);
    }
    
}
