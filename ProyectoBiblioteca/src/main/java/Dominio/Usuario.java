/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Datos.UsuarioDao;
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
    
    public static List <Usuario> ListarUsuarios(){
        UsuarioDao usuarioDao = new UsuarioDao();
        List <Usuario> usuario = null;
        try{
            usuario = usuarioDao.seleccionar();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
        return usuario;
    }
    
    //UNA LISTA DE USUARIOS PERO SÓLO CON SU NOMBRE, CLAVE (DESENCRIPTADA) Y FECHA DE REGISTRO
    
    public static List <Usuario> ListarUsuariosClave(){
        UsuarioDao usuarioDao = new UsuarioDao();
        List <Usuario> usuario = null;
        try{
            usuario = usuarioDao.seleccionarUsuarioClave();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
        return usuario;
    }
    
    
    //actualiza el archivo de texto de usuarios
    public static void actualizarArchivoUsuarios(){
        Usuario usuario = null;
        String contenido ="";
        for (int i = 0; i < usuario.ListarUsuarios().size(); i++) {
            
            contenido += (usuario.ListarUsuarios().get(i).escribir());
        }
        ManejoDeArchivos.escribirArchivo("usuario.txt",contenido);
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
    
    public static Usuario comprobarId(String primaryKey){
        Usuario usur = null;
        for (int i = 0; i < ListarUsuariosClave().size(); i++) {
//            System.out.println(ListarUsuariosClave().get(i).toString());
            if(ListarUsuariosClave().get(i).getUsuario().equals(primaryKey)){
                usur = ListarUsuariosClave().get(i);
            }
        }
        return usur;
    }
    
   
    
    
    
    //Comprueba si el nombre introducido ya existe
    public static boolean entrada(String nom){
        Usuario usr = null;
        boolean existe = false;
        for (int i = 0; i < usr.ListarUsuarios().size() ; i++) {
            if(nom.equals(usr.ListarUsuarios().get(i).getUsuario())){
                existe = true;
            }
        }
        return existe;
    }
    
    public static void eliminarUsuario(){
        Scanner in = new Scanner (System.in);
        System.out.println("Introduzca el usuario que quiere eliminar:");
        String usu = in.nextLine();
        if(entrada(usu)){
            System.out.println("¿Desea eliminar este usuario?");
            char ac = in.nextLine().charAt(0);
            while(ac != 's' || ac != 'n'){
                System.out.println("Introduzca si (s) o no (n)");
                ac = in.nextLine().charAt(0);
            }
            if (ac == 's'){
                
                UsuarioDao usuarioDao = new UsuarioDao();
                usuarioDao.eliminar(comprobarId(usu));
                
            }else{
                System.out.println("Operación cancelada.");
            }
        }
    }
    
    public static void eliminarUsuario(Usuario usuraio){
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
            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.eliminar(usuraio);

        }else{
            System.out.println("Operación cancelada.");
        }
    }
    
    
    
    public static void iniciarSesion(String admin){
        Usuario usr = null;
        Scanner in = new Scanner (System.in);
        System.out.println("Introduzca la contraseña de Administrador:");
        String key = in.nextLine();
        while(!key.equals(usr.comprobarId(admin).clave)){
            System.out.println("Contraseña incorrecta, pruebe de nuevo:");
            key = in.nextLine();
        }
    }
    
    public static Usuario darAlta(){
        Scanner in = new Scanner (System.in);
        UsuarioDao usuarioDao = new UsuarioDao();
        System.out.println("Introduzca su usuario");
        String usar = in.nextLine();
        while(Usuario.entrada(usar)){
            System.out.println("Este nombre ya existe"
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
    
    public static void actualizarUsuario(String usu){
        Usuario u = comprobarId(usu);
         UsuarioDao usuarioDao = new UsuarioDao();
        int opcion;
        Scanner input = new Scanner(System.in);
        opcion = -1;
        /***************************************************/
        
        while (opcion != 0){
            
            System.out.println("\n\n\n\t\t"+ u.getUsuario());
            System.out.println("ESCOJA EL CAMPO");
            System.out.println("-----------------------------\n");
            System.out.println("1 - NOMBRE DE USUARIO: " +u.usuario);
            System.out.println("2 - NOMBRE: " +u.nombre);
            System.out.println("3 - APELLIDO: " +u.apellido);
            System.out.println("4 - FECHA DE NACIMIENTO: " +u.fechaNac);
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
                    System.out.println("Introduzca su fecha de nacimiento (aaaa/mm/dd):");
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
                    break;
                default:
                    Usuario.actualizarArchivoUsuarios();
                    System.out.println("Seleccione una opción entre 0 y 3");
                    // The user input an unexpected choice.
            }
            
            
        }
       
    }
    
    
    
}

