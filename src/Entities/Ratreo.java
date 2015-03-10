package Entities;
// Generated 09-mar-2015 19:39:16 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Ratreo generated by hbm2java
 */
public class Ratreo  implements java.io.Serializable {


     private int id;
     private Paciente paciente;
     private Date fecha;
     private Boolean trasfusionS;
     private Boolean trasplanteO;
     private Boolean quimioterapia;
     private Boolean radioterapia;
     private Boolean marcaPaso;
     private Boolean embarazo;
     private String observaciones;
     private Set grupoRastreos = new HashSet(0);

    public Ratreo() {
    }

	
    public Ratreo(int id) {
        this.id = id;
    }
    public Ratreo(int id, Paciente paciente, Date fecha, Boolean trasfusionS, Boolean trasplanteO, Boolean quimioterapia, Boolean radioterapia, Boolean marcaPaso, Boolean embarazo, String observaciones, Set grupoRastreos) {
       this.id = id;
       this.paciente = paciente;
       this.fecha = fecha;
       this.trasfusionS = trasfusionS;
       this.trasplanteO = trasplanteO;
       this.quimioterapia = quimioterapia;
       this.radioterapia = radioterapia;
       this.marcaPaso = marcaPaso;
       this.embarazo = embarazo;
       this.observaciones = observaciones;
       this.grupoRastreos = grupoRastreos;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Paciente getPaciente() {
        return this.paciente;
    }
    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Boolean getTrasfusionS() {
        return this.trasfusionS;
    }
    
    public void setTrasfusionS(Boolean trasfusionS) {
        this.trasfusionS = trasfusionS;
    }
    public Boolean getTrasplanteO() {
        return this.trasplanteO;
    }
    
    public void setTrasplanteO(Boolean trasplanteO) {
        this.trasplanteO = trasplanteO;
    }
    public Boolean getQuimioterapia() {
        return this.quimioterapia;
    }
    
    public void setQuimioterapia(Boolean quimioterapia) {
        this.quimioterapia = quimioterapia;
    }
    public Boolean getRadioterapia() {
        return this.radioterapia;
    }
    
    public void setRadioterapia(Boolean radioterapia) {
        this.radioterapia = radioterapia;
    }
    public Boolean getMarcaPaso() {
        return this.marcaPaso;
    }
    
    public void setMarcaPaso(Boolean marcaPaso) {
        this.marcaPaso = marcaPaso;
    }
    public Boolean getEmbarazo() {
        return this.embarazo;
    }
    
    public void setEmbarazo(Boolean embarazo) {
        this.embarazo = embarazo;
    }
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public Set getGrupoRastreos() {
        return this.grupoRastreos;
    }
    
    public void setGrupoRastreos(Set grupoRastreos) {
        this.grupoRastreos = grupoRastreos;
    }




}


