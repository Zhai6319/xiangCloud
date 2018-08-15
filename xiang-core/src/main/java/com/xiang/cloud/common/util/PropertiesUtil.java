package com.xiang.cloud.common.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * properties操作工具类
 * @author zhaijianchao
 * @date 2018/08/15
 */
public class PropertiesUtil {
	
	public static void main(String[] args) throws IOException {
        Map<String,Object> map = new HashMap<String, Object>();
        map = PropertiesUtil.loadToMap("src/main/resources/jdbc.properties");
        System.out.println(map);
    }
     
    /**
     *  得到properties文件的map形式
     * @param propertiesFilePath  properties文件的路径
     * @return Map<String,Object> 对应资源文件转换成map
     */
    public static Map<String,Object> loadToMap(String propertiesFilePath) {
         
        //1.加载资源文件
        InputStream in;
        Properties pro = new Properties();
        try {
            in = new FileInputStream(new File(propertiesFilePath));
            pro.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        //获取配置文件的所有键值
        Set<String> keys = pro.stringPropertyNames();
        //文件的内容为空
        if(keys.size() == 0){
            throw new RuntimeException("资源文件:"+propertiesFilePath+"的内容为空");
        }
        //把键值对放入map中
        Map<String,Object> map = new HashMap<String,Object>();
        for(String key : keys){
            map.put(key, pro.getProperty(key));
        }
        return map;
    }
	
	public static String getConfig(String filePath,String key){
		String path = Thread.currentThread().getContextClassLoader().getResource(filePath).getPath();
		Map<String,Object> jdbc = new HashMap<String,Object>();
		jdbc = loadToMap(path);
		return jdbc.get(key).toString();
	}

}
