package edu.sjtu.web.bean;

/**
 * Created by BakerCxy on 2015/11/21.
 */
public class SearchRepo implements Cloneable{
    String reponame;
    int id;
    String des;
    String url;
    String date;
    String username;
    String tags;
    String language;
    int stargazers;
    int forks;
    String markedreponame;

    public String getMarkedreponame() {
		return markedreponame;
	}

	public void setMarkedreponame(String markedreponame) {
		this.markedreponame = markedreponame;
	}

	public void setId(int id) {
        this.id = id;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SearchRepo(String reponame, int id, String des, String url,
                      String date, String username,String tags,String language,int stargazers, int forks) {
        super();
        this.reponame = reponame;
        this.id = id;
        this.des = des;
        this.url = url;
        this.date = date;
        this.username = username;
        this.tags = tags;
        this.language = language;
        this.stargazers = stargazers;
        this.forks = forks;
    }

    public String getReponame() {
        return reponame;
    }

    public void setReponame(String reponame) {
        this.reponame = reponame;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public SearchRepo() {

    }

    public int getId() {
        return id;
    }

    public String getDes() {
        return des;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getStargazers() {
        return stargazers;
    }

    public void setStargazers(int stargazers) {
        this.stargazers = stargazers;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }
    
    @Override  
    public Object clone() throws CloneNotSupportedException  
    {  
        return super.clone();  
    }  
}
