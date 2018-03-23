/**
 * 
 */
package me.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.config.User;

/**
 * @author BlackStone
 *
 */
public class EntityOperation {
	/** 
	 * @ClassName: EntityOperation 
	 * @Description: TODO(这里用一句话描述这个类的作用) 
	 * @date 2017年12月26日 下午3:47:28 
	 * 
	 */

	private static Map<String,String> basicConfig=new HashMap<String,String>();
	static{
		//输出操作日志
		basicConfig.put("isOutputMsg","1");
		
	}
	private static EntityOperation ep=new EntityOperation();
	

	private EntityOperation(){
		
	}
	
	public static EntityOperation getInstance(){
		
		
		printBasicConfig();
		
		return ep==null?new EntityOperation():ep;
		
	}
	
	//库表插入对象
	public void add(Object obj){
	
		String sql="";
		try {
			sql=SqlGenerator.getInsertSql(obj);

			String dbClass=ReflectHelper.getTableConfig(obj).get("dbClass");
			Object db=Class.forName(dbClass).newInstance();
			String url =ReflectHelper.getDBConfig(db).get("url");
			String userName=ReflectHelper.getDBConfig(db).get("userName");
			String password=ReflectHelper.getDBConfig(db).get("password");
			DBUtil.getInstance().doConnection("", url, userName, password);
			DBUtil.getInstance().executeUpdate(sql,null);
			
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(getBasicConfig().get("isOutputMsg").equals("1"))System.out.println(String.format("添加成功 [%s]", sql));
	
	}
	
	
	public List<Object> find(Class<?>clazz,String whereargs){
		
		List<Object> list=new ArrayList<Object>();
		String sql=SqlGenerator.getSelectSql(clazz, whereargs);

		String dbClass=ReflectHelper.getTableConfig(clazz).get("dbClass");
		Object db;
		try {
			db = Class.forName(dbClass).newInstance();
			String url =ReflectHelper.getDBConfig(db).get("url");
			String userName=ReflectHelper.getDBConfig(db).get("userName");
			String password=ReflectHelper.getDBConfig(db).get("password");
			DBUtil.getInstance().doConnection("", url, userName, password);
			ResultSet rs=DBUtil.getInstance().executeQuery(sql);
			

			//System.out.println(clazz.getSimpleName());
			
			
			while(rs.next()){
				
				//
	
				Object obj=clazz.newInstance();
				Field[] fs=clazz.getFields();
				for(Field f:fs){
					
					
					//System.out.println(String.format("%s %s", f.getName(),obj.getClass()));
					String fieldName=f.getName();
					String columnName= ReflectHelper.getTableConfig(clazz).get(fieldName);
					Object columnValue=rs.getObject(columnName);
					String methodName="set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
					//System.out.println(String.format("filedName=%s columnName=%s columnValue=%s methodName=%s",fieldName,columnName,columnValue,methodName));
					Method m=clazz.getMethod(methodName, String.class);
					m.invoke(obj,columnValue);
					
					
				}
				list.add( obj);
				
			
			
				
			}
			
		
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		if(getBasicConfig().get("isOutputMsg").equals("1"))System.out.println(String.format("查找成功 返回%s条数据[%s]",list.size(),sql));
		
		return list;
		
	}
	
	public  void del(Class<?> clazz,String whereargs){
		    String sql="";
		    int count = 0;

			try {
				sql=SqlGenerator.getDeleteSql(clazz, whereargs);

				String dbClass=ReflectHelper.getTableConfig(clazz).get("dbClass");
				Object db;
				db = Class.forName(dbClass).newInstance();
				String url =ReflectHelper.getDBConfig(db).get("url");
				String userName=ReflectHelper.getDBConfig(db).get("userName");
				String password=ReflectHelper.getDBConfig(db).get("password");
				DBUtil.getInstance().doConnection("", url, userName, password);
				count=DBUtil.getInstance().executeUpdate(sql,null);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			if(getBasicConfig().get("isOutputMsg").equals("1"))System.out.println(String.format("删除成功 删除%s行[%s]",count,sql));
	}
	
	public  void update(Class<?> clazz,String setargs,String whereargs){

	    String sql="";
	    int count = 0;

		try {
			sql=SqlGenerator.getUpdateSql(clazz, setargs, whereargs);

			String dbClass=ReflectHelper.getTableConfig(clazz).get("dbClass");
			Object db;
			db = Class.forName(dbClass).newInstance();
			String url =ReflectHelper.getDBConfig(db).get("url");
			String userName=ReflectHelper.getDBConfig(db).get("userName");
			String password=ReflectHelper.getDBConfig(db).get("password");
			DBUtil.getInstance().doConnection("", url, userName, password);
			count=DBUtil.getInstance().executeUpdate(sql,null);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if(getBasicConfig().get("isOutputMsg").equals("1"))System.out.println(String.format("更新成功 更新%s行[%s]",count,sql));
		
	}
	
	private static Map<String,String> getBasicConfig(){
		
		return basicConfig;
		
	}
	
	public void setBasicConfig(String k,String v){
		if(basicConfig.keySet().contains(k))
			basicConfig.put(k, v);
		
	}
	
	public static void printBasicConfig(){
		
		String msg="[EntityOperation 当前设置项 ]  ";
		Set<String> s=basicConfig.keySet();
		for(String k:s){
			msg+=k+"="+basicConfig.get(k)+" ";
			
		}
		if(getBasicConfig().get("isOutputMsg").equals("1"))
			System.err.println(msg);
	}
	
	
	
}
