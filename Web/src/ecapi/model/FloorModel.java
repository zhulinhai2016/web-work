package ecapi.model;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

import org.json.JSONException;
import org.json.JSONObject;

import ecapi.api.ClassesFactory;
import util.Functions;

public class FloorModel {
	private String name = "";
	private String buildId = "";
	private String id = "";
	private String floor = "";
	
	public FloorModel(String name,String id,String build,String floor){
		setBuildId(build);
		setFloor(floor);
		setName(name);
		setId(id);	
	} 
	
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
	
	public static FloorModel getFloorModel(JSONObject o) throws JSONException{
		Field[] fields = FloorModel.class.getDeclaredFields();
		for(Field f : fields){
			String k = f.getName();
			if(!o.has(k) || null==o.get(k) || o.get(k).equals("")) return null;
		}
		return new FloorModel(o.getString("name"),o.getString("id"),o.getString("buildId"),o.getString("floor"));
	}
		
	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getName() {
		if(null==name || name.isEmpty()){
			BuildModel m = ClassesFactory.getBuild(getBuildId());
			if(null!=m){
				setName(m.getName()+"第"+getFloor()+"层");
			}
			
		}
		return name;
	}
	public void setName(String name) {
		if(null==name || name.isEmpty()){
			BuildModel m = ClassesFactory.getBuild(getBuildId());
			if(null!=m){				
				name = m.getName()+"第"+getFloor()+"层";
			}
			
		}
		this.name = name;
	}
	public String getBuildId() {
		return buildId;
	}
	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}
	public String getId() {
		if(null==id || id.equals("")){
			setId(Functions.MD5(getBuildId()+"_"+getFloor()));			
		}
		return id;
	}
	public void setId(String id) {
		if(null==id || id.equals("")){
			id = Functions.MD5(getBuildId()+"_"+getFloor());			
		}
		this.id = id;
	}
}
    
