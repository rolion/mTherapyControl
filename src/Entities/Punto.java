package Entities;
// Generated 09-mar-2015 19:39:16 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Punto generated by hbm2java
 */
public class Punto  implements java.io.Serializable {


     private int id;
     private Grupo grupo;
     private String descripcion;
     private String localizacion;
     private Set pars = new HashSet(0);

    public Punto() {
    }

	
    public Punto(int id) {
        this.id = id;
    }
    public Punto(int id, Grupo grupo, String descripcion, String localizacion, Set pars) {
       this.id = id;
       this.grupo = grupo;
       this.descripcion = descripcion;
       this.localizacion = localizacion;
       this.pars = pars;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Grupo getGrupo() {
        return this.grupo;
    }
    
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getLocalizacion() {
        return this.localizacion;
    }
    
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }
    public Set getPars() {
        return this.pars;
    }
    
    public void setPars(Set pars) {
        this.pars = pars;
    }




}


