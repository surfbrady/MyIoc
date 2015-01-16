package main.com.myioc;

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
	private static Map<String,Object> beanSingletonMap = null;
	
	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
	
	public BeanFactory(String strClassPath){
		beanSingletonMap = new HashMap();
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
				BeanDefinition beanDefinition = new BeanDefinition();
				beanDefinition.setBeanClass(beanClass);
				beanDefinition.setBeanName(beanName);
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
				Map.Entry<String, BeanDefinition> pairs = (Map.Entry<String, BeanDefinition>)i.next();
				String str = pairs.getKey();
				if(str.equals(key)){
					try {
						obj = Class.forName(pairs.getValue().getBeanClass()).newInstance();
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
		}
		return obj;
	}
}
