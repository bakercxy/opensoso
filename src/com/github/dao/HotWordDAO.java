package com.github.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.github.util.ReflectUtils;

public interface HotWordDAO<T>{

	/**
     * 新增
     * <br>------------------------------<br>
     * @param hotWord
     */
    public void insert(T hotWord) ;
     
    /**
     * 批量新增
     * <br>------------------------------<br>
     * @param hotWords
     */
    public void insertAll(List<T> hotWords);
     
    /**
     * 删除,按主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     * @param id
     */
    public void deleteById(String id);
    
    
    /**
     * 按条件删除
     * <br>------------------------------<br>
     * @param criteriaT
     */
    public void delete(double weight);
     
    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    public void deleteAll() ;
    
    /**
     * 按主键修改,
     * 如果文档中没有相关key 会新增 使用$set修改器
     * <br>------------------------------<br>
     * @param hotWord
     */
    public void updateById(String id);
     
    /**
     * 修改多条
     * <br>------------------------------<br>
     * @param criteriaT
     * @param hotWord
     */
    public void update(T criteriaT, T hotWord);
     
    /**
     * 根据主键查询
     * <br>------------------------------<br>
     * @param id
     * @return
     */
    public T findById(String id);
     
    /**
     * 查询全部
     * <br>------------------------------<br>
     * @return
     */
    public List<T> findAll() ;
     
    /**
     * 按条件查询, 分页
     * <br>------------------------------<br>
     * @param criteriaT
     * @param skip
     * @param limit
     * @return
     */
    public List<T> find(double weight, int skip, int limit,Sort sort);
     
    /**
     * 根据条件查询出来后 再去修改
     * <br>------------------------------<br>
     * @param criteriaT  查询条件
     * @param updateT    修改的值对象
     * @return
     */
    public T findAndModify(T criteriaT, T updateT);
     
    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     * @param criteriaT
     * @return
     */
    public T findAndRemove(T criteriaT);
     
    /**
     * count
     * <br>------------------------------<br>
     * @param criteriaT
     * @return
     */
    public long count(double weight);
 
}
