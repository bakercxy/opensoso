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
import com.github.dao.UserDAO;
import com.github.model.User;

@Component("userDAOImpl")
public class UserDAOImpl extends AbstractBaseMongoTemplete implements UserDAO{

	/**
     * 新增
     * <br>------------------------------<br>
     * @param user
     */
    public void insert(User user) {
        mongoTemplate.insert(user);
    }
     
    /**
     * 批量新增
     * <br>------------------------------<br>
     * @param users
     */
    public void insertAll(List<User> users) {
        mongoTemplate.insertAll(users);
    }
     
    /**
     * 删除,按主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     * @param id
     */
    public void deleteById(String word) {
//        User user = new User(word);
//        mongoTemplate.remove(user);
    }
     
    /**
     * 按条件删除
     * <br>------------------------------<br>
     * @param criteriaUser
     */
    public void delete(User criteriaUser) {
//        Criteria criteria = Criteria.where("weight").gt(criteriaUser.getWeight());
//        Query query = new Query(criteria);
//        mongoTemplate.remove(query, User.class);
    }
     
    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    public void deleteAll() {
        mongoTemplate.dropCollection(User.class);
    }
    
    /**
     * 按主键修改,
     * 如果文档中没有相关key 会新增 使用$set修改器
     * <br>------------------------------<br>
     * @param user
     */
    public void updateById(User user) {
//        Criteria criteria = Criteria.where("id").is(user.getId());
//        Query query = new Query(criteria);
//        Update update = Update.update("weight", user.getWeight()).set("word", user.getWord());
//        mongoTemplate.updateFirst(query, update, User.class);
    }
     
    /**
     * 修改多条
     * <br>------------------------------<br>
     * @param criteriaUser
     * @param user
     */
    public void update(User criteriaUser, User user) {
//        Criteria criteria = Criteria.where("weight").gt(criteriaUser.getWeight());
//        Query query = new Query(criteria);
//        Update update = Update.update("weight", user.getWeight()).set("word", user.getWord());
//        mongoTemplate.updateMulti(query, update, User.class);
    }
     
    /**
     * 根据主键查询
     * <br>------------------------------<br>
     * @param id
     * @return
     */
    public User findById(String id) {
        return mongoTemplate.findById(id, User.class);
    }
     
    /**
     * 查询全部
     * <br>------------------------------<br>
     * @return
     */
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }
     
    /**
     * 按条件查询, 分页
     * <br>------------------------------<br>
     * @param criteriaUser
     * @param skip
     * @param limit
     * @return
     */
    public List<User> find(Set<Integer> criteriaUser) {
        Query query = getQuery(criteriaUser);
        return mongoTemplate.find(query, User.class);
    }
     
    /**
     * 根据条件查询出来后 再去修改
     * <br>------------------------------<br>
     * @param criteriaUser  查询条件
     * @param updateUser    修改的值对象
     * @return
     */
    public User findAndModify(User criteriaUser, User updateUser) {
//        Query query = getQuery(criteriaUser);
//        Update update = Update.update("weight", updateUser.getWeight()).set("word", updateUser.getWord());
//        return mongoTemplate.findAndModify(query, update, User.class);
        return null;
    }
     
    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     * @param criteriaUser
     * @return
     */
    public User findAndRemove(User criteriaUser) {
//        Query query = getQuery(criteriaUser);
//        return mongoTemplate.findAndRemove(query, User.class);
    	return null;
    }
     
    /**
     * count
     * <br>------------------------------<br>
     * @param criteriaUser
     * @return
     */
    public long count(User criteriaUser) {
//        Query query = getQuery(criteriaUser);
//        return mongoTemplate.count(query, User.class);
    	return 0;
    }
 
    /**
     *
     * <br>------------------------------<br>
     * @param criteriaUser
     * @return
     */
    private Query getQuery(Set<Integer> criteriaUser) {
    	
    	Query query = new Query();
    	
        if (criteriaUser == null) {
        	return query;
        }
        Criteria criteria = Criteria.where("index").in(criteriaUser);
        query.addCriteria(criteria);
       
        return query;
    }
}
