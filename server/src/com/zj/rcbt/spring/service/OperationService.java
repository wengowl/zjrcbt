package com.zj.rcbt.spring.service;

import com.zj.rcbt.hibernate.dao.OperationDao;
import com.zj.rcbt.hibernate.model.OperationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("operationService")
public class OperationService {

    @Autowired
    private OperationDao operationDao;


    public void save(OperationBean p) {

        operationDao.save(p);

    }
}
