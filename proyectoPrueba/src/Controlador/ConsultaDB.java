/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Consulta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import utilidades.HibernateUtil;

/**
 *
 * @author Darley
 */
public class ConsultaDB {

    private Session st;

    public ConsultaDB() {
        sessionHibernate();
    }

    public void sessionHibernate() {
        st = HibernateUtil.getSessionFactory().openSession();
    }

    public void nuevaConsulta(Consulta consulta) {
        try {
            st.beginTransaction();
            st.save(consulta);
            st.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar " + e.getMessage());
        }
    }

    public Consulta traeConsulta(int idConsulta) {

        Consulta con = null;

        try {
            con = (Consulta) st.load(Consulta.class, idConsulta);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos de la consulta " + e.getMessage());
        }
        return con;
    }

    public List<Consulta> cargarConsulta(List<Consulta> lis) {

        try {
            lis = (List<Consulta>) st.createCriteria(Consulta.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos en la tabla " + e.getMessage());
        }

        return lis;
    }
    
    
}
