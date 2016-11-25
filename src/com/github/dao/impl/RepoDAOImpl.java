package com.github.dao.impl;

import java.security.cert.CollectionCertStoreParameters;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;






import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.github.dao.AbstractBaseMongoTemplete;
import com.github.dao.RepoDAO;
import com.github.model.Repo;

@Component("repoDAOImpl")
public class RepoDAOImpl extends AbstractBaseMongoTemplete implements RepoDAO{

	/**
     * 新增
     * <br>------------------------------<br>
     * @param repo
     */
    public void insert(Repo repo) {
        mongoTemplate.insert(repo);
    }
     
    /**
     * 批量新增
     * <br>------------------------------<br>
     * @param repos
     */
    public void insertAll(List<Repo> repos) {
        mongoTemplate.insertAll(repos);
    }
     
    /**
     * 删除,按主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     * @param id
     */
    public void deleteById(String word) {
//        Repo repo = new Repo(word);
//        mongoTemplate.remove(repo);
    }
     
    /**
     * 按条件删除
     * <br>------------------------------<br>
     * @param criteriaRepo
     */
    public void delete(Repo criteriaRepo) {
//        Criteria criteria = Criteria.where("weight").gt(criteriaRepo.getWeight());
//        Query query = new Query(criteria);
//        mongoTemplate.remove(query, Repo.class);
    }
     
    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    public void deleteAll() {
        mongoTemplate.dropCollection(Repo.class);
    }
    
    /**
     * 按主键修改,
     * 如果文档中没有相关key 会新增 使用$set修改器
     * <br>------------------------------<br>
     * @param repo
     */
    public void updateById(Repo repo) {
//        Criteria criteria = Criteria.where("id").is(repo.getId());
//        Query query = new Query(criteria);
//        Update update = Update.update("weight", repo.getWeight()).set("word", repo.getWord());
//        mongoTemplate.updateFirst(query, update, Repo.class);
    }
     
    /**
     * 修改多条
     * <br>------------------------------<br>
     * @param criteriaRepo
     * @param repo
     */
    public void update(Repo criteriaRepo, Repo repo) {
//        Criteria criteria = Criteria.where("weight").gt(criteriaRepo.getWeight());
//        Query query = new Query(criteria);
//        Update update = Update.update("weight", repo.getWeight()).set("word", repo.getWord());
//        mongoTemplate.updateMulti(query, update, Repo.class);
    }
     
    /**
     * 根据主键查询
     * <br>------------------------------<br>
     * @param id
     * @return
     */
    public Repo findById(String id) {
        return mongoTemplate.findById(id, Repo.class);
    }
     
    /**
     * 查询全部
     * <br>------------------------------<br>
     * @return
     */
    public List<Repo> findAll() {
        return mongoTemplate.findAll(Repo.class);
    }
     
    /**
     * 按条件查询, 分页
     * <br>------------------------------<br>
     * @param criteriaRepo
     * @param skip
     * @param limit
     * @return
     */
    public List<Repo> find(Set<Integer>criteriaRepo) {
        Query query = getQuery(criteriaRepo);
        return mongoTemplate.find(query, Repo.class);
    }
     
    /**
     * 根据条件查询出来后 再去修改
     * <br>------------------------------<br>
     * @param criteriaRepo  查询条件
     * @param updateRepo    修改的值对象
     * @return
     */
    public Repo findAndModify(Repo criteriaRepo, Repo updateRepo) {
//        Query query = getQuery(criteriaRepo);
//        Update update = Update.update("weight", updateRepo.getWeight()).set("word", updateRepo.getWord());
//        return mongoTemplate.findAndModify(query, update, Repo.class);
        return null;
    }
     
    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     * @param criteriaRepo
     * @return
     */
    public Repo findAndRemove(Repo criteriaRepo) {
//        Query query = getQuery(criteriaRepo);
//        return mongoTemplate.findAndRemove(query, Repo.class);
    	return null;
    }
     
    /**
     * count
     * <br>------------------------------<br>
     * @param criteriaRepo
     * @return
     */
    public long count(Repo criteriaRepo) {
//        Query query = getQuery(criteriaRepo);
//        return mongoTemplate.count(query, Repo.class);
    	return 0;
    }
 
    /**
     *
     * <br>------------------------------<br>
     * @param criteriaRepo
     * @return
     */
    private Query getQuery(Set<Integer> criteriaRepo) {
    	
    	Query query = new Query();
        if (criteriaRepo == null) {
        	return query;
        }
        
        Criteria criteria = Criteria.where("index").in(criteriaRepo);
        query.addCriteria(criteria);
       
        return query;
    }
}
