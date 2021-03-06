package Entities;
// Generated 09-mar-2015 19:39:16 by Hibernate Tools 4.3.1



/**
 * GrupoRastreo generated by hbm2java
 */
public class GrupoRastreo  implements java.io.Serializable {


     private int id;
     private Par par;
     private Ratreo ratreo;
     private Boolean marcado;

    public GrupoRastreo() {
    }

	
    public GrupoRastreo(int id) {
        this.id = id;
    }
    public GrupoRastreo(int id, Par par, Ratreo ratreo, Boolean marcado) {
       this.id = id;
       this.par = par;
       this.ratreo = ratreo;
       this.marcado = marcado;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Par getPar() {
        return this.par;
    }
    
    public void setPar(Par par) {
        this.par = par;
    }
    public Ratreo getRatreo() {
        return this.ratreo;
    }
    
    public void setRatreo(Ratreo ratreo) {
        this.ratreo = ratreo;
    }
    public Boolean getMarcado() {
        return this.marcado;
    }
    
    public void setMarcado(Boolean marcado) {
        this.marcado = marcado;
    }




}


