/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entities.Grupo;
import Hibernate.MyHibernateHelper;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Lion
 */

public class NGrupo extends MyHibernateHelper{
    private Session session;

    public NGrupo(Session session) {
        this.session = session;
    }

    public NGrupo() {
        this.session= Hibernate.NewHibernateUtil.getSessionFactory().getCurrentSession();
    }
    public Grupo insertarGrupo(Grupo g){
        if(g!=null){
            Grupo mGrupo=getGrupoByName(g.getDescripcion());
            if(mGrupo==null)
                saveObjet(g);
            else
                g=mGrupo;
        }
        return g;
    }

    public Grupo getGrupoByName(String name){
        initTransaction();
        Grupo g= (Grupo) getSession().createQuery("FROM Grupo g WHERE g.descripcion like :name")
                .setString("name", name).uniqueResult();
        closeTransaction();
        return g;
    }
    public List<Grupo> listGrupo() throws HibernateException{
        List<Grupo> listaGrupo=null;
        initTransaction();
        listaGrupo=getSession().createQuery("FROM Grupo").list();
        closeTransaction();
        return listaGrupo;
    }
    private Grupo getGrupo(int id){
        Grupo g=null;
        initTransaction();
        g=(Grupo) getSession().createQuery("FROM Grupo g WHERE g.id = :ID").setParameter("ID", id).uniqueResult();
        closeTransaction();
        return g;
    }
    
}
