package com.zj.rcbt.spring.service;

import com.zj.rcbt.hibernate.dao.ArchivesDao;
import com.zj.rcbt.hibernate.model.ArchivesBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("archivesService")
public class ArchivesService {
    @Autowired
    ArchivesDao archivesDao;

    public List<ArchivesBean> findByPages(String idnum,String status,String name, int startRow, int pageSize) {
        return archivesDao.findByPages(idnum,status,name,startRow,pageSize);
    }

    public int  findByPagesCount(String idnum,String status,String name){
        return archivesDao.findByPagesCount(idnum,status,name);
    }


    public void saveOrupdate(ArchivesBean archivesBean){
       archivesDao.saveOrupdate(archivesBean);

    }

    public ArchivesBean findByIDnum(String id_num){
        return archivesDao.findByIDnum(id_num);
    }
}
