/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.AutorDao;
import DatosFichero.AutorTxt;
import DatosInterfaces.InterfaceAutor;
import Dominio.Autor;
import InterfazDatosFichero.InterfazTxtAutor;
import NegocioInterfaces.InterfazNegAutor;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alumno Mañana
 */
public class AutorNegocio implements InterfazNegAutor{
    private final InterfaceAutor autorDao;

    public AutorNegocio() {
        this.autorDao = new AutorDao();
    }
    
    private static InterfazTxtAutor autorTxt = new AutorTxt();
    
    public List <Autor> autores(){
        List <Autor> aut = null;
        try{
            List <Autor> a = autorDao.seleccionar();
            aut = a;
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
        return aut;
    }
    
    public Autor autor(int id){
        Autor nAutor = null;
        List <Autor> lista = autores();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdautor() == id){
                nAutor = lista.get(i);
            }
        }   
        return nAutor;
    }
    
    //FILTRADO DE AUTORES POR NOMBRE O APELLIDO
    public void buscarAutorNombre(String titulo){
       
        for (int i = 0; i < autores().size(); i++) {
            if(autores().get(i).getNombre().equals(titulo) ||
                    autores().get(i).getNombre().contains(titulo)){
                System.out.println(autores().get(i));
            }
        }
    }
    
    public void buscarAutorApellido(String titulo){
        for (int i = 0; i < autores().size(); i++) {
            if(autores().get(i).getApellido().equals(titulo) ||
                    autores().get(i).getApellido().contains(titulo)){
                System.out.println(autores().get(i));
            }
        } 
    }
    
    public void actualizarTxt(){
        autorTxt.actualizarFicheroAutor();
    }
    
    
    //HASTA AQUÍ CAPA VISTA
    
    public Autor darAlta(){
        Scanner in = new Scanner (System.in);
        InterfaceAutor autorDao = new AutorDao();
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
    
    public void eliminarAutor(){
        System.out.println("TABLA AUTORES:\n\n");
        for (int i = 0; i < autores().size(); i++) {
            System.out.println(autores().get(i).toString());
        }
        Scanner in = new Scanner (System.in);
        System.out.println("Inserte el ID del autor que quiere eliminar");
        int id = in.nextInt();
        System.out.println("Pulse intro para continuar");
        in.nextLine();
        InterfaceAutor autorDao = new AutorDao();
        autorDao.eliminar(autor(id));
    }
    
    public void autorActualizar(){
        InterfaceAutor autorDao = new AutorDao();
        int opcion;
        opcion = -1;
        System.out.println("TABLA AUTORES:\n\n");
        for (int i = 0; i < autores().size(); i++) {
            System.out.println(autores().get(i).toString());
        }
        Scanner in = new Scanner (System.in);
        System.out.println("Inserte el ID del autor que quiere actualizar");
        int id = in.nextInt();
        System.out.println("Pulse intro para continuar");
        in.nextLine();
        System.out.println(autor(id).toString());
        System.out.println("Seleccione qué el atributo a cambiar:");
        
        
        /***************************************************/
        
        while (opcion != 0){
            
            System.out.println("\tATRIBUTOS");
            System.out.println(autor(id).getIdautor() + " " + autor(id).getNombre() +" " + autor(id).getApellido());
            System.out.println("-----------------------------\n");
            System.out.println("1 - NOMBRE: "+autor(id).getNombre());
            System.out.println("2 - APELLIDOS "+ autor(id).getApellido());
            System.out.println("3 - NACIONALIDAD: "+autor(id).getNacionalidad());
            System.out.println("4 - FECHA DE NACIMIENTO: "+autor(id).getFechaNac());
            System.out.println("0 - Salir");
            System.out.println("Selecciones una opción");
            opcion = in.nextInt();
            in.nextLine();
            switch(opcion){
                case 1:
                    System.out.println("Introduzca el nuevo nombre:");
                    String nombre = in.nextLine();
                    autor(id).setNombre(nombre);
                    autorDao.actualizar(autor(id));
                    break;
                
                case 2:
                    System.out.println("Introduzca los nuevos apellidos:");
                    String apellido = in.nextLine();
                    autor(id).setApellido(apellido);
                    autorDao.actualizar(autor(id));
                    break;
                
                case 3:
                    System.out.println("Introduzca la nacionalidad:");
                    String nac = in.nextLine();
                    autor(id).setNacionalidad(nac);
                    autorDao.actualizar(autor(id));
                    break;
                
                case 4:
                    System.out.println("Introduzca la Fecha de Naciomiento:");
                    String fn = in.nextLine();
                    autor(id).setFechaNac(Date.valueOf(fn));
                    autorDao.actualizar(autor(id));
                    break;
                
                case 0:
                    break;
                
                default:
                    break;
            }
        }
        
    }
}
