/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genFunctions;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author henry.mora
 */
public class notificacion {
    
    public String msg;
    public RequestDispatcher rd;
    
    public void notificar(String mensaje, String pagina, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        this.msg = mensaje;
        request.setAttribute("message", msg);
        rd = request.getRequestDispatcher(pagina); //al dispatcher se redirecciona al jsp        
        rd.forward(request, response); //se ejecuta la redireccion
    }
}
