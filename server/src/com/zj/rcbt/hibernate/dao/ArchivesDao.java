package com.zj.rcbt.hibernate.dao;

import com.zj.rcbt.hibernate.model.ArchivesBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class ArchivesDao extends HibernateDaoSupport {
    private Logger log =LogManager.getLogger(this.getClass());


    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    public void delete(ArchivesBean archives){
        getHibernateTemplate().delete(archives);
    }

    public void save(ArchivesBean archives){

        getHibernateTemplate().save(archives);
        getHibernateTemplate().flush();
        getHibernateTemplate().clear();
    }

    public void update(ArchivesBean archivesBean){
        getHibernateTemplate().saveOrUpdate(archivesBean);
        getHibernateTemplate().flush();
        getHibernateTemplate().clear();
    }

    public ArchivesBean findByIDnum(String id_num){
      List<ArchivesBean> archivesBeanList= (List<ArchivesBean>) getHibernateTemplate().find("from  ArchivesBean t where  idNum= ? ", new Object[]
                { id_num });
        if (archivesBeanList.size()>0){
            return archivesBeanList.get(0);
        }

        return null;

    }


    public void deleteAll(){
        Session session=null;
        try {


            session = getSessionFactory().openSession();
            String sql="delete from ArchivesBean t  where t.id<>-1 and t.status='1'";
            Transaction tx = session.beginTransaction();
            session.createQuery(sql).executeUpdate();
            tx.commit();
        }finally {
            if (session!=null){
                session.close();
            }

        }

        getHibernateTemplate().flush();
        getHibernateTemplate().clear();



        getSessionFactory().getCurrentSession().clear();
    }


    public void updateAll(){
        Session session=null;
        try {


            session = getSessionFactory().openSession();
            String sql="update   ArchivesBean t  set t.status='1' where t.id<>-1";
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
            String sql="update   ArchivesBean t  set t.status='0' where t.id<>-1";
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

    public List<ArchivesBean> findBystatus(String status) {
        return (List<ArchivesBean> )getHibernateTemplate().find("from  ArchivesBean t where  t.status= ? ", new Object[]
                { status });
    }


    public List<ArchivesBean> findByPages(String idnum,String status,int startRow,int pageSize){
        List<ArchivesBean> socialsecurityBeanList=new ArrayList<>();
        Session session=null;
        try {
            session = getSessionFactory().openSession();
            String sql = "from ArchivesBean t where 1=1 and t.status='0'";
            if (idnum != null&& !idnum.equals("") ) {
                sql = sql + "and t.idNum like '%" + idnum + "%'";
            }
            if ( status != null &&!status.equals("")) {
                sql = sql + "and t.inzhuji like '%" + status + "%'";
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



    public int  findByPagesCount(String idnum,String status){
        int count=0;
        Session session=null;
        try {
            session = getSessionFactory().openSession();
            String sql = "select count(*) from ArchivesBean t where 1=1 and t.status='0'";

            if (idnum != null&& !idnum.equals("") ) {
                sql = sql + "and t.idNum like '%" + idnum + "%'";
            }
            if ( status != null &&!status.equals("")) {
                sql = sql + "and t.inzhuji like '%" + status + "%'";
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
