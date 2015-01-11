package test.com.myioc;

import java.util.Iterator;
import java.util.Map;

import main.com.myioc.ReadXml;

public class TestReadXml {
	
	public static void main(String[] args){
		Map map = ReadXml.getnstance();
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			map.put(key, val);
			System.out.print("key:"+key+"value111:"+val);
		}
	}
}
