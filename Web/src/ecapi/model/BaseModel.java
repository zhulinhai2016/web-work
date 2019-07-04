package ecapi.model;

import java.lang.reflect.Field;
import java.util.HashMap;

public class BaseModel {
	protected HashMap<String, Object> getMap(Object obj){
		HashMap<String, Object> out = new HashMap<String, Object>();
		Field[] fields = this.getClass().getDeclaredFields();
		try {
			for(Field f : fields){
				out.put(f.getName(), f.get(obj));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return out;
	}
}
