/**
 * 
 */
package me.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author BlackStone
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
	
	String expected();	
	String groups();
	int timeOut();
	
	
	
	//忽略测试 Ignore
	
	//异常测试 expect
	
	//超时测试 timeout
	
	//套件测试 suite
	
	//参数化  
	
	
	//组合测试 group
	

	//依赖测试 depen
	
	

}
