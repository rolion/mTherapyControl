/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entities.Grupo;
import Hibernate.MyHibernateHelper;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
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
    public Grupo insertarGrupo(Grupo g) throws Exception{
        if(g!=null){
            Grupo mGrupo=getGrupoByName(g.getDescripcion());
            if(mGrupo==null)
            {
                if(saveObjet(g)==null)
                {
                    throw new Exception("Error al guardar grupo ");
                }
            }
        }
        return g;
    }
    public Grupo updateGrupo(Grupo g) throws Exception{
        if(g!=null){
            if(updateObject(g)==null)
            {
                throw new Exception("Error al actualizar grupo");
            }
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
    public List<Grupo> listGrupo() throws Exception{
        List<Grupo> listaGrupo=null;
        try
        {
            initTransaction();
            listaGrupo=getSession().createQuery("FROM Grupo").list();
            
        }catch(HibernateException he)
        {
            System.out.println("Error al obtener la lista de grupos \n"+he.getMessage());
            System.out.println(he.getLocalizedMessage());
            throw new Exception("Error al obtener la lista de grupos");
        }finally
        {
            closeTransaction();
        }
        
        return listaGrupo;
    }
    private Grupo getGrupo(int id){
        Grupo g=null;
        initTransaction();
        g=(Grupo) getSession().createQuery("FROM Grupo g WHERE g.id = :ID").setParameter("ID", id).uniqueResult();
        closeTransaction();
        return g;
    }
    public DefaultComboBoxModel getModel() throws Exception{
        DefaultComboBoxModel model=null;
        List<Grupo> listaGrupo=listGrupo();
        Grupo g= new Grupo();
        g.setDescripcion("-");
        listaGrupo.add(0, g);
        model=new DefaultComboBoxModel(listaGrupo.toArray());
        return model;
    }
}
