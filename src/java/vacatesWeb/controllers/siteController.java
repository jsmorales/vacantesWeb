//Este servlet va a ser el controlador del inicio del sitio

package vacatesWeb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vacantesWeb.dao.conexion;
import vacantesWeb.dao.vacanteDAO;
import vacantesWeb.model.vacante;

/**
 *
 * @author johan
 */
public class siteController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //se prepara objeto para la redireccion
        RequestDispatcher rd;
        //se crea objeto de conexion
        conexion con = new conexion();
        //se crea objeto dao para consultar vacantes, como parametro la conexion instanciada
        vacanteDAO vctedao = new vacanteDAO(con);
        
        //se crea lista de tipo vacante para cargarla con el retorno
        //de la funcion de consulta de las 3 ultimas vacante
        List<vacante> lista3Vacantes = vctedao.get3Ultimas();
        
        System.out.println(lista3Vacantes);
        //se desconecta la base de datos
        con.desconectar();
        
        //se setea el atributo resultado al request de este metodo
        request.setAttribute("resultado", lista3Vacantes);
        //usando el objeto de redireccionamiento se crea la redireccion
        rd = request.getRequestDispatcher("/index.jsp");
        //se ejecuta la redireccion
        rd.forward(request, response);
        
        
    }

        
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
