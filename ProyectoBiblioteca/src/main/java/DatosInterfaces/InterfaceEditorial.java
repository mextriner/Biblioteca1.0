/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DatosInterfaces;

import Dominio.Editorial;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Maximo
 */
public interface InterfaceEditorial {
    
    
    public List<Editorial> seleccionar() throws SQLException;
    
    public int insertar (Editorial editorial);
    
    public int actualizar (Editorial editorial);
    
    public int eliminar (Editorial editorial);
    
    
}
