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
@WebServlet("/AirCondition")
public class AirConditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AirConditionServlet() {
        super();       
    }
    
    public void updateAirCondition(String classRoomId,String[] kongtType) throws JSONException, InterruptedException{
    	JSONObject o = new JSONObject();
    	boolean flag = Updatter.updateAirCondition(classRoomId, kongtType);
    	String text = "";
    	if (kongtType != null ) {
			for (String string : kongtType) {
				text +=string+",";
			}
			text = text.substring(0, text.lastIndexOf(","));
		}
    	
    	if(flag){
	    	o.put("result", 1);
	    	o.put("msg", "操作成功");
	    	o.put("data", text);
    	}else{
    		o.put("result", 0);
    		o.put("msg", "操作失败");
    		o.put("data", text);
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
			this.updateAirCondition(getPm("classRoomId"),request.getParameterValues("kongtType"));
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
