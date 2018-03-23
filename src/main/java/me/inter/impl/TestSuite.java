/**
 * 
 */
package me.inter.impl;

import java.util.ArrayList;
import java.util.List;

import me.inter.Target;
import me.util.TestRunner;

/**
 * @author BlackStone
 *
 */
public class TestSuite implements Target{

	/* (non-Javadoc)
	 * @see me.inter.TestRunner#run()
	 */
	
	private List<Class<?>> list=new ArrayList<Class<?>>();
	public TestSuite(Class<?>[] clazzs){
		
		for(Class<?> clazz:clazzs){
			list.add(clazz);
			
		}
	}
	public void run() {
		

	}

}
