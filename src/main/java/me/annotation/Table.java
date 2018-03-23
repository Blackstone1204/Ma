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
public @interface Table {
	/** 
	 * @ClassName: TableName 
	 * @Description: TODO(这里用一句话描述这个类的作用) 
	 * @date 2017年12月21日 下午2:24:45 
	 * 
	 */
	String name();
    String dbClass();
}
