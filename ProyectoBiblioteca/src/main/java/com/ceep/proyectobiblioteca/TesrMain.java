/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceep.proyectobiblioteca;

import Dominio.Autor;
import Dominio.AutorDao;
import Dominio.Usuario;
import Dominio.UsuarioDao;
import ManejoArchivos.ManejoDeArchivos;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MaximoMestriner
 */
public class TesrMain {

    /**
     * @param args the command line arguments
     */
    
    
    public static UsuarioDao usuarioDao = new UsuarioDao();
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        /*Date date = new Date(70,4,5);
        Autor autor = new Autor("Patrick","Rothfuss","EEUU",date);*/
//        
//        
//        autorDao.actualizar(autor);

        /*Date date1 = new Date(98,6,28);
        Autor autor1 = new Autor("Máximo","Mestriner","España",date1);
        autorDao.insertar(autor1);
        autorDao.insertar(autor);*/
 
        //Recorro el List de autores y añado a traves del id los autores en el archivp de texto

        /*for (int i = 1; i <= autor.autores().size(); i++) {
            ManejoDeArchivos.agregarArchivo("autores.txt",autor.autor(i).escribir());
        }*/

//        menu();
//        Usuario usuario = new Usuario("mextrienr","1234");
//        
//        usuarioDao.insertar(usuario);
//        for (int i = 0; i < usuario.usuarios().size(); i++) {
//            System.out.println(usuario.usuarios().get(i).toString());
//            ManejoDeArchivos.agregarArchivo("usuario.txt",usuario.usuarios().get(i).escribir());
//        }
    
        Usuario usuario = null;
        usuario = usuario.archivoPk("fran");
        System.out.println(usuario.toString());
    }
    
    //MENU PARA SELECCIONAR EL TIPO DE USUARIO (ADMIN O USUARIO)
    
    public static void menu(){
        
        int opcion;
        Scanner input = new Scanner(System.in);
        opcion = -1;
        /***************************************************/
        
        while (opcion != 0){
            System.out.println("INICIAR SESIÓN");
            System.out.println("-------------------------\n");
            System.out.println("1 - INICIAR SESIÓN COMO ADMIN");
            System.out.println("2 - INICIAR SESIÓN COMO USUARIO");
            System.out.println("0 - Salir");
            System.out.println("Selecciones una opción");
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1:
                    // FUNCION INICIAR SESION (ADMIN)
                    menuEntidad(true);
                    break;
                case 2:
                    // FUNCION INICIAR SESIÓN (USUARIO)
                    menuEntidad(false);
                    break;
                case 0:
                    System.out.println("");
                    break;
                default:
                    // The user input an unexpected choice.
            }
            
            
        }
       
    }
    
    //COMPRUEBA SI ES O NO ADMIN EN BASE A LO CUAL LE PERMITE ACCEDER A UNAS U OTRASENTIDADES
    
    public static void menuEntidad(boolean admin){
        
        int opcion;
        Scanner input = new Scanner(System.in);
        opcion = -1;
        /***************************************************/
     //SI ES ADMIN ACCEDE A TODO EL CRUD DE LAS TABLAS
     //SI ES USUARIO SOLO PUEDE BUSCAR LIBRSO AUTORES Y CATEGORIAS
        
        while (opcion != 0){
            if (admin){
                System.out.println("Choose from these choices");
                System.out.println("-------------------------\n");
                System.out.println("1 - LIBRO");
                System.out.println("2 - AUTOR");
                System.out.println("3 - CATEGORÍA");
                System.out.println("4 - EDITORIAL");
                System.out.println("5 - PROVEEDOR");
                System.out.println("6 - UNIDAD");
                System.out.println("7 - USUARIO");
                System.out.println("0 - Salir");
                opcion = input.nextInt();
                input.nextLine();

                switch (opcion) {
                    case 1:
                        // Perform "original number" case.
                        menuCrud("LIBRO",admin);
                        break;
                    case 2:
                        // Perform "encrypt number" case.
                        menuCrud("AUTOR",admin);
                        break;
                    case 3:
                        // Perform "decrypt number" case.
                        menuCrud("CATEGORÍA",admin);
                        break;
                    case 4:
                        // Perform "decrypt number" case.
                        menuCrud("EDITORIAL",admin);
                        break;
                    case 5:
                        // Perform "decrypt number" case.
                        menuCrud("PROVEEDOR",admin);
                        break;
                    case 6:
                        // Perform "decrypt number" case.
                        menuCrud("UNIDAD",admin);
                        break;
                    case 7:
                        // Perform "decrypt number" case.
                        menuCrud("USUARIO",admin);
                        break;
                    case 0:
                        System.out.println("");
                        break;
                    default:
                        // The user input an unexpected choice.
                }
            
            }else if(!admin){
                System.out.println("Choose from these choices");
                System.out.println("-------------------------\n");
                System.out.println("1 - LIBRO");
                System.out.println("2 - AUTOR");
                System.out.println("3 - CATEGORÍA");
                System.out.println("0 - Salir");
                opcion = input.nextInt();
                input.nextLine();

                switch (opcion) {
                    case 1:
                        // Perform "original number" case.
                        break;
                    case 2:
                        // Perform "encrypt number" case.
                        break;
                    case 3:
                        // Perform "decrypt number" case.
                        break;
                    case 0:
                        System.out.println("");
                        break;
                    default:
                        // The user input an unexpected choice.
                }
            }
            
        }
       
    }
    
    //COMPRUEBA LA ENTIDAD ENVIADA Y SI ES ADMIN Y LE DA ACCESO A UNAS U
    //OTRAS OPERACIONES
    
    public static void menuCrud(String entidad, boolean admin){
         int opcion;
        Scanner input = new Scanner(System.in);
        opcion = -1;
        /***************************************************/
        while (opcion != 0){
            if(admin){
                System.out.println("Choose from these choices");
                System.out.println("-------------------------\n");
                System.out.println("1 - BUSCAR");
                System.out.println("2 - INSERTAR");
                System.out.println("3 - ACTUALIZAR");
                System.out.println("4 - ELIMINAR");
                System.out.println("0 - Salir");
                opcion = input.nextInt();
                input.nextLine();
                
                switch(entidad){
                    case "LIBRO":
                        switch (opcion) {
                            case 1:
                                // Perform "original number" case.
                                break;
                            case 2:
                                // Perform "encrypt number" case.
                                break;
                            case 3:
                                // Perform "decrypt number" case.
                                break;
                            case 4:
                                // Perform "decrypt number" case.
                                break;
                            case 0:
                                System.out.println("");
                                break;
                            default:
                                // The user input an unexpected choice.
                        }
                        break;
                    case "AUTOR":
                        switch (opcion) {
                            case 1:
                                // Perform "original number" case.
                                break;
                            case 2:
                                // Perform "encrypt number" case.
                                break;
                            case 3:
                                // Perform "decrypt number" case.
                                break;
                            case 4:
                                // Perform "decrypt number" case.
                                break;
                            case 0:
                                System.out.println("");
                                break;
                            default:
                                // The user input an unexpected choice.
                        }
                        break;
                    case "CATEGORÍA":
                        switch (opcion) {
                            case 1:
                                // Perform "original number" case.
                                break;
                            case 2:
                                // Perform "encrypt number" case.
                                break;
                            case 3:
                                // Perform "decrypt number" case.
                                break;
                            case 4:
                                // Perform "decrypt number" case.
                                break;
                            case 0:
                                System.out.println("");
                                break;
                            default:
                                // The user input an unexpected choice.
                        }
                        break;
                    case "EDITORIAL":
                        switch (opcion) {
                            case 1:
                                // Perform "original number" case.
                                break;
                            case 2:
                                // Perform "encrypt number" case.
                                break;
                            case 3:
                                // Perform "decrypt number" case.
                                break;
                            case 4:
                                // Perform "decrypt number" case.
                                break;
                            case 0:
                                System.out.println("");
                                break;
                            default:
                                // The user input an unexpected choice.
                        }
                        break;
                    case "UNIDAD":
                        switch (opcion) {
                            case 1:
                                // Perform "original number" case.
                                break;
                            case 2:
                                // Perform "encrypt number" case.
                                break;
                            case 3:
                                // Perform "decrypt number" case.
                                break;
                            case 4:
                                // Perform "decrypt number" case.
                                break;
                            case 0:
                                System.out.println("");
                                break;
                            default:
                                // The user input an unexpected choice.
                        }
                        break;
                    case "PROVEEDOR":
                        switch (opcion) {
                            case 1:
                                // Perform "original number" case.
                                break;
                            case 2:
                                // Perform "encrypt number" case.
                                break;
                            case 3:
                                // Perform "decrypt number" case.
                                break;
                            case 4:
                                // Perform "decrypt number" case.
                                break;
                            case 0:
                                System.out.println("");
                                break;
                            default:
                                // The user input an unexpected choice.
                        }
                        break;
                    case "USUARIO":
                        switch (opcion) {
                            case 1:
                                // Perform "original number" case.
                                break;
                            case 2:
                                // Perform "encrypt number" case.
                                break;
                            case 3:
                                // Perform "decrypt number" case.
                                break;
                            case 4:
                                // Perform "decrypt number" case.
                                break;
                            case 0:
                                System.out.println("");
                                break;
                            default:
                                // The user input an unexpected choice.
                        }
                        break;
                }
            }else if(!admin){
                System.out.println("Choose from these choices");
                System.out.println("-------------------------\n");
                System.out.println("1 - BUSCAR");
                System.out.println("2 - INSERTAR");
                System.out.println("3 - ACTUALIZAR");
                System.out.println("4 - ELIMINAR");
                System.out.println("0 - Salir");
                opcion = input.nextInt();
                input.nextLine();
                
                switch(entidad){
                    case "LIBRO":
                        switch (opcion) {
                            case 1:
                                // Perform "original number" case.
                                break;
                            case 2:
                                // Perform "encrypt number" case.
                                break;
                            case 3:
                                // Perform "decrypt number" case.
                                break;
                            case 4:
                                // Perform "decrypt number" case.
                                break;
                            case 0:
                                System.out.println("");
                                break;
                            default:
                                // The user input an unexpected choice.
                        }
                        break;
                    case "AUTOR":
                        switch (opcion) {
                            case 1:
                                // Perform "original number" case.
                                break;
                            case 2:
                                // Perform "encrypt number" case.
                                break;
                            case 3:
                                // Perform "decrypt number" case.
                                break;
                            case 4:
                                // Perform "decrypt number" case.
                                break;
                            case 0:
                                System.out.println("");
                                break;
                            default:
                                // The user input an unexpected choice.
                        }
                        break;
                    case "CATEGORÍA":
                        switch (opcion) {
                            case 1:
                                // Perform "original number" case.
                                break;
                            case 2:
                                // Perform "encrypt number" case.
                                break;
                            case 3:
                                // Perform "decrypt number" case.
                                break;
                            case 4:
                                // Perform "decrypt number" case.
                                break;
                            case 0:
                                System.out.println("");
                                break;
                            default:
                                // The user input an unexpected choice.
                        }
                        break;
                    case "EDITORIAL":
                        switch (opcion) {
                            case 1:
                                // Perform "original number" case.
                                break;
                            case 2:
                                // Perform "encrypt number" case.
                                break;
                            case 3:
                                // Perform "decrypt number" case.
                                break;
                            case 4:
                                // Perform "decrypt number" case.
                                break;
                            case 0:
                                System.out.println("");
                                break;
                            default:
                                // The user input an unexpected choice.
                        }
                        break;
                    case "UNIDAD":
                        switch (opcion) {
                            case 1:
                                // Perform "original number" case.
                                break;
                            case 2:
                                // Perform "encrypt number" case.
                                break;
                            case 3:
                                // Perform "decrypt number" case.
                                break;
                            case 4:
                                // Perform "decrypt number" case.
                                break;
                            case 0:
                                System.out.println("");
                                break;
                            default:
                                // The user input an unexpected choice.
                        }
                        break;
                    case "PROVEEDOR":
                        switch (opcion) {
                            case 1:
                                // Perform "original number" case.
                                break;
                            case 2:
                                // Perform "encrypt number" case.
                                break;
                            case 3:
                                // Perform "decrypt number" case.
                                break;
                            case 4:
                                // Perform "decrypt number" case.
                                break;
                            case 0:
                                System.out.println("");
                                break;
                            default:
                                // The user input an unexpected choice.
                        }
                        break;
                    case "USUARIO":
                        switch (opcion) {
                            case 1:
                                // Perform "original number" case.
                                break;
                            case 2:
                                // Perform "encrypt number" case.
                                break;
                            case 3:
                                // Perform "decrypt number" case.
                                break;
                            case 4:
                                // Perform "decrypt number" case.
                                break;
                            case 0:
                                System.out.println("");
                                break;
                            default:
                                // The user input an unexpected choice.
                        }
                        break;
                }
            }
            
        }
    }
}
