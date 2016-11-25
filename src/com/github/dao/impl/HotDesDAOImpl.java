package com.github.dao.impl;

import java.util.List;





import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.github.dao.AbstractBaseMongoTemplete;
import com.github.dao.HotDesDAO;
import com.github.model.HotDes;

@Component("hotDesDAOImpl")
public class HotDesDAOImpl extends AbstractBaseMongoTemplete implements HotDesDAO{

	/**
     * 新增
     * <br>------------------------------<br>
     * @param hotDes
     */
    public void insert(HotDes hotDes) {
        mongoTemplate.insert(hotDes);
    }
     
    /**
     * 批量新增
     * <br>------------------------------<br>
     * @param hotDess
     */
    public void insertAll(List<HotDes> hotDess) {
        mongoTemplate.insertAll(hotDess);
    }
     
    /**
     * 删除,按主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     * @param id
     */
    public void deleteById(String id) {
        HotDes hotDes = new HotDes(id, "" ,0);
        mongoTemplate.remove(hotDes);
    }
     
    /**
     * 按条件删除
     * <br>------------------------------<br>
     * @param criteriaHotDes
     */
    public void delete(HotDes criteriaHotDes) {
        Criteria criteria = Criteria.where("weight").gt(criteriaHotDes.getWeight());
        Query query = new Query(criteria);
        mongoTemplate.remove(query, HotDes.class);
    }
     
    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    public void deleteAll() {
        mongoTemplate.dropCollection(HotDes.class);
    }
    
    /**
     * 按主键修改,
     * 如果文档中没有相关key 会新增 使用$set修改器
     * <br>------------------------------<br>
     * @param hotDes
     */
    public void updateById(HotDes hotDes) {
        Criteria criteria = Criteria.where("id").is(hotDes.getId());
        Query query = new Query(criteria);
        Update update = Update.update("weight", hotDes.getWeight()).set("word", hotDes.getWord());
        mongoTemplate.updateFirst(query, update, HotDes.class);
    }
     
    /**
     * 修改多条
     * <br>------------------------------<br>
     * @param criteriaHotDes
     * @param hotDes
     */
    public void update(HotDes criteriaHotDes, HotDes hotDes) {
        Criteria criteria = Criteria.where("weight").gt(criteriaHotDes.getWeight());
        Query query = new Query(criteria);
        Update update = Update.update("weight", hotDes.getWeight()).set("word", hotDes.getWord());
        mongoTemplate.updateMulti(query, update, HotDes.class);
    }
     
    /**
     * 根据主键查询
     * <br>------------------------------<br>
     * @param id
     * @return
     */
    public HotDes findById(String id) {
        return mongoTemplate.findById(id, HotDes.class);
    }
     
    /**
     * 查询全部
     * <br>------------------------------<br>
     * @return
     */
    public List<HotDes> findAll() {
        return mongoTemplate.findAll(HotDes.class);
    }
     
    /**
     * 按条件查询, 分页
     * <br>------------------------------<br>
     * @param criteriaHotDes
     * @param skip
     * @param limit
     * @return
     */
    public List<HotDes> find(HotDes criteriaHotDes, int skip, int limit,Sort sort) {
        Query query = getQuery(criteriaHotDes);
        query.skip(skip);
        query.limit(limit);
        if(sort != null)
	        query.with(sort);
        return mongoTemplate.find(query, HotDes.class);
    }
     
    /**
     * 根据条件查询出来后 再去修改
     * <br>------------------------------<br>
     * @param criteriaHotDes  查询条件
     * @param updateHotDes    修改的值对象
     * @return
     */
    public HotDes findAndModify(HotDes criteriaHotDes, HotDes updateHotDes) {
        Query query = getQuery(criteriaHotDes);
        Update update = Update.update("weight", updateHotDes.getWeight()).set("word", updateHotDes.getWord());
        return mongoTemplate.findAndModify(query, update, HotDes.class);
    }
     
    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     * @param criteriaHotDes
     * @return
     */
    public HotDes findAndRemove(HotDes criteriaHotDes) {
        Query query = getQuery(criteriaHotDes);
        return mongoTemplate.findAndRemove(query, HotDes.class);
    }
     
    /**
     * count
     * <br>------------------------------<br>
     * @param criteriaHotDes
     * @return
     */
    public long count(HotDes criteriaHotDes) {
        Query query = getQuery(criteriaHotDes);
        return mongoTemplate.count(query, HotDes.class);
    }
 
    /**
     *
     * <br>------------------------------<br>
     * @param criteriaHotDes
     * @return
     */
    private Query getQuery(HotDes criteriaHotDes) {
        if (criteriaHotDes == null) {
            criteriaHotDes = new HotDes();
        }
        Query query = new Query();
        if (criteriaHotDes.getId() != null) {
            Criteria criteria = Criteria.where("id").is(criteriaHotDes.getId());
            query.addCriteria(criteria);
        }
        if (criteriaHotDes.getWeight() > 0) {
            Criteria criteria = Criteria.where("weight").gt(criteriaHotDes.getWeight());
            query.addCriteria(criteria);
        }
//        if (criteriaHotDes.getWeight() != 0) {
//            Criteria criteria = Criteria.where("name").regex("^" + criteriaHotDes.getWeight());
//            query.addCriteria(criteria);
//        }
        return query;
    }
}
