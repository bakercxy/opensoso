package com.github.module;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import Lab1.FileUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import edu.sjtu.bean.SearchRepo;

@Component("githubrank")
public class RepoFetch {

	Set<String> ExistedSet;
	List<String> userList;
	FileUtil fu;
	HttpURLConnection httpConn = null;
	public static String ACCESS_TOKEN = "a1739e4059099d155a443963f9655db246c81d16";
	public static String SEARCHURL = "https://api.github.com/search/repositories";
	public static String TESTURL = "https://api.github.com/search/repositories?q=java&token="
			+ ACCESS_TOKEN;
	com.github.resource.Resource resource;

	public com.github.resource.Resource getResource() {
		return resource;
	}

	@Resource(name = "resource")
	public void setResource(com.github.resource.Resource resource) {
		this.resource = resource;
	}

	public List<SearchRepo> search(String query, int top) {

		List<SearchRepo> searchRepos = new ArrayList<SearchRepo>();
		Set<Integer> existedId = new HashSet<Integer>();
		int page = 0;
		String htmlquery = query.replaceAll("\\s", "%20");
//		query = query.replaceAll("\\s", "%20");
		while (true) {
			System.out.println("fetching page " + ++page);
			String url = SEARCHURL + "?" + "&q=" + htmlquery + "&page=" + page
					+ "&per_page=" + 100 + "&token=" + ACCESS_TOKEN;
			System.out.println(url);
			String content = connectUrl(url);
			if (content != null) {
				// 解析repo
				try {
					JSONObject searchResult = JSONObject.parseObject(content);
					JSONArray arr = searchResult.getJSONArray("items");
					if (arr.size() > 0)
						for (int i = 0; i < arr.size(); i++) {
							JSONObject repo = arr.getJSONObject(i);
							int id = repo.getInteger("id");
							if (resource.getRepoids().containsKey(id)
									&& !existedId.contains(id)) {
								System.out.println("id:" + id);
								// String name = repo.getString("name");
								// String description =
								// repo.getString("description");
								// String url = repo.getString("url_html");
								// String date = repo.getString("updated_at");
								// String username =
								// repo.getJSONObject("owner").getString("login");
								// String language = repo.getString("language");
								// int stargazers =
								// repo.getInteger("stargazers_count");
								// int forks = repo.getInteger("forks_count");
								int myid = resource.getRepoids().get(id);
//								SearchRepo searchRepo = resource.getRepos()
//										.get(myid);
								// SearchRepo searchRepo = new SearchRepo(name,
								// id, description, url, date, username, "",
								// language, stargazers, forks);
								SearchRepo r;
								try {
									r = (SearchRepo) resource.getRepos().get(myid).clone();
									searchRepos.add(HighLight.highLight(r, query));
								} catch (CloneNotSupportedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								existedId.add(id);
								if (searchRepos.size() == top)
									return searchRepos;
							}
						}
					else
						return searchRepos;
					if (page == 10)
						return searchRepos;
				} catch (com.alibaba.fastjson.JSONException e) {
					System.out.println("### Parsing fails, skip user-repos..");
					return searchRepos;
				}
			} else
				return searchRepos;
		}
	}

	public String connectUrl(String urlString) {
		for (int wrongTime = 1; wrongTime <= 3;) {
			if (wrongTime == 3) {
				try {
					URL url = new URL(TESTURL);
					httpConn = (HttpURLConnection) url.openConnection();
					httpConn.setRequestMethod("GET");

					// 获取网页源代码
					BufferedReader br = new BufferedReader(
							new InputStreamReader(httpConn.getInputStream(),
									"UTF-8"));
					System.out.println("### Url is bad, skip...");
					return null;

				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					return null;
				}
			}
			try {
				String webContent = ""; // 保存json信息
				URL url = new URL(urlString);
				httpConn = (HttpURLConnection) url.openConnection();
				httpConn.setRequestMethod("GET");
				httpConn.setConnectTimeout(1000 * 20);
				httpConn.setReadTimeout(1000 * 15);

				// 获取网页源代码
				BufferedReader br = null;
				br = new BufferedReader(new InputStreamReader(
						httpConn.getInputStream(), "UTF-8"));
				String line = br.readLine();
				while (line != null) {
					webContent = webContent + line; // 获取原网页内容
					line = br.readLine();
				}
				// System.out.println(webContent);
				return webContent;

			} catch (Exception e) {
				if (wrongTime < 3) {
					System.out.println("### Error: " + wrongTime
							+ ". Please wait 3 seconds...");
					try {
						Thread.sleep(1000 * 3);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				wrongTime++;
			}
		}
		return null;
	}

	// public void saveList(){
	// fu.writeFile(new File(outputPath + "/" + preName + "_" + fileNo++),
	// repoList);
	// System.out.println(repoList.size() + " items saved.");
	// repoList.clear();
	// }

	public static void main(String[] args) {
		RepoFetch rf = new RepoFetch();
		System.out.println(rf.search("hello java", 5));

	}

}
