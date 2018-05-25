package com.zj.rcbt.spring.service;


import com.zj.rcbt.hibernate.dao.ArchivesDao;
import com.zj.rcbt.hibernate.dao.SocialsecurityDao;
import com.zj.rcbt.hibernate.model.ArchivesBean;
import com.zj.rcbt.hibernate.model.SocialsecurityBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("importService")
public class ImportService {


    private Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private SocialsecurityDao socialsecurityDao;

    @Autowired
    private ArchivesDao archivesDao;

    public void saveSocialsecurity(List<SocialsecurityBean> socialsecurityBeans){
        socialsecurityDao.deleteAll();
        for (SocialsecurityBean bean:socialsecurityBeans){
            SocialsecurityBean oldbean = socialsecurityDao.findByIDnum(bean.getIdNum());
            if (oldbean!=null){
               oldbean.setMonthes(bean.getMonthes());
               oldbean.setLastTime(bean.getLastTime());
               oldbean.setBeginTime(bean.getBeginTime());
               oldbean.setUserName(bean.getUserName());
               oldbean.setCompany(bean.getCompany());
               bean=oldbean;
            }
            socialsecurityDao.update(bean);
        }
//    TODO    社保改为增量更新
    }

    public void saveArchives(List<ArchivesBean> archivesBeans){
       archivesDao.deleteAll();
        for (ArchivesBean bean:archivesBeans){
            ArchivesBean oldbean = archivesDao.findByIDnum(bean.getIdNum());
            if (oldbean!=null){
               oldbean.setInzhuji(bean.getInzhuji());
               oldbean.setUserName(bean.getUserName());
               bean=oldbean;
            }
            archivesDao.update(bean);
        }
////        archivesDao.updatenewAll();
//        TODO 档案改为增量更新
    }




}
