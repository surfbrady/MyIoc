package main.com.myioc.beans;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.*;
import org.dom4j.io.*;

/*
 *  read xml file and create ioc container
 *  
 */
public class BeanFactory {
	private static Map<String,Object> beanSingletonMap = null; //save the singleton object to beanSingletonMap
	
	private Map<String, DefaultBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, DefaultBeanDefinition>();
	
	public BeanFactory(String strClassPath){
		beanSingletonMap = new ConcurrentHashMap();
		long lasting = System.currentTimeMillis();
		try {
			File f = new File(strClassPath);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			Element foo;
			for ( Iterator i = root.elementIterator("bean");i.hasNext();) {
				foo = (Element) i.next();
				String beanName = foo.elementText("id");
				String beanClass = foo.elementText("class");
				String scope = foo.attributeValue("scope");
				DefaultBeanDefinition beanDefinition = new DefaultBeanDefinition();
				beanDefinition.setBeanClass(beanClass);
				beanDefinition.setBeanName(beanName);
				beanDefinition.setScope(scope);
				this.beanDefinitionMap.put(beanName, beanDefinition);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * get the bean
	 * 
	 */
	public Object getBean(String key){
		Object obj = beanSingletonMap.get(key);
		if(obj==null){
			// loop beanDefinitionMap
			Iterator i = this.beanDefinitionMap.entrySet().iterator();
			while(i.hasNext()){
				Map.Entry<String, DefaultBeanDefinition> pairs = (Map.Entry<String, DefaultBeanDefinition>)i.next();
				String str = pairs.getKey();
				if(str.equals(key)){
					try {
						obj = Class.forName(pairs.getValue().getBeanClass()).newInstance();
						if(pairs.getValue().getScope()!=null && pairs.getValue().getScope().equals("singleton")){
							// save the singleton object to beanSingletonMap
							beanSingletonMap.put(key, obj);
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
					}
				}
			}
		}else{
			System.out.println("yes the container has the element");
		}
		return obj;
	}
}
