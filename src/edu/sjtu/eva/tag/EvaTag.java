package edu.sjtu.eva.tag;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class EvaTag implements Comparator{
	int id;
	Set<String> tags;

	@Override
	public String toString() {
		return "Tag [id=" + id + ", tags=" + tags + "]";
	}

	public EvaTag(int id, Set<String> tags) {
		super();
		this.id = id;
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		EvaTag t1 = (EvaTag) o1;
		EvaTag t2 = (EvaTag) o2;
		if (t1.id != t2.id)
			return t1.id > t2.id ? 1 : -1;
		else
			return 0;
	}

	public EvaTag() {
		super();
	}

}
