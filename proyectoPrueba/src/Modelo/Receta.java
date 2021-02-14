/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Personal
 */
@Entity
    public class Receta {
    private int id_receta;
    private Calendar fecha_receta;
    private String medicam_receta;
    private String indicac_receta;
    private int num_receta;
    private List<Consulta> ListaConsultas = new ArrayList<Consulta>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    public int getId_receta() {
        return id_receta;
    }

    public void setId_receta(int id_receta) {
        this.id_receta = id_receta;
    }

    public Calendar getFecha_receta() {
        return fecha_receta;
    }

    public void setFecha_receta(Calendar fecha_receta) {
        this.fecha_receta = fecha_receta;
    }

    public String getMedicam_receta() {
        return medicam_receta;
    }

    public void setMedicam_receta(String medicam_receta) {
        this.medicam_receta = medicam_receta;
    }

    public String getIndicac_receta() {
        return indicac_receta;
    }

    public void setIndicac_receta(String indicac_receta) {
        this.indicac_receta = indicac_receta;
    }

    public int getNum_receta() {
        return num_receta;
    }

    public void setNum_receta(int num_receta) {
        this.num_receta = num_receta;
    }
  @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
    public List<Consulta> getListaConsultas() {
        return ListaConsultas;
    }

    public void setListaConsultas(List<Consulta> ListaConsultas) {
        this.ListaConsultas = ListaConsultas;
    }

}
