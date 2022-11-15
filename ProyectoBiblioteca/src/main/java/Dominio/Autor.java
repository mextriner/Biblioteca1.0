/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import ManejoArchivos.ManejoDeArchivos;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MaximoMestriner
 */
public class Autor {
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
        return "Autor{" + "idautor=" + idautor + ", nombre=" + nombre + ", apellido=" + apellido + ", nacionalidad=" + nacionalidad + ", fechaNac=" + fechaNac + '}';
    }
    
    public String escribir() {
        return idautor + "º" + nombre + 'º' + apellido + 'º' + nacionalidad + 'º' + fechaNac + 'º'+'/' ;
    }
    
    
    public static List <Autor> autores(){
        AutorDao autorDao = new AutorDao();
        List <Autor> aut = null;
        try{
            List <Autor> a = autorDao.seleccionar();
            aut = a;
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
        return aut;
    }
    
    public static Autor autor(int id){
        Autor nAutor = null;
        List <Autor> lista = autores();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdautor() == id){
                nAutor = lista.get(i);
            }
        }
            
        return nAutor;
    }
     
    
    public static Autor archivoPk(int primaryKey){
        Autor fileAutor = null;
        
        String nombre [] = ManejoDeArchivos.cadenaArchivo("autor.txt").split("/");
        for (int i = 0; i < nombre.length; i++) {
            System.out.println(nombre[i]);
            String col [] = nombre[i].split("º");
            if (col[0].equals(primaryKey)){
                fileAutor = new Autor (Integer.valueOf(col[0]),col[1],(col[2]),col[3],Date.valueOf(col[4]));
                
            }else{
                System.out.println("ENTRADA NO ENCONTRADA");
            }
        }
        return fileAutor;
    }
     
    public static void actualizarAutores(){
        Autor autor = null;
        String contenido ="";
        for (int i = 0; i < autor.autores().size(); i++) {
            contenido += (autor.autores().get(i).escribir());
        }
        System.out.println(contenido);
        ManejoDeArchivos.escribirArchivo("autor.txt",contenido);
    }
     
        public static Autor darAlta(){
        Scanner in = new Scanner (System.in);
        AutorDao autorDao = new AutorDao();
        System.out.println("Nombre de Autor");
        String nom = in.nextLine();
        System.out.println("Apellidos de Autor");
        String ape = in.nextLine();
        System.out.println("Nacionalidad de Autor");
        String nac = in.nextLine();
        System.out.println("Fecha de nacimiento de Autor (aaaa-mm-dd)");
        String fn = in.nextLine();
        Autor autor = new Autor (nom,ape,nac,Date.valueOf(fn));
        autorDao.insertar(autor);
        return autor;
    }
    
    
}
