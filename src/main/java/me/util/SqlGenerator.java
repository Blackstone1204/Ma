/**
 * 
 */
package me.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import me.annotation.Column;
import me.annotation.Table;

/**
 * @author BlackStone
 *
 */
public class SqlGenerator {
	/** 
	 * @ClassName: SqlGenerater 
	 * @Description: TODO(这里用一句话描述这个类的作用) 
	 * @date 2017年12月26日 下午3:49:57 
	 * 
	 */
	
	private static SqlGenerator sg=new SqlGenerator();
	
	private SqlGenerator(){
		
	}
	public static SqlGenerator getInstance(){
		return sg==null?new SqlGenerator():sg;
		
	}
	
	public static <T> String getInsertSql(T t) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		String sf="insert into %s(%s) values(%s)";
		
		String tableName=ReflectHelper.getTableConfig(t).get("tableName");
		
		Field[] fields=t.getClass().getFields();
		String zds="";
		String vs="";
		
		for(Field field:fields){
			//System.out.println(String.format("字段%s 属性%s", field.getName(),field.getAnnotation(Column.class).name()));
			zds+=field.getAnnotation(Column.class).name()+",";
			
			String methodName="get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
			//System.out.println("methodName="+methodName);
			Method m=t.getClass().getMethod(methodName);
			String result=(String) m.invoke(t);
			vs+="'"+result+"'"+",";
		}
		
		String sql=String.format(sf, tableName,zds.substring(0,zds.length()-1),vs.substring(0, vs.length()-1));
		return sql;
		
	}
	
	public static String getSelectSql(Class<?> clazz,String whereargs){
		String  sf="select * from  %s ";
		String tableName=ReflectHelper.getTableConfig(clazz).get("tableName");
		String sql=String.format(sf,tableName);

		return whereargs.length()>0?sql+" where "+whereargs:sql;

	}
	
	public static String getDeleteSql(Class<?> clazz,String whereargs){
		String  sf="delete from  %s ";
		String tableName=ReflectHelper.getTableConfig(clazz).get("tableName");
		String sql=String.format(sf,tableName);

		return whereargs.length()>0?sql+" where "+whereargs:sql;
	}
	
	public static String getUpdateSql(Class<?> clazz,String setargs,String whereargs){
		String  sf="update %s set "+setargs;
		String tableName=ReflectHelper.getTableConfig(clazz).get("tableName");
		String sql=String.format(sf,tableName);

		return whereargs.length()>0?sql+" where "+whereargs:sql;
		
	}
	
	
	
}
