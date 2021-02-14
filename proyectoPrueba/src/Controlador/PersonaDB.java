/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import utilidades.HibernateUtil;


public class PersonaDB {
    
     private Session st;
   
    public PersonaDB(){
       sessionHibernate();
    }
    
    /**
     * el mapeo de atributos entre una base de datos relacional 
     * tradicional y el modelo de objetos de una aplicación
     */
     public void sessionHibernate(){
        st = HibernateUtil.getSessionFactory().openSession();
    }
    
    
    public Persona traeClientesCedula(String ced){
           Persona per = null;
        
        try {
            Query query= st.createQuery("from Persona p Where p.cedula = ?");
            query.setParameter(0, ced); // admin, secretaria, veterinario, cliente
            
            try {
                per=(Persona)query.uniqueResult();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error de la cedula ya existe en el sistema"+e.getMessage());
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en el controlador"+e.getMessage());
        }
        
        return per;
    }
       
    public void nuevoCliente(Persona cliente){
        
        try {
            st.beginTransaction();
            st.save(cliente);
            st.getTransaction().commit();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar "+e.getMessage());
        }
    }
    
    //metodo para cargar los datos en la tabla
    
    public List<Persona> cargarClientes(String estado, List<Persona>lis){
        
        try {
            lis = (List<Persona>)st.createQuery("from Persona where estado='"+estado+"'order by nombre").list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos en la tabla "+e.getMessage());
        }
        
        return lis;
    }
    
    //traer a los clientes por el ID
    public Persona traeClientesId(int idPersona){
        
        Persona per = null;
        
        try {
            per = (Persona)st.load(Persona.class, idPersona);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos del cliente "+e.getMessage());
        }
        
        return per;
    }
    
    
    //modificar los datos 
     public void actualizarCliente(Persona cliente){
        
        try {
            st.beginTransaction();
            st.update(cliente);
            st.getTransaction().commit();
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Modificar el cliente "+e.getMessage());
        }
    }
    
    //buscar persona por la cedula
     public List<Persona> buscarPersonaCed(String ced){
         List<Persona>lis = new ArrayList<>();
         try {
             lis = (List<Persona>)st.createQuery("From Persona where cedula = '"+ced+"'").list();
              
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error al BUSCAR AL CLIENTE "+e.getMessage());
         }
         
         return lis;
     }
     
     public List<Persona> listarPersonas() {
        List<Persona> list = new ArrayList<>();
        try {
            list = (List<Persona>) st.createQuery("From Persona order by id_persona").list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al traer la lista " + e.getMessage());
        }
        return list;
    }
     
    public Persona traerPorCedula(String ced) {
        Persona p = new Persona();
        List<Persona> list = new ArrayList<>();
        try {
            list = (List<Persona>) st.createQuery("From Persona where cedula = '"+ced+"'").list();
            for (Persona persona : list) {
                if(persona.getCedula().equals(ced)){
                    p=persona;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al traer persona con cedula " + e.getMessage());
        }
        return p;
    }
    
}
