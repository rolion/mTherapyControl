/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Lion
 */
public abstract class MyHibernateHelper {
    
    private  Session session;

    public Session getSession() {
        return session;
    }
    
    public Object saveObjet(Object object){
        this.session=Hibernate.NewHibernateUtil.getSessionFactory().getCurrentSession();
        try{
            this.session.beginTransaction();
            this.session.save(object);
            this.session.getTransaction().commit();
        }catch(HibernateException he)
        {
            object=null;
            System.out.println("Error al guardar Object /n"+he.getMessage());
            System.out.println(he.getLocalizedMessage());
            this.session.getTransaction().rollback();
        }
        return object;
    }
    public Object updateObject(Object object) {
        this.session=Hibernate.NewHibernateUtil.getSessionFactory().getCurrentSession();
        try{
            this.session.beginTransaction();
            this.session.update(object);
            this.session.getTransaction().commit();
        }catch(HibernateException he){
            object=null;
            System.out.println("Error al actualizar Object /n"+he.getMessage());
            System.out.println(he.getLocalizedMessage());
            this.session.getTransaction().rollback();
           
        }
        return object;
    }
    public Object delteObject(Object object){
        this.session=Hibernate.NewHibernateUtil.getSessionFactory().getCurrentSession();
        try{
            this.session.beginTransaction();
            this.session.delete(object);
            this.session.getTransaction().commit();
        }
        catch(HibernateException he)
        {
            object=null;
            System.out.println("Error al Eliminar Object /n"+he.getMessage());
            System.out.println(he.getLocalizedMessage());
            this.session.getTransaction().rollback();
           
        }
        return object;
    }
    public void initTransaction(){
        this.session=Hibernate.NewHibernateUtil.getSessionFactory().getCurrentSession();
        this.session.beginTransaction();
    }
    public void closeTransaction(){
        this.session.getTransaction().commit();
    }
    public void rollBackTransaction(){
        this.session.getTransaction().rollback();
    }
    
}
