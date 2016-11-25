package com.github.dao.impl;

import java.util.List;





import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.github.dao.AbstractBaseMongoTemplete;
import com.github.dao.HotCommitDAO;
import com.github.model.HotCommit;

@Component("hotCommitDAOImpl")
public class HotCommitDAOImpl extends AbstractBaseMongoTemplete implements HotCommitDAO{

	/**
     * 新增
     * <br>------------------------------<br>
     * @param hotCommit
     */
    public void insert(HotCommit hotCommit) {
        mongoTemplate.insert(hotCommit);
    }
     
    /**
     * 批量新增
     * <br>------------------------------<br>
     * @param hotCommits
     */
    public void insertAll(List<HotCommit> hotCommits) {
        mongoTemplate.insertAll(hotCommits);
    }
     
    /**
     * 删除,按主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     * @param id
     */
    public void deleteById(String id) {
        HotCommit hotCommit = new HotCommit(id, "" ,0);
        mongoTemplate.remove(hotCommit);
    }
     
    /**
     * 按条件删除
     * <br>------------------------------<br>
     * @param criteriaHotCommit
     */
    public void delete(HotCommit criteriaHotCommit) {
        Criteria criteria = Criteria.where("weight").gt(criteriaHotCommit.getWeight());
        Query query = new Query(criteria);
        mongoTemplate.remove(query, HotCommit.class);
    }
     
    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    public void deleteAll() {
        mongoTemplate.dropCollection(HotCommit.class);
    }
    
    /**
     * 按主键修改,
     * 如果文档中没有相关key 会新增 使用$set修改器
     * <br>------------------------------<br>
     * @param hotCommit
     */
    public void updateById(HotCommit hotCommit) {
        Criteria criteria = Criteria.where("id").is(hotCommit.getId());
        Query query = new Query(criteria);
        Update update = Update.update("weight", hotCommit.getWeight()).set("word", hotCommit.getWord());
        mongoTemplate.updateFirst(query, update, HotCommit.class);
    }
     
    /**
     * 修改多条
     * <br>------------------------------<br>
     * @param criteriaHotCommit
     * @param hotCommit
     */
    public void update(HotCommit criteriaHotCommit, HotCommit hotCommit) {
        Criteria criteria = Criteria.where("weight").gt(criteriaHotCommit.getWeight());
        Query query = new Query(criteria);
        Update update = Update.update("weight", hotCommit.getWeight()).set("word", hotCommit.getWord());
        mongoTemplate.updateMulti(query, update, HotCommit.class);
    }
     
    /**
     * 根据主键查询
     * <br>------------------------------<br>
     * @param id
     * @return
     */
    public HotCommit findById(String id) {
        return mongoTemplate.findById(id, HotCommit.class);
    }
     
    /**
     * 查询全部
     * <br>------------------------------<br>
     * @return
     */
    public List<HotCommit> findAll() {
        return mongoTemplate.findAll(HotCommit.class);
    }
     
    /**
     * 按条件查询, 分页
     * <br>------------------------------<br>
     * @param criteriaHotCommit
     * @param skip
     * @param limit
     * @return
     */
    public List<HotCommit> find(HotCommit criteriaHotCommit, int skip, int limit,Sort sort) {
        Query query = getQuery(criteriaHotCommit);
        query.skip(skip);
        query.limit(limit);
        if(sort != null)
	        query.with(sort);
        return mongoTemplate.find(query, HotCommit.class);
    }
     
    /**
     * 根据条件查询出来后 再去修改
     * <br>------------------------------<br>
     * @param criteriaHotCommit  查询条件
     * @param updateHotCommit    修改的值对象
     * @return
     */
    public HotCommit findAndModify(HotCommit criteriaHotCommit, HotCommit updateHotCommit) {
        Query query = getQuery(criteriaHotCommit);
        Update update = Update.update("weight", updateHotCommit.getWeight()).set("word", updateHotCommit.getWord());
        return mongoTemplate.findAndModify(query, update, HotCommit.class);
    }
     
    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     * @param criteriaHotCommit
     * @return
     */
    public HotCommit findAndRemove(HotCommit criteriaHotCommit) {
        Query query = getQuery(criteriaHotCommit);
        return mongoTemplate.findAndRemove(query, HotCommit.class);
    }
     
    /**
     * count
     * <br>------------------------------<br>
     * @param criteriaHotCommit
     * @return
     */
    public long count(HotCommit criteriaHotCommit) {
        Query query = getQuery(criteriaHotCommit);
        return mongoTemplate.count(query, HotCommit.class);
    }
 
    /**
     *
     * <br>------------------------------<br>
     * @param criteriaHotCommit
     * @return
     */
    private Query getQuery(HotCommit criteriaHotCommit) {
        if (criteriaHotCommit == null) {
            criteriaHotCommit = new HotCommit();
        }
        Query query = new Query();
        if (criteriaHotCommit.getId() != null) {
            Criteria criteria = Criteria.where("id").is(criteriaHotCommit.getId());
            query.addCriteria(criteria);
        }
        if (criteriaHotCommit.getWeight() > 0) {
            Criteria criteria = Criteria.where("weight").gt(criteriaHotCommit.getWeight());
            query.addCriteria(criteria);
        }
//        if (criteriaHotCommit.getWeight() != 0) {
//            Criteria criteria = Criteria.where("name").regex("^" + criteriaHotCommit.getWeight());
//            query.addCriteria(criteria);
//        }
        return query;
    }
}
