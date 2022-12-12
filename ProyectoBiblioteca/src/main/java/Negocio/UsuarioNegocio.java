/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.UsuarioDao;
import DatosInterfaces.InterfaceUsuario;
import Dominio.Usuario;
import InterfazDatosFichero.InterfazTxtUsuario;
import ManejoArchivos.ManejoDeArchivos;
import NegocioInterfaces.InterfazNegUsuario;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alumno Mañana
 */
public class UsuarioNegocio implements InterfazNegUsuario{
    
    private final InterfaceUsuario usuarioDao;

    public UsuarioNegocio() {
        this.usuarioDao = new UsuarioDao();
    }
    
    public List <Usuario> listarUsuarios(){
        List <Usuario> usuario = null;
        try{
            usuario = usuarioDao.seleccionar();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
        return usuario;
    }
    
    //UNA LISTA DE USUARIOS PERO SÓLO CON SU NOMBRE, CLAVE (DESENCRIPTADA) Y FECHA DE REGISTRO
    
    public List <Usuario> listarUsuariosClave(){
        List <Usuario> usuario = null;
        try{
            usuario = usuarioDao.seleccionarUsuarioClave();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
        return usuario;
    }
    
    // DEVUELVE UN USUARIO CUYO NOMBRE DE USUARIO ES IGUAL AL DEL PARÁMETRO
    public Usuario comprobarId(String primaryKey){
        Usuario usur = null;
        for (int i = 0; i < listarUsuariosClave().size(); i++) {
//            System.out.println(listarUsuariosClave().get(i).toString());
            if(listarUsuariosClave().get(i).getUsuario().equals(primaryKey)){
                usur = listarUsuariosClave().get(i);
            }
        }
        return usur;
    }
    
    //Comprueba si el nombre introducido ya existe
    public boolean entrada(String nom){
        Usuario usr = null;
        boolean existe = false;
        for (int i = 0; i < listarUsuarios().size() ; i++) {
            if(nom.equals(listarUsuarios().get(i).getUsuario())){
                existe = true;
            }
        }
        return existe;
    }
    
    //PARTE DE LA CAPA VISTA PARA ELIMINAR USUARIO SIENDO ADMINISTRADOR
    public void eliminarUsuario(){
        Scanner in = new Scanner (System.in);
        System.out.println("Introduzca el usuario que quiere eliminar:");
        String usu = in.nextLine();
        if(entrada(usu)){
            System.out.println("¿Desea eliminar este usuario? si (s) o no (n)");
            char ac = in.nextLine().charAt(0);
            while(ac != 's' && ac != 'n'){
                System.out.println("Introduzca si (s) o no (n)");
                ac = in.nextLine().charAt(0);
            }
            if (ac == 's'){
                usuarioDao.eliminar(comprobarId(usu));
                
            }else{
                System.out.println("Operación cancelada.");
            }
        }
    }
    
    
    //PARTE DE LA CAPA VISTA PARA ELIMINAR USUARIO
    public void eliminarUsuario(Usuario usuraio){
        Scanner in = new Scanner (System.in);
        System.out.println("Introduzca la contraseña:");
        String key = in.nextLine();
        while(!key.equals(comprobarId(usuraio.getUsuario()).getClave())){
            System.out.println("Contraseña incorrecta, pruebe de nuevo:");
            key = in.nextLine();
        }

        System.out.println("¿Desea eliminar este usuario? sí(s) / no(n)");
        char ac = in.nextLine().charAt(0);
        while(ac != 's' && ac != 'n'){
            System.out.println("Introduzca si (s) o no (n)");
            ac = in.nextLine().charAt(0);
        }
        if (ac == 's'){
            InterfaceUsuario usuarioDao = new UsuarioDao();
            usuarioDao.eliminar(usuraio);

        }else{
            System.out.println("Operación cancelada.");
        }
    }
    
    //PARTE DE LA CAPA VISTA PARA INICIAR SESIÓN
    public void iniciarSesion(String admin){
        Usuario usr = null;
        Scanner in = new Scanner (System.in);
        System.out.println("Introduzca la contraseña de Administrador:");
        String key = in.nextLine();
        while(!key.equals(comprobarId(admin).getClave())){
            System.out.println("Contraseña incorrecta, pruebe de nuevo:");
            key = in.nextLine();
        }
    }
    
    public void actualizarArchivoUsuarios(){
        Usuario usuario = null;
        String contenido ="";
        for (int i = 0; i < listarUsuarios().size(); i++) {
            
            contenido += (listarUsuarios().get(i).escribir());
        }
        ManejoDeArchivos.escribirArchivo("usuario.txt",contenido);
    }
    
    
     //PARTE DE LA CAPA VISTA PARA DAR DE ALTA
    public Usuario darAlta(){
        Scanner in = new Scanner (System.in);
        InterfaceUsuario usuarioDao = new UsuarioDao();
        System.out.println("Introduzca su usuario");
        String usar = in.nextLine();
        while(entrada(usar)){
            System.out.println("Este nombre ya existe. "
                    + "Escoja otro nombre de usuario");
            usar = in.nextLine();
        }
        System.out.println("Introduzca su contraseña:");
        String clave = in.nextLine();
        Usuario usr = new Usuario(usar,clave,Date.valueOf(LocalDate.now()));
        usuarioDao.insertar(usr);
        actualizarArchivoUsuarios();
        return usr;
    }
    
    public void actualizarUsuario(String usu){
        Usuario u = comprobarId(usu);
         InterfaceUsuario usuarioDao = new UsuarioDao();
        int opcion;
        Scanner input = new Scanner(System.in);
        opcion = -1;
        /***************************************************/
        
        while (opcion != 0){
            
            System.out.println("\n\n\n\t\t"+ u.getUsuario());
            System.out.println("ESCOJA EL CAMPO");
            System.out.println("-----------------------------\n");
            System.out.println("1 - NOMBRE DE USUARIO: " +u.getUsuario());
            System.out.println("2 - NOMBRE: " +u.getNombre());
            System.out.println("3 - APELLIDO: " +u.getApellido());
            System.out.println("4 - FECHA DE NACIMIENTO: " +u.getFechaNac());
            System.out.println("5 - CONTRASEÑA");
            System.out.println("0 - Salir");
            System.out.println("Selecciones una opción");
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1:
                    // CAMBIO NOMBRE DE USUARIO
                    System.out.println("Introduzca su contraseña:");
                    String key = input.nextLine();
                    while(!u.getClave().equals(key)){
                        System.out.println("Contraseña incorrecta\ninténtelo de nuevo");
                        key = input.nextLine();
                    }
                    String prevUsuario = u.getUsuario();
                    System.out.println("Introduzca el nuevo nombre de usuario:");
                    String nom = input.nextLine();
                    while(entrada(nom) || nom.isEmpty()){
                        if(nom.equals("")){
                            System.out.println("Este campo es obligatorio");;
                            nom = input.nextLine();
                        }else{
                            System.out.println("Ese nombre ya existe, introduzca otro");
                            nom = input.nextLine();
                        }
                    }
                    
                    u.setUsuario(nom);
                    usuarioDao.actualizarId(u, prevUsuario);
                    break;
                case 2:
                    // CAMBIAR NOMBRE
                    System.out.println("Introduzca su contraseña:");
                    key = input.nextLine();
                    while(!u.getClave().equals(key)){
                        System.out.println("Contraseña incorrecta\ninténtelo de nuevo");
                        key = input.nextLine();
                    }
                    System.out.println("Introduzca su nombre:");
                    nom = input.nextLine();
                    while(nom.isEmpty()){
                        System.out.println("Este campo es obligatorio");;
                        nom = input.nextLine();
                    }
                    u.setNombre(nom);
                    usuarioDao.actualizar(u);
                    break;
                case 3:
                    // CAMBIAR APELLIDOS
                    System.out.println("Introduzca su contraseña:");
                    key = input.nextLine();
                    while(!u.getClave().equals(key)){
                        System.out.println("Contraseña incorrecta\ninténtelo de nuevo");
                        key = input.nextLine();
                    }
                    System.out.println("Introduzca sus apellidos:");
                    nom = input.nextLine();
                    u.setApellido(nom);
                    usuarioDao.actualizar(u);
                    break;
                case 4:
                    // CAMBIAR FECHA DE NACIMIENTO
                    System.out.println("Introduzca su contraseña:");
                    key = input.nextLine();
                    while(!u.getClave().equals(key)){
                        System.out.println("Contraseña incorrecta\ninténtelo de nuevo");
                        key = input.nextLine();
                    }
                    System.out.println("Introduzca su fecha de nacimiento (aaaa-mm-dd):");
                    nom = input.nextLine();
                    u.setFechaNac(Date.valueOf(nom));
                    usuarioDao.actualizar(u);
                    break;
                case 5:
                    // CAMBIAR CONTRASEÑA
                    System.out.println("Introduzca su antigua contraseña:");
                    key = input.nextLine();
                    while(!u.getClave().equals(key)){
                        System.out.println("Contraseña incorrecta\ninténtelo de nuevo");
                        key = input.nextLine();
                    }
                    System.out.println("Introduzca la nueva contraseña:");
                    nom = input.nextLine();
                    u.setClave(nom);
                    usuarioDao.actualizar(u);
                    
                    break;
                case 0:
                    System.out.println("");
                    actualizarArchivoUsuarios();
                    break;
                default:
                    actualizarArchivoUsuarios();
                    System.out.println("Seleccione una opción entre 0 y 3");
                    // The user input an unexpected choice.
            }
            
            
        }
       
    }

    //BUSCADOR POR NOMBRE DE USUARIO
    public void buscarUsuario(String usur){
        
        for (int i = 0; i < listarUsuarios().size(); i++) {
            if(listarUsuarios().get(i).getUsuario().equals(usur) ||
                    listarUsuarios().get(i).getUsuario().contains(usur)){
                System.out.println(listarUsuarios().get(i));
            }
        }
    }
    
    //BUSCADOR POR NOMBRE
    public void buscarNombre(String usur){
        for (int i = 0; i < listarUsuarios().size(); i++) {
            if(listarUsuarios().get(i).getNombre() != null){
                if(listarUsuarios().get(i).getNombre().equals(usur) ||
                    listarUsuarios().get(i).getNombre().contains(usur)){
                System.out.println(listarUsuarios().get(i));
                }
            }
        }
    }
    
    //BUSCADOR POR APELLIDO
    public void buscarApellido(String usur){
        
        for (int i = 0; i < listarUsuarios().size(); i++) {
            if(listarUsuarios().get(i).getApellido() != null){
                if(listarUsuarios().get(i).getApellido().equals(usur) ||
                    listarUsuarios().get(i).getNombre().contains(usur)){
                System.out.println(listarUsuarios().get(i));
                }
            }
        }
    }

}
