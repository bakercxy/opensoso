package edu.sjtu.core.ranking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.springframework.stereotype.Component;

import weka.classifiers.Classifier;
import weka.core.Instance;
import edu.sjtu.web.util.Path;

@Component("rankingmodel")
public class RankingModel {
	
	Classifier cls = null;
	
	public RankingModel(){
		ObjectInputStream ois;
		try {
			System.out.println("RankingModel: Reading model ...");
			ois = new ObjectInputStream(  
			        new FileInputStream(Path.resPath + "rank-model\\mp.model"));
			cls = (Classifier) ois.readObject();  
			ois.close(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean compare(double[] features){
		Instance instance = new Instance(features.length);
		for(int i = 0; i < features.length; i++)
		{
			instance.setValue(i,features[i]);
		}
		try {
			double value = cls.classifyInstance(instance);
			if(value >= 0.5)
				return true;
			else
				return false;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("RankingModel: features' length is incorrect.");
			return true;
		}
	}
}
