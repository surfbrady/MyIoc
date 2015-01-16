package test.com.myioc;

import java.util.Iterator;
import java.util.Map;

import main.com.myioc.BeanFactory;

public class TestBeanFactory {
	
	public static void main(String[] args){
		System.out.println("是的");
		BeanFactory beanFactory = new BeanFactory("MyIoc.xml");
		Object o = beanFactory.getBean("Student");
		System.out.print(o);
	}
}
