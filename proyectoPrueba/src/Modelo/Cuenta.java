/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Cuenta implements Serializable{
    
    private int id_cuenta;
    private String user_ced;
    private String contra;
    private Persona persona;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getUser_ced() {
        return user_ced;
    }

    public void setUser_ced(String user_ced) {
        this.user_ced = user_ced;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    @OneToOne
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
   
    
    
    
    
}
