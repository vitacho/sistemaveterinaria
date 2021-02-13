/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Detallefactura;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import utilidades.HibernateUtil;

/**
 *
 * @author DELL
 */
public class DetallefacturaDB {

    private Session st;

    public DetallefacturaDB() {
        sessionHibernate();
    }

    public void sessionHibernate() {
        st = HibernateUtil.getSessionFactory().openSession();
    }
    
    public void nuevodetallefactura(Detallefactura detallefactura){
    try {
            st.beginTransaction();
            st.save(detallefactura);
            st.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Eror a guadra factura"+e.getMessage());
        }
    }
}
