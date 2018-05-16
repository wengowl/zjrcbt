package com.zj.rcbt.hibernate.dao;



import com.zj.rcbt.hibernate.model.TopmajorBean;
import com.zj.rcbt.hibernate.model.TopschoolBean;
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
public class TopSchoolDao extends HibernateDaoSupport {

    private Logger log =LogManager.getLogger(this.getClass());


    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }


    public TopschoolBean findByName(String name){
        List<TopschoolBean> topschoolBeanList= (List<TopschoolBean>) getHibernateTemplate().find("from TopschoolBean  t where t.schoolname=?",new Object[]{name});

        if (topschoolBeanList.size()>0){
            return topschoolBeanList.get(0);
        }
        return null;
    }


    public TopmajorBean findByNameAndMajor(String name,String major){
        List<TopmajorBean> topschoolBeanList= (List<TopmajorBean>) getHibernateTemplate().find("from TopmajorBean  t where t.school=? and t.major=?",new Object[]{name,major});

        if (topschoolBeanList.size()>0){
            return topschoolBeanList.get(0);
        }
        return null;
    }


}
