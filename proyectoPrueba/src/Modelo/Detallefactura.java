/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author DELL
 */
@Entity
public class Detallefactura implements Serializable{

    private int id_detallefactura;
    private int cantidad;
//    private String detalle;
    private double precio_unitario;
    private double precio_total;
    private Factura factura;
    private Servicio servicio;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_detallefactura() {
        return id_detallefactura;
    }

    public void setId_detallefactura(int id_detallefactura) {
        this.id_detallefactura = id_detallefactura;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

//    public String getDetalle() {
//        return detalle;
//    }
//
//    public void setDetalle(String detalle) {
//        this.detalle = detalle;
//    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }

    @ManyToOne
    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @ManyToOne
    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

}
