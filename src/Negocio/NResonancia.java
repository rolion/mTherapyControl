/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entities.Punto;
import Entities.Resonancia;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Lion
 */
public class NResonancia {
    Session session;

    public NResonancia(Session session) {
        this.session = session;
    }

    public NResonancia() {
        session=Hibernate.NewHibernateUtil.getSessionFactory().getCurrentSession();
    }
    public Resonancia guardarResonancia(Resonancia resonancia) throws HibernateException{
        if(resonancia!=null){
            session.beginTransaction();
            if(!existeResonancia(resonancia.getDescription()))
                session.save(resonancia);
            session.getTransaction().commit();
        }
        return resonancia;
    }
    private boolean existeResonancia(String name){
        boolean existe=false;
        Query q =session.createQuery("From Resonancia  r WHERE r.description like '"+name+"'");
        List result =q.list();
        if(result.size()>0)
            existe=true;
        return existe;
    }
    public Resonancia getResonancia(int id){
        Resonancia r=null;
        r=(Resonancia) this.session.createQuery
            ("FROM Resonancia r WHERE r.id = :ID").setParameter("ID", id).uniqueResult();
        return r;
    }
     public Resonancia getPunto(String name) throws HibernateException{
        Resonancia p=null;
        //devuelve null si la consulta no consiguq un match
        p= (Resonancia) session.createQuery("From Resonancia p WHERE p.description = :name")
                .setParameter("name", name)
                .uniqueResult();
        return p;
    }
    public List<Resonancia> listarResonancia(){
        List<Resonancia> listaR=null;
            listaR= this.session.createQuery("FROM Resonancia r").list();
        return listaR;
    }
    public int updatePunto(Resonancia r) throws HibernateException{
        int rslt=-1;
        if(r!=null)
        {
            Query q=this.session.createQuery("UPDATE Resonancia p SET p.descripcion = :desc AND p.localizacion= :local")
                    .setParameter("desc", r.getDescription())
                    .setParameter("local", r.getLocalizacion());
            //devuelve el nro de filas afectadas
            rslt=q.executeUpdate();
        }
        return rslt;
    }
    public int deletePunto(Resonancia r) throws HibernateException{
        int rslt=-1;
        if(r!=null)
        {
            Query q= this.session.createQuery("DELETE Resonancia r WHERE r.id= :ID")
                    .setParameter("ID", r.getId());
            //devuelve el nro de filas eliminadas;
            rslt=q.executeUpdate();
        }
        return rslt;
    }
    
}
