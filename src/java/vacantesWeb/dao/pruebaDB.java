/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacantesWeb.dao;

import vacantesWeb.model.usuario;

/**
 *
 * @author henry.mora
 */
public class pruebaDB extends conexion{
    
   public static void main(String args[]){
       conexion conecta = new conexion();
       //jsmorales 12345
       usuarioDAO usrDao = new usuarioDAO(conecta);
       usuario usr = usrDao.login("jsmorales", "12345");
       System.out.println(usr);
   } 
}
