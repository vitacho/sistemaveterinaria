/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Factura;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import utilidades.HibernateUtil;

/**
 *
 * @author DELL
 */
public class FacturaDB {
    private Session st;
    
    public FacturaDB() {
        sessionHibernate();
    }
    public void sessionHibernate (){
        st =HibernateUtil.getSessionFactory().openSession();
    }
    public void nuevaFactura(Factura factura){
        try {
            st.beginTransaction();
            st.save(factura);
            st.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Eror a guadra factura"+e.getMessage());
        }
    
    }
    public  void traerFactura(){
    
   
    }
}
