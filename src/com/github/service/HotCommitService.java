package com.github.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.github.dao.HotCommitDAO;
import com.github.model.HotCommit;
import com.github.model.HotWord;
import com.github.util.Freq2WeightUtil;

@Component("hotCommitService")
public class HotCommitService {
	
	private HotCommitDAO hotCommitDAO;
	
	
	public HotCommitDAO getHotCommitDAO() {
		return hotCommitDAO;
	}

	@Resource(name = "hotCommitDAOImpl")
	public void setHotCommitDAO(HotCommitDAO hotCommitDAO) {
		this.hotCommitDAO = hotCommitDAO;
	}

	public List<HotCommit> queryHotCommitList(double minweight,int maxsize,int minsize){
		HotCommit criteriaHotCommit = new HotCommit();
		criteriaHotCommit.setWeight(minweight);
		Sort sort = new Sort(Direction.DESC, "weight");
		List<HotCommit> result = hotCommitDAO.find(criteriaHotCommit, 0, 0,sort);
		Freq2WeightUtil.Freq2WeightList_Commit(result,minweight,maxsize,minsize);
		System.out.println(result.size());
//		for(Iterator<HotCommit> iter = result.iterator();iter.hasNext();)
//		{
//			HotCommit hotCommit = iter.next();
//			System.out.println("name:" + hotCommit.getWord()  + "  weight:" + hotCommit.getWeight());
//			hotCommit.setWeight(hotCommit.getWeight() * 500);
//		}
//		System.out.println("result_size:" + result.size());
		return result;
	}
	
}
