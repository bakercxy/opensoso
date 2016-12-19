package edu.sjtu.core.ranking;

import java.util.Comparator;

/**
 * Created by BakerCxy on 2015/11/21.
 */
public class RepoScore implements Comparator {

	int id;
    double score;
    double rank = -1;

	@Override
	public String toString() {
		return "RepoScore [id=" + id + ", score=" + score + ", rank=" + rank
				+ "]";
	}

	public double getRank() {
		return rank;
	}

	public void setRank(double rank) {
		this.rank = rank;
	}

	public RepoScore(int id, double score) {
        this.id = id;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public RepoScore() {
    }

    @Override
    public int compare(Object o1, Object o2) {
        // TODO Auto-generated method stub
        RepoScore t1 = (RepoScore) o1;
        RepoScore t2 = (RepoScore) o2;
        if(t1.getRank() >= 0 && t2.getRank() >= 0)
        {
        	if(t1.rank > t2.rank)
        		return -1;
        	else if(t1.rank < t2.rank)
        		return 1;
        	else  if (t1.score != t2.score)
                return t1.score < t2.score ? 1 : -1;
            else
                return 0;
        }
        else if (t1.score != t2.score)
            return t1.score < t2.score ? 1 : -1;
        else
            return 0;
    }
}
