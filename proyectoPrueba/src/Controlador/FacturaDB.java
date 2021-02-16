/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Factura;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
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
   public Factura traerFactura(int idFactura) {
        Factura fac = null;
        try {
            fac = (Factura) st.load(Factura.class, idFactura);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al traer la ultima factura" + e.getMessage());
        }
        return fac;
    }

    public List<Factura> caragarFacturas(List<Factura> lis) {
        try {
            lis = (List<Factura>) st.createQuery("From Factura").list();
        } catch (Exception e) {
        }
        return lis;
    }
    public Factura traenumfact(String nrofact){
        Factura fact = null;
        
        try {
            Query query= st.createQuery("from Factura fact Where fact.nro_factura = ?");
            query.setParameter(0, nrofact); // admin, secretaria, veterinario, cliente
            
            try {
                fact=(Factura)query.uniqueResult();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al traer el rol"+e.getMessage());
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al traer el rol"+e.getMessage());
        }
        
        return fact;
    }
}
