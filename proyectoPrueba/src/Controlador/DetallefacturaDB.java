/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Detallefactura;
import java.util.List;
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

    public void nuevodetallefactura(Detallefactura detallefactura) {
        try {
            st.beginTransaction();
            st.save(detallefactura);
            st.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Eror a guadra factura" + e.getMessage());
        }
    }

    public List<Detallefactura> caragarDetallefactura(int id_factura, List<Detallefactura> list) {
        try {
              list = (List<Detallefactura>) st.createQuery("From Detallefactura").list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al traer detalle factura");
        }
        return list;
    }

    public Detallefactura traerdetallefactura(int idDetalleFactura) {
        Detallefactura det = null;
        try {
            det = (Detallefactura) st.load(Detallefactura.class, idDetalleFactura);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos de Detalle factura " + e.getMessage());
        }
        return det;
    }

}
