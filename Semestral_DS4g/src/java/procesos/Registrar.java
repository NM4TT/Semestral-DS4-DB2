
package procesos;

import entidades.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author Polar
 */
public class Registrar {
    
    public static int insertarUsuario(Usuario usuario) throws SQLException {
        
        int resultado = 0;
        
        Connection conn = Conectar.conectar();
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO Usuario(cedula,correo,contraseña,nombre,apellido) VALUES ('"
                    + usuario.getCedula() + "','"
                    + usuario.getCorreo() + "','"
                    + usuario.getPassword() + "','"
                    + usuario.getNombre() + "','"
                    + usuario.getApellido() + "')";
            
            resultado = stmt.executeUpdate(query);
            
        Conectar.cerrarConexiones(conn, stmt);
        
        return resultado;
    }
    
    public static boolean revisarExistencia(String correo, String cedula){
    
        boolean resultado = false;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = Conectar.conectar();
            stmt = conn.createStatement();
            String query = "SELECT * FROM Usuario WHERE "
                    + "correo='" + correo + "' or cedula='" + cedula+"'";
            
            rs = stmt.executeQuery(query);
            
            if(rs.next()){
                resultado = true;
            }
            

            
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        
        Conectar.cerrarConexiones(conn, stmt, rs);
        
        return resultado;
    }
    
}
