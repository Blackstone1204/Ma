/**
 * 
 */
package me.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BlackStone
 * 分析测试过程中所有方法的执行顺序
 */
public class RunOrder {
	/** 
	 * @ClassName: RunStep 
	 * @Description: TODO(这里用一句话描述这个类的作用) 
	 * @date 2017年12月28日 下午5:38:22 
	 * 
	 */
	private static Map<String,Object> runOrderMap=new HashMap<String,Object>();
	private static List<String> testMethodNameList=new ArrayList<String>();
	private static String[] notes={"@BeforeSuite","@BeforeTest","@Before","@Test","@After","@AfterTest","@AfterSuite"};
	
	public static void addTestMethodName(String methodName){
		testMethodNameList.add(methodName);
		
	}
	
	public static void addRunOrder(String key,String value){
		
		if(testMethodNameList.size()<1){
			System.out.println("没定义测试方法");
			return;
		}
		if(Arrays.asList(notes).contains(key))
			runOrderMap.put(key, value);

	}
	
	
	
	
	
}
