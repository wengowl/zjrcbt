package com.zj.rcbt.hibernate.dao;

import com.zj.rcbt.hibernate.model.AllowanceBean;
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
public class AllowanceDao extends HibernateDaoSupport {
    private Logger log =LogManager.getLogger(this.getClass());


    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }


    public void save(AllowanceBean allowance){
        getHibernateTemplate().save(allowance);
//        getHibernateTemplate().flush();
    }
    public void delete(AllowanceBean allowanceBean){
        getHibernateTemplate().delete(allowanceBean);

    }

    public void flush(){
        getHibernateTemplate().flush();
    }

    public void update(AllowanceBean allowanceBean){
        log.debug("update "+allowanceBean.getIdNum());
        getHibernateTemplate().update(allowanceBean);
//       getHibernateTemplate().flush();
    }

    public List<AllowanceBean> findByIDnum(String idnum){
        return (List<AllowanceBean>) getHibernateTemplate().find("from AllowanceBean t where t.idNum=?",new Object[]{idnum});
    }


    public List<AllowanceBean> findMonthesRemain(){
        return (List<AllowanceBean>) getHibernateTemplate().find("from AllowanceBean t where t.monthes<36 and (t.over <>'1' OR t.over is null )",new Object[]{});
    }

    public List<AllowanceBean> findMonthesOver(){
        return (List<AllowanceBean>) getHibernateTemplate().find("from AllowanceBean t where t.monthes>=36  and t.lastMoney<>0 ",new Object[]{});
    }

    public List<AllowanceBean> findLastMoneyBack(){
        return (List<AllowanceBean>) getHibernateTemplate().find("from AllowanceBean t where t.lastMoney<>0 or t.lastMoney<>''",new Object[]{});
    }


    public List<AllowanceBean> findByPages(String month, String allowancetype, String idnum, String batch,String name,int startRow, int pageSize,String rcType){
        List<AllowanceBean> allowanceBeanList=new ArrayList<AllowanceBean>();
        Session session=null;
        try {
            session = getSessionFactory().openSession();
            StringBuffer sql =new StringBuffer("from AllowanceBean t where 1=1 ");
            if (month != null&&!month.equals("") && month.equals("0")) {
                sql = sql .append( "and (t.monthes>=36 or t.over='1')");
            }

            if ( month != null&&!month.equals("") &&month.equals("1")) {
                sql = sql .append( "and t.monthes<36 and (t.over <>'1' OR t.over is null )");
            }

            if (allowancetype != null && !allowancetype.equals("")) {
                sql = sql .append("and t.allowancetype  in  (" + allowancetype + ")");
            }

            if (idnum != null &&!idnum.equals("") ) {
                sql = sql .append("and t.idNum like '%" + idnum + "%'");
            }
            if (batch != null &&!batch.equals("") ) {
                sql = sql .append( "and t.batch like '%" + batch + "%'");
            }
            if (name != null && !name.equals("")) {
                sql = sql .append(" and t.name like '%" + name + "%'");
            }

            if (rcType!=null && !rcType.equals("")){
                if (rcType.equals("0")){
                    sql = sql .append( "and t.rcType not in (7,8,9,10,11,12,13)");
                }else if (rcType.equals("1")){
                    sql = sql .append( "and t.rcType in (7,8,9,10,11,12,13)");
                }
            }

            log.info(sql);

            Query queryObject = session.createQuery(sql.toString());
            queryObject.setFirstResult(startRow);
            queryObject.setMaxResults(pageSize);

            allowanceBeanList = queryObject.list();
        }catch(Exception e){
            log.info("",e);

        }finally {
            if (session!=null)
                session.close();
        }

        return allowanceBeanList;



    }



    public int findByPagesCount(String month, String allowancetype, String idnum,String batch,String name,String rcType){
       int count=0;
        Session session=null;
        try {
            session = getSessionFactory().openSession();
            StringBuffer sql = new StringBuffer("select count(*) from AllowanceBean t where 1=1 ");
            if (month != null&&!month.equals("") && month.equals("0")) {
                sql = sql .append( "and (t.monthes>=36 or t.over='1')");
            }

            if ( month != null&&!month.equals("") &&month.equals("1")) {
                sql =sql .append("and t.monthes<36 and (t.over <>'1' OR t.over is null )");
            }

            if (allowancetype != null && !allowancetype.equals("")) {
                sql = sql .append( "and t.allowancetype in (" + allowancetype + ")");
            }

            if (idnum != null &&!idnum.equals("") ) {
                sql = sql .append("and t.idNum like '%" + idnum + "%'");
            }

            if (batch != null && !batch.equals("")) {
                sql = sql .append("and t.batch like '%" + batch + "%'");
            }

            if (name != null && !name.equals("")) {
                sql = sql .append(" and t.name like '%" + name + "%'");
            }
            if (rcType!=null && !rcType.equals("")){
                if (rcType.equals("0")){
                    sql = sql .append( "and t.rcType not in (7,8,9,10,11,12,13)");
                }else if (rcType.equals("1")){
                    sql = sql .append( "and t.rcType in (7,8,9,10,11,12,13)");
                }
            }

            log.info(sql);

            Query queryObject = session.createQuery(sql.toString());


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


    public List<AllowanceBean> findByproperty(String month, String allowancetype, String idnum){
        List<AllowanceBean> allowanceBeanList=new ArrayList<AllowanceBean>();
        Session session=null;
        try {
            session = getSessionFactory().openSession();
            StringBuffer sql = new StringBuffer("from AllowanceBean t where 1=1 ");
            if (month != null&&!month.equals("") && month.equals("0")) {
                sql = sql .append("and t.monthes>=36 or t.over='1'");
            }

            if ( month != null&&!month.equals("") &&month.equals("1")) {
                sql = sql .append("and t.monthes<36 and (t.over <>'1' OR t.over is null )");
            }

            if ( allowancetype != null &&!allowancetype.equals("")) {
                sql = sql .append("and t.allowancetype in (" + allowancetype + ")");
            }

            if (idnum != null && !idnum.equals("")) {
                sql = sql .append( "and t.idNum like '%" + idnum + "%'");
            }
            log.info(sql);

            Query queryObject = session.createQuery(sql.toString());


            allowanceBeanList = queryObject.list();
        }catch(Exception e){
            log.info("",e);

        }finally {
            if (session!=null)
                session.close();
        }

        return allowanceBeanList;



    }



    public List<AllowanceBean > getIdnumsAllowance(){
        List<AllowanceBean> idnums = new ArrayList<>();
        Session session=null;
        try {
            session = getSessionFactory().openSession();
//            String sql1 = " from AllowanceBean a where a.idNum  not in (select  b.idNum from ApplytableBean b where b.applyStatus  not in ('-1','0') ) and a.monthes<36  and a.idNum not in (select b.idNum from SocialsecurityBean b where b.status='0')";
//           每次导出仍为全量导出
            String sql1 = " from AllowanceBean a where a.monthes<36  and (a.over <>'1' OR a.over is null ) ";
            Query queryObject = session.createQuery(sql1);

            idnums = queryObject.list();
        }finally {
            if (session!=null){
                session.close();
            }
        }

        return idnums;
    }


    public List<AllowanceBean > getIdnumsAllowancenew(){
        List<AllowanceBean> idnums = new ArrayList<>();
        Session session=null;
        try {
            session = getSessionFactory().openSession();
            String sql1 = " from AllowanceBean a where a.idNum  not in (select  b.idNum from ApplytableBean b where b.applyStatus  not in ('-1','0') )  and (a.over <>'1' OR a.over is null ) and a.monthes<36  and a.idNum not in (select b.idNum from SocialsecurityBean b where b.status='0')";

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
