/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Rol;
import javax.swing.JOptionPane;
import org.hibernate.Query;

import org.hibernate.Session;
import utilidades.HibernateUtil;


public class RolDB {
    
      private Session st;
   
    public RolDB(){
       sessionHibernate();
    }
    
    /**
     * el mapeo de atributos entre una base de datos relacional 
     * tradicional y el modelo de objetos de una aplicación
     */
    public void sessionHibernate(){
        st = HibernateUtil.getSessionFactory().openSession();
    }
    
    public Rol traeRol(String nombre){
        Rol r = null;
        
        try {
            Query query= st.createQuery("from Rol ro Where ro.nombre_rol = ?");
            query.setParameter(0, nombre); // admin, secretaria, veterinario, cliente
            
            try {
                r=(Rol)query.uniqueResult();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al traer el rol"+e.getMessage());
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al traer el rol"+e.getMessage());
        }
        
        return r;
    }
    
}
