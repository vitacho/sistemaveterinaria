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

/**
 *
 * @author Personal
 */
@Entity
public class Servicio implements Serializable {
    private int id_serv;
    private String nombre_serv;
    private int cantidad_serv;
    private double precio_serv;
    private String desc_serv;
    private String estado_serv;
    private List<Detallefactura> listPerso = new ArrayList<Detallefactura>();

   
    
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_serv() {
        return id_serv;
    }

    public void setId_serv(int id_serv) {
        this.id_serv = id_serv;
    }

    public String getNombre_serv() {
        return nombre_serv;
    }

    public void setNombre_serv(String nombre_serv) {
        this.nombre_serv = nombre_serv;
    }

    public int getCantidad_serv() {
        return cantidad_serv;
    }

    public void setCantidad_serv(int cantidad_serv) {
        this.cantidad_serv = cantidad_serv;
    }

    public double getPrecio_serv() {
        return precio_serv;
    }

    public void setPrecio_serv(double precio_serv) {
        this.precio_serv = precio_serv;
    }

    public String getDesc_serv() {
        return desc_serv;
    }

    public void setDesc_serv(String desc_serv) {
        this.desc_serv = desc_serv;
    }

    public String getEstado_serv() {
        return estado_serv;
    }

    public void setEstado_serv(String estado_serv) {
        this.estado_serv = estado_serv;
    }
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
     public List<Detallefactura> getListPerso() {
        return listPerso;
    }

    public void setListPerso(List<Detallefactura> listPerso) {
        this.listPerso = listPerso;
    }
    
}
