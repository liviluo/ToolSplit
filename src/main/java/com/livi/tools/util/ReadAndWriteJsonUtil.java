package com.livi.tools.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSONObject;

public class ReadAndWriteJsonUtil {

	/**
	 * 默认使用UTF-8编码
	 * 
	 * @param FilePath 文件路径
	 * @return JSONObject
	 * @throws IOException
	 * 
	 **/
	public static JSONObject read(String FilePath) throws IOException {
		return read(FilePath, "UTF-8");
	}

	/**
	 * 需要指定编码格式
	 * 
	 * @param FilePath 文件路径
	 * @param encoding 编码格式
	 * @return JSONObject
	 * @throws IOException
	 * 
	 **/
	public static JSONObject read(String FilePath, String encoding) throws IOException {
		JSONObject result = new JSONObject();
		File file = new File(FilePath);
		if (file.exists() && !file.isDirectory()) {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
			BufferedReader br = new BufferedReader(isr);
			String line;
			StringBuffer content = new StringBuffer();
			while ((line = br.readLine()) != null) {
				content.append(line);
			}
			result = JSONObject.parseObject(content.toString());
			br.close();
			isr.close();
		}
		return result;
	}

	/**
	 * 
	 * @param FilePath 文件路径
	 * @param content  文本内容
	 * @return void
	 * @throws IOException
	 * 
	 **/
	public static void write(String FilePath, String content) throws IOException {
		File file = new File(FilePath);
		if (!file.exists() || !file.isDirectory()) {
			File fileParent = file.getParentFile();
			if (!fileParent.exists()) {
				fileParent.mkdirs();
			}
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file);
		fw.write(content);
		fw.flush();
		fw.close();
	}

}
