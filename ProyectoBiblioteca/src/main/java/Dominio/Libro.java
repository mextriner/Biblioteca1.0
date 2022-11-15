/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.awt.Image;
import java.sql.Date;
import javax.swing.ImageIcon;

/**
 *
 * @author MaximoMestriner
 */
public class Libro {
    private String isbn;
    private String titulo;
    private String idioma;
    private Date fechaPubli;
    private boolean bestSeller;
    String portada;

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
        return "Libro{" + "isbn=" + isbn + ", titulo=" + titulo + ", idioma=" + idioma + ", fechaPubli=" + fechaPubli + ", bestSeller=" + bestSeller + ", portada=" + portada + '}';
    }

    
    
    public String escribir() {
        return '%' + '*' + isbn + '*' + titulo + '*' + idioma + '*' + fechaPubli + '*' + bestSeller + '*' ;
    }
    
    
}
