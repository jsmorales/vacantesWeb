
package vacantesWeb.dao;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
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

    public List<vacante> get3Ultimas(){
        
        try {
            //se crea la sentencia sql
            String sql = "select * from vacante order by id desc limit 3";
            //se prepara la ejecucion
            PreparedStatement ps = (PreparedStatement) cnn.getConnection().prepareStatement(sql);
            //se ejcuta la sentencia dentro de un resultset(objeto que almacena resultados de bases de datos)            
            ResultSet rs = ps.executeQuery();
            //en una lista se cargan los resultados
            List<vacante> listav = new LinkedList<>();
            
            
            //se crea objeto vacio de tipo vacante de la clase modelo
            vacante vacante;
            
            //se itera el resultset
            while(rs.next()){
                
                //se instancia la clase vacante en el objeto vacante
                vacante = new vacante(rs.getInt("id"));
                vacante.setFechaPublicacion(rs.getDate("fechaPublicacion").toLocalDate());
                vacante.setNombre(rs.getString("nombre"));
                vacante.setDescripcion(rs.getString("descripcion"));
                vacante.setDetalle(rs.getString("detalle"));
                
                //se agrega a la lista el resultado obtenido
                listav.add(vacante);
            }
            
            return listav;
            
        } catch (Exception e) {
            
            System.out.println("Error vacante get3Ultimas: "+e.getMessage());
            return null;
        }
    }
    
    public List<vacante> getAll(){
        
        try {
            //se crea la sentencia sql
            String sql = "select * from vacante";
            //se prepara la ejecucion
            PreparedStatement ps = (PreparedStatement) cnn.getConnection().prepareStatement(sql);
            //se ejcuta la sentencia dentro de un resultset(objeto que almacena resultados de bases de datos)            
            ResultSet rs = ps.executeQuery();
            //en una lista se cargan los resultados
            List<vacante> listav = new LinkedList<>();
            
            
            //se crea objeto vacio de tipo vacante de la clase modelo
            vacante vacante;
            
            //se itera el resultset
            while(rs.next()){
                
                //se instancia la clase vacante en el objeto vacante
                vacante = new vacante(rs.getInt("id"));
                vacante.setFechaPublicacion(rs.getDate("fechaPublicacion").toLocalDate());
                vacante.setNombre(rs.getString("nombre"));
                vacante.setDescripcion(rs.getString("descripcion"));
                vacante.setDetalle(rs.getString("detalle"));
                
                //se agrega a la lista el resultado obtenido
                listav.add(vacante);
            }
            
            return listav;
            
        } catch (Exception e) {
            
            System.out.println("Error vacante getAll: "+e.getMessage());
            return null;
        }
    }
    
    public List<vacante> getBusqueda(String busqueda){
        
        try {
            //se crea la sentencia sql
            //" WHERE nombre OR descripcion LIKE '%"+busqueda+"%'"
            String cond = busqueda != "" ? " WHERE nombre OR descripcion LIKE '%"+busqueda+"%'" : "";
            
            String sql = "SELECT * FROM `vacante`"+cond;
            //se prepara la ejecucion
            PreparedStatement ps = (PreparedStatement) cnn.getConnection().prepareStatement(sql);
            //se ejcuta la sentencia dentro de un resultset(objeto que almacena resultados de bases de datos)            
            ResultSet rs = ps.executeQuery();
            //en una lista se cargan los resultados
            List<vacante> listav = new LinkedList<>();
            
            
            //se crea objeto vacio de tipo vacante de la clase modelo
            vacante vacante;
            
            //se itera el resultset
            while(rs.next()){
                
                //se instancia la clase vacante en el objeto vacante
                vacante = new vacante(rs.getInt("id"));
                vacante.setFechaPublicacion(rs.getDate("fechaPublicacion").toLocalDate());
                vacante.setNombre(rs.getString("nombre"));
                vacante.setDescripcion(rs.getString("descripcion"));
                vacante.setDetalle(rs.getString("detalle"));
                
                //se agrega a la lista el resultado obtenido
                listav.add(vacante);
            }
            
            return listav;
            
        } catch (Exception e) {
            
            System.out.println("Error vacante getBusqueda: "+e.getMessage());
            return null;
        }
    }
    
    //metodo para recuperar vacante por id, retorna un objeto de tipo vacante
    
    public vacante getById(int idVacante){
        
        try {
            String sql = "select * from vacante where id = ?";
            
            //se prepara la ejecucion
            PreparedStatement ps = (PreparedStatement) cnn.getConnection().prepareStatement(sql);
            
            ps.setInt(1, idVacante);
            //se ejcuta la sentencia dentro de un resultset(objeto que almacena resultados de bases de datos)            
            ResultSet rs = ps.executeQuery();
                                   
            //se crea objeto vacio de tipo vacante de la clase modelo
            vacante vacante = new vacante(0);
            
            //se itera el resultset
            while(rs.next()){
                
                //se instancia la clase vacante en el objeto vacante
                vacante.setId(rs.getInt("id"));
                vacante.setFechaPublicacion(rs.getDate("fechaPublicacion").toLocalDate());
                vacante.setNombre(rs.getString("nombre"));
                vacante.setDescripcion(rs.getString("descripcion"));
                vacante.setDetalle(rs.getString("detalle"));                               
                
            }
            
            return vacante;
            
        } catch (Exception e) {
            
            System.out.println("Error vacante getById: "+e.getMessage());
            return null;
        }
    }
    
}
