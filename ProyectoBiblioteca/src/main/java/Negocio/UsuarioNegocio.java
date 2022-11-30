/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.UsuarioDao;
import DatosInterfaces.InterfaceUsuario;
import Dominio.Usuario;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alumno Mañana
 */
public class UsuarioNegocio {
    
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
    public static boolean entrada(String nom){
        Usuario usr = null;
        boolean existe = false;
        for (int i = 0; i < usr.listarUsuarios().size() ; i++) {
            if(nom.equals(usr.listarUsuarios().get(i).getUsuario())){
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
        while(!key.equals(usr.comprobarId(admin).getClave())){
            System.out.println("Contraseña incorrecta, pruebe de nuevo:");
            key = in.nextLine();
        }
    }
}
