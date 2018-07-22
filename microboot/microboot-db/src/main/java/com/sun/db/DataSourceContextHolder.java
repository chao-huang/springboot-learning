package com.sun.db;

import java.util.ArrayList;
import java.util.List;

/** 
 * Created by pure on 2018-05-06. 
 */  
public class DataSourceContextHolder {  
    /** 
     * 默认数据源 
     */  
    public static final String DEFAULT_DS = "datasource1";  
  
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>(); 
    public static List<String> dataSourceIds = new ArrayList<>();
  
    // 设置数据源名  
    public static void setDB(String dbType) {  
        System.out.println("切换到{"+dbType+"}数据源");  
        contextHolder.set(dbType);  
    }  
  
    // 获取数据源名  
    public static String getDB() {  
        return (contextHolder.get());  
    }  
  
    // 清除数据源名  
    public static void clearDB() {  
        contextHolder.remove();  
    }  
    /**
     * 判断指定DataSrouce当前是否存在
     */
    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }
}  