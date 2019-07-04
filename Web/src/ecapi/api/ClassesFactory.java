package ecapi.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import util.SpringContextUtil;

import com.iot.model.Classroom;
import com.iot.model.ClassroomExample;
import com.iot.model.ClassroomExample.Criteria;
import com.iot.service.ClassroomService;
import com.iot.service.impl.ClassroomServiceImpl;

import conf.Defined;
import ecapi.model.BuildModel;
import ecapi.model.ClassesInfoModel;
import ecapi.model.ClassesModel;
import ecapi.model.FloorModel;

public class ClassesFactory {
	private static Byte poolLock = 1;
//	public static Hashtable<String, ClassesModel> pool = null;
	public static ConcurrentHashMap<String, ClassesModel> pool = new ConcurrentHashMap<>();
	/** key:ip+"_"+port value:次数 */
	public static ConcurrentHashMap<String,Integer> timesMap = new ConcurrentHashMap<>();
	private static ArrayList<ClassesInfoModel> classes = null;
	private static ArrayList<String> classesKeys = null;
	private static ArrayList<BuildModel> builds = null;
	private static ArrayList<String> buildKeys = null;
	private static ArrayList<FloorModel> floors = null;
	private static ArrayList<String> floorKeys = null;
	
	// TODO 获取所有教室列表
	public static List<ClassesModel> getAllClassModel(){
		
		if (pool==null || pool.isEmpty()) {
			return null;
		}
		
		Collection<ClassesModel> values = pool.values();
		List<ClassesModel> list = new ArrayList<>();
		list.addAll(values);
		return list;
	}
	
	public static ArrayList<FloorModel> getFloorList(String build){
		if(null == floors || floors.isEmpty()){
			floors = new ArrayList<FloorModel>();
			floorKeys = new ArrayList<String>();
			File f = new File(Defined.FLOOR_SAVE_PATH);		
			if(f.exists()){						
				try {
					String s = FileUtils.readFileToString(f,"utf-8");
					if(null!=s && !s.equals("")){
						JSONArray jsonLs = new JSONArray(s);
						FloorModel m = null;
						int len = jsonLs.length();
						for(int i=0;i<len; i++){
							m = FloorModel.getFloorModel(jsonLs.getJSONObject(i));
							if(null!=m && !floorKeys.contains(m.getId())) {
								floors.add(m);
								floorKeys.add(m.getId());
							}
						}						
					}
				} catch (IOException e) {			
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}	
		}
		
		if(build.equals("all")) return floors;
		ArrayList<FloorModel> out = new ArrayList<FloorModel>();
		for(FloorModel c : floors){
			if(c.getBuildId().equals(build)) out.add(c);
		}	
		return floors;
	}
	
	/**
	 * 删除教室
	 * @param k
	 * @return
	 */
	public static synchronized boolean removeClass(String k){
		try {
			if(null != classes && null!=classesKeys){
				if(classesKeys.contains(k)){
					classesKeys.remove(k);					
					for(int i=0;classes!=null&&i<classes.size();i++){
						ClassesInfoModel cls = classes.get(i);
						if(cls!=null&&!StringUtils.isEmpty(cls.getId())&&cls.getId().equals(k)) {
							String ck = cls.getServerHost()+"_"+cls.getServerPort();						
							if(null!=pool && pool.containsKey(k)){
								ClassesModel cm = pool.get(ck);
								if(null !=cm ) cm.destory();
							}
							classes.remove(cls);
							break;
						}
					}
					if(null==classes || classes.isEmpty()){
						File f = new File(Defined.CLASSES_SAVE_PATH);
						FileUtils.writeStringToFile(f, "[]","UTF-8");	
					}else{
						JSONArray o = new JSONArray();
						for(ClassesInfoModel c : classes){
							if(c!=null)
								o.put(c.getJSONObject());
						}
						File f = new File(Defined.CLASSES_SAVE_PATH);
						FileUtils.writeStringToFile(f, o.toString(),"UTF-8");	
					}
									
				}
			}
			return true;
		} catch (IOException e) {			
			
		}
		return false;
	}
	
	public static ArrayList<BuildModel> getBuildList(){
		if(null == builds || builds.isEmpty()){
			builds = new ArrayList<BuildModel>();
			buildKeys = new ArrayList<String>();
			File f = new File(Defined.BUILD_SAVE_PATH);		
			if(f.exists()){						
				try {
					String s = FileUtils.readFileToString(f,"utf-8");
					if(null!=s && !s.equals("")){
						JSONArray jsonLs = new JSONArray(s);
						BuildModel m = null;
						int len = jsonLs.length();
						for(int i=0;i<len; i++){
							m = BuildModel.getBuildModel(jsonLs.getJSONObject(i));
							if(null!=m && !buildKeys.contains(m.getId())) {
								builds.add(m);
								buildKeys.add(m.getId());
							}
						}							
					}
				} catch (IOException e) {			
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}	
		}
		return builds;
	}
	
	public static BuildModel getBuild(String build_id){
		ArrayList<BuildModel> builds = getBuildList();
		for(BuildModel m : builds){
			if(m.getId().equals(build_id)) return m;
		}
		return null;
	}
	
	public static FloorModel getFloor(String floor,String build_id){
		ArrayList<FloorModel> builds = getFloorList(build_id);
		for(FloorModel m : builds){
			if(m.getId().equals(floor) && m.getBuildId().equals(build_id)) return m;
		}
		return null;
	}
	
//	public static ClassesModel getClassesObj(String host,int port, String className){
//		String k = host+"_"+port;
//		if(null!=pool && pool.containsKey(k)) return pool.get(k);//常用路径
//		
//		if(null == pool){
//			synchronized (poolLock) {
//				if(null==pool) pool = new Hashtable<String, ClassesModel>();				
//				if(!pool.containsKey(k)) pool.put(k, new ClassesModel(host, port, className));
//			}			
//		}else{
//			synchronized (poolLock) {					
//				if(!pool.containsKey(k)) pool.put(k, new ClassesModel(host, port, className));
//			}	
//		}
//		
//		return pool.get(k);
//	}
	
	public static ClassesModel getClassesObj(ClassesInfoModel c){
		if(null==c) return null;	
		
		if (!(c.getServerHost() != null && !c.getServerHost().equals("") && c.getServerPort()!=0)) {
			return null;
		}
		
		String key = c.getServerHost()+"_"+c.getServerPort();
		if (!pool.containsKey(key)) {
			pool.put(key, new ClassesModel(c));
		} else {
			
			ClassesModel model= pool.get(key);
			if (model == null) {
				pool.remove(key);
			} else {
				/*if (!model.isNetwork_status() || model.getDevStatus().getAir_switch().equals("offline")
						||model.getDevStatus().getOther_switch().equals("offline") || !model.isCenter_network_status()
						|| !model.isMonitor_network_status() || model.getDevStatus().getCloud_terminal().equals("offline")) {*/
//					ClassesModel remove = pool.remove(key);
//					remove.destory();
//					remove = null;
					
					/*if (!pool.containsKey(key)) {
						pool.put(key, new ClassesModel(c));
					} else*/
					/*if (key.startsWith("192.168.1.56")) {
						timesMap.put(key, 30);
					}*/
					if(timesMap.containsKey(key)){
						/*
						 * 这个是用来调整断网恢复保护机制的粒度大小，如果你页面同步一次状态是1秒，那现在就是200s会去检查一次网络状态，
						 * 
						 */
						if(timesMap.get(key) >= 20){
//							model.init();
							model.autoConnect();
							timesMap.put(key,1);
						} else{
							timesMap.put(key,timesMap.get(key)+1);
						}
						
					} else{
						timesMap.put(key,1);
					}
					
				/*	
				}*/
//				if (!model.isMonitor_network_status() || !model.isCenter_network_status()) {
//					if (!model.isMonitor_network_status() && !model.isCenter_network_status()) {
//						
//						model.initMonitor();
//						model.initCenter();
//					} else if (!model.isMonitor_network_status()) {
//						model.initMonitor();
//					} else if (!model.isCenter_network_status()){
//						model.initCenter();
//					}
//				} 
//				pool.put(key, model);
			}
		}
		return pool.get(key);
//		if(null==c) return null;	
//		String k = c.getServerHost()+"_"+c.getServerPort();		
//		if(null!=pool && pool.containsKey(k)) return pool.get(k);//常用路径
//		
//		if(null == pool){
//			synchronized (poolLock) {
//				if(null==pool) pool = new Hashtable<String, ClassesModel>();				
//				if(!pool.containsKey(k)) pool.put(k, new ClassesModel(c));
//			}			
//		}else{
//			synchronized (poolLock) {								
//				if(!pool.containsKey(k)) pool.put(k, new ClassesModel(c));
//			}	
//		}
//		return pool.get(k);
	}
	
	public static ClassesModel getClassesObj(String key){
		ClassesInfoModel c = getClassesInfo(key);
		if(null==c) return null;	
		return getClassesObj(c);
	}
//	
//	public static ClassesModel getClassesObj(String key){
//		ClassesInfoModel c = getClassesInfo(key);
//		if(null==c) return null;		
//		return getClassesObj(c.getServerHost(),c.getServerPort(),c.getName());
//	}
	
	public static ClassesModel getClassesObj(String bid,String fid,String cid){
		ClassesInfoModel c = getClassesInfo(bid,fid,cid);
		if(null==c) return null;		
		return getClassesObj(c);
	}
	
//	public static ClassesModel getClassesObj(String bid,String fid,String cid){
//		ClassesInfoModel c = getClassesInfo(bid,fid,cid);
//		if(null==c) return null;		
//		return getClassesObj(c.getServerHost(),c.getServerPort(),c.getName());
//	}
	
	public static ClassesInfoModel getClassesInfo(String key){
		ArrayList<ClassesInfoModel> classes = getClassesByBuildAndFloor("all","all",key);
		if(CollectionUtils.isNotEmpty(classes)){
			return classes.get(0);
		}	
		return null;
	}
		
	public static ClassesInfoModel getClassesInfo(String bid,String fid,String cid){
		ArrayList<ClassesInfoModel> classes = getClassesByBuildAndFloor(bid,fid,cid);
		if(CollectionUtils.isNotEmpty(classes)){
			return classes.get(0);
		}	
		return null;
	}
	
	/**
	 * wjd 0927 
	 * @param build
	 * @param floor
	 * @return
	 */
	public static ArrayList<ClassesInfoModel> getClassesByBuildAndFloor(String build,String floor,String classId){
		ClassroomExample articleExample = new ClassroomExample();
		Criteria criteria = articleExample.createCriteria();
		if(!"all".equalsIgnoreCase(build)){
			criteria.andBuildIdEqualTo(Long.parseLong(build));
		}
		if(!"all".equalsIgnoreCase(floor)){
			criteria.andFloorIdEqualTo(Long.parseLong(floor));
		}
		if(org.apache.commons.lang.StringUtils.isNotBlank(classId)){
			criteria.andIdEqualTo(Long.parseLong(classId));
		}
		ClassroomService classroomService = (ClassroomServiceImpl)SpringContextUtil.getBean("classroomServiceImpl");
		List<Classroom> list = classroomService.selectByExample(articleExample);
		ArrayList<ClassesInfoModel> out = new ArrayList<ClassesInfoModel>();
		for(Classroom cr : list){
			ClassesInfoModel c = new ClassesInfoModel(String.valueOf(cr.getBuildId()), String.valueOf(cr.getFloorId()), cr.getName(), String.valueOf(cr.getId()), cr.getClassTouy(), 
					cr.getClassIp(), 485, cr.getClassCenterIp(), 20000);
			c.setVersionNum((cr.getVersionNum()==null || cr.getVersionNum()=="") ? "1":cr.getVersionNum());
			c.setClassId(cr.getClassId());
			out.add(c);
		}		
		return out;
	}
	/**
	 * wjd 20180508
	 * 根据classId 获取教室 非主键  用于课表控制获取
	 * @return
	 */
	public static ArrayList<ClassesInfoModel> getClassesByClassId(String classId){
		ClassroomExample articleExample = new ClassroomExample();
		Criteria criteria = articleExample.createCriteria();
		
		if(org.apache.commons.lang.StringUtils.isNotBlank(classId)){
			criteria.andClassIdEqualTo(classId);
		}
		ClassroomService classroomService = (ClassroomServiceImpl)SpringContextUtil.getBean("classroomServiceImpl");
		List<Classroom> list = classroomService.selectByExample(articleExample);
		ArrayList<ClassesInfoModel> out = new ArrayList<ClassesInfoModel>();
		for(Classroom cr : list){
			ClassesInfoModel c = new ClassesInfoModel(String.valueOf(cr.getBuildId()), String.valueOf(cr.getFloorId()), cr.getName(), String.valueOf(cr.getId()), cr.getClassTouy(), 
					cr.getClassIp(), 485, cr.getClassCenterIp(), 20000);
			c.setClassId(cr.getClassId());
			out.add(c);
		}		
		return out;
	}

	/**
	 * TODO
	 * 2018年6月5日 created by z
	 * @param ip
	 * @return
	 * return_type boolean
	 */
	public static boolean checkedByIP(String ip,String classId,String id){
		ClassroomService classroomService = (ClassroomServiceImpl)SpringContextUtil.getBean("classroomServiceImpl");
		List<Classroom> byIP = classroomService.checkedByIP(ip,classId,id);
			
		if (byIP== null || byIP.isEmpty()) {
			return true;
		}
		return false;
	}
	
	
	synchronized public static boolean addBuild(BuildModel m){
		try {
			if(null == builds){
				builds = new ArrayList<BuildModel>();
				buildKeys = new ArrayList<String>();
			}
			String k = m.getId();
			if(!buildKeys.contains(k)){
				builds.add(m);
				buildKeys.add(k);
				JSONArray o = new JSONArray();				
				for(BuildModel cls : builds){
					o.put(cls.getJSONObject());
				}	
				File f = new File(Defined.BUILD_SAVE_PATH);		
				FileUtils.writeStringToFile(f, o.toString(),"UTF-8");
				return true;
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return false;
	}
	
	synchronized public static boolean addFloor(FloorModel m){
		try {
			if(null == floors){
				floors = new ArrayList<FloorModel>();
				floorKeys = new ArrayList<String>();
			}
			String k = m.getId();
			if(!floorKeys.contains(k)){
				floors.add(m);
				floorKeys.add(k);
				JSONArray o = new JSONArray();
				for(FloorModel cls : floors){
					o.put(cls.getJSONObject());
				}	
				File f = new File(Defined.FLOOR_SAVE_PATH);
				FileUtils.writeStringToFile(f, o.toString(),"UTF-8");
				return true;
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return false;
	}
	
	synchronized public static boolean addClass(ClassesInfoModel m){
		try {
			if(null == classes){
				classes = new ArrayList<ClassesInfoModel>();
				classesKeys = new ArrayList<String>();
			}
			String k = m.getId();
			if(!classesKeys.contains(k)){
				classes.add(m);
				classesKeys.add(k);
				JSONArray o = new JSONArray();
				for(ClassesInfoModel cls : classes){
					if(cls!=null)
						o.put( new JSONObject(cls.getMap()));
				}	
				File f = new File(Defined.CLASSES_SAVE_PATH);
				FileUtils.writeStringToFile(f, o.toString(),"UTF-8");
				return true;
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return false;
	}
}
