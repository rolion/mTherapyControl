/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entities.Par;
import Entities.Punto;
import Hibernate.MyHibernateHelper;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Lion
 */
public class NPunto extends MyHibernateHelper{
    private Session session;
    private final String insert_cGrupo="";
    private final String insert_sGrupo="";
   
    public NPunto() {
      
    }

    public NPunto(Session session) {
        this.session = session;
    }
    public Punto guardarPunto(Punto punto) throws HibernateException{
        if(punto!=null)
        {
            Punto p=getPunto(punto.getDescripcion());
            if(p==null)
              punto=(Punto)saveObjet(punto);
            else
                punto=p;
        }
        return punto;
    }
    public Punto getPunto(int id) throws HibernateException{
        Punto p=null;
        //devuelve null si la consulta no consiguq un match
        this.initTransaction();
        p= (Punto) getSession().createQuery("From Punto p WHERE p.id = "+id).uniqueResult();
        this.closeTransaction();
        return p;
    }
    public Punto getPunto(String name) throws HibernateException{
        Punto p=null;
        //devuelve null si la consulta no consiguq un match
        initTransaction();
        p= (Punto)getSession().createQuery("From Punto p WHERE p.descripcion like :name")
                .setString("name", name)
                .uniqueResult();
        closeTransaction();
        return p;
    }
    public List<Punto> listarPuntos(){
        List<Punto> lPunto=null;
        initTransaction();
        lPunto=getSession().createQuery("FROM Punto p").list();
        closeTransaction();
        return lPunto;
    }
    public int updatePunto(Punto p) throws HibernateException{
        int rslt=-1;
        if(p!=null)
        {
            initTransaction();
            Query q=getSession().createQuery("UPDATE Punto p SET p.descripcion = :desc AND p.localizacion= :local" 
                +" AND p.Grupo = :grupo")
                    .setParameter("desc", p.getDescripcion())
                    .setParameter("local", p.getLocalizacion())
                    .setEntity("grupo", p.getGrupo());
            //devuelve el nro de filas afectadas
            rslt=q.executeUpdate();
            closeTransaction();
        }
        return rslt;
    }
    public int deletePunto(Punto p) throws HibernateException{
        int rslt=-1;
        if(p!=null)
        {
            initTransaction();
            Query q=getSession().createQuery("DELETE Punto p WHERE p.id= :ID")
                    .setParameter("ID", p.getId());
            //devuelve el nro de filas eliminadas;
            rslt=q.executeUpdate();
            closeTransaction();
        }
        return rslt;
    }
    public List<Par> getPares(){
        //TODO
        List<Par> mPares=null;
            
        return mPares;
    }
    
}
