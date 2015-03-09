/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entities.Par;
import Entities.Punto;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Lion
 */
public class NPunto {
    Session session;

    public NPunto() {
        session= Hibernate.NewHibernateUtil.getSessionFactory().getCurrentSession();
    }

    public NPunto(Session session) {
        this.session = session;
    }
    public Punto guardarPunto(Punto punto) throws HibernateException{
        if(punto!=null)
        {
            session.beginTransaction();
            session.save(punto);
            session.getTransaction().commit();
        }
        return punto;
    }
    private boolean existePunto(String name){
        boolean existe=false;
        Query q =session.createQuery("From Punto  p WHERE p.description like :name")
                .setParameter("name", name);
        List result =q.list();
        if(result.size()>0)
            existe=true; 
        return existe;
    }
    public Punto getPunto(int id) throws HibernateException{
        Punto p=null;
        //devuelve null si la consulta no consiguq un match
        p= (Punto) session.createQuery("From Punto p WHERE p.id = "+id).uniqueResult();
        return p;
    }
    public Punto getPunto(String name) throws HibernateException{
        Punto p=null;
        //devuelve null si la consulta no consiguq un match
        p= (Punto) session.createQuery("From Punto p WHERE p.description = :name")
                .setParameter("name", name)
                .uniqueResult();
        return p;
    }
    public List<Punto> listarPuntos(){
        List<Punto> lPunto=null;
        lPunto=session.createQuery("FROM Punto p").list();
        return lPunto;
    }
    public int updatePunto(Punto p) throws HibernateException{
        int rslt=-1;
        if(p!=null)
        {
            Query q=this.session.createQuery("UPDATE Punto p SET p.descripcion = :desc AND p.localizacion= :local" 
                +" AND p.Grupo = :grupo")
                    .setParameter("desc", p.getDescripcion())
                    .setParameter("local", p.getLocalizacion())
                    .setEntity("grupo", p.getGrupo());
            //devuelve el nro de filas afectadas
            rslt=q.executeUpdate();
        }
        return rslt;
    }
    public int deletePunto(Punto p) throws HibernateException{
        int rslt=-1;
        if(p!=null)
        {
            Query q= this.session.createQuery("DELETE Punto p WHERE p.id= :ID")
                    .setParameter("ID", p.getId());
            //devuelve el nro de filas eliminadas;
            rslt=q.executeUpdate();
        }
        return rslt;
    }
    public List<Par> getPares(){
        //TODO
        List<Par> mPares=null;
            
        return mPares;
    }
    
}
