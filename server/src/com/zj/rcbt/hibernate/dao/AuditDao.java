package com.zj.rcbt.hibernate.dao;


import com.zj.rcbt.hibernate.model.ApplytableBean;
import com.zj.rcbt.hibernate.model.AuditcommentBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AuditDao extends HibernateDaoSupport {
    private Logger log =LogManager.getLogger(this.getClass());


    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }


    public void save(AuditcommentBean p) {

        getHibernateTemplate().save(p);

    }


    public List<AuditcommentBean> findByIdNum(String idnum) {
        return (List<AuditcommentBean>) getHibernateTemplate().find("from AuditcommentBean  t where t.idNum=?",new Object[]{idnum});




    }




}
