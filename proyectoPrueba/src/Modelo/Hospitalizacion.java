/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Darley
 */
@Entity
public class Hospitalizacion implements Serializable{

    private int id;
    private String diagnostico;
    private String motivo;
    private int temp;
    private String sangrado;
    private String conbulcion;
    private String intoxicacion;
    private String probleResp;
    private String hinchazon;
    private Date ingreso;
    private Date salida;
    private String vereterinario;
    private String estado;
    private Mascota mascota;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getSangrado() {
        return sangrado;
    }

    public void setSangrado(String sangrado) {
        this.sangrado = sangrado;
    }

    public String getConbulcion() {
        return conbulcion;
    }

    public void setConbulcion(String conbulcion) {
        this.conbulcion = conbulcion;
    }

    public String getIntoxicacion() {
        return intoxicacion;
    }

    public void setIntoxicacion(String intoxicacion) {
        this.intoxicacion = intoxicacion;
    }

    public String getProbleResp() {
        return probleResp;
    }

    public void setProbleResp(String probleResp) {
        this.probleResp = probleResp;
    }

    public String getHinchazon() {
        return hinchazon;
    }

    public void setHinchazon(String hinchazon) {
        this.hinchazon = hinchazon;
    }

    public Date getIngreso() {
        return ingreso;
    }

    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    public String getVereterinario() {
        return vereterinario;
    }

    public void setVereterinario(String vereterinario) {
        this.vereterinario = vereterinario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @ManyToOne
    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    } 
}
