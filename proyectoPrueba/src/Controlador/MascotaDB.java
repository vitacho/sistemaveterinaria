/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Mascota;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import utilidades.HibernateUtil;

/**
 *
 * @author DELL
 */
public class MascotaDB {
     private Session st;

    public MascotaDB() {
        sessionHibernate();
    }
    
     public void sessionHibernate(){
        st = HibernateUtil.getSessionFactory().openSession();
    }
    
    public void nuevaMascota(Mascota mascota){
        try {
            st.beginTransaction();
            st.save(mascota);
            st.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar "+e.getMessage());
        }
    }
}
