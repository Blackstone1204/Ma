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
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Operation {
	String name();
	String by() default "";
	Sort sort() default Sort.asc;
	
	enum Sort{asc,desc};
}
