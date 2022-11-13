/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MaximoMestriner
 */
public class Conexion {
    
    
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/mydb?useSSL=false"
                +"&useTimezone=true&serverTimezone=UTC"
                +"&allowPublicKeyRetrieval=true&";
    
    private static final String JDBC_USER="root";
    
    private static final String JDBC_PASSWORD="1234";
    
    //Método que establece la conexión
    
    public static Connection getConnection() throws SQLException{ 
        return DriverManager.getConnection(JDBC_URL, JDBC_USER,JDBC_PASSWORD);
    }
    
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
    public static void close(Statement stm) throws SQLException{
        stm.close();
    }
    
    public static void close(PreparedStatement stmt) throws SQLException{
        stmt.close();
    }
    
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
}
