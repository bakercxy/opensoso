package edu.sjtu.web.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class FileUtil {
	public static void readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			List list = new ArrayList<String>();
			int count = 0;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				if(tempString.contains("<abstract>"))
				{
					tempString = tempString.replace("<abstract>", "");
					tempString = tempString.replace("</abstract>", "");
					list.add(tempString);
					line++;
					if(line == 10000)
					{
						System.out.println(count+"_complete");
						saveFileByList(list,"./abstractlist" + count);
						count++;
						line = 0;
						list = new ArrayList<String>();
					}
				}
				
			}
			reader.close();
			saveFileByList(list,"./abstractlist" + count);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public static void saveFileByList(List<String> list, String fileName) {
		File f = new File(fileName);
		String saveString = "";
		for (String s : list) {
			saveString += "\r\n" + s;
		}

		FileOutputStream fos = null;

		try {
			if (!f.exists()) {// 文件不存在则创建
				f.createNewFile();
			}
			fos = new FileOutputStream(f);
			fos.write(saveString.getBytes());// 写入文件内容
			fos.flush();
		} catch (IOException e) {
			System.err.println("文件创建失败");
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					System.err.println("文件流关闭失败");
				}
			}
		}
	}
	
	public static void saveFileByMap(Map<String,Integer> map, String fileName) {
		System.out.println("map.length:" + map.size());
		File f = new File(fileName);
		String saveString = "";
		for (String s : map.keySet()) {
			System.out.println(s);
			saveString += "\r\n" + s + " : " + map.get(s);
		}

		FileOutputStream fos = null;

		try {
			if (!f.exists()) {// 文件不存在则创建
				f.createNewFile();
			}
			fos = new FileOutputStream(f);
			fos.write(saveString.getBytes());// 写入文件内容
			fos.flush();
		} catch (IOException e) {
			System.err.println("文件创建失败");
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					System.err.println("文件流关闭失败");
				}
			}
		}
	}
	
	public static void writeFile(File f, List<String> list){
		BufferedWriter writer = getBufferedWriter(f);
		if (list == null)
			return;
		// String content = "";
		try{
			for (String str : list) {
				// content += str + "\n";
				writer.write(str + "\n");
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			closeWriter(writer);
		}
	}
	
	public static <T> void writeFiles(File f, Map<String,T> map){
		BufferedWriter writer = getBufferedWriter(f);
		if (map == null)
			return;
		try{
			int total = map.size();
			int process = 0;
			for (String key : map.keySet())
			{
				writer.write(JSONObject.toJSON(map.get(key)).toString() + "\n");
//				map.remove(key);
				if(++process % 5000 == 0)
					System.out.println("### 已经完成保存" + process + "/" + total +"");
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			closeWriter(writer);
		}
	}
	
	public static BufferedWriter getBufferedWriter(File file) {
		BufferedWriter writer = null;
		try {
			createFile(file, true);
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "utf-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return writer;
	}
	
	public static void writeFile(File f, String content) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(f), "utf-8"));
			writer.write(content);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		} finally {
			if (writer != null) {
				try {
					writer.flush();
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static void closeWriter(BufferedWriter writer) {
		if (writer != null) {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



	public static Set<String> readWordList(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			Set set = new HashSet<String>();
			
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				set.add(tempString);
				line++;
			}
			reader.close();
			return set;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		FileUtil fu = new FileUtil();
		fu.readFileByLines("./articles_en.xml");
//		Set s = fu.readWordList("./wordlist");
//		for(Iterator<String> iter = s.iterator();iter.hasNext(); )
//			System.out.println(iter.next());
		
	}
	
	public static List<String> readAbstract(String fileName) {
		File file = new File(fileName);
		if(!file.exists())  //如果文件不存在， 则返回null
			return null;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			List list = new ArrayList<String>();
			
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				list.add(tempString);
				line++;
			}
			reader.close();
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return null;
	}
	
	public static void readFile(File file, ILineHandler handler) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "utf-8"));
			String line = null;
			int i = 0;
			while ((line = reader.readLine()) != null) {
//				if (StringUtils.isBlank(line))
//					continue;
				handler.process(line,++i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 创建一个文件夹
	 * 
	 * @param path
	 * @return
	 */
	public static boolean createDir(String path) {
		boolean flag = false;
		File file = new File(path);
		String fatherDir = file.getAbsolutePath().substring(0,
				file.getAbsolutePath().lastIndexOf(File.separator));
		File father = new File(fatherDir);
		if (!father.exists())
			createDir(fatherDir);
		if (!file.isDirectory()) {
			flag = file.mkdir();
		}

		return flag;
	}

	public static boolean createDir(String path, boolean isDelete) {
		boolean flag = false;
		File file = new File(path);
		if (file.exists() && file.isDirectory()) {
			if (isDelete)
				deleteFolder(file);
		}
		String fatherDir = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(File.separator));
		File father = new File(fatherDir);
		if (!father.exists())
			createDir(fatherDir);
		if (!file.isDirectory()) {
			flag = file.mkdir();
		}

		return flag;
	}

	/**
	 * 创建一个文件
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static boolean createFile(String path) throws IOException {
		return createFile(path, false);
	}

	/**
	 * 
	 * @param file
	 * @param isDelete
	 * @return
	 * @throws IOException
	 */
	public static boolean createFile(File file, boolean isDelete) {
		boolean flag = true;
		try {
			if (file.exists()) {
				if (isDelete) {
					file.delete();
					file.createNewFile();
				} else {
					flag = false;
				}
			} else {
				String dir = file.getAbsolutePath().substring(0,
						file.getAbsolutePath().lastIndexOf(File.separator));
				createDir(dir);
				file.createNewFile();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 
	 * @param path
	 * @param isDelete
	 * @return
	 * @throws IOException
	 */
	public static boolean createFile(String path, boolean isDelete)
			throws IOException {
		File file = new File(path);

		return createFile(file, isDelete);
	}
	
	public static boolean deleteFolder(File folder) {
		return deleteFolderContents(folder) && folder.delete();
	}

	/**
	 * Delete folder's children files
	 * 
	 * @param folder
	 * @return
	 */
	public static boolean deleteFolderContents(File folder) {
		File[] files = folder.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {
					if (!file.delete()) {
						return false;
					}
				} else {
					if (!deleteFolder(file)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public static List<String> readLine(InputStream in) {
		List<String> result = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null)
				result.add(line);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

}
