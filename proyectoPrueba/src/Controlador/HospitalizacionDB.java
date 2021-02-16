/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Hospitalizacion;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import utilidades.HibernateUtil;

/**
 *
 * @author Darley
 */
public class HospitalizacionDB {
    
    private Session st;

    public HospitalizacionDB() {
        sessionHibernate();
    }

    public void sessionHibernate() {
        st = HibernateUtil.getSessionFactory().openSession();
    }
    
    public void nuevaHospitalizacion(Hospitalizacion hospitalizacion) {
        try {
            st.beginTransaction();
            st.save(hospitalizacion);
            st.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar " + e.getMessage());
        }
    }

    public Hospitalizacion traeHospitalizacion(int idHospitalizacion) {

        Hospitalizacion hos = null;

        try {
            hos = (Hospitalizacion) st.load(Hospitalizacion.class, idHospitalizacion);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos de la Hospitalizacion " + e.getMessage());
        }
        return hos;
    }

    public List<Hospitalizacion> cargarHospitalizacion(List<Hospitalizacion> lis) {

        try {
            lis = (List<Hospitalizacion>) st.createQuery("From Hospitalizacion order by id").list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos en la tabla " + e.getMessage());
        }

        return lis;
    }
    
    
}
