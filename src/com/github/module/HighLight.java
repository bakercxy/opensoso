package com.github.module;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.sjtu.bean.SearchRepo;

public class HighLight {

	public static SearchRepo highLight(SearchRepo r, String query) {

		String[] ss = query.split(" ");
		Set<String> keyword = new HashSet<String>();
		for (String s : ss)
			keyword.add(s);
		String color = "254,254,197";

		if (r.getDes() != null) {
			String des = " " + r.getDes() + " ";
			for (String s : keyword) {
				String regEx = "([\\./\\\\\\-,_\\s])(?i)" + s
						+ "([\\./\\\\\\-,_\\s])";
				for (String sss : getList(des, regEx))
					des = des.replaceAll(
							"([\\./\\\\\\-,_\\s])"
									+ sss.substring(1, sss.length() - 1)
									+ "([\\./\\\\\\-,_\\s])", "$1###<<<###"
									+ sss.substring(1, sss.length() - 1)
									+ "###>>>###$2");
			}
			des = des.replace("###<<<###", "<mark>");
			des = des.replace("###>>>###", "</mark>");
			r.setDes(des.trim());
		}

		if (r.getLanguage() != null) {
			String language = r.getLanguage();
			String[] langs = language.split(", ");
			String result_language = "";
			for (String lang : langs) {
				boolean flag = false;
				for (String s : keyword)
					if (lang.toLowerCase().equals(s)) {
						result_language += ("###<<<###" + lang + "###>>>###, ");
						flag = true;
						break;
					}
				if (!flag)
					result_language += lang + ", ";

			}

			result_language = result_language.replace("###<<<###", "<mark>");
			result_language = result_language.replace("###>>>###", "</mark>");
			r.setLanguage(result_language.substring(0,
					result_language.length() - 2));
		}

		if (r.getTags() != null) {
			String tag = r.getTags();
			String[] tags = tag.split(", ");
			String result_tag = "";
			for (String t : tags) {
				boolean flag = false;
				for (String s : keyword)
					if (t.toLowerCase().equals(s)) {
						result_tag += ("###<<<###" + t + "###>>>###, ");
						flag = true;
						break;
					}
				if (!flag)
					result_tag += t + ", ";
			}

			result_tag = result_tag.replace("###<<<###", "<mark>");
			result_tag = result_tag.replace("###>>>###", "</mark>");
			r.setTags(result_tag.substring(0, result_tag.length() - 2));
		}

		if (r.getReponame() != null) {

			for (int j = 0; j < ss.length - 1; j++)
				for (int k = j + 1; k < ss.length; k++) {
					if (ss[j].indexOf(ss[k]) != -1)
						ss[k] = "";
					else if (ss[k].indexOf(ss[j]) != -1)
						ss[j] = "";
				}

			String name = r.getReponame();
			for (int j = 0; j < ss.length; j++)
				if (ss[j].length() >= 3
						&& name.toLowerCase().indexOf(ss[j]) != -1)
					name = name.substring(0, name.toLowerCase().indexOf(ss[j]))
							+ "###<<<###"
							+ name.substring(name.toLowerCase().indexOf(ss[j]),
									ss[j].length()
											+ name.toLowerCase().indexOf(ss[j]))
							+ "###>>>###"
							+ name.substring(ss[j].length()
									+ name.toLowerCase().indexOf(ss[j]));
			name = name.replace("###<<<###", "<mark>");
			name = name.replace("###>>>###", "</mark>");
			r.setMarkedreponame(name);
		}
		return r;
	}

	public static Set<String> getList(String str, String reg) {
		List<String> list = new ArrayList<String>();
		Pattern pattern = Pattern.compile(reg);
		if (str == null) {
			return null;
		}
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			if (!list.contains(matcher.group()))
				list.add(matcher.group());
		}
		Set<String> tmpList = new HashSet<String>();
		for (int i = 0; i < list.size(); i++) {
			tmpList.add((String) list.get(i));
		}
		return tmpList;
	}

}
