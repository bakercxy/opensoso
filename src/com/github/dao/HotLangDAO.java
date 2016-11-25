package com.github.dao;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.github.model.HotLang;

public interface HotLangDAO{

	/**
     * 新增
     * <br>------------------------------<br>
     * @param hotLang
     */
    void insert(HotLang hotLang);
     
    /**
     * 新增
     * <br>------------------------------<br>
     * @param hotLangs
     */
    void insertAll(List<HotLang> hotLangs);
     
    /**
     * 删除,主键id, 如果主键的值为null,删除会失败
     * <br>------------------------------<br>
     * @param id
     */
    void deleteById(String id);
     
    /**
     * 按条件删除
     * <br>------------------------------<br>
     * @param criteriaHotLang
     */
    void delete(HotLang criteriaHotLang);
     
    /**
     * 删除全部
     * <br>------------------------------<br>
     */
    void deleteAll();
     
    /**
     * 修改
     * <br>------------------------------<br>
     * @param hotLang
     */
    void updateById(HotLang hotLang);
     
    /**
     * 更新多条
     * <br>------------------------------<br>
     * @param criteriaHotLang
     * @param hotLang
     */
    void update(HotLang criteriaHotLang, HotLang hotLang);
     
    /**
     * 根据主键查询
     * <br>------------------------------<br>
     * @param id
     * @return
     */
    HotLang findById(String id);
     
    /**
     * 查询全部
     * <br>------------------------------<br>
     * @return
     */
    List<HotLang> findAll();
     
    /**
     * 按条件查询
     * <br>------------------------------<br>
     * @param criteriaHotLang
     * @param skip
     * @param limit
     * @return
     */
    List<HotLang> find(HotLang criteriaHotLang, int skip, int limit,Sort sort);
     
    /**
     * 根据条件查询出来后 在去修改
     * <br>------------------------------<br>
     * @param criteriaHotLang  查询条件
     * @param updateHotLang    修改的值对象
     * @return
     */
    HotLang findAndModify(HotLang criteriaHotLang, HotLang updateHotLang);
     
    /**
     * 查询出来后 删除
     * <br>------------------------------<br>
     * @param criteriaHotLang
     * @return
     */
    HotLang findAndRemove(HotLang criteriaHotLang);
     
    /**
     * count
     * <br>------------------------------<br>
     * @param criteriaHotLang
     * @return
     */
    long count(HotLang criteriaHotLang);
	
}
