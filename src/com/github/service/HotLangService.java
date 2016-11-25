package com.github.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.github.dao.HotLangDAO;
import com.github.model.HotLang;
import com.github.util.Freq2WeightUtil;

@Component("hotLangService")
public class HotLangService {
	
	private HotLangDAO hotLangDAO;
	
	
	public HotLangDAO getHotLangDAO() {
		return hotLangDAO;
	}

	@Resource(name = "hotLangDAOImpl")
	public void setHotLangDAO(HotLangDAO hotLangDAO) {
		this.hotLangDAO = hotLangDAO;
	}

	public List<HotLang> queryHotLangList(double minweight,int maxsize,int minsize){
		HotLang criteriaHotLang = new HotLang();
		criteriaHotLang.setWeight(minweight);
		Sort sort = new Sort(Direction.DESC, "weight");
		List<HotLang> result = hotLangDAO.find(criteriaHotLang, 0, 0,sort);
		Freq2WeightUtil.Freq2WeightList_Lang(result,minweight,maxsize,minsize);
		System.out.println(result.size());
//		for(Iterator<HotLang> iter = result.iterator();iter.hasNext();)
//		{
//			HotLang hotLang = iter.next();
//			System.out.println("name:" + hotLang.getWord()  + "  weight:" + hotLang.getWeight());
//			hotLang.setWeight(hotLang.getWeight() * 500);
//		}
//		System.out.println("result_size:" + result.size());
		return result;
	}
	
}
