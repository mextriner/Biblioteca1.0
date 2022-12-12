/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosFichero;

import Dominio.Usuario;
import InterfazDatosFichero.InterfazTxtUsuario;
import ManejoArchivos.ManejoDeArchivos;
import Negocio.UsuarioNegocio;
import NegocioInterfaces.InterfazNegUsuario;
import java.sql.Date;

/**
 *
 * @author Alumno Mañana
 */
public class UsuarioTxt implements InterfazTxtUsuario{
    
    private final InterfazNegUsuario usuarioNegocio = new UsuarioNegocio();
    
    //actualiza el archivo de texto de usuarios
    public void actualizarArchivoUsu(){
        Usuario usuario = null;
        String contenido ="";
        for (int i = 0; i < usuarioNegocio.listarUsuarios().size(); i++) {
            
            contenido += (usuarioNegocio.listarUsuarios().get(i).escribir());
        }
        ManejoDeArchivos.escribirArchivo("usuario.txt",contenido);
    }
    
    //Devuelve un usuario comprobando col[0], la primary key a partir
    //del archivo de texto de usaurios.
    public Usuario archivoPk(String primaryKey){
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
}
