package test.com.myioc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class testmap {
	public static void main(String[] args){
		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("m", 1);
		m.put("n", 2);
		
		// test test
		//遍历map集合
		System.out.println("通过entryset遍历。。。");
		Set<Map.Entry<String, Integer>> s = m.entrySet();
		Iterator<Map.Entry<String, Integer>> i = s.iterator();
		while(i.hasNext()){
			Map.Entry<String, Integer> e = i.next();
			System.out.print(e.getKey() + "  ");
			System.out.println(e.getValue());
		}
		
		//遍历map集合
		System.out.println("通过key遍历。。。");
		Set<String> sKey = m.keySet();
		Iterator<String> iKey = sKey.iterator();
		while(iKey.hasNext()){
			String e = iKey.next();
			System.out.print(e + "  ");
			System.out.println(m.get(e));
		}
	}
}
