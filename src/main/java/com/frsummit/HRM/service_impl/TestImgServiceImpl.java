package com.frsummit.HRM.service_impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frsummit.HRM.model.TestImage;
import com.frsummit.HRM.service.TestImgService;

@Service("testImgService")
@Transactional
public class TestImgServiceImpl implements TestImgService {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<TestImage> findImg() {
        return entityManager.createQuery("SELECT i FROM TestImage AS i WHERE i.id= 3", TestImage.class).getResultList();
    }
}
