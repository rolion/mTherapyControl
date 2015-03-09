/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entities.Grupo;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Lion
 */

public class NGrupo {
    private Session session;

    public NGrupo(Session session) {
        this.session = session;
    }

    public NGrupo() {
        this.session= Hibernate.NewHibernateUtil.getSessionFactory().getCurrentSession();
    }
    public Grupo insertarGrupo(Grupo g){
        Grupo mGrupo=null;
        if(g!=null){
            this.session.beginTransaction();
            this.session.save(g);
            mGrupo=g;
            this.session.getTransaction().commit();
        }
        return mGrupo;
    }
    private boolean existeGrupo(String name){
        boolean existe=false;
        List g= this.session.createQuery
            ("FROM Grupo g WHERE g.descripcion like :name").setString("name", name).list();
        if(g.size()>0)
            existe=true;
        return existe;
    }
    private Grupo getGrupo(int id){
        Grupo g=null;
        g=(Grupo) this.session.createQuery("FROM Grupo g WHERE g.id = :ID").setParameter("ID", id).uniqueResult();
        return g;
    }
    
}
