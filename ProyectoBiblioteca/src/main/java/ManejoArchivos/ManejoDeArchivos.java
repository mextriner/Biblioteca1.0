/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManejoArchivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Alumno Mañana
 */
public class ManejoDeArchivos {
    
    public static void crearArchivo(String nombre){
        File archivo = new File(nombre);
        try{
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
        }catch (FileNotFoundException ex){
            ex.printStackTrace(System.out);
        }
        

    }
    
    
    //DESCARTA EL CONTENIDO EXISTENTE Y AÑADE EL NUEVO
    public static void escribirArchivo(String nombre, String contenido){
        PrintWriter salida = null;
        File archivo = new File(nombre);
         try{
            salida = new PrintWriter(archivo);
            salida.print(contenido);
             System.out.println("Se ha creado el archivo");
        }catch (FileNotFoundException ex){
            ex.printStackTrace(System.out);
            
        }finally{
             salida.close(); //siempre se ejecuta
         }
    }
    
    
    //AGREGA CONTENIDO ADEMÁS DEL EXISTENTE
    public static void agregarArchivo(String nombre, String contenido){
        PrintWriter salida = null;
        File archivo = new File(nombre);
        try {
           salida = new PrintWriter(new FileWriter(nombre,true));
           salida.print(contenido);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }finally{
             salida.close(); //siempre se ejecuta
        }
    }
    
     public static String cadenaArchivo(String nombre){
         File archivo = new File(nombre);
         BufferedReader entrada = null;
         String total = "";
        try {
            entrada = new BufferedReader (new FileReader(archivo));
            int lectura = entrada.read();
            while(lectura != -1){
                total += ((char)lectura);
                lectura = entrada.read();
                
            }
            entrada.close();
            return total;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }catch(IOException ex){
            ex.printStackTrace(System.out);
        }
        return null;
     }
     
     public static void leerArchivo(String nombre){
         File archivo = new File(nombre);
         BufferedReader entrada = null;
        try {
            entrada = new BufferedReader (new FileReader(archivo));
            String lectura = entrada.readLine();
            while(lectura != null){
                System.out.println("lectura = " + lectura);
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }catch(IOException ex){
            ex.printStackTrace(System.out);
        }
     }
     
     public static void leerArchivoPorPalabra(String nombre){
         File archivo = new File(nombre);
         Scanner entrada = null;
         
        try {
          entrada = new Scanner(archivo);
          int cont = 0;
          while(entrada.hasNext()){
            String palabra = entrada.next();
            System.out.println(palabra);
            cont++;
          }
         System.out.println("Número de palabras: " + cont);
        } catch (FileNotFoundException ex) {
           
        }
     }
}
