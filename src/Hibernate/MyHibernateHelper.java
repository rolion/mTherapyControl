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
    
    public Object saveObjet(Object object) throws HibernateException{
        this.session=Hibernate.NewHibernateUtil.getSessionFactory().getCurrentSession();
        this.session.beginTransaction();
        this.session.save(object);
        this.session.getTransaction().commit();
        return object;
    }
    public void initTransaction(){
        this.session=Hibernate.NewHibernateUtil.getSessionFactory().getCurrentSession();
        this.session.beginTransaction();
    }
    public void closeTransaction(){
        this.session.getTransaction().commit();
    }
    
}
