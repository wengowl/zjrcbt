package com.zj.rcbt.hibernate.dao;


import com.zj.rcbt.hibernate.model.ApplytablecompareBean;
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
public class ApplyCompareDao extends HibernateDaoSupport {

    private Logger log =LogManager.getLogger(this.getClass());


    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }


    public void delete(ApplytablecompareBean p) {

        getHibernateTemplate().delete(p);

    }


    public void save(ApplytablecompareBean p) {

        getHibernateTemplate().saveOrUpdate(p);


    }

    public ApplytablecompareBean findByIdnum(String idnum){
        List<ApplytablecompareBean>applytablecompareBeans= (List<ApplytablecompareBean>)getHibernateTemplate().find("from ApplytablecompareBean  t where t.idNum=?",new Object[]{idnum});

        if (applytablecompareBeans.size()>0){
            return applytablecompareBeans.get(0);
        }
        return null;
    }
    public void refresh(){
        getHibernateTemplate().flush();
        getHibernateTemplate().clear();
    }



}
