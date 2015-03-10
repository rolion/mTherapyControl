/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entities.Punto;
import Entities.Resonancia;
import Hibernate.MyHibernateHelper;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Lion
 */
public class NResonancia extends MyHibernateHelper{
    Session session;

    public NResonancia(Session session) {
        this.session = session;
    }

    public NResonancia() {
        session=Hibernate.NewHibernateUtil.getSessionFactory().getCurrentSession();
    }
    public Resonancia guardarResonancia(Resonancia resonancia) throws HibernateException{
        if(resonancia!=null){
            Resonancia r=getResonancia(resonancia.getDescription());
            if(r==null)
                saveObjet(resonancia);
            else
                resonancia=r;
        }
        return resonancia;
    }

    public Resonancia getResonancia(int id){
        Resonancia r=null;
        initTransaction();
        r=(Resonancia) getSession().createQuery
            ("FROM Resonancia r WHERE r.id = :ID").setParameter("ID", id).uniqueResult();
        closeTransaction();
        return r;
    }
     public Resonancia getResonancia(String name) throws HibernateException{
        Resonancia p=null;
        initTransaction();
        //devuelve null si la consulta no consiguq un match
        p= (Resonancia)getSession().createQuery("From Resonancia p WHERE p.description like :name")
                .setString("name", name)
                .uniqueResult();
        closeTransaction();
        return p;
    }
    public List<Resonancia> listarResonancia(){
        List<Resonancia> listaR=null;
        initTransaction();
            listaR= getSession().createQuery("FROM Resonancia r").list();
        closeTransaction();
        return listaR;
    }
    public int updatePunto(Resonancia r) throws HibernateException{
        int rslt=-1;
        if(r!=null)
        {
            initTransaction();
            Query q=getSession().createQuery("UPDATE Resonancia p SET p.descripcion = :desc AND p.localizacion= :local")
                    .setParameter("desc", r.getDescription())
                    .setParameter("local", r.getLocalizacion());
            //devuelve el nro de filas afectadas
            rslt=q.executeUpdate();
            closeTransaction();
        }
        return rslt;
    }
    public int deletePunto(Resonancia r) throws HibernateException{
        int rslt=-1;
        if(r!=null)
        {
            initTransaction();
            Query q= getSession().createQuery("DELETE Resonancia r WHERE r.id= :ID")
                    .setParameter("ID", r.getId());
            //devuelve el nro de filas eliminadas;
            rslt=q.executeUpdate();
            closeTransaction();
        }
        return rslt;
    }
    
}
