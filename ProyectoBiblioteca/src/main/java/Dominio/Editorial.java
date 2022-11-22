/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import Datos.EditorialDao;
import Interfaces.InterfaceEditorial;
import ManejoArchivos.ManejoDeArchivos;
import static com.ceep.proyectobiblioteca.TestMain.editorialDao;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

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
    
    
    public static List <Editorial> listarEditorial(){
        InterfaceEditorial editorialDao = new EditorialDao();
        List <Editorial> editorial = null;
        try{
            editorial = editorialDao.seleccionar();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
        return editorial;
    }
    
    //DEVUELVE LA DEITORIAL CUYO ID ES IGUAL AL DEL PARÁMETRO
    public static Editorial editorialId(int id){
        Editorial editorial = null;
        for (int i = 0; i < listarEditorial().size(); i++) {
            
            if(listarEditorial().get(i).getIdEditorial() == id){
                editorial = listarEditorial().get(i);
            }
        }
        return editorial;
    }
    
    //A PARTIR DE AQUÍ ES CAPA VISTA
    
    public static void actualizarArchivoEditorial(){
        String contenido ="";
        for (int i = 0; i < listarEditorial().size(); i++) {
            
            contenido += (listarEditorial().get(i).escribir());
        }
        ManejoDeArchivos.escribirArchivo("editorial.txt",contenido);
        }

    public static Editorial darAlta(){
        Scanner in = new Scanner (System.in);
        

        System.out.println("Introduce el Nombre de la Editorial:");
        String nombre = in.nextLine();
        while(nombre.isEmpty()){
            System.out.println("Este campo no puede ser vacío."
                    + " Introduca un valor:");
            nombre = in.nextLine();
        }

        System.out.println("Introduzca la Dirección:");
        String direccion = in.nextLine();
        while(direccion.isEmpty()){
            System.out.println("Este campo no puede ser vacío."
                    + " Introduca un valor:");
            direccion = in.nextLine();
        }
        Editorial editorial = new Editorial(nombre,direccion);
        editorialDao.insertar(editorial);

        return editorial;
    }
    
    public static void eliminarEditorial(){
        
        Scanner in = new Scanner (System.in);
        System.out.println("TABLA Editorial:\n\n");
        for (int i = 0; i < listarEditorial().size(); i++) {
            System.out.println(listarEditorial().get(i).toString());
        }

        System.out.println("Inserte el ID de la editorial que quiere eliminar");
        int id = in.nextInt();
        in.nextLine();
        while(editorialId(id) == null){
            System.out.println("Esta Editorial no existe en la Base de Datos.\n"
                    + "Introduzca un ID correcto.");
            id = in.nextInt();
        }
        editorialDao.eliminar(editorialId(id));
    }
    
    public static void actualizarEditorial(Editorial editorial){
        Scanner in = new Scanner (System.in);
        String bus;
        int opc = -1;
        while(opc != 0){
            System.out.println("\tATRIBUTOS:");
            System.out.println("\t1 - NOMBRE:");
            System.out.println("\t2 - DIRECCIÓN:");
            System.out.println("\t0 - SALIR:");
            opc = in.nextInt();
            in.nextLine();
            switch(opc){
                case 1:
                    System.out.println("Introduzca el Nombre de la Editorial:");
                    bus = in.nextLine();
                    editorial.setNombre(bus);
                    editorialDao.actualizar(editorial);
                    break;
                case 2:
                    System.out.println("Introduzca la Dirección de la Editorial:");
                    bus = in.nextLine();
                    editorial.setDireccion(bus);
                    editorialDao.actualizar(editorial);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Seleccione una de las opciones (0 - 2)");
                    break;
            }
        }
    }
    
    public static void buscarEditorialNombre(String titulo){
        Editorial ed = new Editorial();
        for (int i = 0; i < listarEditorial().size(); i++) {
            if(listarEditorial().get(i).getNombre().equals(titulo) ||
                    listarEditorial().get(i).getNombre().contains(titulo)){
                ed = listarEditorial().get(i);
                System.out.println(ed);
            }
        }
    }
}