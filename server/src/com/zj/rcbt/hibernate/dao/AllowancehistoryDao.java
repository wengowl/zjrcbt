package com.zj.rcbt.hibernate.dao;

import com.zj.rcbt.hibernate.model.AllowancehistoryBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class AllowancehistoryDao extends HibernateDaoSupport {
    private Logger log =LogManager.getLogger(this.getClass());


    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    public void save(AllowancehistoryBean allowancehistoryBean){
        log.debug("save "+allowancehistoryBean.getIdNum());
        getHibernateTemplate().save(allowancehistoryBean);
      //  getHibernateTemplate().flush();
    }
    public void refresh(){
        getHibernateTemplate().flush();
        getHibernateTemplate().clear();
    }
    public void flush(){
        getHibernateTemplate().flush();
    }

    public void update(AllowancehistoryBean allowancehistoryBean){
        log.debug("update "+allowancehistoryBean.getIdNum());
        getHibernateTemplate().saveOrUpdate(allowancehistoryBean);
       // getHibernateTemplate().flush();
    }

    public void delete(AllowancehistoryBean allowancehistoryBean){
        getHibernateTemplate().delete(allowancehistoryBean);
    }

    public List<AllowancehistoryBean> findByPages(String offer_time,String allowancetype,String idnum,String batch,String name,int startRow,int pageSize,String rcType){
        List<AllowancehistoryBean> allowancehistoryBeanList=new ArrayList<AllowancehistoryBean>();
        Session session=null;
     try {
         session = getSessionFactory().openSession();
         String sql = "from AllowancehistoryBean t where 1=1 ";
         if ( offer_time != null&&!offer_time.equals("") ) {
             sql = sql + "and t.offerTime like '%" + offer_time + "%'";
         }

         if (allowancetype != null&& !allowancetype.equals("") ) {
             sql = sql + "and t.allowancetype like '%" + allowancetype + "%'";
         }

         if (idnum != null&&!idnum.equals("")  ) {
             sql = sql + "and t.idNum like '%" + idnum + "%'";
         }
         if (batch != null&&!batch.equals("")  ) {
             sql = sql + "and t.batch like '%" + batch + "%'";
         }

         if (name != null && !name.equals("")) {
             sql = sql + " and t.name like '%" + name + "%'";
         }
         if (rcType!=null && !rcType.equals("")){
             if (rcType.equals("0")){
                 sql = sql + "and t.rcType not in (7,8,9,10,11,12,13)";
             }else if (rcType.equals("1")){
                 sql = sql + "and t.rcType in (7,8,9,10,11,12,13)";
             }
         }

         log.info(sql);

         Query queryObject = session.createQuery(sql);
         queryObject.setFirstResult(startRow);
         queryObject.setMaxResults(pageSize);

         allowancehistoryBeanList = queryObject.list();
     }catch(Exception e){
         log.info("",e);

     }finally {
         if (session!=null)
             session.close();
     }

        return allowancehistoryBeanList;



    }



    public int  findByPagesCount(String offer_time,String allowancetype,String idnum,String batch,String name,String rcType){
        int count=0;
        Session session=null;
        try {
            session = getSessionFactory().openSession();
            String sql = "select count(*) from AllowancehistoryBean t where 1=1  ";
            if (offer_time != null && !offer_time.equals("") ) {
                sql = sql + "and t.offerTime like '%" + offer_time + "%'";
            }

            if (allowancetype != null && !allowancetype.equals("") ) {
                sql = sql + "and t.allowancetype like '%" + allowancetype + "%'";
            }

            if (idnum != null && !idnum.equals("") ) {
                sql = sql + "and t.idNum like '%" + idnum + "%'";
            }
            if (batch != null && !batch.equals("")) {
                sql = sql + "and t.batch like '%" + batch + "%'";
            }

            if (name != null && !name.equals("")) {
                sql = sql + " and t.name like '%" + name + "%'";
            }

            if (rcType!=null && !rcType.equals("")){
                if (rcType.equals("0")){
                    sql = sql + "and t.rcType not in (7,8,9,10,11,12,13)";
                }else if (rcType.equals("1")){
                    sql = sql + "and t.rcType in (7,8,9,10,11,12,13)";
                }
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



    public List<AllowancehistoryBean> findByProperty(String offer_time,String allowancetype,String idnum){
        List<AllowancehistoryBean> allowancehistoryBeanList=new ArrayList<AllowancehistoryBean>();
        Session session=null;
        try {
            session = getSessionFactory().openSession();
            String sql = "from AllowancehistoryBean t where 1=1 ";
            if (offer_time != null && !offer_time.equals("") ){
                sql = sql + "and t.offerTime like '%" + offer_time + "%'";
            }

            if (allowancetype != null && !allowancetype.equals("") ) {
                sql = sql + "and t.allowancetype like '%" + allowancetype + "%'";
            }

            if (idnum != null && !idnum.equals("") ) {
                sql = sql + "and t.idNum like '%" + idnum + "%'";
            }
            log.info(sql);

            Query queryObject = session.createQuery(sql);
            allowancehistoryBeanList = queryObject.list();
        }catch(Exception e){
            log.info("",e);

        }finally {
            if (session!=null)
                session.close();
        }

        return allowancehistoryBeanList;



    }



}
