/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Datos.LibroDao;
import Interfaces.InterfaceLibro;
import ManejoArchivos.ManejoDeArchivos;
import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MaximoMestriner
 */
public class Libro implements Serializable{

    
    private String isbn;
    private String titulo;
    private String idioma;
    private Date fechaPubli;
    private boolean bestSeller;
    private String portada;
    
    static private InterfaceLibro libroDao = new LibroDao();

    public Libro() {
    }

    public Libro(String titulo, String idioma, Date fechaPubli, boolean bestSeller) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.fechaPubli = fechaPubli;
        this.bestSeller = bestSeller;
    }

    public Libro(String isbn, String titulo, String idioma, Date fechaPubli, boolean bestSeller) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.idioma = idioma;
        this.fechaPubli = fechaPubli;
        this.bestSeller = bestSeller;
    }

    public Libro(String isbn, String titulo, String idioma, Date fechaPubli, boolean bestSeller, String portada) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.idioma = idioma;
        this.fechaPubli = fechaPubli;
        this.bestSeller = bestSeller;
        this.portada = portada;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }
    
    

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Date getFechaPubli() {
        return fechaPubli;
    }

    public void setFechaPubli(Date fechaPubli) {
        this.fechaPubli = fechaPubli;
    }

    public boolean isBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(boolean bestSeller) {
        this.bestSeller = bestSeller;
    }

    @Override
    public String toString() {
        return "LIBRO: " + "|isbn:" + isbn + "|titulo: " + titulo + "|idioma: " + idioma + "|fechaPubli: " + fechaPubli + "|bestSeller: " + bestSeller;
    }

    
    
    public String escribir() {
        return 'º' + isbn + 'º' + titulo + 'º' + idioma + 'º' + fechaPubli + 'º' + bestSeller + 'º' + '/';
    }
    
    //DEVUELVE UN LIST CON LOS LIBROS DE LA BASE DE DATOS
    
    public List <Libro> listarLibro(){
        List <Libro> libros = null;
        try{
            libros = libroDao.seleccionar();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
        return libros;
    }
    
    //DEVUELVE UN LIBRO RECORRIENDO listarLibro() Y COMPARANDO EL isbn con el 
    //parámetro String isbn
    public static Libro libroIsbn(String isbn){
        Libro lb = new Libro();
        for (int i = 0; i < lb.listarLibro().size(); i++) {
            
            if(lb.listarLibro().get(i).getIsbn().equals(isbn)){
                lb = lb.listarLibro().get(i);
            }
        }
        return lb;
    }
    
    
    //COMPRUEBA SI EL PARÁMETRO String isbn YA EXISTE EN UN isbn DE 
    //ALGUNA OCURRENCIA
    public static boolean entrada(String isbn){
        Libro lb = new Libro();
        boolean existe = false;
        for (int i = 0; i < lb.listarLibro().size(); i++) {
            
            if(lb.listarLibro().get(i).getIsbn().equals(isbn)){
                existe = true;
            }
        }
        return existe;
    }
    
    
    
    public static void eliminarLibro(){
        
        Scanner in = new Scanner (System.in);
        Libro lb = new Libro();
        System.out.println("TABLA LIBROS:\n\n");
        for (int i = 0; i < lb.listarLibro().size(); i++) {
            System.out.println(lb.listarLibro().get(i).toString());
        }

        System.out.println("Inserte el ISBN del libro que quiere eliminar");
        String isbn = in.nextLine();
        while(!entrada(isbn)){
            System.out.println("Este libro no existe en la Base de Datos.\n"
                    + "Introduzca un ISBN correcto.");
            isbn = in.nextLine();
        }
        libroDao.eliminar(libroIsbn(isbn));
    }
    
    
    //INICIALIZA CADA UNO DE LOS ATRIBUTOS DE LIBRO POR TECLADO
    public static Libro darAlta(){
        Scanner in = new Scanner (System.in);
        
        //ISBN
        System.out.println("Introduzca el ISBN del libro");
        String vl = in.nextLine();
        while(entrada(vl) || vl.isEmpty()){
            if(vl.isEmpty()){
                System.out.println("Este campo es obligatorio");
                vl = in.nextLine();
            }else{
                System.out.println("Este libro ya existe."
                    + "Introdúzcalo de nuevo.");
                vl = in.nextLine();
            }
            
        }
        
        System.out.println("TÍTULO:");
        String titulo = in.nextLine();
        while(titulo.isEmpty()){
            System.out.println("Este campo no puede ser vacío."
                    + " Introduca un valor:");
            titulo = in.nextLine();
        }
        
        System.out.println("FECHA DE PUBLICACIÓN:");
        String fecha = in.nextLine();
        while(fecha.isEmpty()){
            System.out.println("Este campo no puede ser vacío."
                    + " Introduca un valor:");
            fecha = in.nextLine();
        }
        
        System.out.println("IDIOMA:");
        String idioma = in.nextLine();
        while(idioma.isEmpty()){
            System.out.println("Este campo no puede ser vacío."
                    + " Introduca un valor:");
            idioma = in.nextLine();
        }
        
        System.out.println("BESTSELLER:");
        boolean bestSeller = false;
        System.out.println("¿Es o fue Best Seller?");
        String v = in.nextLine().toLowerCase();
        if(v.equals("si")){
            bestSeller =true;
        }
        
        //RUTA DE LA PORTADA
        System.out.println("INTRODUZCA EL NOMBRE DEL ARCHIVO DE LA IMAGEN:");
        v = "src/main/img/"+in.nextLine();
        
        Libro libro = new Libro(vl,titulo,idioma,Date.valueOf(fecha),bestSeller,v);
        libroDao.insertar(libro);
       
        return libro;
    }
    
    //ACTUALIZA EL ARCHIVO DE TEXTO A PARTIR DE LOS DATOS DE LA BASE DE DATOS
    public void actualizarArchivoLibros() {
        String contenido ="";
        for (int i = 0; i < listarLibro().size(); i++) {
            
            contenido += (listarLibro().get(i).escribir());
        }
        ManejoDeArchivos.escribirArchivo("libro.txt",contenido);
    }
    
    
    //DESPLIEGA UN MENÚ PARA ELEGIR EL ATRIBUTO QUE QUIERE ACTUALIZAR
    public static void libroActualizar(){
        Libro lb = new Libro();
        int opcion;
        opcion = -1;
        System.out.println("TABLA LIBROS:\n\n");
        for (int i = 0; i < lb.listarLibro().size(); i++) {
            System.out.println(lb.listarLibro().get(i).toString());
        }
        Scanner in = new Scanner (System.in);
        System.out.println("Inserte el ISBN del libro que quiere actualizar");
        String isbn = in.nextLine();
        
        System.out.println(libroIsbn(isbn).toString());
        System.out.println("Seleccione el atributo a cambiar:");
        
        
        /***************************************************/
        
        while (opcion != 0){
            
            System.out.println("\tATRIBUTOS");
            System.out.println(libroIsbn(isbn).isbn + " | " + libroIsbn(isbn).titulo);
            System.out.println("----------------------------------------------------\n");
            System.out.println("1 - ISBN: "+libroIsbn(isbn).isbn);
            System.out.println("2 - TITULO "+ libroIsbn(isbn).titulo);
            System.out.println("3 - IDIOMA: "+libroIsbn(isbn).idioma);
            System.out.println("4 - FECHA DE PUBLICACIÓN: "+libroIsbn(isbn).fechaPubli);
            System.out.println("5 - BESTSELLER: "+libroIsbn(isbn).bestSeller);
            System.out.println("6 - RUTA DE PORTADA: "+libroIsbn(isbn).portada);
            System.out.println("0 - Salir");
            System.out.println("Selecciones una opción");
            opcion = in.nextInt();
            in.nextLine();
            switch(opcion){
                case 1:
                    System.out.println("Introduzca el nuevo ISBN:");
                    String vl = in.nextLine();
                    
                    while(vl.isEmpty()){
                        System.out.println("Este campo no puede ser vacío."
                                + " Introduca un valor:");
                        vl = in.nextLine();
                    }
                    lb.libroIsbn(isbn).setIsbn(vl);
                    libroDao.actualizar(libroIsbn(isbn));
                    break;
                
                case 2:
                    
                    System.out.println("Introduzca el nuevo TÍTULO:");
                    vl = in.nextLine();
                    
                    while(vl.isEmpty()){
                        System.out.println("Este campo no puede ser vacío."
                                + " Introduca un valor:");
                        vl = in.nextLine();
                    }
                    lb.libroIsbn(isbn).setTitulo(vl);
                    libroDao.actualizar(libroIsbn(isbn));
                    
                    break;
                
                case 3:
                    
                    System.out.println("Introduzca el nuevo IDIOMA:");
                    vl = in.nextLine();
                    
                    while(vl.isEmpty()){
                        System.out.println("Este campo no puede ser vacío."
                                + " Introduca un valor:");
                        vl = in.nextLine();
                    }
                    lb.libroIsbn(isbn).setIdioma(vl);
                    break;
                
                case 4:
                    System.out.println("Introduzca el nuevo FECHA:");
                    vl = in.nextLine();
                    
                    while(vl.isEmpty()){
                        System.out.println("Este campo no puede ser vacío."
                                + " Introduca un valor:");
                        vl = in.nextLine();
                    }
                    lb.libroIsbn(isbn).setFechaPubli(Date.valueOf(vl));
                    break;
                case 5:
                    System.out.println("¿Es o fue Best Seller?");
                    vl = in.nextLine().toLowerCase();
                    if(vl.equals("si")){
                        lb.libroIsbn(isbn).setBestSeller(true);
                    }else{
                        lb.libroIsbn(isbn).setBestSeller(false);
                    }
                    
                    break;
                case 6:
                    System.out.println("Introduzca el nombre de la imagen:");
                    vl = "src/main/img/"+in.nextLine();
                    lb.libroIsbn(isbn).setPortada(vl);
                    break;
                
                case 0:
                    break;
                
                default:
                    break;
            }
        }
    }
    
    
}
