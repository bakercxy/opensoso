package com.github.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Sort;

import com.github.model.Repo;

public interface RepoDAO{

	/**
     * 新增
     * <br>------------------------------<br>
     * @param repo
     */
    void insert(Repo repo);
     
    /**
     * 新增
     * <br>------------------------------<br>
     * @param repos
     */
    void insertAll(List<Repo> repos);
     
    /**
     * 删除,主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     * @param id
     */
    void deleteById(String id);
     
    /**
     * 按条件删除
     * <br>------------------------------<br>
     * @param criteriaRepo
     */
    void delete(Repo criteriaRepo);
     
    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    void deleteAll();
     
    /**
     * 修改
     * <br>------------------------------<br>
     * @param repo
     */
    void updateById(Repo repo);
     
    /**
     * 更新多条
     * <br>------------------------------<br>
     * @param criteriaRepo
     * @param repo
     */
    void update(Repo criteriaRepo, Repo repo);
     
    /**
     * 根据主键查询
     * <br>------------------------------<br>
     * @param id
     * @return
     */
    Repo findById(String id);
     
    /**
     * 查询全部
     * <br>------------------------------<br>
     * @return
     */
    List<Repo> findAll();
     
    /**
     * 按条件查询
     * <br>------------------------------<br>
     * @param criteriaRepo
     * @param skip
     * @param limit
     * @return
     */
    List<Repo> find(Set<Integer> criteriaRepo);
     
    /**
     * 根据条件查询出来后 在去修改
     * <br>------------------------------<br>
     * @param criteriaRepo  查询条件
     * @param updateRepo    修改的值对象
     * @return
     */
    Repo findAndModify(Repo criteriaRepo, Repo updateRepo);
     
    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     * @param criteriaRepo
     * @return
     */
    Repo findAndRemove(Repo criteriaRepo);
     
    /**
     * count
     * <br>------------------------------<br>
     * @param criteriaRepo
     * @return
     */
    long count(Repo criteriaRepo);
	
}
