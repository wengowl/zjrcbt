package com.zj.rcbt.spring.service;

import com.zj.rcbt.hibernate.dao.SocialsecurityDao;
import com.zj.rcbt.hibernate.model.SocialsecurityBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("socialsecurityService")
public class SocialsecurityService {
    @Autowired
    private SocialsecurityDao socialsecurityDao;

    public List<SocialsecurityBean> findByPages(String idnum,String name,int startRow,int pageSize) {
        return socialsecurityDao.findByPages(idnum,name,startRow,pageSize);
    }

    public int  findByPagesCount(String idnum,String name){
        return socialsecurityDao.findByPagesCount(idnum,name);
    }
    public SocialsecurityBean findByIDnum(String id_num){
        return socialsecurityDao.findByIDnum(id_num);
    }

    public void saveOrUpdate(SocialsecurityBean socialsecurityBean){
       socialsecurityDao.saveOrUpdate(socialsecurityBean);

    }
}
