package com.xcy.community.service;

import com.xcy.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Scope("prototype")
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    public AlphaService() {
        System.out.println("instantiate AlphaService");
    }

    @PostConstruct
    public void init() {
        System.out.println("initialize AlphaService");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy AlphaService");
    }

    public String find() {
        return alphaDao.select();
    }
}
