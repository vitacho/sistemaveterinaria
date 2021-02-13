/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Rol implements Serializable {

    private int id_rol;
    private String nombre_rol;
    private List<Persona> listPerso = new ArrayList<Persona>();

    /**
     * llave primaria
     *
     * @return
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_rol() {
        return id_rol;
    }

    //id
    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    
    //un ROL puede tener muchas personas
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    public List<Persona> getListPerso() {
        return listPerso;
    }

    /**
     * @param listPerso the listPerso to set
     */
    public void setListPerso(List<Persona> listPerso) {
        this.listPerso = listPerso;
    }
}
