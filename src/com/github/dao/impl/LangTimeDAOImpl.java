package com.github.dao.impl;

import java.util.Iterator;
import java.util.List;





import java.util.Set;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.github.dao.AbstractBaseMongoTemplete;
import com.github.dao.LangTimeDAO;
import com.github.model.LangTime;

@Component("langTimeDAOImpl")
public class LangTimeDAOImpl extends AbstractBaseMongoTemplete implements LangTimeDAO{

	/**
     * 新增
     * <br>------------------------------<br>
     * @param langTime
     */
    public void insert(LangTime langTime) {
        mongoTemplate.insert(langTime);
    }
     
    /**
     * 批量新增
     * <br>------------------------------<br>
     * @param repos
     */
    public void insertAll(List<LangTime> langTimes) {
        mongoTemplate.insertAll(langTimes);
    }
     
    /**
     * 删除,按主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     * @param id
     */
    public void deleteById(String id) {
        LangTime langTime = new LangTime(id,null);
        mongoTemplate.remove(langTime);
    }
     
    /**
     * 按条件删除
     * <br>------------------------------<br>
     * @param criteriaLangTime
     */
    public void delete(LangTime criteriaLangTime) {
//        Criteria criteria = Criteria.where("age").gt(criteriaLangTime.getAge());
//        Query query = new Query(criteria);
//        mongoTemplate.remove(query, LangTime.class);
    }
     
    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    public void deleteAll() {
        mongoTemplate.dropCollection(LangTime.class);
    }
    
    /**
     * 按主键修改,
     * 如果文档中没有相关key 会新增 使用$set修改器
     * <br>------------------------------<br>
     * @param langTime
     */
    public void updateById(LangTime langTime) {
//        Criteria criteria = Criteria.where("id").is(langTime.getId());
//        Query query = new Query(criteria);
//        Update update = Update.update("age", langTime.getAge()).set("name", langTime.getName());
//        mongoTemplate.updateFirst(query, update, LangTime.class);
    }
     
    /**
     * 修改多条
     * <br>------------------------------<br>
     * @param criteriaLangTime
     * @param langTime
     */
    public void update(LangTime criteriaLangTime, LangTime langTime) {
//        Criteria criteria = Criteria.where("age").gt(criteriaLangTime.getAge());;
//        Query query = new Query(criteria);
//        Update update = Update.update("name", langTime.getName()).set("age", langTime.getAge());
//        mongoTemplate.updateMulti(query, update, LangTime.class);
    }
     
    /**
     * 根据主键查询
     * <br>------------------------------<br>
     * @param id
     * @return
     */
    public LangTime findById(String id) {
        return mongoTemplate.findById(id, LangTime.class);
    }
     
    /**
     * 查询全部
     * <br>------------------------------<br>
     * @return
     */
    public List<LangTime> findAll() {
        return mongoTemplate.findAll(LangTime.class);
    }
     
    /**
     * 按条件查询, 分页
     * <br>------------------------------<br>
     * @param criteriaLangTime
     * @param skip
     * @param limit
     * @return
     */
    public List<LangTime> find(LangTime criteriaLangTime, int skip, int limit) {
        Query query = getQuery(criteriaLangTime);
        if(skip != 0)
        	query.skip(skip);
        if(limit != 0)
        	query.limit(limit);
        return mongoTemplate.find(query, LangTime.class);
    }
    
    public List<LangTime> find(String[] set, int skip, int limit) {
        Query query = getQuery(set);
        if(skip != 0)
        	query.skip(skip);
        if(limit != 0)
        	query.limit(limit);
        return mongoTemplate.find(query, LangTime.class);
    }
    
    /**
     * 根据条件查询出来后 再去修改
     * <br>------------------------------<br>
     * @param criteriaLangTime  查询条件
     * @param updateLangTime    修改的值对象
     * @return
     */
    public LangTime findAndModify(LangTime criteriaLangTime, LangTime updateLangTime) {
//        Query query = getQuery(criteriaLangTime);
//        Update update = Update.update("age", updateLangTime.getAge()).set("name", updateLangTime.getName());
//        return mongoTemplate.findAndModify(query, update, LangTime.class);
    	return null;
    }
    
    
     
    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     * @param criteriaLangTime
     * @return
     */
    public LangTime findAndRemove(LangTime criteriaLangTime) {
        Query query = getQuery(criteriaLangTime);
        return mongoTemplate.findAndRemove(query, LangTime.class);
    }
     
    /**
     * count
     * <br>------------------------------<br>
     * @param criteriaLangTime
     * @return
     */
    public long count(LangTime criteriaLangTime) {
        Query query = getQuery(criteriaLangTime);
        return mongoTemplate.count(query, LangTime.class);
    }
 
    /**
     *
     * <br>------------------------------<br>
     * @param criteriaLangTime
     * @return
     */
    private Query getQuery(LangTime criteriaLangTime) {
        if (criteriaLangTime == null) {
            criteriaLangTime = new LangTime();
        }
        Query query = new Query();
        if (criteriaLangTime.getId() != null) {
            Criteria criteria = Criteria.where("id").is(criteriaLangTime.getId());
            query.addCriteria(criteria);
        }
        if (criteriaLangTime.getLanguage() != null) {
            Criteria criteria = Criteria.where("language").is(criteriaLangTime.getLanguage());
            query.addCriteria(criteria);
        }
        return query;
    }
    
    private Query getQuery(String[] languageSet) {
    	Query query = new Query();
//    	Criteria criteria2 = Criteria.where("language").is("C++");
//    	Criteria criteria = Criteria.where("language").is("Java").in(o);
    	Criteria criteria = Criteria.where("language").in(languageSet);
    	query.addCriteria(criteria);
        return query;
    }

}
