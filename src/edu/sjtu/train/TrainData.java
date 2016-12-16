package edu.sjtu.train;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Lab1.FileUtil;
import Lab1.ILineHandler;
import edu.sjtu.core.queryprc.QueryExpansion;
import edu.sjtu.core.ranking.RepoRanking;
import edu.sjtu.core.resource.Resource;
import edu.sjtu.web.util.Path;

/*输入：用户对搜索对的标注文件，格式如下：
 * id1,id2,[0,1]
 * 输出：用于weka训练的arff文件，包含计算得到的N维向量
 */
public class TrainData {
	public static void main(String[] args) {
		Resource r = new Resource();
		QueryExpansion q = new QueryExpansion(r); 
		final RepoRanking repoRanking = new RepoRanking(r,q);
		
		final List<String> result = new ArrayList<String>();
		result.add("@ATTRIBUTE repo1_titlesim  NUMERIC");
		result.add("@ATTRIBUTE repo2_titlesim  NUMERIC");
		result.add("@ATTRIBUTE repo1_textsim  NUMERIC");
		result.add("@ATTRIBUTE repo2_textsim  NUMERIC");
		result.add("@ATTRIBUTE repo1_tagsim  NUMERIC");
		result.add("@ATTRIBUTE repo2_tagsim  NUMERIC");
		
		result.add("@ATTRIBUTE repo1_star  NUMERIC");
		result.add("@ATTRIBUTE repo2_star  NUMERIC");
		result.add("@ATTRIBUTE repo1_fork  NUMERIC");
		result.add("@ATTRIBUTE repo2_fork  NUMERIC");
		result.add("@ATTRIBUTE repo1_active  NUMERIC");
		result.add("@ATTRIBUTE repo2_active  NUMERIC");
		
		result.add("@ATTRIBUTE deslen1diff  NUMERIC");
		result.add("@ATTRIBUTE namelen1diff  NUMERIC");
		
		result.add("@ATTRIBUTE repo1_ed  NUMERIC");
		result.add("@ATTRIBUTE repo2_ed  NUMERIC");
		result.add("@ATTRIBUTE repo1_mc  NUMERIC");
		result.add("@ATTRIBUTE repo2_mc  NUMERIC");
		
		result.add("@ATTRIBUTE repo1_keyword  NUMERIC");
		result.add("@ATTRIBUTE repo2_keyword  NUMERIC");
		result.add("@ATTRIBUTE repo1_keytag  NUMERIC");
		result.add("@ATTRIBUTE repo2_keytag  NUMERIC");
		result.add("@ATTRIBUTE class {0,1}");
		result.add("");
		result.add("@DATA");

		FileUtil.readFile(new File(Path.resPath + "rank-model\\labelpair.dat"), new ILineHandler() {
			
			@Override
			public void process(String line, int index) throws Exception {
				// TODO Auto-generated method stub
				String[] datas = line.split(",");
				String query = datas[0].trim();
				int id1 = Integer.parseInt(datas[1].trim());
				int id2 = Integer.parseInt(datas[2].trim());
				double[] features = repoRanking.getFeatures(query, id1, id2);
				result.add(Arrays.toString(features).replaceAll("\\[|\\]", "") + "," + Integer.parseInt(datas[3].trim()));
			}
		});
		
		FileUtil.writeFile(new File(Path.resPath + "rank-model\\traindata.arff"), result);
		System.out.println("done.");
		
	}
}
