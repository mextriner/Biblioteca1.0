/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import Datos.EditorialDao;
import Interfaces.InterfaceEditorial;
import ManejoArchivos.ManejoDeArchivos;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MaximoMestriner
 */
public class Editorial implements Serializable{
    int idEditorial;
    String nombre;
    String direccion;

    public Editorial(int idEditorial, String nombre, String direccion) {
        this.idEditorial = idEditorial;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Editorial(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Editorial() {
    }

    public int getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Editorial{" + "idEditorial=" + idEditorial + ", nombre=" + nombre + ", direccion=" + direccion + '}';
    }
    
    public String escribir() {
        return   idEditorial + "º" + nombre + "º" + direccion + '/';
    }
    
    
    public static List <Editorial> ListarEditorial(){
        InterfaceEditorial editorialDao = new EditorialDao();
        List <Editorial> editorial = null;
        try{
            editorial = editorialDao.seleccionar();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
        return editorial;
    }
    
    
    public static void actualizarArchivoEditorial(){
        Usuario usuario = null;
        String contenido ="";
        for (int i = 0; i < ListarEditorial().size(); i++) {
            
            contenido += (ListarEditorial().get(i).escribir());
        }
        ManejoDeArchivos.escribirArchivo("editorial.txt",contenido);
    }
}
