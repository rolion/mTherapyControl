package Entities;
// Generated 09-mar-2015 19:39:16 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Grupo generated by hbm2java
 */
public class Grupo  implements java.io.Serializable {


     private int id;
     private String descripcion;
     private Set puntos = new HashSet(0);

    public Grupo() {
    }

	
    public Grupo(int id) {
        this.id = id;
    }
    public Grupo(int id, String descripcion, Set puntos) {
       this.id = id;
       this.descripcion = descripcion;
       this.puntos = puntos;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set getPuntos() {
        return this.puntos;
    }
    
    public void setPuntos(Set puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return descripcion;
    }




}


