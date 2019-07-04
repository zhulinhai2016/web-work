package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import ecapi.api.Updatter;

/**
 * Servlet implementation class Classes
 */
@WebServlet("/Touy")
public class TouyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TouyServlet() {
        super();       
    }
    
    public void updateTouy(String classRoomId,String touyType,String openCode,String closeCode) throws JSONException{
    	JSONObject o = new JSONObject();
    	o.put("data", touyType);
    	boolean flag = Updatter.updateTouy(classRoomId, touyType,openCode, closeCode);
    	if(flag){
	    	o.put("result", 1);
	    	o.put("msg", "操作成功");
    	}else{
    		o.put("result", 0);
    		o.put("msg", "操作失败");
    	}
    	try {
			PrintWriter out = response.getWriter();
			out.print(o.toString());
		} catch (IOException e) {
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
		try {
			/*System.err.println("--------客户请求ip："+request.getRemoteHost()+", 方法名称：");*/
			this.updateTouy(getPm("classRoomId"),getPm("touyType"),getPm("openCode"),getPm("closeCode"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
