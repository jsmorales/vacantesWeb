/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacatesWeb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vacantesWeb.dao.conexion;
import vacantesWeb.dao.usuarioDAO;
import vacantesWeb.model.usuario;

/**
 *
 * @author jsmorales
 */
public class usuarioController extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String actionParam = request.getParameter("action");
        
        System.out.println(actionParam);
        
        //se instancia HttpSession para validar si existe o no una sesion creada
        HttpSession sesion = request.getSession();
        RequestDispatcher rd;
        String msg = "";
        
        switch (actionParam){
            case "validaLogin" : {
                if(sesion.getAttribute("usuario") == null ){
                    msg = "No ha iniciado sesión.";
                    request.setAttribute("mensaje", msg);
                    rd = request.getRequestDispatcher("/login.jsp"); //al dispatcher se redirecciona al jsp        
                    rd.forward(request, response); //se ejecuta la redireccion
                }else{
                    rd = request.getRequestDispatcher("/admin.jsp"); //al dispatcher se redirecciona al jsp        
                    rd.forward(request, response); //se ejecuta la redireccion
                }
                break;
            }
            case "logout" : {
                sesion.invalidate();
                //msg = "Ha cerrado la sesión.";
                rd = request.getRequestDispatcher("/homepage"); //al dispatcher se redirecciona al jsp        
                rd.forward(request, response); //se ejecuta la redireccion
                break;
            }
            case "crearVacante" : {
                if(sesion.getAttribute("usuario") == null ){
                    msg = "Acceso Denegado!";
                    request.setAttribute("mensaje", msg);
                    rd = request.getRequestDispatcher("/login.jsp"); //al dispatcher se redirecciona al jsp        
                    rd.forward(request, response); //se ejecuta la redireccion
                }else{
                    rd = request.getRequestDispatcher("/frmVacante.jsp"); //al dispatcher se redirecciona al jsp        
                    rd.forward(request, response); //se ejecuta la redireccion
                }
                break;
            }
        }
                
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //el post de este servlet es para poder hacer login a la app
        //usando el DAO de usuario, metodo login
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        
        //mensaje para retornar
        String msg = "";
        
        //se recupera una instancia del objeto HttpSession
        HttpSession sesion = request.getSession(); //las variables de sesion estan disponibles para toda la aplicacion
        
        //se instancia el objeto de conexion
        conexion con = new conexion();
        //se instancia el objeto usuario
        usuarioDAO usr = new usuarioDAO(con);
        //se usa el metodo login del objeto usuario de clase usuarioDAO detro de una variable tipo usuario que es el modelo
        usuario usrObj = usr.login(user, pass);
        //se desconecta la base de datos
        con.desconectar();
        
        RequestDispatcher rd; //permite hacer un reenvio de la solicitud
        
        if(usrObj.getId() > 0){ //evalua si el objeto usuario cargó el id desde la base de datos
            //se crea la variable de sesion con los datos del usuario
            sesion.setAttribute("usuario", usrObj); //se setea el atributo usuario en la sesion con el objeto usuario creado anteriormente
            //para acceder a este objeto se usa el nombre del atributo.nombre de la propiedad del objeto
            rd = request.getRequestDispatcher("/admin.jsp"); //al dispatcher se redirecciona al jsp
            rd.forward(request, response); //se ejecuta la redireccion
        }else{
            msg = "Nombre de usuario o contraseña inválidos.";            
            request.setAttribute("mensaje", msg); //se añade un atributo message al request        
            rd = request.getRequestDispatcher("/login.jsp"); //al dispatcher se redirecciona al jsp
            rd.forward(request, response); //se ejecuta la redireccion
        }
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
