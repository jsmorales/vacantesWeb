
package vacantesWeb.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import vacantesWeb.model.usuario;

/**
 *
 * @author johan
 */
public class usuarioDAO {
    
    conexion cnn;

    public usuarioDAO(conexion cnn) {
        this.cnn = cnn;
    }
    
    public usuario login(String user, String pass){
        
        try {
            
            String sql = "select * from usuario where username=? and password=md5(?) and estatus='activo' limit 1";
            
            PreparedStatement ps;
            ps = cnn.getConnection().prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            
            ResultSet rs = ps.executeQuery();
            
            usuario usuario = new usuario(0);
            
            while (rs.next()) {                
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setPerfil(rs.getString("perfil"));
                usuario.setEstatus(rs.getString("estatus"));                
            }
            
            return usuario;
            
        } catch (SQLException e) {//hacer catch a sqlexception
            System.out.println("vacantesWeb.dao.usuarioDAO.login() Error: "+e);
            return null;
        }
    }
    
}
