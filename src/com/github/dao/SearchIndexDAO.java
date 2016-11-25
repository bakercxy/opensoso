package com.github.dao;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.github.model.Index;

public interface SearchIndexDAO{

	/**
     * 新增
     * <br>------------------------------<br>
     * @param index
     */
    void insert(Index index);
     
    /**
     * 新增
     * <br>------------------------------<br>
     * @param indexs
     */
    void insertAll(List<Index> indexs);
     
    /**
     * 删除,主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     * @param id
     */
    void deleteById(String id);
     
    /**
     * 按条件删除
     * <br>------------------------------<br>
     * @param criteriaIndex
     */
    void delete(Index criteriaIndex);
     
    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    void deleteAll();
     
    /**
     * 修改
     * <br>------------------------------<br>
     * @param index
     */
    void updateById(Index index);
     
    /**
     * 更新多条
     * <br>------------------------------<br>
     * @param criteriaIndex
     * @param index
     */
    void update(Index criteriaIndex, Index index);
     
    /**
     * 根据主键查询
     * <br>------------------------------<br>
     * @param id
     * @return
     */
    Index findById(String id);
     
    /**
     * 查询全部
     * <br>------------------------------<br>
     * @return
     */
    List<Index> findAll();
     
    /**
     * 按条件查询
     * <br>------------------------------<br>
     * @param criteriaIndex
     * @param skip
     * @param limit
     * @return
     */
    List<Index> find(String[] criteriaIndex);
     
    /**
     * 根据条件查询出来后 在去修改
     * <br>------------------------------<br>
     * @param criteriaIndex  查询条件
     * @param updateIndex    修改的值对象
     * @return
     */
    Index findAndModify(Index criteriaIndex, Index updateIndex);
     
    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     * @param criteriaIndex
     * @return
     */
    Index findAndRemove(Index criteriaIndex);
     
    /**
     * count
     * <br>------------------------------<br>
     * @param criteriaIndex
     * @return
     */
    long count(Index criteriaIndex);
	
}
