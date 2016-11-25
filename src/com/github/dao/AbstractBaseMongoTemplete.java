package com.github.dao;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.DB;

@Component("baseMongoTemplete")
public class AbstractBaseMongoTemplete implements ApplicationContextAware{

	protected MongoTemplate mongoTemplate;
	
	protected DB db;
	
	public DB getDb() {
		return db;
	}

	public void setDb(DB db) {
		this.db = db;
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		MongoTemplate mongoTemplate = applicationContext.getBean("mongoTemplate", MongoTemplate.class);   
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//		MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
		setMongoTemplate(mongoTemplate);
//		db = mongoTemplate.getDb();
	}

}
