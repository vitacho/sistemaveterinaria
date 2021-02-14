/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Mascota;
import java.util.ArrayList;
import java.util.List;
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
    
    public void actualizarMascota(Mascota mascota){
        try {
            st.beginTransaction();
            st.update(mascota);
            st.getTransaction().commit();
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Modificar el cliente "+e.getMessage());
        }
    }
    public List<Mascota> listarMascotas() {
        List<Mascota> list = new ArrayList<Mascota>();
        try {
            list = (List<Mascota>) st.createQuery("From Mascota order by id").list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al traer la lista " + e.getMessage());
        }
        return list;
    } 
    
     public List<Mascota> listarMascotasId(int idPersona) {
        List<Mascota> list = new ArrayList<Mascota>();
        try {
            list = (List<Mascota>) st.createQuery("From Mascota where persona_id_persona='"+idPersona+"'").list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al traer la lista " + e.getMessage());
        }
        return list;
    } 
     
    public Mascota traerMascota(int idPersona,String nombre) {
        Mascota m = new Mascota();
        List<Mascota> list = new ArrayList<Mascota>();
        try {
            list = (List<Mascota>) st.createQuery("From Mascota where persona_id_persona='"+idPersona+"'").list();
            for (Mascota mascota : list) {
                if (mascota.getNombre().equalsIgnoreCase(nombre)) {
                    m=mascota;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al traer la lista " + e.getMessage());
        }
        return m;
    } 
}
