package main.com.myioc.beans;

public interface BeanDefinition {
	/*
	 * return whether this bean should be lazily initialied
	 * 
	 */
	boolean isLazyInit();
}
