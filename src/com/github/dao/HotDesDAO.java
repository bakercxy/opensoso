package com.github.dao;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.github.model.HotDes;

public interface HotDesDAO{

	/**
     * 新增
     * <br>------------------------------<br>
     * @param hotDes
     */
    void insert(HotDes hotDes);
     
    /**
     * 新增
     * <br>------------------------------<br>
     * @param hotDess
     */
    void insertAll(List<HotDes> hotDess);
     
    /**
     * 删除,主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     * @param id
     */
    void deleteById(String id);
     
    /**
     * 按条件删除
     * <br>------------------------------<br>
     * @param criteriaHotDes
     */
    void delete(HotDes criteriaHotDes);
     
    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    void deleteAll();
     
    /**
     * 修改
     * <br>------------------------------<br>
     * @param hotDes
     */
    void updateById(HotDes hotDes);
     
    /**
     * 更新多条
     * <br>------------------------------<br>
     * @param criteriaHotDes
     * @param hotDes
     */
    void update(HotDes criteriaHotDes, HotDes hotDes);
     
    /**
     * 根据主键查询
     * <br>------------------------------<br>
     * @param id
     * @return
     */
    HotDes findById(String id);
     
    /**
     * 查询全部
     * <br>------------------------------<br>
     * @return
     */
    List<HotDes> findAll();
     
    /**
     * 按条件查询
     * <br>------------------------------<br>
     * @param criteriaHotDes
     * @param skip
     * @param limit
     * @return
     */
    List<HotDes> find(HotDes criteriaHotDes, int skip, int limit,Sort sort);
     
    /**
     * 根据条件查询出来后 在去修改
     * <br>------------------------------<br>
     * @param criteriaHotDes  查询条件
     * @param updateHotDes    修改的值对象
     * @return
     */
    HotDes findAndModify(HotDes criteriaHotDes, HotDes updateHotDes);
     
    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     * @param criteriaHotDes
     * @return
     */
    HotDes findAndRemove(HotDes criteriaHotDes);
     
    /**
     * count
     * <br>------------------------------<br>
     * @param criteriaHotDes
     * @return
     */
    long count(HotDes criteriaHotDes);
	
}
