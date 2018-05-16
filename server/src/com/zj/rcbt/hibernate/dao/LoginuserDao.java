package com.zj.rcbt.hibernate.dao;


import com.zj.rcbt.hibernate.model.LoginuserBean;
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
public class LoginuserDao extends HibernateDaoSupport {

    private Logger log =LogManager.getLogger(this.getClass());

    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }


    public void save(LoginuserBean p) {

        getHibernateTemplate().saveOrUpdate(p);

    }



    public void delete(LoginuserBean p) {

        getHibernateTemplate().delete(p);

    }


    public void update(LoginuserBean p) {

        getHibernateTemplate().saveOrUpdate(p);

    }


    public LoginuserBean findById(String id) {

        return getHibernateTemplate().load(LoginuserBean.class, id);

    }

    public LoginuserBean findByUsername(String username){
        List<LoginuserBean> loginuserBeans= (List<LoginuserBean>)getHibernateTemplate().find("from LoginuserBean t where t.userName=? ",new Object[]{username});
        if (loginuserBeans.size()>0){
            return loginuserBeans.get(0);
        }
        return null;
    }

    public LoginuserBean findByIDnum(String idnum){
        List<LoginuserBean> loginuserBeans= (List<LoginuserBean>)getHibernateTemplate().find("from LoginuserBean t where t.idNum=? ",new Object[]{idnum});
        if (loginuserBeans.size()>0){
            return loginuserBeans.get(0);
        }
        return null;
    }

    public LoginuserBean findByEmail(String email){
        log.info("email:"+email);
        List<LoginuserBean> loginuserBeans= (List<LoginuserBean>)getHibernateTemplate().find("from LoginuserBean t where t.email=? ",new Object[]{email});
         log.info("   "+loginuserBeans.size());
        if (loginuserBeans.size()>0){
            return loginuserBeans.get(0);
        }
        return null;
    }



    public LoginuserBean findByNameAndPasswd(String name,String passwd) {

       List<LoginuserBean> loginuserBeans= (List<LoginuserBean>)getHibernateTemplate().find("from LoginuserBean t where t.userName=? and t.passwd =?",new Object[]{name,passwd});
       log.info(""+name+"   "+passwd);
       log.info(loginuserBeans.size());
       if (loginuserBeans.size()>0){
           return loginuserBeans.get(0);
       }
       return null;

    }

    public LoginuserBean findByUserName(String username){
        List<LoginuserBean> loginuserBeans= (List<LoginuserBean>)getHibernateTemplate().find("from LoginuserBean t where t.userName=? ",new Object[]{username});
        if (loginuserBeans.size()>0){
            return loginuserBeans.get(0);
        }
        return null;
    }


}
