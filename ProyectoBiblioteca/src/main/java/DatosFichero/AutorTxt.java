/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosFichero;

import Dominio.Autor;
import InterfazDatosFichero.InterfazTxtAutor;
import ManejoArchivos.ManejoDeArchivos;
import Negocio.AutorNegocio;
import NegocioInterfaces.InterfazNegAutor;
import java.sql.Date;

/**
 *
 * @author Alumno Mañana
 */
public class AutorTxt implements InterfazTxtAutor{
    
    private static InterfazNegAutor negocioAutor = new AutorNegocio();
    
    public Autor archivoPk(int primaryKey){
        Autor fileAutor = null;
        
        String nombre [] = ManejoDeArchivos.cadenaArchivo("autor.txt").split("/");
        for (int i = 0; i < nombre.length; i++) {
            System.out.println(nombre[i]);
            String col [] = nombre[i].split("º");
            if (col[0].equals(primaryKey)){
                fileAutor = new Autor (Integer.valueOf(col[0]),col[1],(col[2]),col[3],Date.valueOf(col[4]));
                
            }else{
                System.out.println("ENTRADA NO ENCONTRADA");
            }
        }
        return fileAutor;
    }
     
    public void actualizarFicheroAutor(){
        String contenido ="";
        for (int i = 0; i < negocioAutor.autores().size(); i++) {
            contenido += (negocioAutor.autores().get(i).escribir());
        }
        System.out.println(contenido);
        ManejoDeArchivos.escribirArchivo("autor.txt",contenido);
    }
}
