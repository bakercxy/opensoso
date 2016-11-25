package com.github.dao.impl;

import java.util.List;







import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.github.dao.AbstractBaseMongoTemplete;
import com.github.dao.HotWordDAO;
import com.github.util.ReflectUtils;

@Component("hotWordDAOImpl")
public class HotWordDAOImpl<T> extends AbstractBaseMongoTemplete implements HotWordDAO<T>{
	
	protected Class<T> classEntity;
	
	public HotWordDAOImpl(){
		classEntity = ReflectUtils.getClassGenricType(getClass());
	}
	
	/**
     * 新增
     * <br>------------------------------<br>
     * @param hotWord
     */
    public void insert(T hotWord) {
        mongoTemplate.insert(hotWord);
    }
     
    /**
     * 批量新增
     * <br>------------------------------<br>
     * @param hotWords
     */
    public void insertAll(List<T> hotWords) {
        mongoTemplate.insertAll(hotWords);
    }
     
    /**
     * 删除,按主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     * @param id
     */
    public void deleteById(String id) {
    	
        Query query = getQuery(id,0,null);
        mongoTemplate.remove(query,classEntity);
        
    }
     
    /**
     * 按条件删除
     * <br>------------------------------<br>
     * @param criteriaT
     */
    public void delete(double weight) {
        Query query = getQuery(null,weight,null);
        mongoTemplate.remove(query,classEntity);
    }
     
    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    public void deleteAll() {
        mongoTemplate.dropCollection(classEntity);
    }
    
    /**
     * 按主键修改,
     * 如果文档中没有相关key 会新增 使用$set修改器
     * <br>------------------------------<br>
     * @param hotWord
     */
    public void updateById(String id) {
//        Criteria criteria = Criteria.where("id").is(id);
//        Query query = new Query(criteria);
//        Update update = Update.update("weight", hotWord.getWeight()).set("word", hotWord.getWord());
//        mongoTemplate.updateFirst(query, update, T.class);
    }
     
    /**
     * 修改多条
     * <br>------------------------------<br>
     * @param criteriaT
     * @param hotWord
     */
    public void update(T criteriaT, T hotWord) {
//        Criteria criteria = Criteria.where("weight").gt(criteriaT.getWeight());
//        Query query = new Query(criteria);
//        Update update = Update.update("weight", hotWord.getWeight()).set("word", hotWord.getWord());
//        mongoTemplate.updateMulti(query, update, T.class);
    }
     
    /**
     * 根据主键查询
     * <br>------------------------------<br>
     * @param id
     * @return
     */
    public T findById(String id) {
        return mongoTemplate.findById(id,classEntity);
    }
     
    /**
     * 查询全部
     * <br>------------------------------<br>
     * @return
     */
    public List<T> findAll() {
        return mongoTemplate.findAll(classEntity);
    }
     
    /**
     * 按条件查询, 分页
     * <br>------------------------------<br>
     * @param criteriaT
     * @param skip
     * @param limit
     * @return
     */
    public List<T> find(double weight, int skip, int limit,Sort sort) {
        Query query = getQuery(null,weight,null);
        query.skip(skip);
        query.limit(limit);
        if(sort != null)
	        query.with(sort);
        if(mongoTemplate == null)
        	System.out.println("mongoTemplate is null");
        return mongoTemplate.find(query, classEntity);
    }
     
    /**
     * 根据条件查询出来后 再去修改
     * <br>------------------------------<br>
     * @param criteriaT  查询条件
     * @param updateT    修改的值对象
     * @return
     */
    public T findAndModify(T criteriaT, T updateT) {
//        Query query = getQuery(criteriaT);
//        Update update = Update.update("weight", updateT.getWeight()).set("word", updateT.getWord());
//        return mongoTemplate.findAndModify(query, update, T.class);
        return null;
    }
     
    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     * @param criteriaT
     * @return
     */
    public T findAndRemove(T criteriaT) {
//        Query query = getQuery(criteriaT);
//        return mongoTemplate.findAndRemove(query, T.class);
        return null;
    }
     
    /**
     * count
     * <br>------------------------------<br>
     * @param criteriaT
     * @return
     */
    public long count(double weight) {
        Query query = getQuery(null,weight,null);
        return mongoTemplate.count(query, classEntity);
    }
 
    /**
     *
     * <br>------------------------------<br>
     * @param criteriaT
     * @return
     */
    private Query getQuery(String id,double weight,String word) {
        Query query = new Query();
        if (id != null) {
            Criteria criteria = Criteria.where("id").is(id);
            query.addCriteria(criteria);
        }
        if (weight != 0) {
            Criteria criteria = Criteria.where("weight").gte(weight);
            query.addCriteria(criteria);
        }
        if (word != null) {
        	Criteria criteria = Criteria.where("word").is(word);
            query.addCriteria(criteria);
        }
        return query;
    }
}
