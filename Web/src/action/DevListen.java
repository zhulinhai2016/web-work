package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import ecapi.api.ClassesFactory;
import ecapi.model.ClassesDevModel;
import ecapi.model.ClassesInfoModel;
import ecapi.model.ClassesModel;

/**
 * Servlet implementation class DevListen
 */
@WebServlet("/DevListen")
public class DevListen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DevListen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String floor = request.getParameter("floor");		
		String classId = request.getParameter("classId");		
		ArrayList<ClassesInfoModel> classes = ClassesFactory.getClassesByBuildAndFloor("all",floor,classId);
//		if (floor!=null && floor.equals("126")) {
//			System.err.println("126");
//		}
		response.setHeader("Content-Type", "text/json;charset=utf-8");
		JSONObject jsonObj = new JSONObject();
		if(null!=classes && !classes.isEmpty()){
			for(ClassesInfoModel c : classes){
				 ClassesModel m = ClassesFactory.getClassesObj(c.getId());
				 if(null!=m){
					 ClassesDevModel status = m.getDevStatus();
					 if(null!=status)
						try {
							/*System.err.println("--------客户请求ip："+request.getRemoteHost()+", 方法名称：devlisten");*/
							jsonObj.put(c.getId(),status.getJSONObject());
							jsonObj.put(c.getId()+"className",c.getName());
							
						} catch (JSONException e) {
							e.printStackTrace();
						}
				 }				
			}			
		}		
		PrintWriter out = response.getWriter();
		out.print(jsonObj.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
