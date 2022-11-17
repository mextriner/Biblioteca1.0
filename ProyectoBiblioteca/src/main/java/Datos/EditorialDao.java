/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.Editorial;
import static AccesoDatos.Conexion.close;
import static AccesoDatos.Conexion.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MaximoMestrienr
 */
public class EditorialDao {
    private static final String SQL_SELECT ="SELECT * FROM editorial";
    private static final String SQL_INSERT = "INSERT INTO autor (nombre,direccion) VALUES (?,?,?)";
    
    private static final String SQL_UPDATE = "UPDATE editorial SET "
            + "nombre = ?,"
            + "direccion = ?, WHERE idEditorial = ?";
    
    private static final String SQL_DELETE = "DELETE FROM editorial WHERE idEditorial = ?";
    
//    Método que nos lista todas las personas de nuestro sistema
    public List<Editorial> seleccionar() throws SQLException {
        //INICIALIZAR VARIABLES
        
        Connection conn = null;
        PreparedStatement stmt= null;
        ResultSet rs = null;
        Editorial editorial = null;
        List<Editorial> editoriales = new ArrayList<>();
        
        conn = getConnection();
        stmt = conn.prepareStatement(SQL_SELECT);
        rs = stmt.executeQuery();
        
        while(rs.next()){
            int ideditorial = rs.getInt("idEditorial");
            String nombre = rs.getString("nombre");
            String direccion = rs.getString("direccion");
            
            //Instancio un nuevo objeto
            editoriales.add(new Editorial(ideditorial, nombre,direccion));
            
        }
        
        
        close(rs);
        close(stmt);
        close(conn);
        
        return editoriales;
    }
    
    //MÉTODO QUE INSERTA UNA PERSONA EN EL SISTEMA
    public int insertar (Editorial editorial){
        Connection conn =null;
        PreparedStatement stmt=null;
        int registro = 0;
        
        try{
             //1. ESTABLECER CONEXIÓN
        
            conn = getConnection();
            
            //2. PREPARO LA INSTRUCCIÓN EJECUTABLE EN MYSQL
            
            stmt = conn.prepareStatement(SQL_INSERT);
            
            
            //3. ASIGNAR LOS VALORES A LOS INTERROGANTES DE LA CONSULTA
            
            stmt.setInt(1, editorial.getIdEditorial());
            stmt.setString(2, editorial.getNombre());
            stmt.setString(3, editorial.getDireccion());
            
            
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
    
    public int actualizar (Editorial editorial){
        Connection conn =null;
        PreparedStatement stmt=null;
        int registro = 0;
        
        try{
             //1. ESTABLECER CONEXIÓN
        
            conn = getConnection();
            
            //2. PREPARO LA INSTRUCCIÓN EJECUTABLE EN MYSQL
            
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            
            //3. ASIGNAR LOS VALORES A LOS INTERROGANTES DE LA CONSULTA
            
            stmt.setString(1, editorial.getNombre());
            stmt.setString(2, editorial.getDireccion());
            stmt.setInt(3, editorial.getIdEditorial());
            
            
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
    
    public int eliminar (Editorial editorial){
        Connection conn =null;
        PreparedStatement stmt=null;
        int registro = 0;
        
        try{
             //1. ESTABLECER CONEXIÓN
        
            conn = getConnection();
            
            //2. PREPARO LA INSTRUCCIÓN EJECUTABLE EN MYSQL
            
            stmt = conn.prepareStatement(SQL_DELETE);
            
            
            //3. ASIGNAR LOS VALORES A LOS INTERROGANTES DE LA CONSULTA
            
            stmt.setInt(1, editorial.getIdEditorial());
            
            
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
