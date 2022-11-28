/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    package Datos;
    
    
    import static AccesoDatos.Conexion.close;
    import Dominio.Autor;
    import static AccesoDatos.Conexion.getConnection;
    import DatosInterfaces.InterfaceAutor;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Date;
    import java.util.ArrayList;
    import java.util.List;

/**
 *
 * @author MaximoMestriner
 */
public class AutorDao implements InterfaceAutor{
    private static final String SQL_SELECT ="SELECT * FROM autor ORDER BY nombre";
    private static final String SQL_INSERT = "INSERT INTO autor (nombre,"
            + "apellido,nacionalidad, fechaNac) VALUES (?,?,?,?)";
    
    private static final String SQL_UPDATE = "UPDATE autor SET "
            + "nombre = ?,"
            + "apellido = ?,"
            + "nacionalidad = ?,"
            + "fechaNac = ? WHERE idAutor = ?";
    
    private static final String SQL_DELETE = "DELETE FROM autor WHERE idAutor = ?";
    
//    Método que nos lista todas las personas de nuestro sistema
    public List<Autor> seleccionar() throws SQLException {
        //INICIALIZAR VARIABLES
        
        Connection conn = null;
        PreparedStatement stmt= null;
        ResultSet rs = null;
        List<Autor> autores = new ArrayList<>();
        
        conn = getConnection();
        stmt = conn.prepareStatement(SQL_SELECT);
        rs = stmt.executeQuery();
        
        while(rs.next()){
            int idautor = rs.getInt("idAutor");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String nacionalidad = rs.getString("nacionalidad");
            Date fechaNac = rs.getDate("fechaNac");
            
            //Instancio un nuevo objeto
            autores.add(new Autor(idautor, nombre,apellido,nacionalidad,fechaNac));
            
        }
        
        
        close(rs);
        close(stmt);
        close(conn);
        
        return autores;
    }
    
    //MÉTODO QUE INSERTA UNA PERSONA EN EL SISTEMA
    public int insertar (Autor autor){
        Connection conn =null;
        PreparedStatement stmt=null;
        int registro = 0;
        
        try{
             //1. ESTABLECER CONEXIÓN
        
            conn = getConnection();
            
            //2. PREPARO LA INSTRUCCIÓN EJECUTABLE EN MYSQL
            
            stmt = conn.prepareStatement(SQL_INSERT);
            
            
            //3. ASIGNAR LOS VALORES A LOS INTERROGANTES DE LA CONSULTA
            
            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getApellido());
            stmt.setString(3, autor.getNacionalidad());
            stmt.setDate(4, autor.getFechaNac());
            
            
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
    
    public int actualizar (Autor autor){
        Connection conn =null;
        PreparedStatement stmt=null;
        int registro = 0;
        
        try{
             //1. ESTABLECER CONEXIÓN
        
            conn = getConnection();
            
            //2. PREPARO LA INSTRUCCIÓN EJECUTABLE EN MYSQL
            
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            
            //3. ASIGNAR LOS VALORES A LOS INTERROGANTES DE LA CONSULTA
            
            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getApellido());
            stmt.setString(3, autor.getNacionalidad());
            stmt.setDate(4, autor.getFechaNac());
            stmt.setInt(5, autor.getIdautor());
            
            
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
    
    public int eliminar (Autor autor){
        Connection conn =null;
        PreparedStatement stmt=null;
        int registro = 0;
        
        try{
             //1. ESTABLECER CONEXIÓN
        
            conn = getConnection();
            
            //2. PREPARO LA INSTRUCCIÓN EJECUTABLE EN MYSQL
            
            stmt = conn.prepareStatement(SQL_DELETE);
            
            
            //3. ASIGNAR LOS VALORES A LOS INTERROGANTES DE LA CONSULTA
            
            stmt.setInt(1, autor.getIdautor());
            
            
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
