/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

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
    
}
