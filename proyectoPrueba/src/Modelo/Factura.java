/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
 * @author DELL
 */
@Entity
public class Factura implements Serializable {

    private int id_factura;
    private int nro_factura;
    private Date fecha;
    private double iva;
    private Persona persona;
    private List<Detallefactura> detallefactura = new ArrayList<Detallefactura>();

  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getNro_factura() {
        return nro_factura;
    }

    public void setNro_factura(int nro_factura) {
        this.nro_factura = nro_factura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    @ManyToOne
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
   public List<Detallefactura> getDetallefactura() {
        return detallefactura;
    }

    public void setDetallefactura(List<Detallefactura> detallefactura) {
        this.detallefactura = detallefactura;
    }

}
