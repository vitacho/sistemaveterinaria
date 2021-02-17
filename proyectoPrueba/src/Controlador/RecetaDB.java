/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Receta;
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
public class RecetaDB {
         private Session st;

    public RecetaDB() {
        sessionHibernate();
    }

    public void sessionHibernate() {
        st = HibernateUtil.getSessionFactory().openSession();
    }
    
     public void nuevaReceta(Receta rec) {
        try {
            st.beginTransaction();
            st.save(rec);
            st.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR LA RECETA " + e.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }

    }
    
//         public List<Servicio> cargaServicio(String est, List<Servicio> lis) {
//
//        try {
//            lis = (List<Servicio>) st.createQuery("from Servicio where estado_serv='" + est + "'order by nombre_serv").list();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "ERROR AL TRAER LOS SERVICIOS " + e.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
//        }
//        return lis;
//    }
//     
      public List<Receta> cargaRecetaTabla(String est, List<Receta> lis) {
        try {
            lis = (List<Receta>) st.createQuery("from Receta where estado='" + est + "'order by id_receta").list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL TRAER LAS RECETAS " + e.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return lis;
    }
      
       public Receta traeRecetaID(int id) {
        Receta rec = null;
        try {
            rec = (Receta) st.load(Receta.class, id);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL TRAER LA RECETA MEDICA " + e.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return rec;
    }
       
       
       
      public List<Receta> cargarCodigoReceta (List<Receta> list) {
        try {
            list = (List<Receta>) st.createQuery("From Receta order by num_receta").list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al traer Receta " + e.getMessage());
        }
        return list;

    }

public List<Receta> buscarReceta(String num, List<Receta> lis) {
        try {
            lis = (List<Receta>) st.createQuery("From Receta where num_receta LIKE '%" + num + "%'").list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LOS DATOS DEL SERVICIO "+e.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
        return lis;
    }
   public Receta traenumReceta(String nroRec){
        Receta fact = null;
        
        try {
            Query query= st.createQuery("from Receta r Where r.num_receta = ?");
            query.setParameter(0, nroRec); // admin, secretaria, veterinario, cliente
            
            try {
                fact=(Receta)query.uniqueResult();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al traer la receta"+e.getMessage());
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al traer la receta"+e.getMessage());
        }
        
        return fact;
    }
    
}
