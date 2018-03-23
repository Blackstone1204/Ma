/**
 * 
 */
package me.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import me.annotation.Operation;

import junit.framework.Test;

/**
 * @author BlackStone
 *
 */
public class AnnationProcess {
	/** 
	 * @ClassName: AnnationProcess 
	 * @Description: TODO(这里用一句话描述这个类的作用) 
	 * @date 2017年12月22日 下午1:46:04 
	 * 
	 */

	
	public void run(String className){
		try {
			Class<?> c=Class.forName(className);
			Method[] ms=c.getMethods();
			for(Method m:ms){

			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String [] args){
	  // new AnnationProcess().run("me.test.FirstCase");
	   
	   new AnnationProcess().run("me.dao.UserMapper");
	}
}
