package com.qushihan.check_work_system.core.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qushihan.check_work_system.core.api.ClazzStudentService;
import com.qushihan.check_work_system.core.dao.ClazzStudentDao;

@Service
public class ClazzStudentServiceImpl implements ClazzStudentService {

    @Autowired
    private ClazzStudentDao clazzStudentDao;

    @Override
    public Integer deleteClazzStudentByClazzId(Long clazzId) {
        return clazzStudentDao.deleteClazzStudentByClazzId(clazzId);
    }
}
