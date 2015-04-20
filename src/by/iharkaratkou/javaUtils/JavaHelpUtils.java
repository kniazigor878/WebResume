package by.iharkaratkou.javaUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import by.iharkaratkou.FindResume;

/**
 * This class contains methods to make easy some routines (generally cloning).
 * 
 * @author Ihar Karatkou
 * @version 1.0
 * @since 2015-04-20
 */
public class JavaHelpUtils {
	final static Logger logger = Logger.getLogger(JavaHelpUtils.class);
	
	/**
	 * This method makes a "deep clone" of any Java object it is given.
	 */
	public Object deepClone(Object object) {
	   try {
	     ByteArrayOutputStream baos = new ByteArrayOutputStream();
	     ObjectOutputStream oos = new ObjectOutputStream(baos);
	     oos.writeObject(object);
	     ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	     ObjectInputStream ois = new ObjectInputStream(bais);
	     return ois.readObject();
	   }
	   catch (Exception e) {
	     logger.error(e);
	     return null;
	   }
	 }
	
	public static List<LinkedHashMap<Integer, List<String>>> cloneListHm(List<LinkedHashMap<Integer, List<String>>> list) {
		List<LinkedHashMap<Integer, List<String>>> clone = new ArrayList<LinkedHashMap<Integer, List<String>>>(list.size());
	    for(LinkedHashMap<Integer, List<String>> item: list) clone.add((LinkedHashMap<Integer, List<String>>) item.clone());
		return clone;
	}
	
	public Entry<Integer, List<String>> getHmLastEntry(LinkedHashMap<Integer, List<String>> hm){
		Entry<Integer, List<String>> lastEntry = null;
		//Map.Entry<Integer, List<String>> lastEntry = new AbstractMap.SimpleEntry<Integer, List<String>>(1,Arrays.asList(""));
		Iterator i = hm.entrySet().iterator();
		while(i.hasNext()){
			lastEntry = (Entry<Integer, List<String>>) i.next();
		}
		return lastEntry;
	}

}
