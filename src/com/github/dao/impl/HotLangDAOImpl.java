package com.github.dao.impl;

import java.util.List;





import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.github.dao.AbstractBaseMongoTemplete;
import com.github.dao.HotLangDAO;
import com.github.model.HotLang;

@Component("hotLangDAOImpl")
public class HotLangDAOImpl extends AbstractBaseMongoTemplete implements HotLangDAO{

	/**
     * 新增
     * <br>------------------------------<br>
     * @param hotLang
     */
    public void insert(HotLang hotLang) {
        mongoTemplate.insert(hotLang);
    }
     
    /**
     * 批量新增
     * <br>------------------------------<br>
     * @param hotLangs
     */
    public void insertAll(List<HotLang> hotLangs) {
        mongoTemplate.insertAll(hotLangs);
    }
     
    /**
     * 删除,按主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     * @param id
     */
    public void deleteById(String id) {
        HotLang hotLang = new HotLang(id, "" ,0);
        mongoTemplate.remove(hotLang);
    }
     
    /**
     * 按条件删除
     * <br>------------------------------<br>
     * @param criteriaHotLang
     */
    public void delete(HotLang criteriaHotLang) {
        Criteria criteria = Criteria.where("weight").gt(criteriaHotLang.getWeight());
        Query query = new Query(criteria);
        mongoTemplate.remove(query, HotLang.class);
    }
     
    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    public void deleteAll() {
        mongoTemplate.dropCollection(HotLang.class);
    }
    
    /**
     * 按主键修改,
     * 如果文档中没有相关key 会新增 使用$set修改器
     * <br>------------------------------<br>
     * @param hotLang
     */
    public void updateById(HotLang hotLang) {
        Criteria criteria = Criteria.where("id").is(hotLang.getId());
        Query query = new Query(criteria);
        Update update = Update.update("weight", hotLang.getWeight()).set("word", hotLang.getWord());
        mongoTemplate.updateFirst(query, update, HotLang.class);
    }
     
    /**
     * 修改多条
     * <br>------------------------------<br>
     * @param criteriaHotLang
     * @param hotLang
     */
    public void update(HotLang criteriaHotLang, HotLang hotLang) {
        Criteria criteria = Criteria.where("weight").gt(criteriaHotLang.getWeight());
        Query query = new Query(criteria);
        Update update = Update.update("weight", hotLang.getWeight()).set("word", hotLang.getWord());
        mongoTemplate.updateMulti(query, update, HotLang.class);
    }
     
    /**
     * 根据主键查询
     * <br>------------------------------<br>
     * @param id
     * @return
     */
    public HotLang findById(String id) {
        return mongoTemplate.findById(id, HotLang.class);
    }
     
    /**
     * 查询全部
     * <br>------------------------------<br>
     * @return
     */
    public List<HotLang> findAll() {
        return mongoTemplate.findAll(HotLang.class);
    }
     
    /**
     * 按条件查询, 分页
     * <br>------------------------------<br>
     * @param criteriaHotLang
     * @param skip
     * @param limit
     * @return
     */
    public List<HotLang> find(HotLang criteriaHotLang, int skip, int limit,Sort sort) {
        Query query = getQuery(criteriaHotLang);
        query.skip(skip);
        query.limit(limit);
        if(sort != null)
	        query.with(sort);
        return mongoTemplate.find(query, HotLang.class);
    }
     
    /**
     * 根据条件查询出来后 再去修改
     * <br>------------------------------<br>
     * @param criteriaHotLang  查询条件
     * @param updateHotLang    修改的值对象
     * @return
     */
    public HotLang findAndModify(HotLang criteriaHotLang, HotLang updateHotLang) {
        Query query = getQuery(criteriaHotLang);
        Update update = Update.update("weight", updateHotLang.getWeight()).set("word", updateHotLang.getWord());
        return mongoTemplate.findAndModify(query, update, HotLang.class);
    }
     
    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     * @param criteriaHotLang
     * @return
     */
    public HotLang findAndRemove(HotLang criteriaHotLang) {
        Query query = getQuery(criteriaHotLang);
        return mongoTemplate.findAndRemove(query, HotLang.class);
    }
     
    /**
     * count
     * <br>------------------------------<br>
     * @param criteriaHotLang
     * @return
     */
    public long count(HotLang criteriaHotLang) {
        Query query = getQuery(criteriaHotLang);
        return mongoTemplate.count(query, HotLang.class);
    }
 
    /**
     *
     * <br>------------------------------<br>
     * @param criteriaHotLang
     * @return
     */
    private Query getQuery(HotLang criteriaHotLang) {
        if (criteriaHotLang == null) {
            criteriaHotLang = new HotLang();
        }
        Query query = new Query();
        if (criteriaHotLang.getId() != null) {
            Criteria criteria = Criteria.where("id").is(criteriaHotLang.getId());
            query.addCriteria(criteria);
        }
        if (criteriaHotLang.getWeight() > 0) {
            Criteria criteria = Criteria.where("weight").gt(criteriaHotLang.getWeight());
            query.addCriteria(criteria);
        }
//        if (criteriaHotLang.getWeight() != 0) {
//            Criteria criteria = Criteria.where("name").regex("^" + criteriaHotLang.getWeight());
//            query.addCriteria(criteria);
//        }
        return query;
    }
}
