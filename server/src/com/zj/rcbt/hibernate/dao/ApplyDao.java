package com.zj.rcbt.hibernate.dao;

import com.zj.rcbt.hibernate.model.ApplytableBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ApplyDao extends HibernateDaoSupport {
    private Logger log =LogManager.getLogger(this.getClass());


    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }


    public void delete(ApplytableBean p) {

        getHibernateTemplate().delete(p);

    }


    public void update(ApplytableBean p) {

        getHibernateTemplate().saveOrUpdate(p);

    }


    public ApplytableBean findById(String id) {

        return getHibernateTemplate().load(ApplytableBean.class, id);

    }

    public List<ApplytableBean> findBystatus(String status){
        return (List<ApplytableBean>) getHibernateTemplate().find("from ApplytableBean  t where t.applyStatus=? and t.applyType in ('0','1')",new Object[]{status});
    }

    public  ApplytableBean findByIDnum(String idnum){
        List<ApplytableBean> applytableBeans= (List<ApplytableBean>) getHibernateTemplate().find("from ApplytableBean  t where t.idNum=?",new Object[]{idnum});

        if (applytableBeans.size()>0){
            return applytableBeans.get(0);
        }
        return null;
    }

    public List<ApplytableBean> findByPages( String applyType, String status,String batch,String company, String name,int startRow, int pageSize,String idCard,String rcType){
        List<ApplytableBean> applytableBeans=new ArrayList<ApplytableBean>();
        Session session=null;
        try {
            session = getSessionFactory().openSession();
            String sql = "from ApplytableBean t where 1=1 ";

            if (status != null && !status.equals("")) {
                sql = sql + " and t.applyStatus in ("+status+") ";
            }

            if (applyType != null&& !applyType.equals("") ) {
                sql = sql + " and t.applyType  in ("+ applyType + ")";
            }

            if (idCard != null && !idCard.equals("")) {
                sql = sql + " and t.idNum like '%" + idCard + "%'";
            }
            if (batch != null && !batch.equals("")) {
                sql = sql + " and t.batch like '%" + batch + "%'";
            }

            if (company != null && !company.equals("")) {
                sql = sql + " and t.companyName like '%" + company + "%'";
            }

            if (name != null && !name.equals("")) {
                sql = sql + " and t.name like '%" + name + "%'";
            }
            if (rcType != null && !rcType.equals("")) {
                sql = sql + " and t.rcType in (" + rcType + ")";
            }

            sql = sql+" order by t.applyTime desc";



            log.info(sql);

            Query queryObject = session.createQuery(sql);
            queryObject.setFirstResult(startRow);
            queryObject.setMaxResults(pageSize);

            applytableBeans = queryObject.list();

        }catch(Exception e){
            log.info("",e);

        }finally {
            if (session!=null)
                session.close();
        }

        return applytableBeans;



    }



    public int findByPagesCount( String applyType, String status,String batch,String company,String name,String idCard,String rcType){
       int count=0;
        Session session=null;
        try {
            session = getSessionFactory().openSession();
            String sql = "select count(*) from ApplytableBean t where 1=1 ";

            if (status != null && !status.equals("")) {
                sql = sql + "and t.applyStatus in ("+status+")";
            }

            if (applyType != null&& !applyType.equals("") ) {
                sql = sql + "and t.applyType in (" + applyType + ")";
            }

            if (idCard != null && !idCard.equals("")) {
                sql = sql + "and t.idNum like '%" + idCard + "%'";
            }
            if (batch != null && !batch.equals("")) {
                sql = sql + "and t.batch like '%" + batch + "%'";
            }
            if (company != null && !company.equals("")) {
                sql = sql + " and t.companyName like '%" + company + "%'";
            }

            if (name != null && !name.equals("")) {
                sql = sql + " and t.name like '%" + name + "%'";
            }

            if (rcType != null && !rcType.equals("")) {
                sql = sql + " and t.rcType in (" + rcType + ")";
            }



            log.info(sql);

            Query queryObject = session.createQuery(sql);
            count = ((Long) queryObject.uniqueResult()).intValue();

        }catch(Exception e){
            log.info("",e);
            count=0;

        }finally {
            if (session!=null)
                session.close();
        }

        return count;



    }


    public List<ApplytableBean > getIdnumsApply(){
        List<ApplytableBean> idnums = new ArrayList<>();
        Session session=null;
        try {
            session = getSessionFactory().openSession();
//            String sql1 = "from ApplytableBean a where a.applyStatus  not in ('-1','0') and a.idNum not in (select b.idNum from SocialsecurityBean b where b.status='0')";
//           每次导出仍为全量导出
            String sql1 = "from ApplytableBean a where a.applyStatus  not in ('-1','0')  and a.idNum not in  (select  b.idNum from AllowanceBean b ) and a.applyType in ('0','1') ";

            Query queryObject = session.createQuery(sql1);

            idnums = queryObject.list();
        }finally {
            if (session!=null){
                session.close();
            }
        }

        return idnums;
    }
    public List<ApplytableBean > getIdnumsApplynewshebao(){
        List<ApplytableBean> idnums = new ArrayList<>();
        Session session=null;
        try {
            session = getSessionFactory().openSession();
            String sql1 = "from ApplytableBean a where a.applyStatus  not in ('-1','0','3') and a.idNum not in (select b.idNum from SocialsecurityBean b where b.status='0') and a.idNum not in  (select  c.idNum from AllowanceBean c ) and a.applyType in ('0','1')";

            Query queryObject = session.createQuery(sql1);

            idnums = queryObject.list();
        }finally {
            if (session!=null){
                session.close();
            }
        }

        return idnums;
    }



    public List<ApplytableBean > getIdnumsApplynewdangan(){
        List<ApplytableBean> idnums = new ArrayList<>();
        Session session=null;
        try {
            session = getSessionFactory().openSession();
            String sql1 = "from ApplytableBean a where a.applyStatus  not in ('-1','0','3') and a.idNum not in (select b.idNum from ArchivesBean b where b.status='0') and a.idNum not in  (select  c.idNum from AllowanceBean c )  and a.applyType in ('0','1')";

            Query queryObject = session.createQuery(sql1);

            idnums = queryObject.list();
        }finally {
            if (session!=null){
                session.close();
            }
        }

        return idnums;
    }

}
