
package vacantesWeb.dao;

import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import vacantesWeb.model.vacante;


/**
 *
 * @author jsmorales
 * Esta clase contiene todos los metodos sql de esta entidad CRUD
 */
public class vacanteDAO {
    //VARIABLE PARA LA CONEXION
    conexion cnn;
    
    //En el constructor se pasa la variable de conexion
    //para poderlo usar en todos los metodos
    public vacanteDAO(conexion cnn) {
        this.cnn = cnn;
    }
    
    public boolean inserta(vacante vacante){
        String sql = "insert into vacante values(?,?,?,?,?);";
        try {
            //se instancia el metodo para ejecutar la sentencia sql
            
            //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            
            //se instancia un statatement y se declaran las variables para cada poscicion
            PreparedStatement pa = (PreparedStatement) cnn.getConnection().prepareStatement(sql);
            pa.setInt(1, vacante.getId());
            pa.setString(2, vacante.getFechaPublicacion().toString());
            pa.setString(3, vacante.getNombre());
            pa.setString(4, vacante.getDescripcion());
            pa.setString(5, vacante.getDetalle());
            
            pa.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(vacanteDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            return false;
        }
    }    
    
}
