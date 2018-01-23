/*
 * En este servlet se reciben las variables enviadas por frmVacante
 * 
 * 
 */
package vacatesWeb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
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
