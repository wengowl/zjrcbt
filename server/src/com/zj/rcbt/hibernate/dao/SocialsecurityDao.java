package com.zj.rcbt.hibernate.dao;

import com.zj.rcbt.hibernate.model.SocialsecurityBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.hql.internal.ast.tree.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class SocialsecurityDao extends HibernateDaoSupport {
    private Logger log =LogManager.getLogger(this.getClass());


    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }


    public void refresh(){
        getHibernateTemplate().flush();
        getHibernateTemplate().clear();
    }
    public void save(SocialsecurityBean socialsecurityBean){
        getHibernateTemplate().save(socialsecurityBean);
        getHibernateTemplate().flush();
        getHibernateTemplate().clear();
    }

    public void delete(SocialsecurityBean socialsecurityBean){
        getHibernateTemplate().delete(socialsecurityBean);
    }

    public void update(SocialsecurityBean socialsecurityBean){
        getHibernateTemplate().saveOrUpdate(socialsecurityBean);
        getHibernateTemplate().flush();
        getHibernateTemplate().clear();
    }

    public void saveOrUpdate(SocialsecurityBean socialsecurityBean){
        getHibernateTemplate().saveOrUpdate(socialsecurityBean);

    }

    public SocialsecurityBean findByIDnum(String id_num){
        List<SocialsecurityBean> socialsecurityBeanList=(List<SocialsecurityBean> )getHibernateTemplate().find("from  SocialsecurityBean t where  t.idNum= ? ", new Object[]
                { id_num });

       if (socialsecurityBeanList.size()>0){
           return socialsecurityBeanList.get(0);
       }

       return null;
    }


    public List<SocialsecurityBean> findBystatus(String status){
        return (List<SocialsecurityBean> )getHibernateTemplate().find("from  SocialsecurityBean t where  t.status= ? ", new Object[]
                { status });
    }




    public void updateAll(){
        Session session=null;
        try {


            session = getSessionFactory().openSession();
            String sql="update   SocialsecurityBean t  set t.status='1' where t.id<>-1";
            Transaction tx = session.beginTransaction();
            session.createQuery(sql).executeUpdate();
            tx.commit();
        }catch (Exception e){
            log.error(e.getMessage());
        }

        finally {
            if (session!=null){
                session.close();
            }

        }

        getHibernateTemplate().flush();
        getHibernateTemplate().clear();



        getSessionFactory().getCurrentSession().clear();

    }

    public void deleteAll(){
        Session session=null;
        try {


         session = getSessionFactory().openSession();
        String sql="delete  from SocialsecurityBean t where t.id<>-1 and t.status='1'";
        Transaction tx = session.beginTransaction();
        session.createQuery(sql).executeUpdate();
        tx.commit();
        }catch (Exception e){
            log.error(e.getMessage());
        }

        finally {
            if (session!=null){
                session.close();
            }

        }


        getHibernateTemplate().flush();
        getHibernateTemplate().clear();
        getSessionFactory().getCurrentSession().clear();
    }


    public void updatenewAll(){
        Session session=null;
        try {


            session = getSessionFactory().openSession();
            String sql="update   SocialsecurityBean t  set t.status='0' where t.id<>-1 ";
            Transaction tx = session.beginTransaction();
            session.createQuery(sql).executeUpdate();
            tx.commit();
        }catch (Exception e){
            log.error(e.getMessage());
        }

        finally {
            if (session!=null){
                session.close();
            }

        }

        getHibernateTemplate().flush();
        getHibernateTemplate().clear();



        getSessionFactory().getCurrentSession().clear();

    }


    public List<SocialsecurityBean> findByPages(String idnum,String name,int startRow,int pageSize){
        List<SocialsecurityBean> socialsecurityBeanList=new ArrayList<>();
        Session session=null;
        try {
            session = getSessionFactory().openSession();
//            String sql = "from SocialsecurityBean t where 1=1 and t.status='0'";

            String sql = "from SocialsecurityBean t where 1=1 ";
            if (idnum != null && !idnum.equals("")) {
                sql = sql + "and t.idNum like '%" + idnum + "%'";
            }
            if (name != null && !name.equals("")) {
                sql = sql + " and t.userName like '%" + name + "%'";
            }

            log.info(sql);

            Query queryObject = session.createQuery(sql);
            queryObject.setFirstResult(startRow);
            queryObject.setMaxResults(pageSize);

            socialsecurityBeanList = queryObject.list();
        }catch(Exception e){
            log.info("",e);

        }finally {
            if (session!=null)
                session.close();
        }

        return socialsecurityBeanList;



    }



    public int  findByPagesCount(String idnum,String name){
        int count=0;
        Session session=null;
        try {
            session = getSessionFactory().openSession();
//            String sql = "select count(*) from SocialsecurityBean t where 1=1 and t.status='0'";

            String sql = "select count(*) from SocialsecurityBean t where 1=1 ";

            if (idnum != null && !idnum.equals("")) {
                sql = sql + "and t.idNum like '%" + idnum + "%'";
            }
            if (name != null && !name.equals("")) {
                sql = sql + " and t.userName like '%" + name + "%'";
            }

            log.info(sql);

            Query queryObject = session.createQuery(sql);
            count = ((Long) queryObject.uniqueResult()).intValue();


        }catch(Exception e){
            log.info("",e);

        }finally {
            if (session!=null)
                session.close();
        }

        return count;



    }




}
