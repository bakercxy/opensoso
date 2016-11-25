package com.github.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.github.dao.HotDesDAO;
import com.github.model.HotDes;
import com.github.model.HotWord;
import com.github.util.Freq2WeightUtil;

@Component("hotDesService")
public class HotDesService {
	
	private HotDesDAO hotDesDAO;
	
	
	public HotDesDAO getHotDesDAO() {
		return hotDesDAO;
	}

	@Resource(name = "hotDesDAOImpl")
	public void setHotDesDAO(HotDesDAO hotDesDAO) {
		this.hotDesDAO = hotDesDAO;
	}

	public List<HotDes> queryHotDesList(double minweight,int maxsize,int minsize){
		HotDes criteriaHotDes = new HotDes();
		criteriaHotDes.setWeight(minweight);
		Sort sort = new Sort(Direction.DESC, "weight");
		List<HotDes> result = hotDesDAO.find(criteriaHotDes, 0, 0,sort);
		Freq2WeightUtil.Freq2WeightList_Des(result,minweight,maxsize,minsize);
		System.out.println(result.size());
//		for(Iterator<HotDes> iter = result.iterator();iter.hasNext();)
//		{
//			HotDes hotDes = iter.next();
//			System.out.println("name:" + hotDes.getWord()  + "  weight:" + hotDes.getWeight());
//			hotDes.setWeight(hotDes.getWeight() * 500);
//		}
//		System.out.println("result_size:" + result.size());
		return result;
	}
	
}
