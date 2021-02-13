/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cuenta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import utilidades.HibernateUtil;


public class CuentaDB {
    private Session st;
   
    public CuentaDB(){
       sessionHibernate();
    }
    
    public void sessionHibernate(){
        st = HibernateUtil.getSessionFactory().openSession();
    }
    
    public void nuevaCuenta(Cuenta cuenta){
        try {
            st.beginTransaction();
            st.save(cuenta);
            st.getTransaction().commit();
            System.out.println("Cuenta Guardada");
            //JOptionPane.showMessageDialog(null, "Cuenta Guardado");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar "+e.getMessage());
        }
    }
    
    public Cuenta traeCuenta(int idCuenta){
        Cuenta cuenta = null;
        
        try {
            cuenta = (Cuenta)st.load(Cuenta.class, idCuenta);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos de la cuenta "+e.getMessage());
        }
        
        return cuenta;
    }
    
}
