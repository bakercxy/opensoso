package com.github.dao.impl;

import java.util.List;






import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.github.dao.AbstractBaseMongoTemplete;
import com.github.dao.SearchIndexDAO;
import com.github.model.Index;

@Component("searchIndexDAOImpl")
public class SearchIndexDAOImpl extends AbstractBaseMongoTemplete implements SearchIndexDAO{

	/**
     * 新增
     * <br>------------------------------<br>
     * @param index
     */
    public void insert(Index index) {
        mongoTemplate.insert(index);
    }
     
    /**
     * 批量新增
     * <br>------------------------------<br>
     * @param indexs
     */
    public void insertAll(List<Index> indexs) {
        mongoTemplate.insertAll(indexs);
    }
     
    /**
     * 删除,按主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     * @param id
     */
    public void deleteById(String word) {
        Index index = new Index(word);
        mongoTemplate.remove(index);
    }
     
    /**
     * 按条件删除
     * <br>------------------------------<br>
     * @param criteriaIndex
     */
    public void delete(Index criteriaIndex) {
//        Criteria criteria = Criteria.where("weight").gt(criteriaIndex.getWeight());
//        Query query = new Query(criteria);
//        mongoTemplate.remove(query, Index.class);
    }
     
    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    public void deleteAll() {
        mongoTemplate.dropCollection(Index.class);
    }
    
    /**
     * 按主键修改,
     * 如果文档中没有相关key 会新增 使用$set修改器
     * <br>------------------------------<br>
     * @param index
     */
    public void updateById(Index index) {
//        Criteria criteria = Criteria.where("id").is(index.getId());
//        Query query = new Query(criteria);
//        Update update = Update.update("weight", index.getWeight()).set("word", index.getWord());
//        mongoTemplate.updateFirst(query, update, Index.class);
    }
     
    /**
     * 修改多条
     * <br>------------------------------<br>
     * @param criteriaIndex
     * @param index
     */
    public void update(Index criteriaIndex, Index index) {
//        Criteria criteria = Criteria.where("weight").gt(criteriaIndex.getWeight());
//        Query query = new Query(criteria);
//        Update update = Update.update("weight", index.getWeight()).set("word", index.getWord());
//        mongoTemplate.updateMulti(query, update, Index.class);
    }
     
    /**
     * 根据主键查询
     * <br>------------------------------<br>
     * @param id
     * @return
     */
    public Index findById(String id) {
        return mongoTemplate.findById(id, Index.class);
    }
     
    /**
     * 查询全部
     * <br>------------------------------<br>
     * @return
     */
    public List<Index> findAll() {
        return mongoTemplate.findAll(Index.class);
    }
     
    /**
     * 按条件查询, 分页
     * <br>------------------------------<br>
     * @param criteriaIndex
     * @param skip
     * @param limit
     * @return
     */
    public List<Index> find(String[] criteriaIndex) {
        Query query = getQuery(criteriaIndex);
        return mongoTemplate.find(query, Index.class);
    }
     
    /**
     * 根据条件查询出来后 再去修改
     * <br>------------------------------<br>
     * @param criteriaIndex  查询条件
     * @param updateIndex    修改的值对象
     * @return
     */
    public Index findAndModify(Index criteriaIndex, Index updateIndex) {
//        Query query = getQuery(criteriaIndex);
//        Update update = Update.update("weight", updateIndex.getWeight()).set("word", updateIndex.getWord());
//        return mongoTemplate.findAndModify(query, update, Index.class);
        return null;
    }
     
    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     * @param criteriaIndex
     * @return
     */
    public Index findAndRemove(Index criteriaIndex) {
//        Query query = getQuery(criteriaIndex);
//        return mongoTemplate.findAndRemove(query, Index.class);
    	return null;
    }
     
    /**
     * count
     * <br>------------------------------<br>
     * @param criteriaIndex
     * @return
     */
    public long count(Index criteriaIndex) {
//        Query query = getQuery(criteriaIndex);
//        return mongoTemplate.count(query, Index.class);
    	return 0;
    }
 
    /**
     *
     * <br>------------------------------<br>
     * @param criteriaIndex
     * @return
     */
    private Query getQuery(String[] criteriaIndex) {
    	
    	Query query = new Query();
    	
        if (criteriaIndex == null) {
        	return query;
        }
//        for(int i = 0;i<criteriaIndex.length;i++)
//	        System.out.println(criteriaIndex[i]);
        
        Criteria criteria = Criteria.where("word").in(criteriaIndex);
        query.addCriteria(criteria);
       
        return query;
    }
}
