package com.github.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.github.dao.HotWordDAO;
import com.github.model.HotCommit;
import com.github.model.HotDes;
import com.github.model.HotLang;
import com.github.util.Freq2WeightUtil;

@Component("hotWordService")
public class HotWordService {
	
	private HotWordDAO<HotLang> hotWordDAOHotLang;
	private HotWordDAO<HotCommit> hotWordDAOHotCommit;
	private HotWordDAO<HotDes> hotWordDAOHotDes;
	
	public HotWordDAO<HotLang> getHotWordDAOHotLang() {
		return hotWordDAOHotLang;
	}

	@Resource(name = "hotWordDAOImpl")
	public void setHotWordDAOHotLang(HotWordDAO<HotLang> hotWordDAOHotLang) {
		this.hotWordDAOHotLang = hotWordDAOHotLang;
	}

	public HotWordDAO<HotCommit> getHotWordDAOHotCommit() {
		return hotWordDAOHotCommit;
	}

	@Resource(name = "hotWordDAOImpl")
	public void setHotWordDAOHotCommit(HotWordDAO<HotCommit> hotWordDAOHotCommit) {
		this.hotWordDAOHotCommit = hotWordDAOHotCommit;
	}

	public HotWordDAO<HotDes> getHotWordDAOHotDes() {
		return hotWordDAOHotDes;
	}

	@Resource(name = "hotWordDAOImpl")
	public void setHotWordDAOHotDes(HotWordDAO<HotDes> hotWordDAOHotDes) {
		this.hotWordDAOHotDes = hotWordDAOHotDes;
	}

	public List queryHotWordList(double minweight,int maxsize,int minsize,String type){
		Sort sort = new Sort(Direction.DESC, "weight");
		List result = null;
		if(type.equals("lang"))
		{
//			HotWordDAO<HotLang> hotWordDAO = new HotWordDAOImpl<HotLang>();
//			result = hotWordDAO.find(minweight, 0, 0,sort);
			result = hotWordDAOHotLang.find(minweight, 0, 0,sort);
			result = Freq2WeightUtil.Freq2WeightList_Lang(result,minweight,maxsize,minsize);
		}
		else if(type.equals("commit"))
		{
//			HotWordDAO<HotCommit> hotWordDAO = new HotWordDAOImpl<HotCommit>();
//			result = hotWordDAO.find(minweight, 0, 0,sort);
			result = hotWordDAOHotCommit.find(minweight, 0, 0,sort);
			result = Freq2WeightUtil.Freq2WeightList_Commit(result,minweight,maxsize,minsize);
		}
			
		else if(type.equals("des"))
		{
//			HotWordDAO<HotDes> hotWordDAO = new HotWordDAOImpl<HotDes>();
//			result = hotWordDAO.find(minweight, 0, 0,sort);
			result = hotWordDAOHotDes.find(minweight, 0, 0,sort);
			result = Freq2WeightUtil.Freq2WeightList_Des(result,minweight,maxsize,minsize);
		}	
		
		System.out.println(result.size());
		return result;
	}
	
}
