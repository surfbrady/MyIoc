package main.com.myioc;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.*;
import org.dom4j.io.*;

/*
 *  read xml file and create ioc container
 *  
 */
public class ReadXml {
	private static Map<String,Object> beanIoc = null;
	
	private ReadXml(){
		
	}
	
	public static Map getnstance(){
		if(beanIoc==null){
			beanIoc = new HashMap();
			long lasting = System.currentTimeMillis();
			try {
				File f = new File("MyIoc.xml");
				SAXReader reader = new SAXReader();
				Document doc = reader.read(f);
				Element root = doc.getRootElement();
				Element foo;
				for ( Iterator i = root.elementIterator("bean");i.hasNext();) {
					foo = (Element) i.next();
					System.out.println("id：" + foo.elementText("id"));
					System.out.println("class：" + foo.elementText("class"));
					Object obj = Class.forName (foo.elementText("class")).newInstance();
					System.out.println("class：" + obj);
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return beanIoc;
	}
	
	public Object getBeans(String key){
		return beanIoc.get(key);
	}

}
