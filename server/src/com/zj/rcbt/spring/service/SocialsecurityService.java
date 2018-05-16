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

    public List<SocialsecurityBean> findByPages(String idnum,int startRow,int pageSize) {
        return socialsecurityDao.findByPages(idnum,startRow,pageSize);
    }

    public int  findByPagesCount(String idnum){
        return socialsecurityDao.findByPagesCount(idnum);
    }

}
