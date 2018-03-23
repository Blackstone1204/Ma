package me.util;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtil {
	private static DBUtil db=new DBUtil();
	private static Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	
	private DBUtil(){
		
	}
	public static DBUtil getInstance(){
		
		return db==null?new DBUtil():db;
		
	}
	
	/**
	 * 获取连接
	 * @return
	 */
	public Connection doConnection(String type,String url,String userName,String password){
		//String url = "jdbc:mysql://localhost:3306/auto?useUnicode=true&autoReconnect=true&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url,userName,password);
			
			//System.out.println("初始化连接 "+conn);
		}catch (Exception e) {
			System.out.println("DBUtils.getConnection---获取连接失败！");
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 执行查询操作
	 * @param sql
	 * @return 返回值是一个结果集
	 */
	public ResultSet executeQuery(String sql){
		try {
			
			this.checkConn();
			stmt = conn.createStatement();
			rs =  stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("DBUtils.executeQuery---执行查询操作失败！");
			e.printStackTrace();
		}finally{
			//this.close();
		}
		return rs;
	}
	
	/**
	 * 执行带参数的查询操作
	 * @param sql sql语句
	 * @param obj Object数组
	 * @return
	 */
	public ResultSet executeQuery(String sql, Object[] obj){
		try {
			this.checkConn();
			ps = conn.prepareStatement(sql);
			preparStateSql(obj, obj.length);
			rs =  ps.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("DBUtils.executeQuery---执行查询操作失败！");
			e.printStackTrace();
		}finally{
			//this.close();
		}
		return rs;
	}
	
	/**
	 * 执行更新、插入、删除等操作
	 * @param sql
	 * @param obj
	 * @return 返回值是操作影响的行数(即更新、插入或删除了几条数据)
	 */
	public int executeUpdate(String sql, Object[] obj){
		int count = 0;
		try{
			this.checkConn();
			ps = conn.prepareStatement(sql);
			if(null!=obj)preparStateSql(obj, obj.length);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DBUtils.executeUpdate---执行更新、插入、删除等操作失败！");
			e.printStackTrace();
		} finally {
			//this.close();
		}
		return count;
	}
	
	/**
	 * 装载PreparedStatement
	 * @param obj
	 * @param length
	 * @throws SQLException
	 */
	private void preparStateSql(Object[] obj, int length) throws SQLException {
		for(int i=0;i<length;i++)
		{
			if(obj[i]==null){
				obj[i]=false;
			}
			if(obj[i].getClass()==String.class){
				ps.setString(i+1, obj[i].toString());
			}else if(obj[i].getClass()==Integer.class){
				ps.setInt(i+1, (Integer)obj[i]);
			}else if(obj[i].getClass()==Double.class){
				ps.setDouble(i+1, (Double)obj[i]);
			}else if(obj[i].getClass()==Date.class)//java.sql.Date
			{
				ps.setDate(i+1, (Date)obj[i]);
			}else if(obj[i].getClass()==ByteArrayInputStream.class){
				ps.setBinaryStream(i+1, (ByteArrayInputStream)obj[i]);
			}else if(obj[i].getClass()==FileInputStream.class){
				ps.setBinaryStream(i+1, (FileInputStream)obj[i]);
			}else{
				ps.setObject(i+1, null);
			}
		}
	}
	
	/**
	 * 关闭数据库连接
	 */
	public void close(){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void checkConn(){
		if(null==conn){
			System.out.println("连接对象conn为空");
			return;
		}
	}
}
