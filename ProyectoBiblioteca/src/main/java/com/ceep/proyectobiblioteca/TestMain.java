/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceep.proyectobiblioteca;

import Datos.AutorDao;
import Datos.EditorialDao;
import Datos.LibroDao;
import Dominio.Autor;
import Dominio.Libro;
import Dominio.Usuario;
import Datos.UsuarioDao;
import Dominio.Editorial;
import DatosInterfaces.InterfaceAutor;
import DatosInterfaces.InterfaceEditorial;
import DatosInterfaces.InterfaceLibro;
import DatosInterfaces.InterfaceUsuario;
import Negocio.AutorNegocio;
import Negocio.UsuarioNegocio;
import NegocioInterfaces.InterfazNegAutor;
import NegocioInterfaces.InterfazNegUsuario;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.Scanner;

/**
 *
 * @author MaximoMestriner
 */
public class TestMain {
public static InterfaceAutor autorDao = new AutorDao();
    public static InterfaceUsuario usuarioDao = new UsuarioDao();
    public static InterfaceEditorial editorialDao = new EditorialDao();
    public static InterfaceLibro libroDao = new LibroDao();
    
    public static InterfazNegUsuario negocioUsuario = new UsuarioNegocio();
    public static InterfazNegAutor negocioAutor = new AutorNegocio();
    
    
    //DECLARO UN Scanner input PARA LOS INPUT POR TECLADO
    public static Scanner input = new Scanner(System.in);
    
    //PARA LA PARTE DE ADMINISTRADOR SE DECLARA ESTA VARIABLE GLOBAL
    public static Usuario administrador = new Usuario("admin","1234");
    
    //ESTA VARIABLE GLOBAL SIRVE PARA OPERAR EN LA PARTE DE USUARIO
    public static Usuario usuario = new Usuario();
    
    public static Libro lb = new Libro();
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        
//        Editorial editorial = new Editorial("Anaya","Avda. de Pardo Rosas");
//        editorialDao.insertar(editorial);
//        editorial.actualizarArchivoEditorial();
        
        menu();
//        Libro libro = new Libro("999999999","Juego de Tronos","español",Date.valueOf("2002-05-04"),true,"src/main/img/jdt.jpg");
//        LibroDao libroDao = new LibroDao();
//        libroDao.insertar(libro);
        
    }
    
    //MENU PARA SELECCIONAR EL TIPO DE USUARIO (ADMIN O USUARIO)
    
    public static void menu(){
        negocioUsuario.actualizarArchivoUsuarios();
        int opcion;
        
        opcion = -1;
        /***************************************************/
        
        while (opcion != 0){
            
            System.out.println("\tBIBLIOTECA VIRTUAL\n");
            System.out.println("INICIAR SESIÓN");
            System.out.println("---------------\n");
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
                    negocioUsuario.iniciarSesion(administrador.getUsuario());
                    menuEntidad(true);
                    break;
                case 2:
                    // FUNCION INICIAR SESIÓN (USUARIO)
                    System.out.println("Introduzca su usuario:");
                    String nom = input.nextLine();
                    //SI EL USUARIO EXISTE PUEDES INTRODUCIR TU CONTRASEÑA
                    if(negocioUsuario.entrada(nom)){
                        System.out.println("Introduzca la contraseña o pulse intro para salir:");
                        String clave = input.nextLine();
                        if(clave.isEmpty()){
                            break;
                        }
                        while(!clave.equals(negocioUsuario.comprobarId(nom).getClave())){
                            System.out.println("Contraseña incorrecta, pruebe de nuevo o pulse intro para salir:");
                            clave = input.nextLine();
                            if(clave.isEmpty()){
                                break;
                            }
                        }
                        if(clave.isEmpty()){
                                break;
                        }
                        
                        //LA VARIABLE GLOBAL usuario SE IGUALA AL Usuario CON EL
                        //QUE SE HA INICIADO SESIÓN
                        usuario = negocioUsuario.comprobarId(nom);
                        menuEntidad(false);
                        //SI EL USUARIO NO EXISTE PUEDES CREAR UNA NUEVA CUENTA
                    }else{
                        char ac;
                        System.out.println("Este usuario no existe ¿Desea darse de alta? \n si(s) / no(n)");
                        ac = input.nextLine().charAt(0);
                        //SI LA CONTRASEÑÑA ES INCORRECTA LO VUELVE A INTENTAR
                        while(ac != 's' && ac != 'n'){
                            System.out.println("Introduzca si (s) o no (n)");
                            ac = input.nextLine().charAt(0);
                        }
                        if (ac == 's'){
                            usuario = negocioUsuario.darAlta();
                        }else{
                            //SI NO QUIERE DARSE DE ALTA VUELVE AL MENU PRINCIPAL
                            System.out.println("Operación cancelada.");
                            break;
                        }

                    }
                    
                    break;
                case 3:
                    // REGISTRAR UN NUEVO USUARIO
                    usuario = negocioUsuario.darAlta();
                    menuEntidad(false);
                    break;
                case 0:
                    negocioUsuario.actualizarArchivoUsuarios();
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
                System.out.println("3 - CATEGORÍA (EN DESARROLLO)");
                System.out.println("4 - EDITORIAL");
                System.out.println("5 - PROVEEDOR (EN DESARROLLO)");
                System.out.println("6 - UNIDAD (EN DESARROLLO)");
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
                
                negocioUsuario.actualizarArchivoUsuarios();
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
                       
                        switch (opcion) {
                            case 1:
                                System.out.println("LISTA DE LIBROS");
                                for (int i = 0; i < lb.listarLibro().size(); i++) {
                                    System.out.println(lb.listarLibro().get(i));
                                }
                                
                                //ESTO ES NUEVO
                                String bus;
                                int opc = -1;
                                while(opc != 0){
                                    System.out.println("\tBÚSQUEDA POR:");
                                    System.out.println("\t1 - ISBN:");
                                    System.out.println("\t2 - TÍTULO:");
                                    System.out.println("\t0 - SALIR:");
                                    opc = input.nextInt();
                                    input.nextLine();
                                    switch(opc){
                                        case 1:
                                            System.out.println("Introduzca el término de búsqueda:");
                                            bus = input.nextLine();
                                            lb.buscarLibroIsbn(bus);
                                            break;
                                        case 2:
                                            System.out.println("Introduzca el término de búsqueda:");
                                            bus = input.nextLine();
                                            lb.buscarLibroTitulo(bus);
                                            break;
                                        case 0:
                                            break;
                                        default:
                                            System.out.println("Seleccione una de las opciones (0 - 1)");
                                            break;
                                    }
                                }
                                
                                //HASTA AQUÍ ES LO NUEVO
                                break;
                            case 2:
                                System.out.println("INTRODUCIR LIBRO:");
                                lb = Libro.darAlta();
                                break;
                            case 3:
                                System.out.println("ACTUALIZAR LIBRO");
                                lb.libroActualizar();
                                break;
                            case 4:
                                System.out.println("ELIMINAR LIBRO");
                                lb.eliminarLibro();
                                opcion = 0;
                                // Perform "decrypt number" case.
                                break;
                            case 0:
                                lb.actualizarArchivoLibros();
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
                                for (int i = 0; i < negocioAutor.autores().size(); i++) {
                                    System.out.println(negocioAutor.autores().get(i).toString());
                                }
                                
                                 String bus;
                                    int opc = -1;
                                    while(opc != 0){
                                        System.out.println("\tBÚSQUEDA POR:");
                                        System.out.println("\t1 - NOMBRE:");
                                        System.out.println("\t2 - APELLIDOS:");
                                        System.out.println("\t0 - SALIR:");
                                        opc = input.nextInt();
                                        input.nextLine();
                                        switch(opc){
                                            case 1:
                                                System.out.println("Introduzca el término de búsqueda:");
                                                bus = input.nextLine();
                                                negocioAutor.buscarAutorNombre(bus);
                                                break;
                                            case 2:
                                                System.out.println("Introduzca el término de búsqueda:");
                                                bus = input.nextLine();
                                                negocioAutor.buscarAutorApellido(bus);
                                                break;
                                            case 0:
                                                break;
                                            default:
                                                System.out.println("Seleccione una de las opciones (0 - 2)");
                                                break;
                                        }
                                    }
                                break;
                            case 2:
                                //INSERTAR AUTOR
                                System.out.println("INSERTAR AUTOR\n\n");
                                negocioAutor.darAlta();
                                System.out.println("Pulse Intro para continual");
                                input.nextLine();
                                break;
                            case 3:
                                // ACTUALIZAR
                                negocioAutor.autorActualizar();
                                break;
                            case 4:
                                // ELIMINAR
                                System.out.println("ELIMINAR AUTOR");
                                negocioAutor.eliminarAutor();
                                opcion = 0;
                                break;
                            case 0:
                                negocioAutor.actualizarTxt();
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
                                opcion = 0;
                                break;
                            default:
                                System.out.println("Seleccione una opción entre 0 y 4");
                        }
                        break;
                    case "EDITORIAL":
                        
                        switch (opcion) {
                            case 1:
                                System.out.println("LISTA DE EDITORIALES:");
                                for (int i = 0; i < Editorial.listarEditorial().size(); i++) {
                                    System.out.println(Editorial.listarEditorial().get(1));
                                }
                                System.out.println("BÚSQUEDA POR NOMBRE:");
                                String bus;
                                bus = input.nextLine();
                                Editorial.buscarEditorialNombre(bus);
                                // Perform "original number" case.B
                                break;
                            case 2:
                                System.out.println("INSERTAR EDITORIAL");
                                Editorial.darAlta();
                                // Perform "encrypt number" case.I
                                break;
                            case 3:
                                System.out.println("ACTUALIZAR EDITORIAL:");
                                System.out.println("EDITORIALES: ");
                                for (int i = 0; i < Editorial.listarEditorial().size(); i++) {
                                    System.out.println(Editorial.listarEditorial().get(i).toString());
                                }
                                System.out.println("seleccione la Editorial que desee modificar:");
                                int ed = input.nextInt();
                                input.nextLine();
                                Editorial.actualizarEditorial(Editorial.editorialId(ed));
                                // Perform "decrypt number" case.A
                                break;
                            case 4:
                                System.out.println("ELIMINAR: ");
                                Editorial.eliminarEditorial();
                                // Perform "decrypt number" case.E
                                break;
                            case 0:
                                Editorial.actualizarArchivoEditorial();
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
                                for (int i = 0; i < negocioUsuario.listarUsuarios().size(); i++) {
                                    System.out.println(negocioUsuario.listarUsuarios().get(i).toString());
                                }
                                
                                //ESTO ES NUEVO
                                    String bus;
                                    int opc = -1;
                                    while(opc != 0){
                                        System.out.println("\tBÚSQUEDA POR:");
                                        System.out.println("\t1 - USUARIO:");
                                        System.out.println("\t2 - NOMBRE:");
                                        System.out.println("\t3 - APELLIDOS:");
                                        System.out.println("\t0 - SALIR:");
                                        opc = input.nextInt();
                                        input.nextLine();
                                        switch(opc){
                                            case 1:
                                                System.out.println("Introduzca el término de búsqueda:");
                                                bus = input.nextLine();
                                                negocioUsuario.buscarUsuario(bus);
                                                break;
                                            case 2:
                                                System.out.println("Introduzca el término de búsqueda:");
                                                bus = input.nextLine();
                                                negocioUsuario.buscarNombre(bus);
                                                break;
                                            case 3:
                                                System.out.println("Introduzca el término de búsqueda:");
                                                bus = input.nextLine();
                                                negocioUsuario.buscarApellido(bus);
                                                break;
                                            case 0:
                                                break;
                                            default:
                                                System.out.println("Seleccione una de las opciones (0 - 2)");
                                                break;
                                        }
                                    }

                                    //HASTA AQUÍ ES LO NUEVO
                                    
                                break;
                            case 2:
                                // AÑADIR USUARIO
                                negocioUsuario.darAlta();
                                break;
                            case 3:
                                //ACTUALIZAR.
                                
                                break;
                            case 4:
                                //ELIMINAR USUARIO
                                negocioUsuario.eliminarUsuario();
                                opcion = 0;
                                break;
                            case 0:
                                negocioUsuario.actualizarArchivoUsuarios();
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
                                    //BUSCADOR DE LIBROS
                                    System.out.println("LISTA DE LIBROS");
                                    for (int i = 0; i < lb.listarLibro().size(); i++) {
                                        System.out.println(lb.listarLibro().get(i));
                                    }
                                    //ESTO ES NUEVO
                                    String bus;
                                    int opc = -1;
                                    while(opc != 0){
                                        System.out.println("\tBÚSQUEDA POR:");
                                        System.out.println("\t1 - ISBN:");
                                        System.out.println("\t2 - TÍTULO:");
                                        System.out.println("\t0 - SALIR:");
                                        opc = input.nextInt();
                                        input.nextLine();
                                        switch(opc){
                                            case 1:
                                                System.out.println("Introduzca el término de búsqueda:");
                                                bus = input.nextLine();
                                                lb.buscarLibroIsbn(bus);
                                                break;
                                            case 2:
                                                System.out.println("Introduzca el término de búsqueda:");
                                                bus = input.nextLine();
                                                lb.buscarLibroTitulo(bus);
                                                break;
                                            case 0:
                                                break;
                                            default:
                                                System.out.println("Seleccione una de las opciones (0 - 2)");
                                                break;
                                        }
                                    }

                                    //HASTA AQUÍ ES LO NUEVO
                                    
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
                                    for (int i = 0; i < negocioAutor.autores().size(); i++) {
                                        System.out.println(negocioAutor.autores().get(i).toString());
                                    }
                                    String bus;
                                    int opc = -1;
                                    while(opc != 0){
                                        System.out.println("\tBÚSQUEDA POR:");
                                        System.out.println("\t1 - NOMBRE:");
                                        System.out.println("\t2 - APELLIDOS:");
                                        System.out.println("\t0 - SALIR:");
                                        opc = input.nextInt();
                                        input.nextLine();
                                        switch(opc){
                                            case 1:
                                                System.out.println("Introduzca el término de búsqueda:");
                                                bus = input.nextLine();
                                                negocioAutor.buscarAutorNombre(bus);
                                                break;
                                            case 2:
                                                System.out.println("Introduzca el término de búsqueda:");
                                                bus = input.nextLine();
                                                negocioAutor.buscarAutorApellido(bus);
                                                break;
                                            case 0:
                                                break;
                                            default:
                                                System.out.println("Seleccione una de las opciones (0 - 2)");
                                                break;
                                        }
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
                    int edad;
                    if(usuario.getFechaNac()== null || usuario.getFechaNac().toString().equals("") || usuario.getFechaNac().toString().contains(" ")){
                        edad = 0;
                    }else{
                        edad = (int) ChronoUnit.YEARS.between(usuario.getFechaNac().toLocalDate(),LocalDate.now());
                    }
                    
                    System.out.println("\n\n"+"MI CUENTA: "+ usuario.getUsuario());
                    System.out.println("-------------------------\n");
                    System.out.println("Mi Información");
                    System.out.println("\tUsuario\t"+usuario.getUsuario());
                    System.out.println("\tNombre\t"+usuario.getNombre());
                    System.out.println("\tApellidos\t"+usuario.getApellido());
                    if(edad != 0){
                        System.out.println("\t\tEDAD: "+edad+" AÑOS");
                    }
                    System.out.println("1 - ACTUALIZAR");
                    System.out.println("2 - ELIMINAR");
                    System.out.println("0 - Salir");
                    
                    opcion = input.nextInt();
                    input.nextLine();
                    switch(opcion){
                        case 1:
                            //ACTUALIZAR
                            negocioUsuario.actualizarUsuario(usuario.getUsuario());
                            break;
                        case 2:
                            //ELIMINAR
                            negocioUsuario.eliminarUsuario(usuario);
                            negocioUsuario.actualizarArchivoUsuarios();
                            System.exit(0);
                            opcion = 0;
                            
                            break;
                        case 0:
                            //ACTUALIZAR FICHERO USUARIOS Y SALIR
                            negocioUsuario.actualizarArchivoUsuarios();
                            break;
                    }   
                }
            }
            
        }
    }
 
}