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
    //traemos el ultmo id de la factura ingresada en la base
    public  Factura traerFactura(){
        Factura fac = null;
        try {
            //select top 1 *from tbl order by id desc;
            //debemos tare el ulto id de factura 
            fac=(Factura)st.createQuery("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al traer la ultima factura"+e.getMessage());
        }
        return fac;
    }
}
