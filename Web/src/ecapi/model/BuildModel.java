package ecapi.model;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

import org.json.JSONException;
import org.json.JSONObject;

import util.Functions;

public class BuildModel{
	private String name = "";
	private String id = "";	
	
	public LinkedHashMap<String, Object> getMap(){
		LinkedHashMap<String, Object> out = new LinkedHashMap<String, Object>();
		Field[] fields = this.getClass().getDeclaredFields();
		try {
			for(Field f : fields){
				out.put(f.getName(), f.get(this));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return out;
	}
	
	public JSONObject getJSONObject(){
		JSONObject out = new JSONObject(getMap());
		return out;
	}
	
	public String getJSONString(){		
		return getJSONObject().toString();
	}
	
	public BuildModel(String name,String id){
		setName(name);
		setId(id);
	} 
		
	public static BuildModel getBuildModel(JSONObject o) throws JSONException{
		Field[] fields = BuildModel.class.getDeclaredFields();
		for(Field f : fields){
			String k = f.getName();
			if(!o.has(k) || null==o.get(k) || o.get(k).equals("")) return null;
		}
		return new BuildModel(o.getString("name"),o.getString("id"));
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		if(null==this.id || this.id.equals("")) setId(Functions.MD5(getName()));
		return id;
	}	
	public void setId(String id) {
		if(null==this.id || this.id.equals("")) id=Functions.MD5(getName());
		this.id = id;
	}
}
