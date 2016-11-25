package com.github.dao;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.github.model.HotCommit;

public interface HotCommitDAO{

	/**
     * 新增
     * <br>------------------------------<br>
     * @param hotCommit
     */
    void insert(HotCommit hotCommit);
     
    /**
     * 新增
     * <br>------------------------------<br>
     * @param hotCommits
     */
    void insertAll(List<HotCommit> hotCommits);
     
    /**
     * 删除,主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     * @param id
     */
    void deleteById(String id);
     
    /**
     * 按条件删除
     * <br>------------------------------<br>
     * @param criteriaHotCommit
     */
    void delete(HotCommit criteriaHotCommit);
     
    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    void deleteAll();
     
    /**
     * 修改
     * <br>------------------------------<br>
     * @param hotCommit
     */
    void updateById(HotCommit hotCommit);
     
    /**
     * 更新多条
     * <br>------------------------------<br>
     * @param criteriaHotCommit
     * @param hotCommit
     */
    void update(HotCommit criteriaHotCommit, HotCommit hotCommit);
     
    /**
     * 根据主键查询
     * <br>------------------------------<br>
     * @param id
     * @return
     */
    HotCommit findById(String id);
     
    /**
     * 查询全部
     * <br>------------------------------<br>
     * @return
     */
    List<HotCommit> findAll();
     
    /**
     * 按条件查询
     * <br>------------------------------<br>
     * @param criteriaHotCommit
     * @param skip
     * @param limit
     * @return
     */
    List<HotCommit> find(HotCommit criteriaHotCommit, int skip, int limit,Sort sort);
     
    /**
     * 根据条件查询出来后 在去修改
     * <br>------------------------------<br>
     * @param criteriaHotCommit  查询条件
     * @param updateHotCommit    修改的值对象
     * @return
     */
    HotCommit findAndModify(HotCommit criteriaHotCommit, HotCommit updateHotCommit);
     
    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     * @param criteriaHotCommit
     * @return
     */
    HotCommit findAndRemove(HotCommit criteriaHotCommit);
     
    /**
     * count
     * <br>------------------------------<br>
     * @param criteriaHotCommit
     * @return
     */
    long count(HotCommit criteriaHotCommit);
	
}
