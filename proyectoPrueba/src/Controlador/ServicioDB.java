/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Servicio;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import utilidades.HibernateUtil;

/**
 *
 * @author Personal
 */
public class ServicioDB {
      private Session st;

    public ServicioDB() {
         sessionHibernate();
         
    }

      public void sessionHibernate() {
        st = HibernateUtil.getSessionFactory().openSession();
    }
       public void nuevoServicio(Servicio servicio) {
        try {
            st.beginTransaction();
            st.save(servicio);
            st.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "SERVICIO GUARDADO", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR  " + e.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
       
         public void actualizaServicio(Servicio servicio){
        try{
            st.clear();
            st.beginTransaction();
            st.update(servicio);
            st.getTransaction().commit();
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR LOS DATOS "+e.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
             
         public List<Servicio> cargaServicio(String est, List<Servicio> lis) {
////            try {
//            lis = (List<Persona>)st.createQuery("from Persona where estado='"+estado+"'order by nombre").list();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error al cargar los datos en la tabla "+e.getMessage());
//        }
//        
//        return lis;
        try {
            lis = (List<Servicio>) st.createQuery("from Servicio where estado_serv='" + est + "'order by nombre_serv").list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL TRAER LOS SERVICIOS " + e.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return lis;
    }
           
           
    public Servicio traeServicio(int id) {
        Servicio m = null;
        try {
            m = (Servicio) st.load(Servicio.class, id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL TRAER EL SERVICIO " + e.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return m;
    }
    
       public Servicio traeNombreServicio (String nombre) {
        Servicio ser = null; 
        try {
            Query query = st.createQuery("from Servicio se Where se.nombre_serv = ?");
            query.setParameter(0, nombre);
            try {
                ser = (Servicio) query.uniqueResult();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "LA DESCRIPCIÓN DEL SERVICIO YA EXISTE EN EL SISTEMA", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL TRAER EL SERVICIO " + e.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return ser;
    }
 public Servicio traeServicioId(int idServicio){
        
        Servicio serv = null;
        
        try {
            serv = (Servicio)st.load(Servicio.class, idServicio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos del cliente "+e.getMessage());
        }
        
        return serv;
    }
     public List<Servicio> buscarServicio(String nombre, List<Servicio> lis) {
        try {
            lis = (List<Servicio>) st.createQuery("From Servicio where nombre_serv LIKE '%" + nombre + "%'").list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LOS DATOS DEL SERVICIO "+e.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return lis;
    }


    
}
