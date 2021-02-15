/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Receta;
import java.util.List;
import javax.swing.JOptionPane;
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
            list = (List<Receta>) st.createQuery("From Receta order by num_cita").list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al traer Equipo " + e.getMessage());
        }
        return list;

    }
    
}
