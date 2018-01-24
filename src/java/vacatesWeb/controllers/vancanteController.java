/*
 * En este servlet se reciben las variables enviadas por frmVacante
 * 
 * 
 */
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
 * @author jsmorales
 */
public class vancanteController extends HttpServlet {
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // se recibe el parámetro action
        String actionParam = request.getParameter("action");
        
        System.out.println(actionParam);
        
        if("ver".equals(actionParam)){
            
            this.verDetalle(request, response);
        }else if("all".equals(actionParam)){
            
            this.verTodos(request, response);
        }else if("search".equals(actionParam)){
            
            this.verBusqueda(request, response);
        }
    }
    
    protected void verDetalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("Ejecutando ver detalle");
        
        //se toma la id de la vacante
        int idVacante = Integer.parseInt(request.getParameter("id")); // se pasa de string a int con la claseInteger y el metodo parseInt
        
        System.out.println(idVacante);
        
        conexion con = new conexion();
        
        vacanteDAO vacanteD = new vacanteDAO(con);
        
        vacante vacante = vacanteD.getById(idVacante);
        
        con.desconectar();
        
        System.out.println(vacante);
        
        RequestDispatcher rd; //permite hacer un reenvio de la solicitud
        
        request.setAttribute("detalleV", vacante); //se añade un atributo message al request
        
        rd = request.getRequestDispatcher("/detalle.jsp"); //al dispatcher se redirecciona al jsp
        
        rd.forward(request, response); //se ejecuta la redireccion
                
    }
    
    protected void verTodos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("Ejecutando ver busqueda");
                        
        conexion con = new conexion();
        
        vacanteDAO vacanteD = new vacanteDAO(con);
        
        List<vacante> lista = vacanteD.getAll();
        
        con.desconectar();
        
        System.out.println(lista);
        
        RequestDispatcher rd; //permite hacer un reenvio de la solicitud
        
        request.setAttribute("vacantes", lista); //se añade un atributo message al request
        
        rd = request.getRequestDispatcher("/vacantes.jsp"); //al dispatcher se redirecciona al jsp
        
        rd.forward(request, response); //se ejecuta la redireccion
                
    }
    
    protected void verBusqueda(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("Ejecutando ver busqueda");
        
        String query = request.getParameter("query");
        
        System.out.println(query);
        
        conexion con = new conexion();
        
        vacanteDAO vacanteD = new vacanteDAO(con);
        
        List<vacante> vacante = vacanteD.getBusqueda(query);
        
        con.desconectar();
        
        System.out.println(vacante);
        
        RequestDispatcher rd; //permite hacer un reenvio de la solicitud
        
        request.setAttribute("vacantes", vacante); //se añade un atributo message al request
        
        rd = request.getRequestDispatcher("/vacantes.jsp"); //al dispatcher se redirecciona al jsp
        
        rd.forward(request, response); //se ejecuta la redireccion
                
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        //se reciben los parametros enviados
        String nombreParam = request.getParameter("nombre");
        String descripcionParam = request.getParameter("descripcion");
        String detalleParam = request.getParameter("detalle");
        
        System.out.println(nombreParam);
        System.out.println(descripcionParam);
        System.out.println(detalleParam);
        
        //se instancia el modelo de la entidad vacante
        
        vacante vcte = new vacante(0);
        
        //se asignan los campos con los datos recibidos
        //con los metodos set de cada variable
        vcte.setNombre(nombreParam);
        vcte.setDescripcion(descripcionParam);
        vcte.setDetalle(detalleParam);
        
        //comprobando objeto
        System.out.println(vcte);
        
        //con el objeto creado se pasa a insertar el objeto en la base de datos
        
        conexion con = new conexion();
        vacanteDAO vacanteD = new vacanteDAO(con);
        
        boolean status = vacanteD.inserta(vcte);
        
        String msg = "";
        
        if(status){
            msg = "La vacante se guardó correctamente";
        }else{
            msg = "Error: No se guardó la vacante.";
        }
        
        con.desconectar();
        
        RequestDispatcher rd; //permite hacer un reenvio de la solicitud
        
        request.setAttribute("message", msg); //se añade un atributo message al request
        
        rd = request.getRequestDispatcher("/mensaje.jsp"); //al dispatcher se redirecciona al jsp
        
        rd.forward(request, response); //se ejecuta la redireccion
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>    

}
