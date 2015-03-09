/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entities.Par;
import Entities.Punto;
import Entities.Resonancia;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

/**
 *
 * @author Lion
 */
public class NPares {
    private Session session;

    public NPares() {
        this.session=Hibernate.NewHibernateUtil.getSessionFactory().getCurrentSession();
    }

    public NPares(Session session) {
        this.session = session;
    }
    public Par insertPar(Par par){
        if(par!=null && !existePar(par.getPunto(), par.getResonancia())){
            
            this.session.save(par);
        }
        return par;
    }
    public boolean existePar(Punto punto, Resonancia resonancia){
        boolean exist=false;
        Criteria criteria=session.createCriteria(Par.class);
        //From Par p LEFT JOIN p.punto LEFT JOIN p.resonancia WHERE p.punto.id=1 AND p.resonancia.id=1
        Par p=(Par) criteria.createCriteria("punto", JoinType.LEFT_OUTER_JOIN)
                .createCriteria("resonancia", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.eq("punto.id",punto.getId()))
                .add(Restrictions.eq("resonancia.id", resonancia.getId())).uniqueResult();
        if(p!=null)
            exist=true;
        return exist;
    }
    
    
}
