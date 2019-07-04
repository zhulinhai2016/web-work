package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import util.SpringContextUtil;

import com.iot.model.Classroom;
import com.iot.model.ClassroomExample;
import com.iot.model.ClassroomExample.Criteria;
import com.iot.service.ClassroomService;
import com.iot.service.impl.ClassroomServiceImpl;

import ecapi.api.ClassesFactory;
import ecapi.model.ClassesInfoModel;
import ecapi.model.ClassesModel;

/**
 * Servlet implementation class Cmd
 */
@WebServlet("/Cmd")
public class Cmd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cmd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		
		
		response.setHeader("Content-Type", "text/json;charset=utf-8");
		String action = request.getParameter("a");
		String sendText = request.getParameter("sendText");
		String methodName = request.getParameter("method");
		if(null!=action){
			Method[] methods = this.getClass().getMethods();
			for(Method m : methods){
				if(m.getName().toLowerCase().equals(action.toLowerCase())){
					if(methodName.toLowerCase().equals("closeCenterCtrl".toLowerCase())){
						
						System.err.println("--------客户请求关闭中控的ip："+request.getRemoteHost()+", 方法名称：closeCenterCtrl");
					}
					try {
						if(StringUtils.isNotBlank(sendText)){
						m.invoke(this, sendText,request.getParameter("class_id"));
					}else{
						
						m.invoke(this, null);
					}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return ;
				}
			}			
		}		
	}

	// 一键关闭不含空调
	public void batCloseNoAir() throws IOException{
		String class_id = getPm("class_id");
		PrintWriter out = response.getWriter();
		ClassesModel  cm = ClassesFactory.getClassesObj(class_id);
		if(null==cm) out.println("{\"result\":1}");
		else{
			boolean result = cm.batCloseNoAir();
			if(result){
				out.println("{\"result\":1}");
			}else{
				out.println("{\"result\":0}");
			}
		}
		
	}
	// 一键关闭含空调
	public void batCloseHasAir() throws IOException{
		String class_id = getPm("class_id");
		PrintWriter out = response.getWriter();
		ClassesModel  cm = ClassesFactory.getClassesObj(class_id);
		if(null==cm) out.println("{\"result\":1}");
		else{
			boolean result = cm.batCloseHasAir();
			if(result){
				out.println("{\"result\":1}");
			}else{
				out.println("{\"result\":0}");
			}
		}
	}
	// 一键开启含空调
	public void batOpenHasAir() throws IOException{
		String class_id = getPm("class_id");
		PrintWriter out = response.getWriter();
		ClassesModel  cm = ClassesFactory.getClassesObj(class_id);
		if(null==cm) out.println("{\"result\":1}");
		else{
			boolean result = cm.batOpenHasAir();
			if(result){
				out.println("{\"result\":1}");
			}else{
				out.println("{\"result\":0}");
			}
		}
	}

	
	
	/*鏁存爧寮� 0828*/
	public void buildControl() throws IOException{
		PrintWriter out = response.getWriter();
		String build_id = getPm("build_id");
		ArrayList<ClassesInfoModel> cls = ClassesFactory.getClassesByBuildAndFloor(build_id,"all",null);
		//boolean result = false;
		final String opt = getPm("opt");

		if(null!=cls){
			ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			for(ClassesInfoModel c : cls){
				if(c!=null&&build_id.equals(c.getBuildId())){//濡傛灉 c.getLouId.equals (build_id)
				    final ClassesModel  cm = ClassesFactory.getClassesObj(c.getId());
					if(null!=cm){
						cachedThreadPool.execute(new Runnable(){
							@Override
							public void run() {
								if("1".equals(opt)){
									cm.batOpenHasAir();
								}else if("2".equals(opt)){
									cm.batCloseHasAir();
								}
							}
							
						});
	//					if(!tmpResult){
	//						result=false;
	//						break;
	//					}
	//					result = true;
					}
				}
			}
		}
		//if(result)
			out.println("{\"result\":1}");
		//else
			//out.println("{\"result\":0}");
	}
	
	
	

	
	
	/*鏁碿eng kai huo guan  0830*/
	public void floorControl() throws IOException{
		PrintWriter out = response.getWriter();
		String build_id = getPm("build_id");
		String floor_id = getPm("floor_id");
		ArrayList<ClassesInfoModel> cls = ClassesFactory.getClassesByBuildAndFloor(build_id, floor_id, null);
		boolean result = false;
		final String opt = getPm("opt");
		
		if(null!=cls){
			ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			for(ClassesInfoModel c : cls){
				if(c!=null&&build_id.equals(c.getBuildId())&&floor_id.equals(c.getFloorId())){//濡傛灉 c.getLouId.equals (build_id)
					final ClassesModel  cm = ClassesFactory.getClassesObj(c.getId());
					if(null!=cm){
						cachedThreadPool.execute(new Runnable(){

							@Override
							public void run() {
								if("2".equals(opt)){
									cm.batCloseHasAir();
								}
								else if("1".equals(opt)){
									cm.batOpenHasAir();
								}
								
							}
							
						});
//					if(!tmpResult){
//						result=false;
//						break;
//					}
//					result = true;
					}
				}
			}
		}
		//if(result)
		out.println("{\"result\":1}");
		//else
		//out.println("{\"result\":0}");
	}
	
	
	
	
	/*鍏ㄩ儴寮� 0828*/
	public void openAll() throws IOException{
		PrintWriter out = response.getWriter();
		ArrayList<ClassesInfoModel> cls = ClassesFactory.getClassesByBuildAndFloor("all","all",null);
		//boolean result = false;
    	if(null!=cls){
    		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    		for(ClassesInfoModel c : cls){
    			if(c==null){
    				continue;
    			}
    			final ClassesModel  cm = ClassesFactory.getClassesObj(c.getId());
    			if(null!=cm){
    				cachedThreadPool.execute(new Runnable(){
						@Override
						public void run() {
							cm.batOpenHasAir();
						}
    					
    				});
//    				if(!tmpResult){
//    					result=false;
//    					break;
//    				}
//    				result = true;
    			}
    		}
    	}
    	//if(result)
    		out.println("{\"result\":1}");
//    	else
//    		out.println("{\"result\":0}");
	}
	
	
	
	
	
	
	
	/*鍏ㄩ儴寮� 0828*/
	public void closeAll() throws IOException{
		PrintWriter out = response.getWriter();
		ArrayList<ClassesInfoModel> cls = ClassesFactory.getClassesByBuildAndFloor("all","all",null);
		//boolean result = false;
    	if(null!=cls){
    		// 
    		// 
    		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    		for(ClassesInfoModel c : cls){
    			if(c==null){
    				continue;
    			}
    			final ClassesModel  cm = ClassesFactory.getClassesObj(c.getId());
    			if(null!=cm){
    				cachedThreadPool.execute(new Runnable(){
						@Override
						public void run() {
							cm.batCloseHasAir();
						}
    					
    				});
//    				if(!tmpResult){
//    					result=false;
//    					break;
//    				}
//    				result = true;
    			}
    		}
    	}
    	//if(result)
    		out.println("{\"result\":1}");
//    	else
//    		out.println("{\"result\":0}");
	}
	
	
	
	
	
	
	
	
	
	//0828
	/*鍏ㄩ儴鍏�*/
/*	public void closeAll() throws IOException{
		PrintWriter out = response.getWriter();
		ArrayList<ClassesInfoModel> cls = ClassesFactory.getClasses("all","all");
		boolean result = false;
    	if(null!=cls){
    		for(ClassesInfoModel c : cls){
    			
    			ClassesModel  cm = ClassesFactory.getClassesObj(c.getId());
    			if(null!=cm){
    				boolean tmpResult = cm.batCloseHasAir();
    				if(!tmpResult){
    					result=false;
    					break;
    				}
    				result = true;
    			}
    		}
    	}
    	if(result)
    		out.println("{\"result\":1}");
    	else
    		out.println("{\"result\":0}");
	}
	*/
	
	
	
	
	public void batOpenNoAir() throws IOException{
		String class_id = getPm("class_id");
		PrintWriter out = response.getWriter();
		ClassesModel  cm = ClassesFactory.getClassesObj(class_id);
		if(null==cm) out.println("{\"result\":1}");
		else{
			boolean result = cm.batOpenNoAir();
			if(result){
				out.println("{\"result\":1}");
			}else{
				out.println("{\"result\":0}");
			}
		}
	}
	public String sendTouyText(String sendText,String class_id) throws IOException{
		PrintWriter out = response.getWriter();
		ClassesModel  cm = ClassesFactory.getClassesObj(class_id);
		if(null==cm){
			 out.println("{\"result\":1}");
			return "0";
		}
	
			boolean result = cm.sendTouyText(URLDecoder.decode(sendText,"ISO8859-1"));
			if(result){
				out.println("{\"result\":1}");
				return "1";
			}
			out.println("{\"result\":0}");
			return "0";
			
//		}
	}
	/*public void sendTouyText(String sendText,String class_id) throws IOException{
		PrintWriter out = response.getWriter();
		ClassesModel  cm = ClassesFactory.getClassesObj(class_id);
		if(null==cm) out.println("{\"result\":1}");
		else{
			boolean result = cm.sendTouyText(URLDecoder.decode(sendText,"ISO8859-1"));
			if(result){
				out.println("{\"result\":1}");urlDecoder.decode(sendText,"ISO8859-1"));
			}else{
				out.println("{\"result\":0}");
			}
		}
	}*/
    private String getPm(String key){
    	String pm = request.getParameter(key);
    	if(null!=pm && !pm.trim().equals("")) return pm.trim();
    	return "";
    }
	
	public void common() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		PrintWriter out = response.getWriter();
		String method = request.getParameter("method");
		String classKey = request.getParameter("class_id");
		String classFloor = getPm("floor_id");
		String classBuild = getPm("build_id");
		if(null!=method && !method.contentEquals("")){		
			if(this.cmd(classBuild,classFloor,classKey, method)){
				out.println("{\"result\":1}");
				System.out.println("web class:"+classKey+" method:"+method +" send success");
				return ;
			}else{
				System.out.println("web class:"+classKey+" method:"+method +" send fail");
			}
		}
		out.println("{\"result\":0}");
		return ;		
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private boolean cmd(String build,String floor,String key, String method) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{		
		ArrayList<String> methods = ClassesModel.getWebMethods();
		if(methods.contains(method)){
			if(!key.equals("")){
				ClassesModel c = ClassesFactory.getClassesObj(key);
				if(null!=c){ //鍔犱簡涓�瀵瑰ぇ鎷彿
					return (boolean) c.getClass().getMethod(method).invoke(c);
				}
			}else if(!floor.equals("")){
				ArrayList<ClassesInfoModel> cls = ClassesFactory.getClassesByBuildAndFloor(build,floor,null);
				if(null!=cls && !cls.isEmpty()){
					for(ClassesInfoModel c : cls){
						if(null!=c)	c.getClass().getMethod(method).invoke(c);
					}
					return true;
				}
			}			
		}
		return false;
	}
	
}
