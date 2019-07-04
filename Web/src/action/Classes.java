package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ecapi.api.ClassesFactory;
import ecapi.model.BuildModel;
import ecapi.model.ClassesInfoModel;
import ecapi.model.FloorModel;

/**
 * Servlet implementation class Classes
 */
@WebServlet("/Classes")
public class Classes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Classes() {
        super();       
    }

    public void getBuildList() throws JSONException{
    	JSONArray clsOut = new JSONArray();
    	ArrayList<BuildModel> cls = ClassesFactory.getBuildList();
    	if(null!=cls){
    		for(BuildModel c : cls) clsOut.put(new JSONObject(c.getMap()));
    	}

		try {
			PrintWriter out = response.getWriter();
			out.print(clsOut.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void getFloorList() throws JSONException{
    	
    	JSONArray clsOut = new JSONArray();
    	ArrayList<FloorModel> cls = ClassesFactory.getFloorList("all");
    	if(null!=cls){
    		for(FloorModel c : cls) clsOut.put(new JSONObject(c.getMap()));
    	}

		try {			
			PrintWriter out = response.getWriter();
			out.print(clsOut.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void getClassList() throws JSONException{
    	JSONArray clsOut = new JSONArray();
    	ArrayList<ClassesInfoModel> cls = ClassesFactory.getClassesByBuildAndFloor("all","all",null);
    	if(null!=cls){
    		for(ClassesInfoModel c : cls){
    			if(c!=null)
    				clsOut.put(new JSONObject(c.getMap()));
    		}
    	}

		try {
			PrintWriter out = response.getWriter();
			out.print(clsOut.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void deleteClass() throws JSONException{
    	JSONObject o = new JSONObject();
    	o.put("result", 0);
    	o.put("msg", "操作失败");
    	String class_id= getPm("class_id");
    	if(ClassesFactory.removeClass(class_id)){
    		o.put("result", 1);
    		o.put("msg", "操作成功");
    	}
    	
    	try {
			PrintWriter out = response.getWriter();
			out.print(o.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void addClass() throws JSONException{
    	JSONObject o = new JSONObject();
    	o.put("result", 1);
    	o.put("msg", "操作成功");
    	String className= getPm("class_name");
    	String classIp= getPm("class_ip");
    	String classPort= "485"; //getPm("class_port");
    	String classTouy= getPm("class_touy");
    	String classFloor= getPm("class_floor");
    	String classBuild = getPm("class_build");
    	String classId = getPm("class_id");
    	String classCenterIp = getPm("class_center_ip");
    	String classCenterPort= "20000";//getPm("class_center_port");
    	
    	 if(className.equals("")){
     		o.put("result", 0);
     		o.put("msg", "className班级名称不能为空");
     	}else if(!classIp.matches("[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}")){
    		o.put("result", 0);
    		o.put("msg", "Ip地址格式不正确");
    	}/*else if(!classPort.matches("[0-9]{1,5}")){
    		o.put("result", 0);
    		o.put("msg", "Port端口地址格式不正确");
    	}*//*else if(!classTouy.matches("rtsp://.+")){
    		o.put("result", 0);
    		o.put("msg", "Touy监控地址格式不正确");
    	}*/else{
    		//ClassesInfoModel m = new ClassesInfoModel(classBuild, classFloor, className, "", classTouy, classIp, Integer.parseInt(classPort));//new ClassesInfoModel(Bui,classIp, Integer.parseInt(classPort), classFloor, className, classTouy);
    		ClassesInfoModel m = new ClassesInfoModel(classBuild, classFloor, className, classId, classTouy, classIp,Integer.parseInt(classPort),classCenterIp,Integer.parseInt(classCenterPort));
    		if(!ClassesFactory.addClass(m)){
    			o.put("result", 0);
    			o.put("id", m.getId());
        		o.put("msg", "操作失败，未知错误");
    		}else{
    			o.put("classinfo", m.getJSONObject());
    		}    		
    	}
    	try {
			PrintWriter out = response.getWriter();
			out.print(o.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void addBuild() throws JSONException{
    	JSONObject o = new JSONObject();
    	o.put("result", 1);
    	o.put("msg", "操作成功");
    	String name = request.getParameter("name");
    	if(name.equals("")){
     		o.put("result", 0);
     		o.put("msg", "大楼名称不能为空");
     	}else{
     		BuildModel build = new BuildModel(name, "");
     		if(!ClassesFactory.addBuild(build)){
     			o.put("result", 0);
    			o.put("id", build.getId());
        		o.put("msg", "操作失败，未知错误");
     		}else{
     			o.put("buildinfo", build.getJSONObject());
     		}
     	}
    	try {
			PrintWriter out = response.getWriter();
			out.print(o.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void addFloor() throws JSONException{
    	JSONObject o = new JSONObject();
    	o.put("result", 1);
    	o.put("msg", "操作成功");
    	String floor_number = request.getParameter("floor");
    	String build_id = request.getParameter("build");
    	if(!floor_number.matches("[0-9]{1,3}")){
     		o.put("result", 0);
     		o.put("msg", "请输入楼层");
     	}else{
     		FloorModel floor = new FloorModel("","",build_id,floor_number);
     		if(!ClassesFactory.addFloor(floor)){
     			o.put("result", 0);
    			o.put("id", floor.getId());
        		o.put("msg", "操作失败，未知错误");
     		}else{
     			o.put("floorinfo", floor.getJSONObject());
     		}
     	}
    	try {
			PrintWriter out = response.getWriter();
			out.print(o.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void getClasses() throws JSONException{    	
    	JSONObject clsOut = new JSONObject();
    	ArrayList<ClassesInfoModel> cls = ClassesFactory.getClassesByBuildAndFloor("all","all",null);
    	if(null!=cls){
    		for(ClassesInfoModel c : cls) clsOut.put(c.getId(),new JSONObject(c.getMap()));
    	}

		try {
			PrintWriter out = response.getWriter();
			out.print(clsOut.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    private String getPm(String key){
    	String pm = request.getParameter(key);
    	if(null!=pm && !pm.trim().equals("")) return pm.trim();
    	return "";
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Type", "text/json;charset=utf-8");
		String action = request.getParameter("a");
		if(null!=action){
			Method[] methods = this.getClass().getMethods();
			for(Method m : methods){
				if(m.getName().toLowerCase().equals(action.toLowerCase())){
					System.out.println("action name=="+action.toLowerCase()+"--method ="+m.getName());
					try {
						m.invoke(this, null);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return ;
				}
			}
			
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
