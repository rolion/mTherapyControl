/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entities.Par;
import Entities.Punto;
import Entities.Resonancia;
import Hibernate.MyHibernateHelper;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

/**
 *
 * @author Lion
 */
public class NPares extends MyHibernateHelper{
   

    public NPares() {
    }

    public Par insertPar(Par par) throws HibernateException{
        if(par!=null ){
            Par p=getPar(par.getPunto(), par.getResonancia());
            if(p==null)
                saveObjet(par);
            else
                par=p;
        }
        return par;
    }
    public Par getPar(Punto punto, Resonancia resonancia) throws HibernateException
    {
        Par p=null;
        if(punto !=null && resonancia !=null){
            initTransaction();
           p= (Par) getSession().createQuery("From Par p LEFT JOIN FETCH p.punto LEFT JOIN FETCH p.resonancia WHERE p.punto.id= :pid AND p.resonancia.id= :rid")
                    .setInteger("pid", punto.getId())
                    .setInteger("rid", resonancia.getId()).uniqueResult();
//            p=(Par) criteria.createCriteria("punto", JoinType.LEFT_OUTER_JOIN)
//                    .createCriteria("resonancia", JoinType.LEFT_OUTER_JOIN)
//                    .add(Restrictions.eq("punto.id",punto.getId()))
//                    .add(Restrictions.eq("resonancia.id", resonancia.getId())).uniqueResult();
           closeTransaction();
        }
        return p;
    }
    
    
}
