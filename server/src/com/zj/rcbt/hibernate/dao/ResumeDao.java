package com.zj.rcbt.hibernate.dao;

import com.zj.rcbt.hibernate.model.ResumeBean;
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
public class ResumeDao extends HibernateDaoSupport {
    private Logger log =LogManager.getLogger(this.getClass());


    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }


    public void save(ResumeBean resume){
        log.info("abcdefg"+resume.getIdNum());
        getHibernateTemplate().save(resume);
        /*getHibernateTemplate().flush();
        getHibernateTemplate().clear();*/
    }

    public void refresh(){
        getHibernateTemplate().flush();
        getHibernateTemplate().clear();
    }

    public void clear()
    {
        getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
    }

    public void delete(ResumeBean resume){
        getHibernateTemplate().delete(resume);
    }

    public void deleteByidunm(String   idnum){
        List<ResumeBean> list = findByidnum(idnum);
        for (ResumeBean o : list)
        {
            log.info("delete");
            delete(o);
            getHibernateTemplate().flush();
            getHibernateTemplate().clear();
        }
    }

    public void update(ResumeBean resume){
        getHibernateTemplate().saveOrUpdate(resume);
    }

    public List<ResumeBean> findByidnum(String idnum){
        return (List<ResumeBean>) getHibernateTemplate().find("from ResumeBean t where t.idNum = ? ", new Object[]
                { idnum });
    }
}
