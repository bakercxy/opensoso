package com.github.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Sort;

import com.github.model.LangTime;

public interface LangTimeDAO{

	/**
     * 新增
     * <br>------------------------------<br>
     * @param langTime
     */
    void insert(LangTime langTime);
     
    /**
     * 新增
     * <br>------------------------------<br>
     * @param langTimes
     */
    void insertAll(List<LangTime> langTimes);
     
    /**
     * 删除,主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     * @param id
     */
    void deleteById(String id);
     
    /**
     * 按条件删除
     * <br>------------------------------<br>
     * @param criteriaLangTime
     */
    void delete(LangTime criteriaLangTime);
     
    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    void deleteAll();
     
    /**
     * 修改
     * <br>------------------------------<br>
     * @param langTime
     */
    void updateById(LangTime langTime);
     
    /**
     * 更新多条
     * <br>------------------------------<br>
     * @param criteriaLangTime
     * @param langTime
     */
    void update(LangTime criteriaLangTime, LangTime langTime);
     
    /**
     * 根据主键查询
     * <br>------------------------------<br>
     * @param id
     * @return
     */
    LangTime findById(String id);
     
    /**
     * 查询全部
     * <br>------------------------------<br>
     * @return
     */
    List<LangTime> findAll();
     
    /**
     * 按条件查询
     * <br>------------------------------<br>
     * @param criteriaLangTime
     * @param skip
     * @param limit
     * @return
     */
    List<LangTime> find(LangTime criteriaLangTime, int skip, int limit);
    
    
    /**
     * 按条件查询
     * <br>------------------------------<br>
     * @param criteriaLangTime
     * @param skip
     * @param limit
     * @return
     */
    List<LangTime> find(String[] set, int skip, int limit);
     
    /**
     * 根据条件查询出来后 在去修改
     * <br>------------------------------<br>
     * @param criteriaLangTime  查询条件
     * @param updateLangTime    修改的值对象
     * @return
     */
    LangTime findAndModify(LangTime criteriaLangTime, LangTime updateLangTime);
     
    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     * @param criteriaLangTime
     * @return
     */
    LangTime findAndRemove(LangTime criteriaLangTime);
     
    /**
     * count
     * <br>------------------------------<br>
     * @param criteriaLangTime
     * @return
     */
    long count(LangTime criteriaLangTime);	
}
