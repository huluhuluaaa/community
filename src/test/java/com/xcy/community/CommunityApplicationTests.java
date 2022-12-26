package com.xcy.community;

import com.xcy.community.controller.AlphaController;
import com.xcy.community.dao.AlphaDao;
import com.xcy.community.dao.AlphaDaoHibernateImpl;
import com.xcy.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext() {
		System.out.println(applicationContext);

		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());

		alphaDao = applicationContext.getBean("alphaHiber", AlphaDao.class);
		System.out.println(alphaDao.select());

		alphaDao = applicationContext.getBean("alphaMybat", AlphaDao.class);
		System.out.println(alphaDao.select());
	}

	@Test
	public void testBeanManagement() {

		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService + " " + Integer.toHexString(alphaService.hashCode()));
		alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService + " " + Integer.toHexString(alphaService.hashCode()));
		AlphaService alphaService1 = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService1 + " " + Integer.toHexString(alphaService1.hashCode()));
		System.out.println(alphaService == alphaService1);
	}

	@Test
	public void testBeanConfig() {
		SimpleDateFormat simpleDateFormat = applicationContext.getBean("date", SimpleDateFormat.class);
		System.out.println(simpleDateFormat.getClass() == SimpleDateFormat.class);
		System.out.println(simpleDateFormat.getClass().equals(SimpleDateFormat.class));
		SimpleDateFormat simpleDateFormat2 = (SimpleDateFormat) applicationContext.getBean("date");
		System.out.println(simpleDateFormat == simpleDateFormat2);
		SimpleDateFormat simpleDateFormat3 = (SimpleDateFormat)simpleDateFormat2;
		System.out.println(simpleDateFormat3.format(new Date()));
	}

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Autowired
	public AlphaDaoHibernateImpl alphaDao;

	@Autowired
	public AlphaService alphaService;

	@Autowired
	public AlphaController alphaController;

	@Test
	public void testDI() {
		System.out.println(simpleDateFormat);
		System.out.println(alphaDao.select());
		System.out.println(alphaService);
		System.out.println(alphaController);
		System.out.println(simpleDateFormat.format(new Date()));
	}
}
