/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceep.proyectobiblioteca;

import Dominio.Autor;
import Datos.AutorDao;
import Dominio.Libro;
import Datos.LibroDao;
import Dominio.Usuario;
import Datos.UsuarioDao;
import static Dominio.Usuario.entrada;
import ManejoArchivos.ManejoDeArchivos;
import java.sql.Date;
import java.sql.SQLException;
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
    
    public static Scanner input = new Scanner(System.in);
    
    public static Usuario administrador = new Usuario("admin","1234");
    
    public static Usuario usuario = new Usuario();
    
    public static void main(String[] args) {
        // TODO code application logic here
        menu();
//        Libro libro = new Libro("999999999","Juego de Tronos","español",Date.valueOf("2002-05-04"),true,"/C:/Users/mextr/OneDrive/Documents/GitHub/Biblioteca1.0/img/jdt.jpg");
//        LibroDao libroDao = new LibroDao();
//        libroDao.actualizar(libro);
        
    }
    
    //MENU PARA SELECCIONAR EL TIPO DE USUARIO (ADMIN O USUARIO)
    
    public static void menu(){
        Usuario.actualizarArchivoUsuarios();
        int opcion;
        
        opcion = -1;
        /***************************************************/
        
        while (opcion != 0){
            
            System.out.println("\tBIBLIOTECA VIRTUAL\n");
            System.out.println("INICIAR SESIÓN");
            System.out.println("-----------------------------\n");
            System.out.println("1 - INICIAR SESIÓN COMO ADMIN");
            System.out.println("2 - INICIAR SESIÓN COMO USUARIO");
            System.out.println("3 - REGISTRAR NUEVO USUARIO");
            System.out.println("0 - Salir");
            System.out.println("Selecciones una opción");
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1:
                    // FUNCION INICIAR SESION (ADMIN)
                    administrador.iniciarSesion(administrador.getUsuario());
                    menuEntidad(true);
                    break;
                case 2:
                    // FUNCION INICIAR SESIÓN (USUARIO)
                    System.out.println("Introduzca su usuario:");
                    String nom = input.nextLine();
                    if(usuario.entrada(nom)){
                        System.out.println("Introduzca la contraseña:");
                        String clave = input.nextLine();
                        while(!clave.equals(Usuario.comprobarId(nom).getClave())){
                            System.out.println("Contraseña incorrecta, pruebe de nuevo:");
                            clave = input.nextLine();
                        }
                        usuario = Usuario.comprobarId(nom);
                        menuEntidad(false);
                    }else{
                        char ac;
                        System.out.println("Este usuario no existe ¿Desea darse de alta? \n si(s) / no(n)");
                        ac = input.nextLine().charAt(0);
                        while(ac != 's' && ac != 'n'){
                            System.out.println("Introduzca si (s) o no (n)");
                            ac = input.nextLine().charAt(0);
                        }
                        if (ac == 's'){
                            usuario = usuario.darAlta();
                        }else{
                            System.out.println("Operación cancelada.");
                            break;
                        }

                    }
                    
                    break;
                case 3:
                    // REGISTRAR
                    usuario = usuario.darAlta();
                    menuEntidad(false);
                    break;
                case 0:
                    Usuario.actualizarArchivoUsuarios();
                    System.out.println("");
                    break;
                default:
                    
                    System.out.println("Seleccione una opción entre 0 y 3");
                    // The user input an unexpected choice.
            }
            
            
        }
       
    }
    
    //COMPRUEBA SI ES O NO ADMIN EN BASE A LO CUAL LE PERMITE ACCEDER A UNAS U OTRASENTIDADES
    
    public static void menuEntidad(boolean admin){
        
        
        int opcion;
        
        opcion = -1;
        /***************************************************/
     //SI ES ADMIN ACCEDE A TODO EL CRUD DE LAS TABLAS
     //SI ES USUARIO SOLO PUEDE BUSCAR LIBRSO AUTORES Y CATEGORIAS
        
        while (opcion != 0){
            if (admin){
                System.out.println("\n\n"+"ADMINISTRADOR");
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
                        System.out.println("Seleccione una opción entre 0 y 7");
                }
            
            }else if(!admin){
                
                Usuario.actualizarArchivoUsuarios();
                System.out.println("\n\n"+"USUARIO: "+ usuario.getUsuario());
                System.out.println("-------------------------\n");
                System.out.println("1 - LIBROS");
                System.out.println("2 - AUTORES");
                System.out.println("3 - CATEGORÍAS");
                System.out.println("4 - MI CUENTA");
                System.out.println("0 - Salir");
                opcion = input.nextInt();
                input.nextLine();

                switch (opcion) {
                    case 1:
                        menuCrud("LIBRO",admin);
                        break;
                    case 2:
                        menuCrud("AUTOR",admin);
                        break;
                    case 3:
                        menuCrud("CATEGORÍA",admin);
                        break;
                    case 4:
                        menuCrud("CUENTA",admin);
                        break;
                    case 0:
                        System.out.println("");
                        break;
                    default:
                        System.out.println("Seleccione una opción entre 0 y 3");
                }
            }
            
        }
       
    }
    
    //COMPRUEBA LA ENTIDAD ENVIADA Y SI ES ADMIN Y LE DA ACCESO A UNAS U
    //OTRAS OPERACIONES
    
    public static void menuCrud(String entidad, boolean admin){
         int opcion;
       
        opcion = -1;
        /***************************************************/
        while (opcion != 0){
            if(admin){
                System.out.println("\n\nADMINISTRADOR: "+entidad);
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
                        Libro lb = new Libro();
                        switch (opcion) {
                            case 1:
                                System.out.println("LISTA DE LIBROS");
                                for (int i = 0; i < lb.listarLibro().size(); i++) {
                                    System.out.println(lb.listarLibro().get(i));
                                }
                                break;
                            case 2:
                                System.out.println("INTRODUCIR LIBRO:");
                                lb.darAlta();
                                break;
                            case 3:
                                System.out.println("ACTUALIZAR LIBRO");
                                lb.libroActualizar();
                                break;
                            case 4:
                                System.out.println("ELIMINAR LIBRO");
                                lb.eliminarLibro();
                                // Perform "decrypt number" case.
                                break;
                            case 0:
                                Libro.actualizarArchivoLibros();
                                System.out.println("");
                                break;
                            default:
                                System.out.println("Seleccione una opción entre 0 y 4");
                        }
                        break;
                    case "AUTOR":
                        switch (opcion) {
                            case 1:
                                
                                //BUSCAR AUTORES
                                System.out.println("BUSCADOR DE AUTORES\n\n");
                                for (int i = 0; i < Autor.autores().size(); i++) {
                                    System.out.println(Autor.autores().get(i).toString());
                                }
                                break;
                            case 2:
                                //INSERTAR AUTOR
                                System.out.println("INSERTAR AUTOR\n\n");
                                Autor.darAlta();
                                System.out.println("Pulse Intro para continual");
                                input.nextLine();
                                break;
                            case 3:
                                // ACTUALIZAR
                                Autor.autorActualizar();
                                break;
                            case 4:
                                // ELIMINAR
                                System.out.println("ELIMINAR AUTOR");
                                Autor.eliminarAutor();
                                break;
                            case 0:
                                Autor.actualizarFicheroAutor();
                                System.out.println("");
                                break;
                            default:
                                System.out.println("Seleccione una opción entre 0 y 4");
                        }
                        break;
                    case "CATEGORÍA":
                        switch (opcion) {
                            case 1:
                                System.out.println("EN CONSTRUCCIÓN");
                                // Perform "original number" case.
                                break;
                            case 2:
                                System.out.println("EN CONSTRUCCIÓN");
                                // Perform "encrypt number" case.
                                break;
                            case 3:
                                System.out.println("EN CONSTRUCCIÓN");
                                // Perform "decrypt number" case.
                                break;
                            case 4:
                                System.out.println("EN CONSTRUCCIÓN");
                                // Perform "decrypt number" case.
                                break;
                            case 0:
                                System.out.println("");
                                break;
                            default:
                                System.out.println("Seleccione una opción entre 0 y 4");
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
                                System.out.println("Seleccione una opción entre 0 y 4");
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
                                System.out.println("Seleccione una opción entre 0 y 4");
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
                                System.out.println("Seleccione una opción entre 0 y 4");
                        }
                        break;
                    case "USUARIO":
                        switch (opcion) {
                            case 1:
                                // VISUALIZAR USUARIOS
                                for (int i = 0; i < Usuario.ListarUsuarios().size(); i++) {
                                    System.out.println(Usuario.ListarUsuarios().get(i).toString());
                                }
                                break;
                            case 2:
                                // AÑADIR USUARIO
                                Usuario.darAlta();
                                break;
                            case 3:
                                //ACTUALIZAR.
                                
                                break;
                            case 4:
                                //ELIMINAR USUARIO
                                Usuario.eliminarUsuario();
                                break;
                            case 0:
                                Usuario.actualizarArchivoUsuarios();
                                System.out.println("");
                                break;
                            default:
                                System.out.println("Seleccione una opción entre 0 y 4");
                        }
                        break;
                }
            }else if(!admin){
                if (!entidad.equals("CUENTA")){
                    
                    
                    System.out.println("\n\n"+ entidad +": "+ usuario.getUsuario());
                    System.out.println("-------------------------\n");
                    System.out.println("1 - BUSCAR");
                    System.out.println("2 - [EN DESARROLLO]");
                    System.out.println("3 - [EN DESARROLLO]");
                    System.out.println("4 - [EN DESARROLLO]");
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
                                    System.out.println("Seleccione una opción entre 0 y 4");
                            }
                            
                            break;
                        case "AUTOR":
                            switch (opcion) {
                                case 1:
                                    System.out.println("BUSCADOR DE LIBROS");
                                    for (int i = 0; i < Autor.autores().size(); i++) {
                                        System.out.println(Autor.autores().get(i).toString());
                                    }
                                    // Perform "original number" case.
                                    break;
                                case 2:
                                    System.out.println("EN CONSTRUCCIÓN");
                                    // Perform "encrypt number" case.
                                    break;
                                case 3:
                                    System.out.println("EN CONSTRUCCIÓN");
                                    // Perform "decrypt number" case.
                                    break;
                                case 4:
                                    System.out.println("EN CONSTRUCCIÓN");
                                    // Perform "decrypt number" case.
                                    break;
                                case 0:
                                    System.out.println("");
                                    break;
                                default:
                                    System.out.println("Seleccione una opción entre 0 y 4");


                            }
                            break;
                        case "CATEGORÍA":

                            switch (opcion) {
                                case 1:
                                    System.out.println("EN CONSTRUCCIÓN");
                                    // Perform "original number" case.
                                    break;
                                case 2:
                                    System.out.println("EN CONSTRUCCIÓN");
                                    // Perform "encrypt number" case.
                                    break;
                                case 3:
                                    System.out.println("EN CONSTRUCCIÓN");
                                    // Perform "decrypt number" case.
                                    break;
                                case 4:
                                    System.out.println("EN CONSTRUCCIÓN");
                                    // Perform "decrypt number" case.
                                    break;
                                case 0:
                                    System.out.println("");
                                    break;
                                default:
                                    System.out.println("Seleccione una opción entre 0 y 4");
                            }
                            break;
                    }
                }else if (entidad.equals("CUENTA")){
                    System.out.println("\n\n"+"MI CUENTA: "+ usuario.getUsuario());
                    System.out.println("-------------------------\n");
                    System.out.println("1 - ACTUALIZAR");
                    System.out.println("2 - ELIMINAR");
                    System.out.println("0 - Salir");
                    opcion = input.nextInt();
                    input.nextLine();
                    switch(opcion){
                        case 1:
                            //ACTUALIZAR
                            Usuario.actualizarUsuario(usuario.getUsuario());
                            break;
                        case 2:
                            //ELIMINAR
                            Usuario.eliminarUsuario(usuario);
                            Usuario.actualizarArchivoUsuarios();
                            System.exit(0);
                            opcion = 0;
                            
                            break;
                        case 0:
                            //ACTUALIZAR FICHERO USUARIOS Y SALIR
                            Usuario.actualizarArchivoUsuarios();
                            break;
                    }
                    
                        
                }
                    
                
            }
            
        }
    }
 
}
