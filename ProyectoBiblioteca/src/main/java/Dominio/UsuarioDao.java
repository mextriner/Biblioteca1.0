/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import static AccesoDatos.Conexion.close;
import static AccesoDatos.Conexion.getConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MaximoMestriner
 */
public class UsuarioDao {

    private static final String SQL_SELECT ="SELECT * FROM usuario";
    
    private static final String SQL_DECRYPT = "SELECT usuario, "
            + "CAST(AES_DECRYPT(?,'key'))AS clave FROM idUsuario";
    
    private static final String SQL_INSERT = "INSERT INTO usuario (usuario,"
            + "clave,fechaAlt) VALUES (?,AES_ENCRYPT(?,'key'),?)";
    
    private static final String SQL_INSERT_ALL = "INSERT INTO usuario (nombre,"
            + "apellido,direccion, fechaNac) VALUES (?,?,?,?)";
    
    private static final String SQL_UPDATE = "UPDATE usuario SET "
            + "nombre = ?,"
            + "apellido = ?,"
            + "direccion = ?,"
            + "fechaNac = ?"
            + "WHERE usuario = ?";
    
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE usuario = ?";
    
//    Método que nos lista todas las personas de nuestro sistema
    public List<Usuario> seleccionar() throws SQLException {
        //INICIALIZAR VARIABLES
        
        Connection conn = null;
        PreparedStatement stmt= null;
        ResultSet rs = null;
        
        //he quitado usuario = nell
        List<Usuario> usuarios = new ArrayList<>();
        
        conn = getConnection();
        stmt = conn.prepareStatement(SQL_SELECT);
        rs = stmt.executeQuery();
        
        while(rs.next()){
            String usuario = rs.getString("usuario");
            String clave = rs.getString("clave");
            Date fechaAlt = rs.getDate("fechaAlt");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            Date fechaNac = rs.getDate("fechaNac");
            
            //Instancio un nuevo objeto
            usuarios.add(new Usuario(usuario,clave,fechaAlt,nombre,apellido,fechaNac));
            
        }
        
        
        close(rs);
        close(stmt);
        close(conn);
        
        return usuarios;
    }
    
    public List<Usuario> verClaves() throws SQLException {
        //INICIALIZAR VARIABLES
        
        Connection conn = null;
        PreparedStatement stmt= null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();
        
        conn = getConnection();
        stmt = conn.prepareStatement(SQL_DECRYPT);
        rs = stmt.executeQuery();
        
        while(rs.next()){
            String usuarioId = rs.getString("usuario");
            String clave = rs.getString("cast(aes_decrypt(clave,'key'))as char");
            
            //Instancio un nuevo objeto
            usuarios.add(new Usuario(usuarioId, clave));
            
        }
        
        
        close(rs);
        close(stmt);
        close(conn);
        
        return usuarios;
    }
    
    //MÉTODO QUE INSERTA UNA PERSONA EN EL SISTEMA
    public int insertar (Usuario usuario){
        Connection conn =null;
        PreparedStatement stmt=null;
        int registro = 0;
        
        try{
             //1. ESTABLECER CONEXIÓN
        
            conn = getConnection();
            
            //2. PREPARO LA INSTRUCCIÓN EJECUTABLE EN MYSQL
            
            stmt = conn.prepareStatement(SQL_INSERT);
            
            
            //3. ASIGNAR LOS VALORES A LOS INTERROGANTES DE LA CONSULTA
            
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getClave());
            stmt.setDate(3, usuario.getFechaAlt());
            
            
            
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
    
    
    public int insertarAll (Usuario usuario){
        Connection conn =null;
        PreparedStatement stmt=null;
        int registro = 0;
        
        try{
             //1. ESTABLECER CONEXIÓN
        
            conn = getConnection();
            
            //2. PREPARO LA INSTRUCCIÓN EJECUTABLE EN MYSQL
            
            stmt = conn.prepareStatement(SQL_INSERT_ALL);
            
            
            //3. ASIGNAR LOS VALORES A LOS INTERROGANTES DE LA CONSULTA
            
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getClave());
            stmt.setString(3, usuario.getNombre());
            stmt.setString(4, usuario.getApellido());
            stmt.setDate(5, usuario.getFechaAlt());
            stmt.setDate(6, usuario.getFechaNac());
            
            
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
    
    public int actualizar (Usuario usuario){
        Connection conn =null;
        PreparedStatement stmt=null;
        int registro = 0;
        
        try{
             //1. ESTABLECER CONEXIÓN
        
            conn = getConnection();
            
            //2. PREPARO LA INSTRUCCIÓN EJECUTABLE EN MYSQL
            
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            
            //3. ASIGNAR LOS VALORES A LOS INTERROGANTES DE LA CONSULTA
            
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setDate(3, usuario.getFechaNac());
            stmt.setString(4, usuario.getUsuario());
            
            
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
    
    public int eliminar (Usuario usuario){
        Connection conn =null;
        PreparedStatement stmt=null;
        int registro = 0;
        
        try{
             //1. ESTABLECER CONEXIÓN
        
            conn = getConnection();
            
            //2. PREPARO LA INSTRUCCIÓN EJECUTABLE EN MYSQL
            
            stmt = conn.prepareStatement(SQL_DELETE);
            
            
            //3. ASIGNAR LOS VALORES A LOS INTERROGANTES DE LA CONSULTA
            
            stmt.setString(1, usuario.getUsuario());
            
            
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
