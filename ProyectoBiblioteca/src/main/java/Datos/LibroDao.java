/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.Libro;
import static AccesoDatos.Conexion.close;
import static AccesoDatos.Conexion.getConnection;
import DatosInterfaces.InterfaceLibro;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MaximoMestriner
 */
public class LibroDao implements InterfaceLibro{
     private static final String SQL_SELECT ="SELECT * FROM libro";
    private static final String SQL_INSERT = "INSERT INTO libro (isbn, titulo,"
            + "idioma, fechaPublicacion, bestSeller, portada) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE_ISBN = "UPDATE libro SET isbn = ? WHERE isbn = ?"; 
    private static final String SQL_UPDATE = "UPDATE libro SET "
            + "titulo = ?,"
            + "idioma = ?,"
            + "fechaPublicacion = ?,"
            + "bestSeller = ? WHERE isbn = ?";
    
    private static final String SQL_UPDATE_IMAGEN = "UPDATE libro SET "
            + "portada = ? WHERE isbn = ?";
    
    private static final String SQL_DELETE = "DELETE FROM libro WHERE isbn = ?";
    
//    Método que nos lista todas las personas de nuestro sistema
    public List <Libro> seleccionar() throws SQLException {
        //INICIALIZAR VARIABLES
        
        Connection conn = null;
        PreparedStatement stmt= null;
        ResultSet rs = null;
        Libro libro = null;
        List<Libro> libros = new ArrayList<>();
        
        conn = getConnection();
        stmt = conn.prepareStatement(SQL_SELECT);
        rs = stmt.executeQuery();
        
        while(rs.next()){
            String isbn = rs.getString("isbn");
            String titulo = rs.getString("titulo");
            String idioma = rs.getString("idioma");
            boolean bestSeller = rs.getBoolean("bestSeller");
            Date fechaPubli = rs.getDate("fechaPublicacion");
            
            //Instancio un nuevo objeto
            libros.add(new Libro(isbn, titulo,idioma,fechaPubli,bestSeller));
            
        }
        
        
        close(rs);
        close(stmt);
        close(conn);
        
        return libros;
    }
    
    
    
    //MÉTODO QUE INSERTA UNA PERSONA EN EL SISTEMA
    public int insertar (Libro libro){
        Connection conn =null;
        PreparedStatement stmt=null;
        FileInputStream imagen = null;
        int registro = 0;
        
        try{
             //1. ESTABLECER CONEXIÓN
        
            conn = getConnection();
            
            //2. PREPARO LA INSTRUCCIÓN EJECUTABLE EN MYSQL
            
            stmt = conn.prepareStatement(SQL_INSERT);
            
            
            //SE TRANSFORMA LA RUTA DE LA IMAGEN A FLIEINPUTSTREAM
            try{
                imagen = new FileInputStream(libro.getPortada());
            }catch(IOException IOex){
                IOex.printStackTrace(System.out);
            }
            
            
            
                
            //3. ASIGNAR LOS VALORES A LOS INTERROGANTES DE LA CONSULTA
            
            stmt.setString(1, libro.getIsbn());
            stmt.setString(2, libro.getTitulo());
            stmt.setString(3, libro.getIdioma());
            stmt.setDate(4, libro.getFechaPubli());
            stmt.setBoolean(5, libro.isBestSeller());
            stmt.setBlob(6, imagen);
            
            
            //4. EJECUTO LA QUERY
            
            registro = stmt.executeUpdate();
            
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            try{
                close(stmt);
                close(conn);
            }catch(SQLException ex){
                ex.printStackTrace(System.out);
            
            }
            
        }
        return registro;
    }
    
    public int actualizar (Libro libro){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registro = 0;
        try{
             //1. ESTABLECER CONEXIÓN
        
            conn = getConnection();
            
            //2. PREPARO LA INSTRUCCIÓN EJECUTABLE EN MYSQL
            
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            //SE TRANSFORMA LA RUTA DE LA IMAGEN A FLIEINPUTSTREAM
            
            //3. ASIGNAR LOS VALORES A LOS INTERROGANTES DE LA CONSULTA
            
            
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getIdioma());
            stmt.setDate(3, libro.getFechaPubli());
            stmt.setBoolean(4, libro.isBestSeller());
            stmt.setString(5, libro.getIsbn());
            
            
            //4. EJECUTO LA QUERY
            
            registro = stmt.executeUpdate();
            
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            try{
                close(stmt);
                close(conn);
            }catch(SQLException ex){
                ex.printStackTrace(System.out);
            
            }
            
        }
        return registro;
    }
    
    public int actualizarIsbn (Libro libro, String prevIsbn){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registro = 0;
        try{
             //1. ESTABLECER CONEXIÓN
        
            conn = getConnection();
            
            //2. PREPARO LA INSTRUCCIÓN EJECUTABLE EN MYSQL
            
            stmt = conn.prepareStatement(SQL_UPDATE_ISBN);
            
            //SE TRANSFORMA LA RUTA DE LA IMAGEN A FLIEINPUTSTREAM
            
            //3. ASIGNAR LOS VALORES A LOS INTERROGANTES DE LA CONSULTA
            
            
            stmt.setString(1, prevIsbn);
            stmt.setString(2, libro.getIsbn());
            
            
            //4. EJECUTO LA QUERY
            
            registro = stmt.executeUpdate();
            
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            try{
                close(stmt);
                close(conn);
            }catch(SQLException ex){
                ex.printStackTrace(System.out);
            
            }
            
        }
        return registro;
    }
    
    public int actualizarPortada(Libro libro){
        Connection conn = null;
        PreparedStatement stmt = null;
        FileInputStream imagen = null;
        int registro = 0;
        try{
             //1. ESTABLECER CONEXIÓN
        
            conn = getConnection();
            
            //2. PREPARO LA INSTRUCCIÓN EJECUTABLE EN MYSQL
            
            stmt = conn.prepareStatement(SQL_UPDATE_IMAGEN);
            
            //SE TRANSFORMA LA RUTA DE LA IMAGEN A FLIEINPUTSTREAM
            try{
                imagen = new FileInputStream(libro.getPortada());
            }catch(IOException IOex){
                IOex.printStackTrace(System.out);
            }
            //3. ASIGNAR LOS VALORES A LOS INTERROGANTES DE LA CONSULTA
            
            
            stmt.setBlob(1, imagen);
            stmt.setString(2, libro.getIsbn());
            
            
            //4. EJECUTO LA QUERY
            
            registro = stmt.executeUpdate();
            
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            try{
                close(stmt);
                close(conn);
            }catch(SQLException ex){
                ex.printStackTrace(System.out);
            
            }
            
        }
        return registro;
    }
    
    public int eliminar (Libro libro){
        Connection conn =null;
        PreparedStatement stmt=null;
        int registro = 0;
        
        try{
             //1. ESTABLECER CONEXIÓN
        
            conn = getConnection();
            
            //2. PREPARO LA INSTRUCCIÓN EJECUTABLE EN MYSQL
            
            stmt = conn.prepareStatement(SQL_DELETE);
            
            
            //3. ASIGNAR LOS VALORES A LOS INTERROGANTES DE LA CONSULTA
            
            stmt.setString(1, libro.getIsbn());
            
            
            //4. EJECUTO LA QUERY
            
            registro = stmt.executeUpdate();
            
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            try{
                close(stmt);
                close(conn);
            }catch(SQLException ex){
                ex.printStackTrace(System.out);
            
            }
            
        }
        return registro;
    }
}
