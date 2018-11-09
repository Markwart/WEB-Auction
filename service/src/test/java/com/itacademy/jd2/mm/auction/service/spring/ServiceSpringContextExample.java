package com.itacademy.jd2.mm.auction.service.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itacademy.jd2.mm.auction.service.IAdminCommunicationService;
import com.itacademy.jd2.mm.auction.service.ICategoryService;
import com.itacademy.jd2.mm.auction.service.ICompositionService;
import com.itacademy.jd2.mm.auction.service.IConditionService;
import com.itacademy.jd2.mm.auction.service.ICountryOriginService;
import com.itacademy.jd2.mm.auction.service.IPaymentMethodService;
import com.itacademy.jd2.mm.auction.service.IShippingMethodService;
import com.itacademy.jd2.mm.auction.service.IUserAccountService;
import com.itacademy.jd2.mm.auction.service.logger.LoggerTest;


public class ServiceSpringContextExample {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerTest.class);
	
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("service-context.xml");
        LOGGER.info(context.getBean(IConditionService.class).toString());
        LOGGER.info(context.getBean(IAdminCommunicationService.class).toString());
        LOGGER.info(context.getBean(ICategoryService.class).toString());
        LOGGER.info(context.getBean(ICompositionService.class).toString());
        LOGGER.info(context.getBean(ICountryOriginService.class).toString());
        LOGGER.info(context.getBean(IPaymentMethodService.class).toString());
        LOGGER.info(context.getBean(IShippingMethodService.class).toString());
        LOGGER.info(context.getBean(IUserAccountService.class).toString());
        
        //TODO show multiple candidates with Qualifier


    }
}
