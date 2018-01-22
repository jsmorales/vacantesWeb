
package vacantesWeb.dao;
import java.sql.*;
/**
 *
 * @author henry.mora
 */
public class conexion {
    
    /**
     *Declaracion de variables de conexion
     * 
     **/
    
    static String bd = "vacantesWeb";
    static String user = "root";
    static String pass = "";
    static String url = "jdbc:mysql://localhost/"+bd;
    
    Connection conn = null;
    
    public conexion(){
        
        try{
           Class.forName("com.mysql.jdbc.Driver");
           
           conn = DriverManager.getConnection(url,user,pass);
           
           if(conn != null){
               System.out.println("Conectado a la base de datos "+bd+" OK.");
           }
           
        }catch(SQLException e){
            System.out.println("Error de conexion: "+e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println("Error de driver: "+e.getMessage());
        }
    }
    
    public Connection getConnection(){
        return conn;
    }
    
    public void desconectar(){
       System.out.println("Desconectando base de datos: "+bd);
       
       try{
       if(conn != null){
            conn.close();
            System.out.println("Desconectado de la base de datos "+bd);
        }
       }catch(SQLException e){
            System.out.println("Error de conexion: "+e.getMessage());
        }
    }
}
