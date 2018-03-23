/**
 * 
 */
package me.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import me.annotation.Column;
import me.annotation.DataBase;
import me.annotation.Table;

/**
 * @author BlackStone
 *
 */
public class ReflectHelper {
	/** 
	 * @param <T>
	 * @ClassName: ReflectHelper 
	 * @Description: TODO(这里用一句话描述这个类的作用) 
	 * @date 2017年12月26日 下午5:24:35 
	 * 
	 */
	
	
	public static <T> Map<String,String> getTableConfig(Class<?> clazz){
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("tableName", clazz.getAnnotation(Table.class).name());
		map.put("dbClass", clazz.getAnnotation(Table.class).dbClass());
		
		//解析字段&成员属性
		Field[] fs=clazz.getFields();

		
		for(Field f:fs){
			String fieldName=f.getName();
			String columnName=f.getAnnotation(Column.class).name();
			map.put(fieldName, columnName);
			
		}
		


		return map;
		
	}
	public static <T> Map<String,String> getTableConfig(T table){
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("tableName", table.getClass().getAnnotation(Table.class).name());
		map.put("dbClass", table.getClass().getAnnotation(Table.class).dbClass());

		return map;
		
	}

	public static <T> Map<String,String> getDBConfig(Class<?> clazz){
		Map<String,String> map=new HashMap<String,String>();
		
		//System.out.println(db.getClass().getAnnotation(DataBase.class));
		
		map.put("url", clazz.getAnnotation(DataBase.class).url());
		map.put("userName", clazz.getAnnotation(DataBase.class).userName());
		map.put("password", clazz.getAnnotation(DataBase.class).password());
		
	
		
		return map;
		
	}
	
	public static <T> Map<String,String> getDBConfig(T db){
		Map<String,String> map=new HashMap<String,String>();
		
		//System.out.println(db.getClass().getAnnotation(DataBase.class));
		
		map.put("url", db.getClass().getAnnotation(DataBase.class).url());
		map.put("userName", db.getClass().getAnnotation(DataBase.class).userName());
		map.put("password", db.getClass().getAnnotation(DataBase.class).password());
		
		return map;
		
	}
	
	public static String getColumnName(Field field){
		return field.getAnnotation(Column.class).name();
		
	}
	
	

	
	
}
