package com.zj.rcbt.hibernate.dao;


import com.zj.rcbt.hibernate.model.OperationBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OperationDao extends HibernateDaoSupport {

    private Logger log =LogManager.getLogger(this.getClass());


    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }


    public void save(OperationBean p) {

        getHibernateTemplate().save(p);

    }
}
