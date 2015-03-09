package Entities;
// Generated 08-mar-2015 15:56:51 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Par generated by hbm2java
 */
public class Par  implements java.io.Serializable {


     private int id;
     private Punto punto;
     private Resonancia resonancia;
     private String patogeno;
     private String tipo;
     private String sintomatologia;
     private Set grupoRastreos = new HashSet(0);

    public Par() {
    }

	
    public Par(int id, Punto punto, Resonancia resonancia) {
        this.id = id;
        this.punto = punto;
        this.resonancia = resonancia;
    }
    public Par(int id, Punto punto, Resonancia resonancia, String patogeno, String tipo, String sintomatologia, Set grupoRastreos) {
       this.id = id;
       this.punto = punto;
       this.resonancia = resonancia;
       this.patogeno = patogeno;
       this.tipo = tipo;
       this.sintomatologia = sintomatologia;
       this.grupoRastreos = grupoRastreos;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Punto getPunto() {
        return this.punto;
    }
    
    public void setPunto(Punto punto) {
        this.punto = punto;
    }
    public Resonancia getResonancia() {
        return this.resonancia;
    }
    
    public void setResonancia(Resonancia resonancia) {
        this.resonancia = resonancia;
    }
    public String getPatogeno() {
        return this.patogeno;
    }
    
    public void setPatogeno(String patogeno) {
        this.patogeno = patogeno;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getSintomatologia() {
        return this.sintomatologia;
    }
    
    public void setSintomatologia(String sintomatologia) {
        this.sintomatologia = sintomatologia;
    }
    public Set getGrupoRastreos() {
        return this.grupoRastreos;
    }
    
    public void setGrupoRastreos(Set grupoRastreos) {
        this.grupoRastreos = grupoRastreos;
    }




}


