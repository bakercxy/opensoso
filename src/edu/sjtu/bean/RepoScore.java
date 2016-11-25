package edu.sjtu.bean;

import java.util.Comparator;

/**
 * Created by BakerCxy on 2015/11/21.
 */
public class RepoScore implements Comparator {

    int id;
    double score;

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
        if (t1.score != t2.score)
            return t1.score < t2.score ? 1 : -1;
        else
            return 0;
    }
}
