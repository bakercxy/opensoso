package edu.sjtu.core.resource;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import Lab1.FileUtil;
import edu.sjtu.web.util.Path;

/**
 * Created by BakerCxy on 2015/11/17.
 */
public class ScoreSaveRunnable implements Runnable {

	public static List<String> sosoList;
	public static List<String> githubList;
//	public EvaResource evaResource;
//
//	public EvaResource getEvaResource() {
//		return evaResource;
//	}
//
//	@Resource(name = "evaresource")
//	public void setEvaResource(EvaResource evaResource) {
//		this.evaResource = evaResource;
//	}

	public static int MIN = 0;
	public static int MAX = 10;

	public ScoreSaveRunnable() {
		sosoList = Collections.synchronizedList(new ArrayList<String>());
		githubList = Collections.synchronizedList(new ArrayList<String>());
	}

	public void run() {
		int count = 0;
		while (true) {
			// System.out.println(count);
			try {
				Thread.sleep(1000 * 30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			if (++count < 5) {
				if (githubList.size() >= MAX) {
					FileUtil.writeFile(new File(Path.logPath + "github\\"
							+ new Date().getTime() + ".dat"), githubList);
					githubList.clear();
					System.out.println("Github Saved.");
				}
				if (sosoList.size() >= MAX) {
					FileUtil.writeFile(new File(Path.logPath + "soso\\"
							+ new Date().getTime() + ".dat"), sosoList);
					sosoList.clear();
					System.out.println("SOSO Saved.");
				}
			} else {
				if (githubList.size() > MIN) {
					FileUtil.writeFile(new File(Path.logPath + "github\\"
							+ new Date().getTime() + ".dat"), githubList);
					githubList.clear();
					System.out.println("Github Saved.");
				}
				if (sosoList.size() > MIN) {
					FileUtil.writeFile(new File(Path.logPath + "soso\\"
							+ new Date().getTime() + ".dat"), sosoList);
					sosoList.clear();
					System.out.println("SOSO Saved.");
				}
				count = 0;
			}
			
//			List<String> tagList = Collections.synchronizedList(new ArrayList<String>());
//			for(int index : evaResource.getLabeledTagIndex().keySet())
//				tagList.add(JSONObject.toJSONString(evaResource.getLabeledTagIndex().get(index)));
//			
//			FileUtil.writeFile(new File(Path.logPath + "tag-log.dat"), tagList);
//			System.out.println(time + ": TagEva Saved.");
//			System.out.println("TagEva: Remaining Size is " + evaResource.getRepoIndex().size());
//			tagList.clear();
		}

	}
}
